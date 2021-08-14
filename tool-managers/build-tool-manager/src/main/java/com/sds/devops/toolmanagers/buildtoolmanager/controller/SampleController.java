package com.sds.devops.toolmanagers.buildtoolmanager.controller;


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
@RestController
@RequiredArgsConstructor
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
    public List<ToolEntity> jpaTest() {

        toolRepository.save(new ToolEntity(null, "githubName", "github", "url1"));
        Iterable<ToolEntity> all = toolRepository.findAll();

        return Streamable.of(all).toList();
    }

    @DeleteMapping("/tools")
    public void deleteTools() {
        toolRepository.deleteAll();
    }

    @GetMapping("/tools")
    public List<ToolEntity> getTools() {
        Iterable<ToolEntity> all = toolRepository.findAll();
        return Streamable.of(all).toList();
    }

    @DeleteMapping("/throw-runtime-exception")
    public void throwRuntimeException() {
        throw new RuntimeException();
    }

    @PostMapping("/delete-repo")
    public boolean deleteRepo(@RequestBody CreateProjectDto createProjectDto) throws InterruptedException {
        CreateProjectDto.ToolInfo buildTool = createProjectDto.getBuildTool();
        toolRepository.deleteByName(buildTool.getName());
        Thread.sleep(1000L);
        log.debug("{}", createProjectDto);
        return true;
    }

    @PostMapping("/create-repo")
    public boolean createRepo(@RequestBody CreateProjectDto createProjectDto) throws InterruptedException {
        CreateProjectDto.ToolInfo buildTool = createProjectDto.getBuildTool();
        Thread.sleep(1000L);
        log.debug("{}", createProjectDto);
        toolRepository.save(new ToolEntity(null, buildTool.getName(), buildTool.getType(), buildTool.getUrl()));
        return true;
    }

}
