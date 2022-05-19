//package com.holamountain.userdomain.dto.response.kafka;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaConsumer {
//    @KafkaListener(topics = "badgeAlarm", groupId = "kafka_consumer")
//    public void consume(String message) {
//        System.out.println(String.format("consumer message -> %s", message));
//    }
//}
