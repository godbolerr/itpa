package com.work.itpa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.work.itpa.domain.RuleTemplate;

/**
 * Spring Data MongoDB repository for the RuleTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RuleTemplateRepository extends MongoRepository<RuleTemplate,String> {
	
	List<RuleTemplate> findByAssessmentYear(Integer assessmentYear);
    
}
