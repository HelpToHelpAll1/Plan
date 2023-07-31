package com.gaurav.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gaurav.entity.Plan;
import com.gaurav.service.PlanService;


@RestController
public class PlanRestController {

	@Autowired
	public PlanService planService;
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>> planCategories(){
		Map<Integer, String> categories = planService.getPlanCategory();
		
		return new ResponseEntity<>(categories,HttpStatus.OK);
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan){
		String responseMess="";
		
		boolean isSaved = planService.savePlan(plan);
		
		if(isSaved) {
			responseMess = "Plan Saved";
		}else {
			responseMess="Plan Not Saved";
		}
		return new ResponseEntity<>(responseMess,HttpStatus.CREATED);
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans(){
		
		List<Plan> geAllPlanData = planService.geAllPlanData();
		
		return new ResponseEntity<>(geAllPlanData,HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		
		Plan planData = planService.getPlanData(planId);
		return new ResponseEntity<>(planData,HttpStatus.CREATED);
	}
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(Plan plan){
		boolean updatePlanData = planService.updatePlanData(plan);
		
		String msg ="";
		if(updatePlanData) {
			msg = "Plan Updated";
		}else {
			msg="Plan Not Updated";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		boolean plandeleted = planService.deletePlan(planId);
		
		String msg ="";
		if(plandeleted) {
			msg = "Deleted";
		}else { 
			msg="Not Deleted";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId,@PathVariable String status){
		
		String msg = "";
		boolean planStatusChange = planService.planStatusChange(planId,status);
		if(planStatusChange) {
			msg="Status Changed";
		}else {
			msg="Status not changed";
		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
}

