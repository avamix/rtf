package ru.sbrf.nhl.rtf.core.model;

import ru.sbrf.nhl.rtf.core.model.component.TemplateFieldType;

import java.util.HashMap;
import java.util.Map;

public class TemplateField extends AbstractEntity {

    private String name;

    private TemplateFieldType type;

    private Map<String,String> addParams = new HashMap<>();

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

    public Map<String, String> getAddParams() {
        return addParams;
    }

    public void setAddParams(Map<String, String> addParams) {
        this.addParams = addParams;
    }
}
