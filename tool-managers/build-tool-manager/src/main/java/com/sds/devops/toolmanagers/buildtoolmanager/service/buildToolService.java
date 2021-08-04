package com.sds.devops.toolmanagers.buildtoolmanager.service;

import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class buildToolService {

    private final ToolRepository toolRepository;
}
