package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateRequest<T> {
    @NotNull
    private String id;
    private long version;
    @NotNull
    @Valid
    private T data;
}
