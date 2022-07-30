package com.suresh.service;

import java.util.List;

import com.suresh.bindings.PlanResponse;
import com.suresh.bindings.SearchRequest;

public interface InsuranceService {

 
	public List<PlanResponse> searchPlan(SearchRequest request);
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatus();
}



/*
 * public List<String> findByPlanName();
 * 
 * public List<InsurancePlans> findByPlanStatus(String status);
 * 
 * public List<InsurancePlans> findByPlanNameAndPlanStatus(String
 * planName,String status);
 * 
 * public List<InsurancePlans> getAllPlans();
 */

//by using this we will filter everything ussing below one method..
//which follows dynamic serach instead of writing findBy methods