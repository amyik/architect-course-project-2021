package com.sds.devops.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:clock?period=5s")
                .saga()
                .setHeader("id", header(Exchange.TIMER_COUNTER))
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .log("Executing saga #${header.id}")
                .to("http4://camel-saga-train-service:8080/api/train/buy/seat")
                .to("http4://camel-saga-flight-service:8080/api/flight/buy");
    }

}