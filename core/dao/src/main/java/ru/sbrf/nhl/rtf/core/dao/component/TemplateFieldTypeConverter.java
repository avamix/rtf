package ru.sbrf.nhl.rtf.core.dao.component;

import ru.sbrf.nhl.rtf.core.model.component.*;

//TODO - надо доделать конверер (или переделать), чтобы он умел полностью сериализовть и десериализовтаь объект в строку
//можно, например, в JSON переводить
public final class TemplateFieldTypeConverter {

    public static String toString(TemplateFieldType object) {
        return object == null ? null : object.getType();
    }

    public static TemplateFieldType toObject(String string) {
        switch (string) {
            case TemplateFieldTypeString.TYPE:
                return new TemplateFieldTypeString();
            case TemplateFieldTypeNumber.TYPE:
                return new TemplateFieldTypeNumber();
            case TemplateFieldTypeStar.TYPE:
                return new TemplateFieldTypeStar();
            case TemplateFieldTypeEnum.TYPE:
                return new TemplateFieldTypeEnum();
            case TemplateFieldTypeBoolean.TYPE:
                return new TemplateFieldTypeBoolean();
        }
        return null;
    }

}
