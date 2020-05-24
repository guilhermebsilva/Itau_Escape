package br.com.api.consumers;

import org.springframework.stereotype.Service;

import br.com.api.models.ContaCorrente;
import br.com.api.repositorys.ContaCorrenteRepository;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Service
public class ContaCorrenteConsumer {

    private ContaCorrenteRepository repository;

    private ObjectMapper mapper;

    public ContaCorrenteConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }
 
    @KafkaListener(topics = "contacorrenteregister", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(ConsumerRecord consumerRecord) throws IOException {
        ContaCorrente conta = mapper.readValue(consumerRecord.value().toString(), ContaCorrente.class);
        repository.save(conta);
    }
}