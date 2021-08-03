package com.samsungsds.caasportal.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "NAMESPACE")
@Entity
public class NamespaceEntity extends BaseEntity{

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "namespace", fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "namespace", fetch = FetchType.EAGER)
    private List<WorkloadEntity> workloadEntities;
    
//    @OneToMany(mappedBy = "namespace", fetch = FetchType.LAZY)
//    private List<TrafficMgmtEntity> trafficMgmtEntities;
}
