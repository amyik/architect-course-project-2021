package com.sds.devops.camel.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.common.dto.CreateProjectDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateProjectRoute extends RouteBuilder {

    private final ObjectMapper objectMapper;

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.auto);

        rest("/projects")
                .id("porjects_api")
                .consumes("application/json")
                .post()
                .bindingMode(RestBindingMode.json)
                .type(CreateProjectDto.class)
                .to("direct:createProject");

        from("direct:createProject").routeId("route:createProject")
                .log(">> - ${body}")
                .log("sending to validator")
                .to("bean:projectService?method=validate")
                .log("returned from validator")
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .to("http://code-repo-tool-manager:8080/api/code-repo-tool-manager/create-repo?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .process(exchange -> {
                    log.debug("inProcess");
                    log.debug("{}", exchange.getIn().getBody());
                })
//                .to("http://image-repo-tool-manager:8080/api/image-repo-tool-manager/create-repo?bridgeEndpoint=true")
//                .to("http://build-tool-manager:8080/api/build-tool-manager/create-repo?bridgeEndpoint=true")
                .transform().body();

        from("rest:get:hello-world")
                .transform().constant("Bye World");
    }

}