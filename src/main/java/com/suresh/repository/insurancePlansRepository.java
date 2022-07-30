package com.suresh.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.suresh.entity.InsurancePlans;

public interface insurancePlansRepository extends JpaRepository<InsurancePlans, Integer> {

	@Query("select distinct planName from InsurancePlans")
	public List<String> getDistinctPlanNames();
	
	@Query("select distinct planStatus from InsurancePlans")
	public List<String> getDistinctPlanStatus();
}










/*
 * @Query(nativeQuery = true , value =
 * "SELECT DISTINCT plan_name FROM INSURANCE_PLANS") public List<String>
 * findByPlanName();
 * 
 * @Query(nativeQuery = true , value =
 * "SELECT * FROM INSURANCE_PLANS Insur WHERE Insur.plan_status=:status") public
 * List<InsurancePlans> findByPlanStatus(String status);
 * 
 * public List<InsurancePlans> findByPlanNameAndPlanStatus(String
 * planName,String status);
 */