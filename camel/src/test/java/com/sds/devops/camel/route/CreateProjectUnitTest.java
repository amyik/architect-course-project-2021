package com.sds.devops.camel.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sds.devops.camel.CamelApplication;
import com.sds.common.dto.CreateProjectDto;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.apache.camel.test.junit4.TestSupport.deleteDirectory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(CamelSpringBootRunner.class)
//@SpringBootTest(classes = CamelApplication.class)
@ContextConfiguration(classes = CamelApplication.class)
public class CreateProjectUnitTest {

    private static Logger log = LoggerFactory.getLogger(CreateProjectUnitTest.class);

    @Autowired
    private CamelContext context;

    @Autowired
    private ProducerTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void cleanDir() throws Exception {
    }

    @Test
    public void createProject() throws Exception {

//        template.set
        CreateProjectDto createProjectDto = CreateProjectDto.makeTestSample();

        String s = objectMapper.writeValueAsString(createProjectDto);
        log.debug(s);


//                restConfiguration().producerComponent("http4")
//         to call rest service on localhost:8080 (the REST service from GeoRestController)
//                .host("localhost").port(8080);

        template.sendBody("rest:post:create-project", createProjectDto);

//        template.sendBodyAndHeader("file://target/inbox", "Hello World", Exchange.FILE_NAME, "hello.txt");

        Thread.sleep(2000);

//        File target = new File("target/outbox/hello.txt");
//        assertTrue("file not moved", target.exists());

//        String content = context.getTypeConverter().convertTo(String.class, target);
//        assertEquals("Hello World", content);
    }
}
