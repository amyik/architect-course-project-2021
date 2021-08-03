package com.samsungsds.caasportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CaasportalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaasportalApplication.class, args);
    }
}
