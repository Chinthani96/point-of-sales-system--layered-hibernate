package dao;

import org.hibernate.Session;

import java.io.Serializable;

public interface SuperDAO extends Serializable {
    public void setSession(Session session);
}
