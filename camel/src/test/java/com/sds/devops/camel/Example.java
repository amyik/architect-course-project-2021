package com.sds.devops.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

public class Example {

    @Test
    void name() throws Exception {

        DefaultCamelContext context = new DefaultCamelContext();
        RouteBuilder routeBuilder = new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:data/inbox?noop=true")
                        .to("file:data/outbox");
            }
        };

        routeBuilder.addRoutesToCamelContext(context);
//        context.addRoute(routeBuilder);

        context.start();
        Thread.sleep(10000);
        context.stop();
    }

    @Test
    void test2() {

        URL url = getClass().getResource("data/inbox/b.xml");
        File file = new File(url.getPath());
    }
}
