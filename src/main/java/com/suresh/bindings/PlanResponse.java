package com.suresh.bindings;

import lombok.Data;

@Data
public class PlanResponse {

	private Integer planId;
	private String planName;
	private String holderName;
	private Integer holderSsn;
	private String planStatus;
}
