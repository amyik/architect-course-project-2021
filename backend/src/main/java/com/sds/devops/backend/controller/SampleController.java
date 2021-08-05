package com.sds.devops.backend.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/sample")
    @ResponseStatus(HttpStatus.OK)
    public String sample() {
        return "sample api works! : " + appName;
    }

}
