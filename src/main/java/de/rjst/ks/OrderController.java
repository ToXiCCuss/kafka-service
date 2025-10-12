package de.rjst.ks;

import de.rjst.ks.kafka.KafkaWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaWriter kafkaWriter;

    @PostMapping
    public void createOrder() {
        kafkaWriter.run();
    }

}
