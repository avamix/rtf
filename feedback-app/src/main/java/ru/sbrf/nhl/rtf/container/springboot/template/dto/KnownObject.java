package ru.sbrf.nhl.rtf.container.springboot.template.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Объект с идентификатором и версией модификации
 *
 * @param <T>
 * @param <ID>
 */
@Value
@AllArgsConstructor
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
