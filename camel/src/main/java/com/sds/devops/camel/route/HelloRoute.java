package com.sds.devops.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class HelloRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

//        restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
//                // and output using pretty print
//                .dataFormatProperty("prettyPrint", "true")
//                // setup context path and port number that Apache Tomcat will deploy
//                // this application with, as we use the servlet component, then we
//                // need to aid Camel to tell it these details so Camel knows the url
//                // to the REST services.
//                // Notice: This is optional, but needed if the RestRegistry should
//                // enlist accurate information. You can access the RestRegistry
//                // from JMX at runtime
//                .contextPath("camel-example-servlet-rest-tomcat/rest").port(8080);

//        restConfiguration().host("localhost:8080/camel").producerComponent("http");

        from("rest:get:hello").transform().simple("hello camel");

//        rest("/").produces("text/plain")
//                .get("hello")
//                .to("direct:hello");
//
//        from("direct:hello")
//                .to("geocoder:address:current")
//                .transform().simple("Hello from Spring Boot and Camel. We are at: ${body}");
    }

}