package ru.sbrf.nhl.rtf.core.model;

import java.util.ArrayList;
import java.util.List;

public class Template extends AbstractEntity {

    private String name;

    private List<TemplateParam> params = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TemplateParam> getParams() {
        return params;
    }

    public void setParams(List<TemplateParam> params) {
        this.params = params;
    }
}
