package com.samsungsds.caasportal.service.vo;

import com.samsungsds.caasportal.common.CommonConst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ClusterInfoVo {
    private String userId;
    private String token;
    private String k8sVersion;
    private String k8sMasterUrl;
    private String k8sMasterCert;
    private Boolean k8sInsecureSkipTlsVerify;
    private String clusterType;
    private String proxyYn;
    private String proxyUrl;
    private String clusterPrefix;
    
	public void setDevSampleData(){
		this.setK8sMasterUrl("https://127.0.0.1:6443");
		this.setK8sInsecureSkipTlsVerify(true);
		this.setK8sMasterCert("");
		this.setUserId("cicd.devops");
		this.setToken(CommonConst.SAMPLE_DEVOPS1_STG_TOKEN);
		this.setK8sVersion("1.19.7");
	}
	
	public void setDefaultData(){
		this.setK8sMasterUrl("https://127.0.0.1:6443");
		this.setK8sInsecureSkipTlsVerify(true);
		this.setK8sMasterCert("");
		this.setUserId("cicd.devops");
		this.setToken(CommonConst.SAMPLE_DEVOPS1_STG_TOKEN);
		this.setK8sVersion("1.19.7");
	}
}
