package com.samsungsds.caasportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.samsungsds.caasportal.common.BaseRes;
import com.samsungsds.caasportal.common.CommonConst;
import com.samsungsds.caasportal.service.SecurityMgmtService;
import com.samsungsds.caasportal.service.vo.ObjectVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/policy")
public class SecurityMgmtController {
	@Autowired SecurityMgmtService securtiyMgmtService;

	@ApiOperation(value="get Gateways by clusterinfo")
	@ApiResponses({
		@ApiResponse(code = 200, message = CommonConst.OK),
		@ApiResponse(code = 500, message = CommonConst.INTERNAL_SERVER_ERROR)
	})
	@RequestMapping(value="/{cluster-id}/{namespace}/{status}", method=RequestMethod.POST)
	public ResponseEntity<BaseRes> updateMtlsStatus(@PathVariable("cluster-id") String clusterId, @PathVariable("namespace") String namespace, @PathVariable("status") Boolean status, @RequestBody ObjectVo objectVo){
		return securtiyMgmtService.updateMtlsStatus(clusterId, namespace, status, objectVo);
	} 
}
