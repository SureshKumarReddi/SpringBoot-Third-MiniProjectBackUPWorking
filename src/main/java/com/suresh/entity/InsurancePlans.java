package com.suresh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "INSURANCE_PLANS")
@Entity
@Data
public class InsurancePlans {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAN_ID")
	private Integer planId;
	@Column(name = "PLAN_NAME")
	private String planName;
	@Column(name = "HOLDER_NAME")
	private String holderName;
	@Column(name = "HOLDER_SSN")
	private Integer holderSsn;
	@Column(name = "PLAN_STATUS")
	private String planStatus;

}
