package com.example.demo.carinspection;

public class SendCarInspectReqDto {
	private String username;
	private String password;
	private String contractNumber;
	private String inspectionStatus;
	private String remark;
	private String policyNumber;
	private String urlPrint;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getUrlPrint() {
		return urlPrint;
	}

	public void setUrlPrint(String urlPrint) {
		this.urlPrint = urlPrint;
	}

}
