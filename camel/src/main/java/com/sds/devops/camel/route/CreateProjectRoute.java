package com.sds.devops.camel.route;

import com.sds.common.dto.CreateProjectDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.saga.InMemorySagaService;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateProjectRoute extends RouteBuilder {

    private final CamelContext camelContext;

    @Override

    public void configure() throws Exception {

        camelContext.addService(new InMemorySagaService());
        camelContext.setAllowUseOriginalMessage(true);
        camelContext.setMessageHistory(true);

        restConfiguration().bindingMode(RestBindingMode.auto);

        rest("/projects")
                .id("porjects_api")
                .consumes("application/json")
                .post()
                .bindingMode(RestBindingMode.json)
                .type(CreateProjectDto.class)
                .to("direct:createProject");

        rest("/projects-with-fail")
                .id("porjects_api")
                .consumes("application/json")
                .post()
                .bindingMode(RestBindingMode.json)
                .type(CreateProjectDto.class)
                .to("direct:createProjectWithFail");

        rest("/projects-with-fail-but-saga")
                .id("porjects_api")
                .consumes("application/json")
                .post()
                .bindingMode(RestBindingMode.json)
                .type(CreateProjectDto.class)
                .to("direct:createProjectWithFailButSaga");

        from("direct:createProject").routeId("route:createProject")
                .log(">> - ${body}")
                .log("sending to validator")
                .to("bean:projectService?method=validate")
                .log("returned from validator")
                .marshal().json()
                .process(exchange -> {
                    log.debug("inProcess");
                    log.debug("{}", exchange.getIn().getBody());
                })
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .multicast().parallelProcessing()
                .to("http://code-repo-tool-manager:8080/api/code-repo-tool-manager/create-repo?bridgeEndpoint=true"
                        , "http://image-repo-tool-manager:8080/api/image-repo-tool-manager/create-repo?bridgeEndpoint=true"
                        , "http://build-tool-manager:8080/api/build-tool-manager/create-repo?bridgeEndpoint=true")
                .process(exchange -> {
                    log.debug("inProcess");
                    log.debug("{}", exchange.getIn().getBody());
                });

        from("direct:createProjectWithFail").routeId("route:createProjectWithFail")
                .log(">> - ${body}")
                .log("sending to validator")
                .to("bean:projectService?method=validate")
                .log("returned from validator")
                .saga()
                .compensation("direct:cancelCreate").option("originalBody", body())
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .multicast().parallelProcessing()
                .to("http://code-repo-tool-manager:8080/api/code-repo-tool-manager/create-repo?bridgeEndpoint=true"
                        , "http://image-repo-tool-manager:8080/api/image-repo-tool-manager/create-repo?bridgeEndpoint=true"
                        , "http://build-tool-manager:8080/api/build-tool-manager/throw-runtime-exception?bridgeEndpoint=true")
                .process(exchange -> {
                    log.debug("inProcess");
                    log.debug("{}", exchange.getIn().getBody());
                });

        from("direct:createProjectWithFailButSaga").routeId("route:createProjectWithFailButSaga")
                .log(">> - ${body}")
                .log("sending to validator")
                .to("bean:projectService?method=validate")
                .log("returned from validator")
                .saga()
//                .compensation("direct:cancelCreate").option("originalBody", body())
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .multicast().parallelProcessing()
                .to("http://code-repo-tool-manager:8080/api/code-repo-tool-manager/create-repo?bridgeEndpoint=true"
                        , "http://image-repo-tool-manager:8080/api/image-repo-tool-manager/create-repo?bridgeEndpoint=true"
                        , "http://build-tool-manager:8080/api/build-tool-manager/throw-runtime-exception?bridgeEndpoint=true")
                .process(exchange -> {
                    log.debug("inProcess");
                    log.debug("{}", exchange.getIn().getBody());
                });

        from("direct:cancelCreate").routeId("route:cancelCreate")
                .process(exchange -> exchange.getIn().setBody(exchange.getIn().getHeader("originalBody")))
                .marshal().json()
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
                .multicast().parallelProcessing()
                .to("http://code-repo-tool-manager:8080/api/code-repo-tool-manager/delete-repo?bridgeEndpoint=true"
                        , "http://image-repo-tool-manager:8080/api/image-repo-tool-manager/delete-repo?bridgeEndpoint=true"
                        , "http://build-tool-manager:8080/api/build-tool-manager/delete-repo?bridgeEndpoint=true");

        from("rest:get:hello-world")
                .transform().constant("Bye World");
    }

}