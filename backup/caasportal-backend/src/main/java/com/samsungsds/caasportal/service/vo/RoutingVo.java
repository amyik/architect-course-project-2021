package com.samsungsds.caasportal.service.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutingVo {
	String name;
	String prefix;
	List<HostVo> hosts;
}
