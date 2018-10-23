package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class RangeField extends Field {
    private int min = 0;
    private int max = 5;

    @JsonCreator
    public RangeField(@JsonProperty("label") @NotNull String label, @JsonProperty("min") int min, @JsonProperty("max") int max) {
        super(label);
        this.min = min;
        this.max = max;
    }
}
