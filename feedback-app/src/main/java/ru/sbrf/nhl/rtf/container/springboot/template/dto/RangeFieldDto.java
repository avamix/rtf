package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class RangeFieldDto extends FieldDto {
    private int min = 0;
    private int max = 5;

    @JsonCreator
    public RangeFieldDto(@JsonProperty("name") @NotNull String name, @JsonProperty("label") @NotNull String label, @JsonProperty("min") int min, @JsonProperty("max") int max) {
        super(name, label);
        this.min = min;
        this.max = max;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Value extends FieldValue {
        private int value;

        public Value(@JsonProperty("name") @NotNull String name, @JsonProperty("value") @NotNull int value) {
            super(name);
            this.value = value;
        }
    }
}
