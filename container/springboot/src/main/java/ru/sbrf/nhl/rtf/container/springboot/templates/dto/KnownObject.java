package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

import lombok.Data;

/**
 * Объект с идентификатором и версией модификации
 *
 * @param <T>
 * @param <ID>
 */
@Data
public class KnownObject<T, ID> {
    /**
     * ID объекта
     */
    private ID id;
    /**
     * Для Оптимистической блокировки конкурентных изменений
     */
    private long version;
    private T data;
}
