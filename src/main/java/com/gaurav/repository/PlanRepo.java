package com.gaurav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gaurav.entity.Plan;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
