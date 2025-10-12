package de.rjst.ks.kafka;

import de.rjst.ks.Order;
import de.rjst.ks.OrderStatus;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaWriter implements Runnable {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void run() {
        final var order = new Order();
        order.setId(UUID.randomUUID());
        order.setCustomer("customer");
        order.setStatus(OrderStatus.PENDING);
        kafkaTemplate.send(OrderStatus.PENDING.name(), order);
    }
}
