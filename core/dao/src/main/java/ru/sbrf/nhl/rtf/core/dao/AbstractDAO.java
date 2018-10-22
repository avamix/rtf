package ru.sbrf.nhl.rtf.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbrf.nhl.rtf.core.model.AbstractEntity;

import java.util.List;

public abstract class AbstractDAO<T extends AbstractEntity> {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> clazz;

    public AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Class<T> getEntityClass() {
        return clazz;
    }

    protected String getEntityClassName() {
        return clazz.getName();
    }

    public T read(Long id) {
        return getSession().get(clazz, id);
    }

    public Long save(T object) {
        if (object.getId() == null) {
            return (Long) getSession().save(object);
        } else {
            getSession().update(object);
            return object.getId();
        }
    }

    public void delete(T object) {
        getSession().remove(object);
    }

    public void delete(Long id) {
        T obj = read(id);
        if (obj != null) {
            getSession().remove(obj);
        }
    }

    public List<T> getAll() {
        String hql = "FROM " + getEntityClassName();
        return getSession().createQuery(hql, clazz).list();
    }

    public T removeAll() {
        String hql = "delete FROM " + getEntityClassName();
        getSession().createQuery(hql).executeUpdate();
        return null;
    }

}
