package util;

public class OrderDetailsTM {
    private String itemId;
    private String description;
    private int qty;
    private double unitPrice;
    private double total;

    public OrderDetailsTM() {
    }

    public OrderDetailsTM(String itemId, String description, int qty, double unitPrice, double total) {
        this.itemId = itemId;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "OrderDetailsTM{" +
                "itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
