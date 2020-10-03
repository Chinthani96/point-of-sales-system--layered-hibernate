package util;

import java.util.ArrayList;
import java.util.Date;

public class OrderDetail {
    private String orderId;
//    private Date orderDate;
    private ArrayList<OrderDetailsTM> orderDetails;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, ArrayList<OrderDetailsTM> orderDetails) {
        this.orderId = orderId;
//        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
//
//    public Date getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }

    public ArrayList<OrderDetailsTM> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetailsTM> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
