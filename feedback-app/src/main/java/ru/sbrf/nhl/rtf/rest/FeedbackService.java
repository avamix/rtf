package ru.sbrf.nhl.rtf.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.sbrf.nhl.rtf.dao.Ability;
import ru.sbrf.nhl.rtf.dao.AbilityRepository;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshot;
import ru.sbrf.nhl.rtf.dao.Feedback;
import ru.sbrf.nhl.rtf.dao.FeedbackRepository;
import ru.sbrf.nhl.rtf.dao.Person;
import ru.sbrf.nhl.rtf.dao.PersonRepository;
import ru.sbrf.nhl.rtf.rest.dto.FeedbackDto;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackService {
    private final PersonRepository personRepository;
    private final FeedbackRepository feedbackRepository;
    private final AbilityRepository abilityRepository;

    public void add(String successFactorId, FeedbackDto feedbackDto) {
        Person author = personRepository.getBySuccessFactorId(successFactorId);
        Person target = personRepository.getOne(feedbackDto.getTargetPersonId());
        Ability ability = abilityRepository.getOne(feedbackDto.getAbilityId());
        Feedback feedback = Feedback.builder()
                .target(target)
                .postedAt(new Date())
                .comment(feedbackDto.getComment())
                .author(Feedback.FeedbackAuthor.builder()
                        .grade(author.getGrade())
                        .valueOnAbility(getAuthorAbilityValue(author, feedbackDto.getAbilityId()))
                        .person(feedbackDto.isAnonymous() ? null : author)
                        .build())
                .ability(ability)
                .targetCurrentGrade(target.getGrade())
                .value(feedbackDto.getValue())
                .build();
        feedbackRepository.save(feedback);
    }

    @NotNull
    private Integer getAuthorAbilityValue(Person author, @NotNull Long abilityId) {
        if (CollectionUtils.isEmpty(author.getAbilities())) {
            return 0;
        }
        return author.getAbilities().stream()
                .filter(abilitySnapshot -> abilitySnapshot.getAbility().getId().equals(abilityId))
                .findFirst()
                .map(AbilitySnapshot::getValue)
                .orElse(0);
    }
}
