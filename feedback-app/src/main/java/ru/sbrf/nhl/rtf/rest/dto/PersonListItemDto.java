package ru.sbrf.nhl.rtf.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonListItemDto {
    private Long id;
    private String fullName;
    private String successFactorId;
    private Set<String> roles;
}
