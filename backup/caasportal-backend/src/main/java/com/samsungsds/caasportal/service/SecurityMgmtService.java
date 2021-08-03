package com.samsungsds.caasportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samsungsds.caasportal.common.ApiService;
import com.samsungsds.caasportal.common.BaseRes;
import com.samsungsds.caasportal.common.CommonConst;
import com.samsungsds.caasportal.controller.dto.TrafficMgmtRequest;
import com.samsungsds.caasportal.service.vo.ObjectVo;
import com.samsungsds.caasportal.service.vo.YamlVo;
import com.samsungsds.caasportal.yamlgenerator.*;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SecurityMgmtService {

	@Autowired private ApiService<Object> apiService;
	
	@Value("${provisioner.url}") private String provisionURL;
	
	public ResponseEntity<BaseRes> getObjects(String clusterId, String namespace, String object, ObjectVo objectVo) {
		String url = String.format("%s/namespace/%s/objects/%s", provisionURL, namespace, object);
		ResponseEntity<Object> res = apiService.post(url, objectVo); 
		log.info(res.toString());
		return new ResponseEntity<>(new BaseRes(CommonConst.RST_SUCCESS, res), HttpStatus.OK);
	}

	public ResponseEntity<BaseRes> createRouting(String clusterId, String namespace, TrafficMgmtRequest trafficMgmtRequest) {
		String url = String.format("%s/namespace/%s/apply", provisionURL, namespace);
		YamlVo yamlVo = new YamlVo();
		K8sYaml k8sYaml = YamlFactory.createK8sYaml(CommonConst.TEMP_VIRTUAL, trafficMgmtRequest);
		k8sYaml.generateYaml();
		yamlVo.setObjectInfo(trafficMgmtRequest.getClusterInfoVo());
		yamlVo.setYaml(k8sYaml.getStrYaml());
		ResponseEntity<Object> res = apiService.post(url, yamlVo); 
		log.info(res.toString());
		return null;
	}
	public ResponseEntity<BaseRes> updateMtlsStatus(String clusterId, String namespace, Boolean status, ObjectVo objectVo) {
		String lable = status?"kiali-enabled=true":"kiali-enabled=false";
		String url = String.format("%s/namespace/%s/label/%s", provisionURL, namespace, lable);
		ResponseEntity<Object> res = apiService.post(url, objectVo); 
		log.info(res.toString());
		return new ResponseEntity<>(new BaseRes(CommonConst.RST_SUCCESS, res), HttpStatus.OK);
	}
}
