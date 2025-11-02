package de.rjst.ks.kafka;

import de.rjst.ks.InternalOrder;
import de.rjst.ks.Order;
import de.rjst.ks.OrderStatus;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaWriterV2 implements Runnable {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @AsyncPublisher(operation = @AsyncOperation(
        channelName = "PENDING",
        description = "Publish internal order"
    ))
    @KafkaAsyncOperationBinding
    @Override
    public void run() {
        final var order = new InternalOrder();
        order.setId(UUID.randomUUID());
        order.setStatus(OrderStatus.PENDING);
        kafkaTemplate.send(OrderStatus.PENDING.name(), order);
        log.info("order {} sent", order.getId());
    }
}
