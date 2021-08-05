package com.sds.devops.toolmanagers.coderepotoolmanager.controller;


import com.sds.devops.toolmanagers.common.repository.ToolEntity;
import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SampleController {

    private final ToolRepository toolRepository;

    @GetMapping("/sample")
    @ResponseStatus(HttpStatus.OK)
    public String sample() {
        return "sample api works! ( code-repo-tool-manager )";
    }

    @GetMapping("/jpa-test")
    @ResponseStatus(HttpStatus.OK)
    public List<ToolEntity> jpaTest() {

        toolRepository.save(new ToolEntity(null, "githubName", "github", "url1"));
        Iterable<ToolEntity> all = toolRepository.findAll();

        return Streamable.of(all).toList();
    }
}
