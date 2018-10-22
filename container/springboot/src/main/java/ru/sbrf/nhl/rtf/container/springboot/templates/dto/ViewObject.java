package ru.sbrf.nhl.rtf.container.springboot.templates.dto;

public class ViewObject<T> {
    private String id;
    /**
     * Для Оптимистической блокировки конкурентных изменений
     */
    private long version;
    private T data;
}
