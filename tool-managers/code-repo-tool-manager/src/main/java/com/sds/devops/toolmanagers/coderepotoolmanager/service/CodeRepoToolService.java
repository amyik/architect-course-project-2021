package com.sds.devops.toolmanagers.coderepotoolmanager.service;

import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeRepoToolService {

    private final ToolRepository toolRepository;
}
