package de.rjst.ks;

import java.util.UUID;
import lombok.Data;

@Data
public class InternalOrder {

    private UUID id;
    private OrderStatus status;

}
