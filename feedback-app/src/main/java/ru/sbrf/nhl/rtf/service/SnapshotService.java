package ru.sbrf.nhl.rtf.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sbrf.nhl.rtf.dao.Ability;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshot;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshotRepository;
import ru.sbrf.nhl.rtf.dao.Feedback;
import ru.sbrf.nhl.rtf.dao.FeedbackRepository;
import ru.sbrf.nhl.rtf.dao.Person;
import ru.sbrf.nhl.rtf.dao.PersonRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Сервис создания срезов по оценкам
 */
@Slf4j
@Service
public class SnapshotService {

    private final PersonRepository personRepository;
    private final FeedbackRepository feedbackRepository;
    private final AbilitySnapshotRepository abilitySnapshotRepository;

    @Autowired
    public SnapshotService(
            PersonRepository personRepository,
            FeedbackRepository feedbackRepository,
            AbilitySnapshotRepository abilitySnapshotRepository
    ) {
        this.personRepository = personRepository;
        this.feedbackRepository = feedbackRepository;
        this.abilitySnapshotRepository = abilitySnapshotRepository;
    }

    /**
     * Создание среза по оценкам для всех пользователей
     */
    @Scheduled(fixedDelay = 10_000)
    public void createSnapshots() {
        log.info("Start calculate snapshots");
        personRepository.findAll().forEach(this::createPersonSnapshot);
    }

    /**
     * Создание среза по оценкам пользователя
     *
     * @param person пользователь для которого создаётся срез
     */
    private void createPersonSnapshot(Person person) {
        AbilitySnapshot lastSnapshot = abilitySnapshotRepository.findLast(person.getId());
        log.debug("Last snapshot date: {}", nonNull(lastSnapshot) ? lastSnapshot.getCreatedAt() : null);
        Collection<Feedback> newFeedBacks = nonNull(lastSnapshot)
                ? feedbackRepository.findNewByPerson(person.getId(), lastSnapshot.getCreatedAt())
                : feedbackRepository.findNewByPerson(person.getId());

        if (newFeedBacks.size() < 5) {
            log.info("Not enough feedback: {}", newFeedBacks.size());
            return;
        }

        List<AbilitySnapshot> snapshots = newFeedBacks.stream()
                .collect(groupingBy(Feedback::getAbility))
                .entrySet().stream()
                .map(entry -> getAbilitySnapshot(person, entry))
                .collect(toList());
        log.info("New snapshots count: {}", snapshots);
        abilitySnapshotRepository.saveAll(snapshots);
    }

    private static AbilitySnapshot getAbilitySnapshot(Person person, Map.Entry<Ability, List<Feedback>> entry) {
        List<Feedback> sorted = entry.getValue().stream()
                .sorted(Comparator.comparing(f -> f.getAuthor().getWeight()))
                .collect(toList());
        long sum = 0;
        long dt = 0;
        for (int i = 1; i <= sorted.size(); i++) {
            Feedback feedback = sorted.get(i - 1);
            sum += feedback.getValue() * feedback.getAuthor().getWeight();
            dt += feedback.getAuthor().getWeight();
        }
        return AbilitySnapshot.builder()
                .ability(entry.getKey())
                .createdAt(new Date())
                .person(person)
                .value((int) Math.round(sum * 1.0 / dt))
                .build();
    }

}
