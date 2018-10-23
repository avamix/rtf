package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SelectField extends Field {
    @NotNull
    @Size(min = 2)
    @Valid
    private List<Option> options;

    @JsonCreator
    public SelectField(@JsonProperty("label") @NotNull String label, @JsonProperty("options") @NotNull @Size(min = 2) @Valid List<Option> options) {
        super(label);
        this.options = options;
    }

    @Data
    public static class Option {
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

}
