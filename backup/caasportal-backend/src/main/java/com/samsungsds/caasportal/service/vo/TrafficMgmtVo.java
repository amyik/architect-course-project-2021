package com.samsungsds.caasportal.service.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TrafficMgmtVo {
	Long id;
	String name;
	List<RoutingVo> routingList;
}
