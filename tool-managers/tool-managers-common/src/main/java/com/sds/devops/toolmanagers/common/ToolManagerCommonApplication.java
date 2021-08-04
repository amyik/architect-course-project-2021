package com.sds.devops.toolmanagers.common;

import com.sds.devops.toolmanagers.common.repository.ToolEntity;
import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class ToolManagerCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolManagerCommonApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ToolRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new ToolEntity(null, "name1", "github", "http://github.com"));
            repository.save(new ToolEntity(null, "name2", "gitlab", "http://gitlab.com"));
            repository.save(new ToolEntity(null, "name3", "redii", "http://redii.com"));
            repository.save(new ToolEntity(null, "name4", "docker", "http://docker.com"));

            // fetch all customers
            log.info("ToolEntity found with findAll():");
            log.info("-------------------------------");
            for (ToolEntity tool : repository.findAll()) {
                log.info(tool.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<ToolEntity> tool = repository.findById(1L);
            log.info("ToolEntity found with findById(1L):");
            log.info("--------------------------------");
            log.info(tool.toString());
            log.info("");

            // fetch customers by last name
            log.info("ToolEntity found with findByName('name1'):");
            log.info("--------------------------------------------");
            repository.findByName("name1").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}
