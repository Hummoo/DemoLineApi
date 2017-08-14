package com.example.demo.carinspection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarInspectionToSrikrungRespDto {
	private String saveStatus;
	private String resSaveRemark;

	@JsonProperty("SaveStatus")
	public String getSaveStatus() {
		return saveStatus;
	}

	public void setSaveStatus(String saveStatus) {
		this.saveStatus = saveStatus;
	}

	@JsonProperty("ResSaveRemark")
	public String getResSaveRemark() {
		return resSaveRemark;
	}

	public void setResSaveRemark(String resSaveRemark) {
		this.resSaveRemark = resSaveRemark;
	}

}
