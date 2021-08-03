package com.samsungsds.caasportal.yamlgenerator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class K8sYaml {
	 Object sourceObject;
	 String path;
	 String strYaml;
	 
	 public abstract void generateYaml();
	 
	 public void setPathNInput(Object sourceObject, String path){
		 this.sourceObject = sourceObject;
	 	 this.path = path;
	 }
}
