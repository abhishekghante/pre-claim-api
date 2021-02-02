package com.api.preclaimapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "case_detail")
public class case_detail {

	@Id
	@Column
	private int caseId;
	@Column
	private String policyNumber;
	@Column
	private String username;
	@Column
	private String longitude;
	@Column
	private String latitude;
	@Column
	private String description;
	@Column
	private String createdDate;

	public case_detail(int caseId, String policyNumber, String username, String longitude, String latitude,
			String description, String createdDate) {
		super();
		this.caseId = caseId;
		this.policyNumber = policyNumber;
		this.username = username;
		this.longitude = longitude;
		this.latitude = latitude;
		this.description = description;
		this.createdDate = createdDate;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "caseDetails [caseId=" + caseId + ", policyNumber=" + policyNumber + ", username=" + username
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", description=" + description
				+ ", createdDate=" + createdDate + "]";
	}
}
