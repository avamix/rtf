package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextField extends Field {
    @JsonCreator
    public TextField(@JsonProperty("label") @NotNull String label) {
        super(label);
    }
}
