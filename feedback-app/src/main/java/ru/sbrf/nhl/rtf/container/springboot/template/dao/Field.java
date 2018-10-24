package ru.sbrf.nhl.rtf.container.springboot.template.dao;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@Entity
public class Field implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
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

    public Field() {
    }

    public Field(@NotNull @NotEmpty String name, @NotNull @NotEmpty String label) {
        this.name = name;
        this.label = label;
    }

    public Field(Long id, @NotNull @NotEmpty String name, @NotNull @NotEmpty String label) {
        this.id = id;
        this.name = name;
        this.label = label;
    }


}
