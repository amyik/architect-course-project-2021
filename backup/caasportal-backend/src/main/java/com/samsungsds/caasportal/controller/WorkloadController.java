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
import com.samsungsds.caasportal.service.WorkloadService;
import com.samsungsds.caasportal.service.vo.ObjectVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/workload")
public class WorkloadController {
	@Autowired WorkloadService workloadService;
	
	@ApiOperation(value="Workload list 조회 (실시간 상태값 제외)")
	@ApiResponses({
		@ApiResponse(code = 200, message = CommonConst.OK),
		@ApiResponse(code = 500, message = CommonConst.INTERNAL_SERVER_ERROR)
	})
	@RequestMapping(value="/{cluster-id}/{namespace}/workloads", method=RequestMethod.GET)
	public ResponseEntity<BaseRes> getWorkloads(@PathVariable("cluster-id") String clusterId, @PathVariable("namespace") String namespace, @RequestBody ObjectVo objectVo){
		return workloadService.getWorkloads(clusterId, namespace, objectVo);
	}
	
	@ApiOperation(value="Workload list 조회 (실시간 상태값)")
	@ApiResponses({
		@ApiResponse(code = 200, message = CommonConst.OK),
		@ApiResponse(code = 500, message = CommonConst.INTERNAL_SERVER_ERROR)
	})
	@RequestMapping(value="/{cluster-id}/{namespace}/workloads/realtime", method=RequestMethod.GET)
	public ResponseEntity<BaseRes> getRealtimeWorkloadStatus(@PathVariable("cluster-id") String clusterId, @PathVariable("namespace") String namespace, @RequestBody ObjectVo objectVo){
		return workloadService.getRealtimeWorkloadStatus(clusterId, namespace, objectVo);
	}
}
