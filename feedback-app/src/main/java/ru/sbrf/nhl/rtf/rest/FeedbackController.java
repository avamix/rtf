package ru.sbrf.nhl.rtf.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbrf.nhl.rtf.rest.dto.FeedbackDto;
import ru.sbrf.nhl.rtf.rest.dto.PersonDto;
import ru.sbrf.nhl.rtf.rest.dto.PersonListItemDto;

@RestController
@RequiredArgsConstructor
public class FeedbackController {
    private final PersonService personService;

    @GetMapping("/person/{id}")
    public PersonDto person(@PathVariable("id") Long id) {
        return personService.getBy(id);
    }

    @GetMapping("/person")
    public Page<PersonListItemDto> page(Pageable pageable) {
        return personService.page(pageable);
    }

    @PostMapping("/feedback")
    public void addFeedback(@RequestBody FeedbackDto feedbackDto) {
        // todo: validate request
        // TODO: get current user credentials
        // todo: find author person by successfactoryid;
        // todo: call service to persist
    }
}
