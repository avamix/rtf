package ru.sbrf.nhl.rtf.core.model;

import ru.sbrf.nhl.rtf.core.model.component.TemplateFieldType;

public class TemplateField extends AbstractEntity {

    private String name;

    private TemplateFieldType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemplateFieldType getType() {
        return type;
    }

    public void setType(TemplateFieldType type) {
        this.type = type;
    }
}
