package de.rjst.ks;

import java.util.List;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    public KafkaContainer kafkaContainer() {
        final var kafkaContainer = new KafkaContainer(DockerImageName.parse("apache/kafka-native:latest"));
        kafkaContainer.setPortBindings(List.of("9092:9092"));
        return kafkaContainer;
    }


}
