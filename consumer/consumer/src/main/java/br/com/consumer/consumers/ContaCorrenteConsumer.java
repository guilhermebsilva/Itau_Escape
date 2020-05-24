package br.com.consumer.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ContaCorrenteConsumer {

    private String topicName = "contacorrente";
    
    @KafkaListener(topics = "contacorrenteregister", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String conta) {
        // System.out.println(consumerRecord.value());
        System.out.println(conta);
    }
}