package util;

public class SearchOrderTM {
    private String orderId;
    private String date;
    private String customerId;
    private String customerName;
    private double total;

    public SearchOrderTM() {
    }

    public SearchOrderTM(String orderId, String date, String customerId, String customerName, double total) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
        this.customerName = customerName;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SearchOrderTM{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", total=" + total +
                '}';
    }
}
