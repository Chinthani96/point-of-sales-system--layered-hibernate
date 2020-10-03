package util;

public class CustomerTM {
    private String customerId;
    private String customerName;
    private String customerAddress;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String customerName, String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
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

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return customerId;
    }
}
