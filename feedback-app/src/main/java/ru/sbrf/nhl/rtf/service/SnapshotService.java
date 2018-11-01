package ru.sbrf.nhl.rtf.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshot;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshotRepository;
import ru.sbrf.nhl.rtf.dao.Feedback;
import ru.sbrf.nhl.rtf.dao.FeedbackRepository;
import ru.sbrf.nhl.rtf.dao.Person;
import ru.sbrf.nhl.rtf.dao.PersonRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * Сервис создания срезов по оценкам
 */
@Slf4j
@Service
public class SnapshotService {

    private final PersonRepository personRepository;
    private final FeedbackRepository feedbackRepository;
    private final AbilitySnapshotRepository abilityRepository;

    @Autowired
    public SnapshotService(
            PersonRepository personRepository,
            FeedbackRepository feedbackRepository,
            AbilitySnapshotRepository abilityRepository
    ) {
        this.personRepository = personRepository;
        this.feedbackRepository = feedbackRepository;
        this.abilityRepository = abilityRepository;
    }

    /**
     * Создание среза по оценкам для всех пользователей
     */
    public void createSnapshots() {
        personRepository.findAll().forEach(this::createPersonSnapshot);
    }

    /**
     * Создание среза по оценкам пользователя
     *
     * @param person пользователь для которого создаётся срез
     */
    public void createPersonSnapshot(Person person) {
        Feedback feedbackExample = Feedback.builder().target(person).build();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("target", exact());
        List<AbilitySnapshot> snapshots = feedbackRepository.findAll(Example.of(feedbackExample, matcher)).stream()
                .collect(groupingBy(Feedback::getAbility))
                .entrySet().stream()
                .map(entry -> {
                    List<Feedback> sorted = entry.getValue().stream()
                            .sorted(Comparator.comparing(f -> f.getAuthor().getValueOnAbility()))
                            .collect(toList());
                    long sum = 0;
                    long dt = 0;
                    for (int i = 1; i <= sorted.size(); i++) {
                        Feedback feedback = sorted.get(i - 1);
                        sum += feedback.getValue() * feedback.getAuthor().getValueOnAbility();
                        dt += feedback.getAuthor().getValueOnAbility();
                    }
                    return AbilitySnapshot.builder()
                            .ability(entry.getKey())
                            .createdAt(new Date())
                            .person(person)
                            .value((int) Math.round(sum * 1.0 / dt))
                            .build();
                })
                .collect(toList());
        abilityRepository.saveAll(snapshots);
    }

}
