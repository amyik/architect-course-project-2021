package com.sds.devops.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileMoveRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://target/inbox").to("file://target/outbox");
    }

}