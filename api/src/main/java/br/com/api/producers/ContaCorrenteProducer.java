package br.com.api.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

@Component
public class ContaCorrenteProducer {
    
    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ContaCorrenteProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(@RequestBody String contaCorrente) {
        String key = UUID.randomUUID().toString();
        kafkaTemplate.send(topicName, key, contaCorrente);
    }
}