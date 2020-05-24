package br.com.api.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.models.ContaCorrente;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ContaCorrenteProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ContaCorrenteProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(String topic, @RequestBody ContaCorrente contaCorrente) throws JsonProcessingException {
        String key = UUID.randomUUID().toString();
        String serialiaze = objectMapper.writeValueAsString(contaCorrente);
        kafkaTemplate.send(topic, key, serialiaze);
    }
}