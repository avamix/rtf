package ru.sbrf.nhl.rtf.core.model;

public abstract class AbstractEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}