package com.samsungsds.caasportal.yamlgenerator;

import com.samsungsds.caasportal.common.CommonConst;

public final class YamlFactory {
	public static K8sYaml createK8sYaml(String path, Object vo) {
		K8sYaml k8sYaml = null;
		switch (path) {
			case CommonConst.TEMP_VIRTUAL:
				k8sYaml = new VirtualServiceYaml();
				break;
			case CommonConst.TEMP_DEST:
				k8sYaml = new DestinationYaml();
			default:
				break;
		}
		k8sYaml.setPathNInput(vo, path);
		return k8sYaml;
	}
}
