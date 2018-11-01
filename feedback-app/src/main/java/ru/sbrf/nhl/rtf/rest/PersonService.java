package ru.sbrf.nhl.rtf.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.nhl.rtf.dao.AbilitySnapshot;
import ru.sbrf.nhl.rtf.dao.Person;
import ru.sbrf.nhl.rtf.dao.PersonRepository;
import ru.sbrf.nhl.rtf.dao.Role;
import ru.sbrf.nhl.rtf.rest.dto.AbilityDto;
import ru.sbrf.nhl.rtf.rest.dto.PersonDto;
import ru.sbrf.nhl.rtf.rest.dto.PersonListItemDto;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public PersonDto getBy(Long id) {
        Person person = personRepository.getOne(id);
        Set<AbilityDto> abilities = person.getAbilities().stream()
                .collect(Collectors.groupingBy(AbilitySnapshot::getAbility))
                .entrySet().stream()
                .map(entry -> entry.getValue().stream()
                        .max(comparing(AbilitySnapshot::getCreatedAt))
                        .orElse(null))
                .filter(Objects::nonNull)
                .map(abilitySnapshot -> AbilityDto.builder()
                        .name(abilitySnapshot.getAbility().getName())
                        .value(abilitySnapshot.getValue())
                        .createdAt(abilitySnapshot.getCreatedAt())
                        .build())
                .collect(Collectors.toSet());
        return PersonDto.builder()
                .id(person.getId())
                .fullName(person.getFullName())
                .abilities(abilities)
                .roles(getRoleNames(person))
                .build();
    }

    private Set<String> getRoleNames(Person person) {
        return person.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    public Page<PersonListItemDto> page(Pageable pageable) {
        return personRepository.findAll(pageable)
                .map(person -> PersonListItemDto.builder()
                        .fullName(person.getFullName())
                        .id(person.getId())
                        .roles(getRoleNames(person))
                        .successFactorId(person.getSuccessFactorId())
                        .build());
    }
}
