package br.com.api.consumers;

import org.springframework.stereotype.Service;

import br.com.api.services.ContaCorrenteService;
import lombok.extern.java.Log;
import br.com.api.models.ContaCorrente;
import br.com.api.repositorys.ContaCorrenteRepository;

import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class ContaCorrenteConsumer {

    @Autowired
    private ContaCorrenteService contaCorrenteService;

    private TypeReference<HashMap<String, Number>> typeMap = new TypeReference<HashMap<String, Number>>() {};

    private ObjectMapper mapper;

    public ContaCorrenteConsumer(ObjectMapper mapper) {
        this.mapper = mapper;
    }
 
    @KafkaListener(topics = "contacorrenteregister", groupId = "${spring.kafka.consumer.group-id}")
    public void register(ConsumerRecord<String, String> consumerRecord) throws Exception {
        System.out.println("Topic => Register: " + consumerRecord.value());
        ContaCorrente conta = mapper.readValue(consumerRecord.value(), ContaCorrente.class);
        contaCorrenteService.register(conta);
    }

    @KafkaListener(topics = "contacorrentedebito", groupId = "${spring.kafka.consumer.group-id}")
    public void debito(ConsumerRecord<String, String> consumerRecord) throws Exception {
        System.out.println("Topic => Debito: " + consumerRecord.value());
        Map<String, Number> data = mapper.readValue(consumerRecord.value(), typeMap);
        contaCorrenteService.debito(data.get("conta").intValue(), data.get("value").doubleValue());
    }

    @KafkaListener(topics = "contacorrentecredito", groupId = "${spring.kafka.consumer.group-id}")
    public void credito(ConsumerRecord<String, String> consumerRecord) throws Exception {
        System.out.println("Topic => Credito: " + consumerRecord.value());
        Map<String, Number> data = mapper.readValue(consumerRecord.value(), typeMap);
        contaCorrenteService.credito(data.get("conta").intValue(), data.get("value").doubleValue());
    }
}