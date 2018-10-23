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
        @JsonSubTypes.Type(value = TextField.class, name = "text"),
        @JsonSubTypes.Type(value = RangeField.class, name = "range"),
        @JsonSubTypes.Type(value = SelectField.class, name = "select"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field implements Serializable {
    /**
     * Уникальный ключ поля внутри шаблона - системное имя поля
     */
    @NotNull
    @NotEmpty
    private String name;
    /**
     * Текстовое описание поля для - пипл-френдли название
     */
    @NotNull
    @NotEmpty
    private String label;
}
