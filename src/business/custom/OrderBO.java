package business.custom;

import business.SuperBO;
import util.CustomerTM;
import util.SearchOrderTM;

import java.sql.Date;
import java.util.List;

public interface OrderBO extends SuperBO {
    void saveOrder(String id, Date date, CustomerTM customer);
    void saveOrderDetail(String orderId, String itemCode, int qty, double unitPrice);
    String generateNewOrderId();
    List<SearchOrderTM> getOrderDetails();
}
