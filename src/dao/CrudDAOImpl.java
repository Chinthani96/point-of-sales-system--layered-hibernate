package dao;

import entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

public abstract class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T,ID> {
    protected Session session;
    private Class<T> entity;

    public CrudDAOImpl() {
       entity =  (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

    @Override
    public List<T> findAll() throws SQLException {
        return session.createQuery("FROM " + entity.getName()).list();
    }

    @Override
    public T find(ID pk) throws SQLException {
        return session.get(entity,pk);
    }

    @Override
    public void save(T entity) throws SQLException {
        session.save(entity);
    }

    @Override
    public void update(T entity) throws SQLException {
        session.update(entity);
    }

    @Override
    public void delete(ID pk) throws SQLException {
        session.delete(session.load(entity,pk));
    }


    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
