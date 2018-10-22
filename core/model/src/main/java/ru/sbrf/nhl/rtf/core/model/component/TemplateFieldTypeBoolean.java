package ru.sbrf.nhl.rtf.core.model.component;

public class TemplateFieldTypeBoolean implements TemplateFieldType {

    public static final String TYPE = "BOOL";

    @Override
    public String getType() {
        return TYPE;
    }
}
