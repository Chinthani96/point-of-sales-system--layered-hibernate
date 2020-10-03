package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.OrderDAO;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends CrudDAOImpl<Order,String> implements OrderDAO {
    public String lastOrderId() throws SQLException {
        return (String) session.createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1").uniqueResult();
    }

}

