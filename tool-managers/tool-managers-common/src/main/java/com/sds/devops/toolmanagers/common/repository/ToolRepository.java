package com.sds.devops.toolmanagers.common.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ToolRepository extends CrudRepository<ToolEntity, Long> {

    Optional<ToolEntity> findById(Long id);

    Iterable<Object> findByName(String name);
}
