package business.custom;

import business.SuperBO;
import util.ItemTM;

import java.util.List;

public interface ItemBO extends SuperBO {
    String generateNewItemId();
    List<ItemTM> getAllItems();
    void saveItem(String code, String description, double unitPrice, int qtyOnHand);
    void updateItem(String code, String description, double unitPrice, int qtyOnHand);
    void deleteItem(String code);

}
