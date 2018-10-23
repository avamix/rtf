package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextField.class, name = "text"),
        @JsonSubTypes.Type(value = RangeField.class, name = "range"),
        @JsonSubTypes.Type(value = SelectField.class, name = "select"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    @NotNull
    private String label;
}
