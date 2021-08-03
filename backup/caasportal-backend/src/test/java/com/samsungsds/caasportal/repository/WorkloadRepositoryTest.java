package com.samsungsds.caasportal.repository;

import com.samsungsds.caasportal.repository.entity.WorkloadEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class WorkloadRepositoryTest {

    @Autowired
    WorkloadRepository workloadRepository;

    @DisplayName("spring data jpa test")
    @Test
    void jpatest() {

        WorkloadEntity workloadName = WorkloadEntity.builder()
                .name("workloadName")
                .build();

        workloadRepository.save(workloadName);
        WorkloadEntity one = workloadRepository.getOne(workloadName.getId());
        assertThat(one.getName()).isEqualTo("workloadName");

    }
}