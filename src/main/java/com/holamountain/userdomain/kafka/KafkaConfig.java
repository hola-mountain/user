//package com.holamountain.userdomain.kafka;
//
//import com.holamountain.userdomain.dto.response.kafka.ReviewCountResponse;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//class KafkaConfig {
//    private static  final String BOOTSTRAP_SERVER = "3.36.132.196:9092";
//
//    private static final String TOPIC = "mountainBadge";
//
//    @Bean
//    public Map<String, Object> ratingConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, ReviewCountResponse> ratingProducerFactory() {
//        return new DefaultKafkaProducerFactory<>(ratingConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, ReviewCountResponse> ratingKafkaTemplate() {
//        return new KafkaTemplate<>(ratingProducerFactory());
//    }
//}
