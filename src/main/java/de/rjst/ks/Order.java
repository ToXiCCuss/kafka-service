package de.rjst.ks;

import java.util.UUID;
import lombok.Data;

@Data
public class Order {

    private UUID id;
    private String customer;
    private OrderStatus status;

}
