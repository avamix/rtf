package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextFieldDto.Value.class, name = "text"),
        @JsonSubTypes.Type(value = RangeFieldDto.Value.class, name = "range"),
        @JsonSubTypes.Type(value = SelectFieldDto.Value.class, name = "select"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldValue implements Serializable {
    @NotNull
    @NotEmpty
    private String name;
}
