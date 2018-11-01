package ru.sbrf.nhl.rtf.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PersonDto {
    private Long id;
    private String fullName;
    private Set<String> roles;
    private Set<AbilityDto> abilities;
}
