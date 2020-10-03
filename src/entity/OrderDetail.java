package entity;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetail implements SuperEntity {
    @EmbeddedId
    private OrderDetail_PK orderDetail_pk;
    private int qty;
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "itemId",referencedColumnName = "code", insertable = false, updatable = false)
    private Item itemId;

    public OrderDetail(OrderDetail_PK orderDetail_pk, int qty, BigDecimal unitPrice) {
        this.orderDetail_pk = orderDetail_pk;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public OrderDetail(String orderId, String itemCode, int qty, BigDecimal unitPrice) {
        this.orderDetail_pk = new OrderDetail_PK(orderId,itemCode);
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
}
