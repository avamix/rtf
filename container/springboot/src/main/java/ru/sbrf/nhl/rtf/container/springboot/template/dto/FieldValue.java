package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextField.Value.class, name = "text"),
        @JsonSubTypes.Type(value = RangeField.Value.class, name = "range"),
        @JsonSubTypes.Type(value = SelectField.Value.class, name = "select"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldValue implements Serializable {
    private String name;
}
