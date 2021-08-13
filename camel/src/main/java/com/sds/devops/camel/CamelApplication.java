package com.sds.devops.camel;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CamelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelApplication.class, args);
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean
                (new CamelHttpTransportServlet(), "/api/camel/*");
        servlet.setName("CamelServlet");
        return servlet;
    }

}
