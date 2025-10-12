package de.rjst.ks.kafka;

import de.rjst.ks.OrderStatus;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {


    @Bean
    public NewTopic pendingTopic() {
        return new NewTopic(OrderStatus.PENDING.name(), 1, (short) 1);
    }

    @Bean
    public NewTopic processingTopic() {
        return new NewTopic(OrderStatus.PROCESSING.name(), 1, (short) 1);
    }


}
