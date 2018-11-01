package ru.sbrf.nhl.rtf.rest.dto;

import lombok.Data;

public enum Source {
    JIRA(70),
    BITBUCKET(100),
    TEST(90),
    FORM(50);
    private int weight;

    Source(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
