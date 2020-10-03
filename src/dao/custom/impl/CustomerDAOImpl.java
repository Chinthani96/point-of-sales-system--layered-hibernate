package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.CustomerDAO;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {
    public String getLastCustomerId() throws SQLException {
        return (String) session.createNativeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1").uniqueResult();
    }
}
