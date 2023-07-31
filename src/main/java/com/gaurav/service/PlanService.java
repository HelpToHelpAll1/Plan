package com.gaurav.service;

import java.util.List;
import java.util.Map;

import com.gaurav.entity.Plan;

public interface PlanService {
	public Map<Integer,String> getPlanCategory();
	
	public boolean savePlan(Plan plan);
	
	public List<Plan> geAllPlanData();
	
	public Plan getPlanData(Integer planId);
	
	public boolean updatePlanData(Plan plan);
	
	public boolean deletePlan(Integer planId);
	
	public boolean planStatusChange(Integer planId,String status);
}
