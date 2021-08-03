package com.samsungsds.caasportal.yamlgenerator;

import java.util.HashMap;
import java.util.Map;

import com.samsungsds.caasportal.common.CommonConst;
import com.samsungsds.caasportal.common.CommonUtil;
import com.samsungsds.caasportal.controller.dto.TrafficMgmtRequest;

import lombok.extern.log4j.Log4j2;

@Log4j2
class VirtualServiceYaml extends K8sYaml {
	@Override
	public void generateYaml() {
		TrafficMgmtRequest trafficMgmtRequest = (TrafficMgmtRequest) this.sourceObject;
		Map<String, String> var = new HashMap<String, String>();
		var.put("kind", CommonConst.OBJ_VIRTUALSERVICE); 
		var.put("name", trafficMgmtRequest.getName());
		var.put("gateway", trafficMgmtRequest.getGateway()); 
		for(int i = 0; i < trafficMgmtRequest.getRoutingList().size() ; i++) {
			var.put("httpname"+CommonUtil.addUnderB(i), trafficMgmtRequest.getRoutingList().get(i).getName()); 
			var.put("prefix"+CommonUtil.addUnderB(i), trafficMgmtRequest.getRoutingList().get(i).getPrefix()); 
			for(int j = 0; j < trafficMgmtRequest.getRoutingList().get(i).getHosts().size() ; j++) {
				var.put("host"+CommonUtil.addUnderB(i)+CommonUtil.addUnderB(j), 
						trafficMgmtRequest.getRoutingList().get(i).getHosts().get(j).getServiceId()); 
				var.put("weight"+CommonUtil.addUnderB(i)+CommonUtil.addUnderB(j), 
						trafficMgmtRequest.getRoutingList().get(i).getHosts().get(j).getWeight()); 
				
			}
		}
		this.strYaml = VelocityUtil.getConvertedStr(this.path, var);
		log.info("generated password" + this.strYaml);
	}
}
