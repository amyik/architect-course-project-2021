package com.sds.devops.camel.service;

import com.sds.common.dto.CreateProjectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProjectService {

    public String log(CreateProjectDto createProjectDto) {
        log.debug(String.valueOf(createProjectDto));
        return "DONE";
    }
}

