package dao.custom;

import dao.CrudDAO;
import entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    String getLastItemId() throws SQLException;
    int getQty(String pk) throws Exception;
    boolean updateQty(int newQty, String code) throws SQLException;
}
