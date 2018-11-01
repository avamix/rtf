package ru.sbrf.nhl.rtf.rest.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FeedbackDto {
    @NotNull
    private Long targetPersonId;
    @NotNull
    private Long abilityId;
    @NotNull
    @Min(0)
    @Max(100)
    private Integer value;
    @Size(max = 500)
    private String comment;
    private Boolean anonymous = false;
}
