package ru.sbrf.nhl.core.model;

import ru.sbrf.nhl.core.model.component.TemplateParamType;

public class TemplateParam extends AbstractEntity {

    private String name;

    private TemplateParamType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemplateParamType getType() {
        return type;
    }

    public void setType(TemplateParamType type) {
        this.type = type;
    }
}
