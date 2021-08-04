package com.sds.devops.toolmanagers.imagerepotoolmanager.service;

import com.sds.devops.toolmanagers.common.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageRepoToolService {

    private final ToolRepository toolRepository;
}
