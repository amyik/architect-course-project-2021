package com.sds.devops.backend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
public class SampleController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/sample")
    public String sample() {
        return "sample api works! : " + appName;
    }

    @GetMapping("/authentication-test")
    public String authenticationTest(HttpServletRequest request, @RequestHeader HttpHeaders headers) throws JsonProcessingException {

        String payload = headers.get("x-jwt").get(0);
        String userEmail = getUserEmail(payload);

        if (StringUtils.isEmpty(userEmail)) {
            return "istio RequestAuthentication Error, no user email value in header";
        }

        return "istio RequestAuthentication Error works! userEmail: " + userEmail;
    }

    private String getUserEmail(String payload) throws JsonProcessingException {

        byte[] decode = Base64.getDecoder().decode(payload);
        String decodedPayload = new String(decode);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> userInfoMap = (Map<String, String>)objectMapper.readValue(decodedPayload, Map.class);

        return userInfoMap.get("email");
    }

}
