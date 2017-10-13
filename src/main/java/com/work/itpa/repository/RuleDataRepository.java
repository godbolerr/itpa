package com.work.itpa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.work.itpa.domain.RuleData;

/**
 * Spring Data MongoDB repository for the RuleTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RuleDataRepository extends MongoRepository<RuleData,String> {
	
	List<RuleData> findByAssessmentYear(Integer assessmentYear);
	
	List<RuleData> findByAssessmentYearAndRuleTemplateAndStatus(Integer assessmentYear,String ruleTemplate,String status);
    
}
