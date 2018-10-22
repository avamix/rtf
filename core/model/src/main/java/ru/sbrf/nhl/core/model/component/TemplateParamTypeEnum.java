package ru.sbrf.nhl.core.model.component;

import java.util.ArrayList;
import java.util.List;

public class TemplateParamTypeEnum implements TemplateParamType {

    public static final String TYPE = "ENUM";

    private List<String> values = new ArrayList<>();

    @Override
    public String getType() {
        return TYPE;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
