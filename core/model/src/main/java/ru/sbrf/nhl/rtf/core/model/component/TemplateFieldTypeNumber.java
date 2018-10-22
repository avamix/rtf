package ru.sbrf.nhl.rtf.core.model.component;

public class TemplateFieldTypeNumber implements TemplateFieldType {

    public static final String TYPE = "NUMBER";

    private long min = Long.MIN_VALUE;

    private long max = Long.MAX_VALUE;

    @Override
    public String getType() {
        return TYPE;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }
}
