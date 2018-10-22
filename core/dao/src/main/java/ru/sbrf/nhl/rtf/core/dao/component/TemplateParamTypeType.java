package ru.sbrf.nhl.rtf.core.dao.component;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;
import ru.sbrf.nhl.rtf.core.model.component.TemplateParamType;
import ru.sbrf.nhl.rtf.core.model.component.TemplateParamTypeString;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TemplateParamTypeType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{StringType.INSTANCE.sqlType()};
    }

    @Override
    public Class returnedClass() {
        return TemplateParamTypeString.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x != null && x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        assert names.length == 1;
        String stringValue = (String) StringType.INSTANCE.get(rs, names[0], session);
        return TemplateParamTypeConverter.toObject(stringValue);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        String stringValue = TemplateParamTypeConverter.toString((TemplateParamType) value);
        StringType.INSTANCE.set(st, stringValue, index, session);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return null;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return null;
    }
}
