package br.com.consumer.consumers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.consumer.models.ContaCorrente;
import br.com.consumer.repositorys.ContaCorrenteRepository;

@Component
public class ContaCorrenteConsumer {

    private String topicName = "contacorrente";

    private final ObjectMapper mapper;
    private ContaCorrenteRepository repository;

    public ContaCorrenteConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    
    @KafkaListener(topics = "contacorrenteregister", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String contaJson) {
        ContaCorrente conta = ContaCorrente.stringToObject(contaJson);
        repository.save(conta);
    }
}