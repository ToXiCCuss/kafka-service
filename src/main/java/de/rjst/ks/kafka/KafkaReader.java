package de.rjst.ks.kafka;

import de.rjst.ks.InternalOrder;
import de.rjst.ks.Order;
import de.rjst.ks.OrderStatus;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@KafkaListener(topics = "PENDING")
public class KafkaReader {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaHandler
    public void consumePendingOrder(final Order order) {
        log.info("Received order: {}", order.getId());
        order.setStatus(OrderStatus.PROCESSING);
        kafkaTemplate.send(OrderStatus.PROCESSING.name(), order);
    }

    @KafkaHandler
    public void consumePendingInternalOrder(final InternalOrder order) {
        log.info("Received internal order: {}", order.getId());
        order.setStatus(OrderStatus.PROCESSING);
        kafkaTemplate.send(OrderStatus.PROCESSING.name(), order);
    }

    @KafkaHandler(isDefault = true)
    public void consume(final Object order) {
        log.info("Received event: {}", order.toString());
    }

}
