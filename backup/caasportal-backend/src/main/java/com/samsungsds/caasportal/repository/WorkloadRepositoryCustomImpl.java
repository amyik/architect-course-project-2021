package com.samsungsds.caasportal.repository;

import com.samsungsds.caasportal.repository.entity.WorkloadEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

public class WorkloadRepositoryCustomImpl extends QuerydslRepositorySupport implements WorkloadRepositoryCustom {
    public WorkloadRepositoryCustomImpl() {
        super(WorkloadEntity.class);
    }
}
