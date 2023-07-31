package com.gaurav.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaurav.entity.Plan;
import com.gaurav.entity.PlanCategory;
import com.gaurav.repository.PlanCategoryRepo;
import com.gaurav.repository.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {
	@Autowired
	public PlanCategoryRepo planCategoryRepo;
	
	@Autowired
	public PlanRepo planRepo;
	
	 @Override
	    public Map<Integer, String> getPlanCategory() {
	        List<PlanCategory> categories = planCategoryRepo.findAll();
	        Map<Integer, String> categoryMap = new HashMap<>();

	        for (PlanCategory category : categories) {
	            categoryMap.put(category.getCategoryId(), category.getCategoryName());
	        }

	        return categoryMap;
	    }

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved = planRepo.save(plan);
		
//		if(saved.getPlanId() !=null) {
//			return true;
//		}else {
//			return false;
//		}
		
//		return saved.getPlanId() !=null ? true : false;
		
		return saved.getPlanId() !=null;
	}

	@Override
	public List<Plan> geAllPlanData() {
		List<Plan> plans = planRepo.findAll();
		return plans;
	}

	@Override
	public Plan getPlanData(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlanData(Plan plan) {
		Plan saved = planRepo.save(plan);   //Upsert   update if primary key present
		return plan.getPlanId() !=null;
	}
 
	@Override
	public boolean deletePlan(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			return status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			Plan plan = findById.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}
	
}
