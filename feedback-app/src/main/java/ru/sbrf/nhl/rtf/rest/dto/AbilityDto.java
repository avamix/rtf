package ru.sbrf.nhl.rtf.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AbilityDto {
    private String name;
    private Integer value;
    private Date createdAt;
}
