package ru.sbrf.nhl.core.model.component;

public class TemplateParamTypeString implements TemplateParamType {

    public static final String TYPE = "STRING";

    private int minLength = 0;

    private int maxLength = 2000;

    @Override
    public String getType() {
        return TYPE;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
