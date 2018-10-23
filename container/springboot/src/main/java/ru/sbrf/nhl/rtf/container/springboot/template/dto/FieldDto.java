package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sbrf.nhl.rtf.container.springboot.template.dao.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextFieldDto.class, name = "text"),
        @JsonSubTypes.Type(value = RangeFieldDto.class, name = "range"),
        @JsonSubTypes.Type(value = SelectFieldDto.class, name = "select"),
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto implements Serializable {
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

    public static FieldDto from(Field field) {
        // todo: parse hierarchy
        return new FieldDto(field.getName(), field.getLabel());
    }
}
