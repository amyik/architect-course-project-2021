package com.sds.devops.camel.route;

import com.sds.common.dto.CreateProjectDto;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CreateProjectRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        restConfiguration().producerComponent("http4")
                // to call rest service on localhost:8080 (the REST service from GeoRestController)
//                .host("localhost").port(8080);

        from("rest:get:hello-world")
                .transform().constant("Bye World");

        from("direct:backEndSample")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
//                .to("http://www.google.com?bridgeEndpoint=true");
                .to("http://build-tool-manager:8080/api/build-tool-manager/sample?bridgeEndpoint=true")
                .transform().body();
//                        .to("bean:projectService?method=log");

        rest("/projects")
                // need to specify the POJO types the binding is using (otherwise json binding defaults to Map based)
//                .get("{id}").outType(Order.class)
//                .to("bean:orderService?method=getOrder(${header.id})")
                // need to specify the POJO types the binding is using (otherwise json binding defaults to Map based)
                .post().type(CreateProjectDto.class)
                .to("direct:backEndSample");
//                .to("http://backend:8080/api/backend/sample");
//                .to("bean:projectService?method=log");
                // need to specify the POJO types the binding is using (otherwise json binding defaults to Map based)
//                .put().type(Order.class)
//                .to("bean:orderService?method=updateOrder")
//                .delete("{id}")
//                .to("bean:orderService?method=cancelOrder(${header.id})");

        from("rest:post:json-body").transform().simple("json-body-api-works");
    }

}