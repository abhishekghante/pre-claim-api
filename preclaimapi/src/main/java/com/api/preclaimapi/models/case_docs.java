package com.api.preclaimapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "case_docs")
public class case_docs {
	@Id
	@Column
	private int caseId;
	@Column
	private String username;
	@Column
	private String pdf1;
	@Column
	private String pdf2;
	@Column
	private String pdf3;
	@Column
	private String audio;
	@Column
	private String video;
	@Column
	private String sign;

}
