package com.fsiautism_4.springboot.model;

import java.util.Date;

public class Sessions {
	private int patientId;
	private Date sessionDate;
	private String assetId;
	private String blobUri;
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public Date getSessionDate() {
		return sessionDate;
	}
	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getBlobUri() {
		return blobUri;
	}
	public void setBlobUri(String blobUri) {
		this.blobUri = blobUri;
	}
}
