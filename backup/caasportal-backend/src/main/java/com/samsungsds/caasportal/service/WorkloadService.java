package com.samsungsds.caasportal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.samsungsds.caasportal.common.BaseRes;
import com.samsungsds.caasportal.service.vo.ObjectVo;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkloadService {

    private final ProvisionerService provisionerService;

    public void exmapleMehtod() {
        log.debug("lombok logging test:{}", "hihihi");
    }

	public ResponseEntity<BaseRes> getWorkloads(String clusterId, String namespace, ObjectVo objectVo) {
		//TO DO: 클러스터내에 워크로드 리스트를 실시간 k8s 상태를 조회하는 로직을 제외하고 조회 (단순 DB의 값 조회)
		return null;
	}

	public ResponseEntity<BaseRes> getRealtimeWorkloadStatus(String clusterId, String namespace, ObjectVo objectVo) {
		//TO DO: 클러스터내에 워크로드 리스트에 대해 실시간 상태를 포함해서 조회 (caas provisioner 호출)
		return null;
	}
}
