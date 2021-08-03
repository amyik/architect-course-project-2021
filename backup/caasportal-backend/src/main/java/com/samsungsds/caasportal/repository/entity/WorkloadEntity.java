package com.samsungsds.caasportal.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "WORKLOAD")
@Entity
public class WorkloadEntity extends BaseEntity{

    @Column(nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "namespace")
    NamespaceEntity namespace;
}
