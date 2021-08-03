package com.samsungsds.caasportal.service.vo;

public class ObjectVo extends ClusterInfoVo{

	private boolean showLabels;
	private String labels;
	
	public boolean isShowLabels() {
		return showLabels;
	}
	public void setShowLabels(boolean showLabels) {
		this.showLabels = showLabels;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	
	@Override
	public void setDefaultData() {
		super.setDefaultData();
		this.showLabels = true;
	}
}
