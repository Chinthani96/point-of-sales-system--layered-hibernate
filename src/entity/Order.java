package entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "`Order`")
public class Order implements SuperEntity {
    @Id
    private String id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable=false) //false because of the total participation of Customer's side.
    private Customer customer;
    @OneToMany(mappedBy = "order",cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}) //Bi directional
    private List<OrderDetail> orderDetails;

    public Order(String id, Date date, Customer customer, List<OrderDetail> orderDetails) {
        this.id = id;
        this.date = date;
        this.customer = customer;

        //TODO: understand this shit!!!!!!!!!!
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(this);
        }
        this.orderDetails = orderDetails;
    }

    public Order(String id, Date date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails){
        for (OrderDetail orderDetail: orderDetails) {
            orderDetail.setOrder(this);
        }
        this.orderDetails = orderDetails;
    }

    public void addOrderDetails(OrderDetail orderDetail){
        orderDetail.setOrder(this);
        this.getOrderDetails().add(orderDetail);
    }
}
