package com.api.preclaimapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "case_lists")
public class case_lists {

	@Id
	@Column
	private int caseId;
	@Column
	private String policyNumber;
	@Column
	private String investigationCategory;
	@Column
	private String insuredName;
	@Column
	private String insuredDOD;
	@Column
	private String insuredDOB;
	@Column
	private int sumAssured;
	@Column
	private String intimationType;
	@Column
	private String claimantCity;
	@Column
	private String claimantState;
	@Column
	private String claimantZone;
	@Column
	private String caseStatus;
	@Column
	private String caseSubStatus;
	@Column
	private String nominee_name;
	@Column
	private String nomineeContactNumber;
	@Column
	private String nominee_address;
	@Column
	private String insured_address;
	
	
	
}
