package com.samsungsds.caasportal.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class YamlVo extends ClusterInfoVo{
    private String yaml;
    
    public String getYaml() {
    	return this.yaml;
    }
    
    public void setYaml(String yaml) {
    	this.yaml = yaml;
    }

	public void setObjectInfo(ClusterInfoVo clusterInfoVo) {
		this.setClusterPrefix(clusterInfoVo.getClusterPrefix());
		this.setClusterType(clusterInfoVo.getClusterType());
		this.setK8sInsecureSkipTlsVerify(clusterInfoVo.getK8sInsecureSkipTlsVerify());
		this.setK8sMasterCert(clusterInfoVo.getK8sMasterCert());
		this.setK8sMasterUrl(clusterInfoVo.getK8sMasterUrl());
		this.setK8sVersion(clusterInfoVo.getK8sVersion());
		this.setUserId(clusterInfoVo.getUserId());
		this.setToken(clusterInfoVo.getToken());
		this.setProxyUrl(clusterInfoVo.getProxyUrl());
		this.setProxyYn(clusterInfoVo.getProxyYn());
	}
}
