package br.com.api.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.models.ContaCorrente;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Classe que envia mensagens para o Kafka
@Service
public class ContaCorrenteProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    // Da entrada as dependências da classe
    public ContaCorrenteProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    // Manda mensagem passando um objeto ContaCorrente via Json
    public void send(String topic, @RequestBody ContaCorrente contaCorrente) throws JsonProcessingException {
        String key = UUID.randomUUID().toString();
        // Cria um Json do Objeto ContaCorrente
        String serialiaze = objectMapper.writeValueAsString(contaCorrente);

        // Manda pro Kafka
        kafkaTemplate.send(topic, key, serialiaze);
    }

    // Manda mensagem passando um mapa com chave String e Valor Number via Json
    public void send(String topic, Map<String, Number> data) throws JsonProcessingException {
        String key = UUID.randomUUID().toString();
        // Cria um Json do Mapa
        String serialiaze = objectMapper.writeValueAsString(data);

        // Manda pro Kafka
        kafkaTemplate.send(topic, key, serialiaze);
    }
}