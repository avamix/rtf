package ru.sbrf.nhl.rtf.core.model.component;

public class TemplateFieldTypeStar implements TemplateFieldType {

    public static final String TYPE = "STAR";

    private int max = 5;

    @Override
    public String getType() {
        return TYPE;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
