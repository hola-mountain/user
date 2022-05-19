//package com.holamountain.userdomain.kafka;
//
//import com.holamountain.userdomain.dto.response.kafka.ReviewCountResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class KafkaProducer {
//    private static final String TOPIC = "mountainBadge";
//
//    private final KafkaTemplate<String, ReviewCountResponse> kafkaTemplate;
//
//    public void sendMessage(ReviewCountResponse message) {
//        this.kafkaTemplate.send(TOPIC, message);
//    }
//}
