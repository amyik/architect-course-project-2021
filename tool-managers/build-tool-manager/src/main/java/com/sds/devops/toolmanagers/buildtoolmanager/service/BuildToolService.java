package com.sds.devops.toolmanagers.buildtoolmanager.service;

import com.sds.devops.toolmanagers.common.repository.ToolEntity;
import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildToolService {

    private final ToolRepository toolRepository;

    public Long save(ToolEntity toolEntity) {
        return toolRepository.save(toolEntity).getId();
    }

    public List<ToolEntity> findAll() {
        Iterable<ToolEntity> all = toolRepository.findAll();
        return Streamable.of(all).toList();
    }
}
