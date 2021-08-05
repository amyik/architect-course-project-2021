package com.sds.devops.toolmanagers.common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolRepository extends CrudRepository<ToolEntity, Long> {

    Optional<ToolEntity> findById(Long id);

    Iterable<Object> findByName(String name);
}
