package ru.sbrf.nhl.rtf.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.nhl.rtf.dao.Ability;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshot;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshotRepository;
import ru.sbrf.nhl.rtf.dao.Feedback;
import ru.sbrf.nhl.rtf.dao.FeedbackRepository;
import ru.sbrf.nhl.rtf.dao.Person;
import ru.sbrf.nhl.rtf.dao.PersonRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Сервис создания срезов по оценкам
 */
@Slf4j
@Service
@Transactional
public class SnapshotService {

    private final PersonRepository personRepository;
    private final FeedbackRepository feedbackRepository;
    private final AbilitySnapshotRepository abilitySnapshotRepository;
    @Value("${snapshot_calculation.minimal_feedback_count}")
    private int minFeedbacksForSnapshot;
    @Value("${snapshot_calculation.default_weight}")
    private int defaultFeedbackWeight;

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
    public void createSnapshots() {
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

        if (newFeedBacks.size() < minFeedbacksForSnapshot) {
            log.debug("Not enough feedback: {}", newFeedBacks.size());
            return;
        }

        List<AbilitySnapshot> snapshots = newFeedBacks.stream()
                .collect(groupingBy(Feedback::getAbility))
                .entrySet().stream()
                .map(entry -> getAbilitySnapshot(person, entry))
                .collect(toList());
        log.info("New snapshots count: {}", snapshots.size());
        abilitySnapshotRepository.saveAll(snapshots);
    }

    private AbilitySnapshot getAbilitySnapshot(Person person, Map.Entry<Ability, List<Feedback>> entry) {
        // 1. grade
        // 2. ability
        Map<Feedback, Integer> weightsByFeedback = entry.getValue().stream()
                .collect(toMap(Function.identity(), feedback -> getFeedbackWeight(feedback)));
        long aggregate = 0;
        long weightSum = 0;
        for (Map.Entry<Feedback, Integer> feedbackAndWeight : weightsByFeedback.entrySet()) {
            Feedback feedback = feedbackAndWeight.getKey();
            Integer weight = feedbackAndWeight.getValue();

            aggregate += feedback.getValue() * weight;
            weightSum += weight;
        }
        return AbilitySnapshot.builder()
                .ability(entry.getKey())
                .createdAt(new Date())
                .person(person)
                .value(getAbilityValue(aggregate, weightSum))
                .build();
    }

    private int getAbilityValue(long aggregate, long weightSum) {
        if (weightSum == 0) {
            return 0;
        }
        return (int) Math.round(aggregate * 1.0 / weightSum);
    }

    private int getFeedbackWeight(Feedback feedback) {
        int valueOnAbility = feedback.getAuthor().getValueOnAbility();
        if (valueOnAbility == 0) {
            return defaultFeedbackWeight;
        }
        int weight = valueOnAbility;
        weight = (weight * feedback.getAuthor().getGrade()) / feedback.getTarget().getGrade();
        weight = (weight * feedback.getSource().getWeight()) / 100;
        //head
        //general head

        return weight;

    }

}
