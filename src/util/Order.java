package util;

public class Order {
    private OrderDetailsTM orderId;
    private CustomerTM customerId;
    private double total;

    public Order() {
    }

    public Order(OrderDetailsTM orderId, CustomerTM customerId, double total) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.total = total;
    }

    public OrderDetailsTM getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderDetailsTM orderId) {
        this.orderId = orderId;
    }

    public CustomerTM getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerTM customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
