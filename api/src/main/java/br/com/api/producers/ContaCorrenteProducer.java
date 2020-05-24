package br.com.api.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.models.ContaCorrente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

@Component
public class ContaCorrenteProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ContaCorrenteProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, @RequestBody ContaCorrente contaCorrente) {
        String key = UUID.randomUUID().toString();
        kafkaTemplate.send(topic, key, contaCorrente.toString());
        System.out.println(contaCorrente.toString());
    }
}