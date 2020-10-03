package dao.custom.impl;

import dao.CrudDAOImpl;
import dao.custom.ItemDAO;
import entity.Item;

import javax.persistence.Id;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl extends CrudDAOImpl<Item, String> implements ItemDAO {
    public String getLastItemId() throws SQLException {
        return (String) session.createNativeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1").uniqueResult();
    }

    public int getQty(String pk) throws Exception {
        return (int) session.createNativeQuery("SELECT qtyOnHand FROM Item WHERE code=?",pk).uniqueResult();
    }

    public boolean updateQty(int newQty, String code) throws SQLException {
//        boolean result =  CrudUtil.execute("UPDATE Item set qtyOnhand=? WHERE code=?", newQty, code);
//       session.update(Item,);
        return false;
    }
}
