package br.com.api.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ContaCorrenteProducer {
    
    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate kafkaTemplate;

    public ContaCorrenteProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // public void send(@RequestBody String contaCorrente) {

    // }
}