package br.com.api.config;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.api.consumers.ContaCorrenteConsumer;
import br.com.api.models.ContaCorrente;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstratServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstratServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		
		return props;
    }
    
    @Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		return new DefaultKafkaConsumerFactory<String, Object>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(Object.class));
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	@Bean ConsumerFactory<String, ContaCorrente> contaCorrenteConsumerFactory() {
		return new DefaultKafkaConsumerFactory<String, ContaCorrente>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(ContaCorrente.class));
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ContaCorrente>> contaCorrenteKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, ContaCorrente> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(contaCorrenteConsumerFactory());
		return factory;
	}
	
	
	@Bean
	public ContaCorrenteConsumer consumer() {
		return new ContaCorrenteConsumer(new ObjectMapper());
	}
}