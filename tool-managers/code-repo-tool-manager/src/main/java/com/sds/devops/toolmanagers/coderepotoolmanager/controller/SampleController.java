package com.sds.devops.toolmanagers.coderepotoolmanager.controller;


import com.sds.common.dto.CreateProjectDto;
import com.sds.devops.toolmanagers.common.repository.ToolEntity;
import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SampleController {

    private final ToolRepository toolRepository;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/sample")
    @ResponseStatus(HttpStatus.OK)
    public String sample() {
        return "sample api works! : " + appName;
    }

    @GetMapping("/jpa-test")
    @ResponseStatus(HttpStatus.OK)
    public List<ToolEntity> jpaTest() {

        toolRepository.save(new ToolEntity(null, "myGithub", "github", "www.github.com"));
        Iterable<ToolEntity> all = toolRepository.findAll();

        return Streamable.of(all).toList();
    }

    @PostMapping("/delete-repo")
    public boolean deleteRepo(@RequestBody CreateProjectDto createProjectDto) throws InterruptedException {
        CreateProjectDto.ToolInfo codeRepoTool = createProjectDto.getCodeRepoTool();
        toolRepository.deleteByName(codeRepoTool.getName());
        Thread.sleep(1000L);
        log.debug("{}", createProjectDto);
        return true;
    }

    @PostMapping("/create-repo")
    public boolean createRepo(@RequestBody CreateProjectDto createProjectDto) throws InterruptedException {
        CreateProjectDto.ToolInfo codeRepoTool = createProjectDto.getCodeRepoTool();
        Thread.sleep(1000L);
        log.debug("{}", createProjectDto);
        toolRepository.save(new ToolEntity(null, codeRepoTool.getName(), codeRepoTool.getType(), codeRepoTool.getUrl()));
        return true;
    }
}
