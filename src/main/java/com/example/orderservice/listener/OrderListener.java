
package com.example.orderservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class OrderListener {

    @KafkaListener(id = "foo", topics = "${topic.status-order}")
    public void receive(@Payload String data,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) String  partition,
                        @Header(KafkaHeaders.RECEIVED_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP)  String timestamp) {
       log.info("Received message: {}",data);
        log.info("Key:  {}; Partition: {}; Topic: {}; Timestamp: {}; ", key, partition, topic, timestamp);

    }



}
