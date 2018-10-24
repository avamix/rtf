package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectFieldDto extends FieldDto {
    @NotNull
    @Size(min = 2)
    @Valid
    private List<Option> options;

    @JsonCreator
    public SelectFieldDto(@JsonProperty("name") @NotNull String name, @JsonProperty("label") @NotNull String label, @JsonProperty("options") @NotNull @Size(min = 2) @Valid List<Option> options) {
        super(name, label);
        this.options = options;
    }

    @Data
    public static class Option implements Serializable {
        @NotNull
        @NotEmpty
        private String key;
        @NotNull
        @NotEmpty
        private String text;

        @JsonCreator
        public Option(@JsonProperty("key") @NotNull @NotEmpty String key, @JsonProperty("label") @NotNull @NotEmpty String text) {
            this.key = key;
            this.text = text;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Value extends FieldValue {
        private String selectedKey;

        @JsonCreator
        public Value(@JsonProperty("name") @NotNull @NotEmpty String name, @JsonProperty("selectedKey") @NotNull @NotEmpty String selectedKey) {
            super(name);
            this.selectedKey = selectedKey;
        }
    }
}
