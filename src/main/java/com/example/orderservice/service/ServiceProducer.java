package com.example.orderservice.service;


import com.example.orderservice.kafka.KafkaMessagingTemplate;
import com.example.orderservice.kafka.OrderEvent;
import com.example.orderservice.model.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceProducer {

    private final KafkaMessagingTemplate kafkaMessagingService;
    private final ModelMapper modelMapper;


    public ResponseEntity<String> sendOrderEvent(String product,Integer quantity) {
        Order order=Order.builder().product(product).quantity(quantity).build();
        kafkaMessagingService.sendOrder(modelMapper.map(order, OrderEvent.class));
        log.info("Send order from producer {}", order);
         return  ResponseEntity.ok(MessageFormat.format("Send order to kafka with product {0} ", order.getProduct()));
    }
}