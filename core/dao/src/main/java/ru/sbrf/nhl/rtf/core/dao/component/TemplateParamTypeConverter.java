package ru.sbrf.nhl.rtf.core.dao.component;

import ru.sbrf.nhl.rtf.core.model.component.*;

//TODO - надо доделать конверер (или переделать), чтобы он умел полностью сериализовть и десериализовтаь объект в строку
//можно, например, в JSON переводить
public final class TemplateParamTypeConverter {

    public static String toString(TemplateParamType object) {
        return object == null ? null : object.getType();
    }

    public static TemplateParamType toObject(String string) {
        switch (string) {
            case TemplateParamTypeString.TYPE:
                return new TemplateParamTypeString();
            case TemplateParamTypeNumber.TYPE:
                return new TemplateParamTypeNumber();
            case TemplateParamTypeStar.TYPE:
                return new TemplateParamTypeStar();
            case TemplateParamTypeEnum.TYPE:
                return new TemplateParamTypeEnum();
        }
        return null;
    }

}
