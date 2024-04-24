package com.example.orderservice;


import com.example.orderservice.service.ServiceProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final ServiceProducer producer;

    @PostMapping("/{product}/{quantity}")
    public ResponseEntity<String> sendOrder(@PathVariable String product, @PathVariable Integer quantity) {
        log.info("Send order to kafka");
        return producer.sendOrderEvent(product, quantity);
    }


}