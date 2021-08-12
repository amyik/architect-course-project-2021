package com.sds.devops.camel.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

//    @Value("${spring.application.name}")
//    private String appName;
//
//    @GetMapping("/sample")
//    public String sample() {
//        return "sample api works! : " + appName;
//    }

//    @EndpointInject("geocoder:address:current")
//    private FluentProducerTemplate producer;

//    @GetMapping("/hello")
//    public String hello() {
//        String where = producer.request(String.class);
//        return "Hello from Spring Boot and Camel. We are at: " + where;
//    }
}
