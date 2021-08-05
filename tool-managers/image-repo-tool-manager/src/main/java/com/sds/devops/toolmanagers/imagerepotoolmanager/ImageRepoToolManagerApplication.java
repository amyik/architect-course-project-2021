package com.sds.devops.toolmanagers.imagerepotoolmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.sds.devops.toolmanagers.common.repository"})
@EntityScan(basePackages = {"com.sds.devops.toolmanagers.common.repository"})
@SpringBootApplication
public class ImageRepoToolManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageRepoToolManagerApplication.class, args);
    }

}
