package com.sds.devops.toolmanagers.buildtoolmanager.service;

import com.sds.devops.toolmanagers.common.repository.ToolEntity;
import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class buildToolServiceTest {

    @Autowired
    private BuildToolService buildToolService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void commonModuleJpaWorkingTest() {
        buildToolService.save(new ToolEntity(null, "toolName", "github", "http"));
        List<ToolEntity> all = buildToolService.findAll();

        assertThat(all.get(0).getName()).isEqualTo("toolName");
    }
}