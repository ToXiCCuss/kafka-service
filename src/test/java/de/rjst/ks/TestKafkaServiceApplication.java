package de.rjst.ks;

import org.springframework.boot.SpringApplication;

public class TestKafkaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(KafkaServiceApplication::main)
                         .with(TestcontainersConfiguration.class)
                         .run(args);
    }

}
