package com.suresh.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.suresh.bindings.PlanResponse;
import com.suresh.bindings.SearchRequest;
import com.suresh.entity.InsurancePlans;
import com.suresh.repository.insurancePlansRepository;
import com.suresh.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private insurancePlansRepository repository;

	@Override
	public List<PlanResponse> searchPlan(SearchRequest request) {

		InsurancePlans entity = new InsurancePlans();

		// dynamic QueryBuilding

		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}

		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		Example<InsurancePlans> of = Example.of(entity);

		List<InsurancePlans> findAll = repository.findAll(of);

		// we are returning List<PlanRespose> create PlanResponse object and add it to
		// the List

		List<PlanResponse> planResponseList = new ArrayList<>();

		for (InsurancePlans plan : findAll) {
			PlanResponse response = new PlanResponse();
			BeanUtils.copyProperties(plan, response);
			planResponseList.add(response);
		}

		return planResponseList;
	}

	@Override
	public List<String> getUniquePlanNames() {

		return repository.getDistinctPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {

		return repository.getDistinctPlanStatus();
	}

}

/*
 * @Override public List<String> findByPlanName() {
 * 
 * return repository.findByPlanName(); }
 * 
 * @Override public List<InsurancePlans> findByPlanStatus(String status) {
 * return repository.findByPlanStatus(status); }
 * 
 * public List<InsurancePlans> findByPlanNameAndPlanStatus(String planName,
 * String status) { return repository.findByPlanNameAndPlanStatus(planName,
 * status); }
 * 
 * @Override public List<InsurancePlans> getAllPlans() { return
 * repository.findAll(); }
 */