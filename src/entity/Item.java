package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item implements SuperEntity {
    @Id
    private String code;
    private String description;
    private BigDecimal unitPrice;
    private int qtyOnHand;
}
