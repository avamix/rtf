package ru.sbrf.nhl.rtf.core.model;

import java.util.ArrayList;
import java.util.List;

public class Template extends AbstractEntity {

    private String name;

    private List<TemplateField> fields = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TemplateField> getFields() {
        return fields;
    }

    public void setFields(List<TemplateField> fields) {
        this.fields = fields;
    }
}
