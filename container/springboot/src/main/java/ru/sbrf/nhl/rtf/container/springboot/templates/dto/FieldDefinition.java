package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class FieldDefinition {
    /**
     * Наименование поля
     */
    @NotNull
    @NotEmpty
    private String label;
    /**
     * Тип поля
     */
    @NotNull
    private Type type;
    /**
     * Вспомогательные параметры к указанному типу поля
     */
    private Map<String, String> params;

    public enum Type {
        TEXT,
        CHECK,
        RANGE("min", "max");

        private final String[] requiredParams;

        Type(String... requiredParams) {
            this.requiredParams = requiredParams;
        }
    }
}
