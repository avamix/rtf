package ru.sbrf.nhl.rtf.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.sbrf.nhl.rtf.dao.Feedback;
import ru.sbrf.nhl.rtf.dao.FeedbackRepository;
import ru.sbrf.nhl.rtf.dao.Person;
import ru.sbrf.nhl.rtf.dao.PersonRepository;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * Сервис создания срезов по оценкам
 */
@Slf4j
@Service
public class SnapshotService {

    private final PersonRepository personRepository;
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public SnapshotService(PersonRepository personRepository, FeedbackRepository feedbackRepository) {
        this.personRepository = personRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public void createSnapshots() {
        personRepository.findAll().forEach(this::createPersonSnapshot);
    }

    public void createPersonSnapshot(Person person) {
        Feedback feedback = Feedback.builder().target(person).build();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("target", exact());
        List<Feedback> feedbacks = feedbackRepository.findAll(Example.of(feedback, matcher));
        log.info("{}", feedbacks);
    }

}
