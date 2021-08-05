package com.sds.devops.toolmanagers.coderepotoolmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.sds.devops.toolmanagers.common.repository"})
@EntityScan(basePackages = {"com.sds.devops.toolmanagers.common.repository"})
@SpringBootApplication
public class CodeRepoToolManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeRepoToolManagerApplication.class, args);
    }

}
