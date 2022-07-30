package com.suresh.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.suresh.bindings.PlanResponse;
import com.suresh.bindings.SearchRequest;
import com.suresh.entity.InsurancePlans;
import com.suresh.service.InsuranceService;
import com.suresh.util.UserExcelExporter;
import com.suresh.util.UserPDFExporter;

@RestController
public class InsuranceController {

	@Autowired
	private InsuranceService service;

	@GetMapping("/filter")
	public ResponseEntity<List<PlanResponse>> getPlans(SearchRequest request) {

		List<PlanResponse> searchPlan = service.searchPlan(request);

		return new ResponseEntity<>(searchPlan, HttpStatus.OK);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames() {
		return new ResponseEntity<>(service.getUniquePlanNames(), HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<List<String>> getPlanStatus() {
		return new ResponseEntity<>(service.getUniquePlanStatus(), HttpStatus.OK);
	}

	@GetMapping("/plans/export/pdf")
	public void exportToPDF(HttpServletResponse response,SearchRequest request) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> listPlans = service.searchPlan(request);

		UserPDFExporter exporter = new UserPDFExporter(listPlans);
		exporter.export(response);
	}

	@GetMapping("/users/export/excel")
	public void exportToExcel(HttpServletResponse response,SearchRequest request) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> listPlans = service.searchPlan(request);

		UserExcelExporter excelExporter = new UserExcelExporter(listPlans);

		excelExporter.export(response);
	}

}

/*
 * @GetMapping("/distinctplans") public List<String> findPlan() { List<String>
 * list = service.findByPlanName(); return list; }
 * 
 * @GetMapping("/planstatus/{status}") public List<InsurancePlans>
 * getPlanStatus(@PathVariable("status") String status) { return
 * service.findByPlanStatus(status); }
 * 
 * @GetMapping("/planstatus/{planName}/{status}") public List<InsurancePlans>
 * getPlanStatus(@PathVariable("planName") String planName,
 * 
 * @PathVariable("status") String status) { return
 * service.findByPlanNameAndPlanStatus(planName, status); }
 */