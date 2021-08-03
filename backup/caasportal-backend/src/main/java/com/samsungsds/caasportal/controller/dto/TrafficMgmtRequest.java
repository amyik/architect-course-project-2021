package com.samsungsds.caasportal.controller.dto;

import java.util.List;

import com.samsungsds.caasportal.service.vo.ClusterInfoVo;
import com.samsungsds.caasportal.service.vo.RoutingVo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrafficMgmtRequest {
	ClusterInfoVo clusterInfoVo;
	String name;
	String gateway;
	List<RoutingVo> routingList;
}
