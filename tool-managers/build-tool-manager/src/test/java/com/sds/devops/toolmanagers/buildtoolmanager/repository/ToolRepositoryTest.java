package com.sds.devops.toolmanagers.buildtoolmanager.repository;

import com.sds.devops.toolmanagers.common.repository.ToolEntity;
import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ToolRepositoryTest {

    @Autowired
    ToolRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {

        repository.save(new ToolEntity(null, "name1", "github", "http://github.com"));
        repository.save(new ToolEntity(null, "name2", "gitlab", "http://gitlab.com"));
        repository.save(new ToolEntity(null, "name3", "redii", "http://redii.com"));
        repository.save(new ToolEntity(null, "name4", "docker", "http://docker.com"));

        ToolEntity toolEntity = repository.findById(1L).get();
        assertThat(toolEntity.getName()).isEqualTo("name1");
    }

    @Test
    void findByName() {
    }
}