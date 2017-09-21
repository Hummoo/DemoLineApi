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

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("contractnumber")
	public String getContractNumber() {
		return contractNumber;
	}

	@JsonProperty("inspectstatus")
	public String getInspectStatus() {
		return inspectStatus;
	}

	@JsonProperty("remark")
	public String getRemark() {
		return remark;
	}

	@JsonProperty("create_user")
	public String getCreateUser() {
		return createUser;
	}

	@JsonProperty("policynumber")
	public String getPolicyNumber() {
		return policyNumber;
	}

	@JsonProperty("urlprint")
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
