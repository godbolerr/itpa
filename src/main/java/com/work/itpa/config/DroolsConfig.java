package com.work.itpa.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.work.itpa.domain.ItpaRule;
import com.work.itpa.domain.RuleData;
import com.work.itpa.domain.RuleTemplate;
import com.work.itpa.repository.ItpaRuleRepository;
import com.work.itpa.repository.RuleDataRepository;
import com.work.itpa.repository.RuleTemplateRepository;
import com.work.itpa.rules.FiConstants;

/**
 * Drools specific bean to take care of rule execution.
 * 
 * @author Developer
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.work" })
public class DroolsConfig {

	private final Logger logger = LoggerFactory.getLogger(DroolsConfig.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	RuleTemplateRepository ruleTemplateRepository;

	@Autowired
	ItpaRuleRepository ruleRepository;

	@Autowired
	RuleDataRepository ruleDataRepository;

	// @Bean
	public KieContainer kieContainer() throws IOException {
		return KieServices.Factory.get().getKieClasspathContainer();
	}

	@Bean
	KieBase getKieBase() {

		// TODO Hardcoded for now. Get this from configuration.

		int assessmentYear = 2017;

		final DataProviderCompiler converter = new DataProviderCompiler();

		StringBuffer sb = new StringBuffer();

		List<RuleTemplate> ruleTemplates = ruleTemplateRepository.findByAssessmentYear(assessmentYear);

		List<ItpaRule> itpaRules = ruleRepository.findByAssessmentYear(assessmentYear);

		KieServices kieServices = KieServices.Factory.get();

		KieModuleModel kieModuleModel = kieServices.newKieModuleModel();

		KieFileSystem kfs = kieServices.newKieFileSystem();

		kfs.writeKModuleXML(kieModuleModel.toXML());

		// Load Rules from the database.

		for (ItpaRule rule : itpaRules) {

			String inMemoryDrlFileName = FiConstants.KIE_RESOURCE_DIR + rule.getRuleId() + FiConstants.KIE_DRL_EXTN;

			kfs.write(inMemoryDrlFileName, kieServices.getResources()
					.newReaderResource(new StringReader(rule.getRuleText())).setResourceType(ResourceType.DRL));

			// logger.info("Loading Rule : " + rule.getRuleText());

		}

		for (RuleTemplate rt : ruleTemplates) {

			DecisionDataProvider tdp = new DecisionDataProvider(ruleDataRepository
					.findByAssessmentYearAndRuleTemplateAndStatus(assessmentYear, rt.getRuleTemplateId(), "active"),
					rt.getCommaSeperatedFields());

			InputStream is = new ByteArrayInputStream(rt.getRuleText().getBytes());

			String rules = converter.compile(tdp, is);

			// TODO Validate rules before adding it to the main collection

			sb.append(rules);
			sb.append(FiConstants.NEWLINE);

			String inMemoryDrlFileName = FiConstants.KIE_RESOURCE_DIR + rt.getRuleTemplateId()
					+ FiConstants.KIE_DRL_EXTN;

			kfs.write(inMemoryDrlFileName, kieServices.getResources().newReaderResource(new StringReader(sb.toString()))
					.setResourceType(ResourceType.DRL));
		}

		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();

		if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
			logger.error(kieBuilder.getResults().toString());
		}
		KieContainer kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		KieBaseConfiguration kbconf = kieServices.newKieBaseConfiguration();
		return kContainer.newKieBase(kbconf);

	}

	public List<RuleData> getDecisionData(int assessmentYear, String ruleTemplate) {

		Query query = new Query();
		query.addCriteria(Criteria.where(FiConstants.COL_RULETEMPPLATE).is(ruleTemplate));
		query.addCriteria(Criteria.where(FiConstants.COL_ASSESSMENT_YEAR).is(assessmentYear));
		query.addCriteria(Criteria.where(FiConstants.COL_STATUS).is(FiConstants.ACTIVE));

		return mongoTemplate.find(query, RuleData.class, FiConstants.DB_COLLECTION_RULEDATA);

	}

	public List<RuleTemplate> getRuleTemplates(int assessmentYear) {

		Query query = new Query();
		query.addCriteria(Criteria.where(FiConstants.COL_ASSESSMENT_YEAR).is(assessmentYear));
		query.addCriteria(Criteria.where(FiConstants.COL_STATUS).is(FiConstants.ACTIVE));

		return mongoTemplate.find(query, RuleTemplate.class, FiConstants.DB_COLLECTION_RULETEMPLATE);

	}

	public List<ItpaRule> getRules(int assessmentYear) {

		Query query = new Query();
		query.addCriteria(Criteria.where(FiConstants.COL_ASSESSMENT_YEAR).is(assessmentYear));
		query.addCriteria(Criteria.where(FiConstants.COL_STATUS).is(FiConstants.ACTIVE));

		return mongoTemplate.find(query, ItpaRule.class, FiConstants.DB_COLLECTION_RULES);

	}

	private class DecisionDataProvider implements DataProvider {

		private Iterator<RuleData> iterator;

		private String commaSeperatedFields;

		DecisionDataProvider(List<RuleData> rows, String commaSeperatedFields) {
			this.iterator = rows.iterator();
			this.commaSeperatedFields = commaSeperatedFields;
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		/**
		 * section residentStatus assesseeType relationShipCode deductionType
		 * minAge maxAge maxDeduction
		 */

		@Override
		public String[] next() {
			RuleData ruleDataItem = iterator.next();
			String[] row = getFields(commaSeperatedFields, ruleDataItem);
			return row;
		}
	}

	/**
	 * TODO Handle exceptions and log the same. LOG NULL VALUES so that we dont
	 * get unexpected results
	 * 
	 * @param commaSeperatedFields
	 * @param ruleData
	 * @return
	 */
	private String[] getFields(String commaSeperatedFields, RuleData ruleData) {

		String[] fields = commaSeperatedFields.split(",");

		String[] dataFields = new String[fields.length];

		for (int i = 0; i < fields.length; i++) {
			String thisField = fields[i];
			try {
				Field f = RuleData.class.getField(thisField);
				if (f != null && f.get(ruleData) != null) {
					String value = f.get(ruleData).toString();
					dataFields[i] = value;
				}
			} catch (NoSuchFieldException | SecurityException e) {
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}

		return dataFields;
	}

}