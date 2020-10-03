package entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail_PK implements Serializable {
    private String orderId;
    private String itemCode;
}
