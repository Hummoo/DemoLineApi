package com.example.demo.carinspection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarInspectionToSrikrungReqDto {
	private String username;
	private String password;
	private String contractNumber;
	private String inspectStatus;
	private String remark;
	private String createUser;
	private String policyNumber;
	private String urlPrint;

	@JsonProperty("Username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("Password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("ContractNumber")
	public String getContractNumber() {
		return contractNumber;
	}

	@JsonProperty("InspectStatus")
	public String getInspectStatus() {
		return inspectStatus;
	}

	@JsonProperty("Remark")
	public String getRemark() {
		return remark;
	}

	@JsonProperty("Create_User")
	public String getCreateUser() {
		return createUser;
	}

	@JsonProperty("PolicyNumber")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("URLPrint")
	public String getUrlPrint() {
		return urlPrint;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public void setInspectStatus(String inspectStatus) {
		this.inspectStatus = inspectStatus;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public void setUrlPrint(String urlPrint) {
		this.urlPrint = urlPrint;
	}

}
