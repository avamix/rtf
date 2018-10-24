package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextFieldDto extends FieldDto {
    @JsonCreator
    public TextFieldDto(@JsonProperty("name") @NotNull String name, @JsonProperty("label") @NotNull String label) {
        super(name, label);
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Value extends FieldValue {
        private String value;

        public Value(@JsonProperty("name") @NotNull String name, @JsonProperty("value") @NotNull String value) {
            super(name);
            this.value = value;
        }
    }
}
