package de.rjst.ks.kafka;

import de.rjst.ks.Order;
import de.rjst.ks.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaReader {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "PENDING")
    public void consumePendingOrder(final Order order) {
        log.info("Received order: {}", order.getId());
        order.setStatus(OrderStatus.PROCESSING);
        kafkaTemplate.send(OrderStatus.PROCESSING.name(), order);
    }

    @KafkaListener(topics = "PROCESSING")
    public void consumeProcessingOrder(final Order order) {
        log.info("Processed order: {}", order.getId());
    }

}
