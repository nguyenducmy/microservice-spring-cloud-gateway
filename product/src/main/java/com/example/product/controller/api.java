package com.example.product.controller;

import com.example.product.config.AppConfig;
import com.example.product.dto.ProductRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.RabbitUtils;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api/v1/product")
public class api {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpServletRequest request;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    Queue queue;

    @Autowired
    ObjectMapper om;

    @GetMapping("/get-products")
    public String getProducts(){
        String url = "http://localhost:8080/api/v1/store/get-stores";
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = bearer.substring(7);
//        String url = "http://localhost:8083/api/v1/store/get-stores";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return "products" + " - " + result.getBody();
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestBody ProductRequest productRequest) throws JsonProcessingException {
        String json = om.writeValueAsString(productRequest);
        rabbitTemplate.convertAndSend(AppConfig.EXCHANGE, AppConfig.ROUTING_KEY,json);
        return "Product Added";
    }

}
