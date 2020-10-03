package util;

public class ItemTM {
    private String itemId;
    private String description;
    private double unitPrice;
    private int qtyOnHand;

    public ItemTM() {
    }
    public ItemTM(String itemId, String description, double unitPrice, int qtyOnHand) {
        this.itemId = itemId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return itemId;
    }
}
