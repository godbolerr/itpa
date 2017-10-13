package com.work.itpa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.work.itpa.domain.ItpaRule;

/**
 * Spring Data MongoDB repository for the ItpaRule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItpaRuleRepository extends MongoRepository<ItpaRule,String> {
	
	List<ItpaRule> findByAssessmentYear(Integer assessmentYear);
    
}
