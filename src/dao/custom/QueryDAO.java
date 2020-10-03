package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    public List<CustomEntity> getOrderDetails() throws Exception;
}
