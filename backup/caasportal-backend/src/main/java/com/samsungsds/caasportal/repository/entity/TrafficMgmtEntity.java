package com.samsungsds.caasportal.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TrafficMgMt")
@Entity
public class TrafficMgmtEntity extends BaseEntity{
	
	  @Id
	  Long id;
	  
	  @Column(nullable = false)
	  String name;

//	  @ManyToOne(fetch = FetchType.LAZY)
//	  @JoinColumn(name = "namespace")
//	  NamespaceEntity namespace;
}
