package com.example.demo.carinspection;

public class SendCarInspectReqDto {
	private String transactionId;
	private String inspectionResult;
	private String inspectionReason;
	private String policyNo;
	private String urlPrint;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getInspectionResult() {
		return inspectionResult;
	}

	public void setInspectionResult(String inspectionResult) {
		this.inspectionResult = inspectionResult;
	}

	public String getInspectionReason() {
		return inspectionReason;
	}

	public void setInspectionReason(String inspectionReason) {
		this.inspectionReason = inspectionReason;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getUrlPrint() {
		return urlPrint;
	}

	public void setUrlPrint(String urlPrint) {
		this.urlPrint = urlPrint;
	}
}
