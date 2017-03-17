package com.versawork.http.validation;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.ConsequenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public class RequestValidator {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidator.class);

	private StatefulKnowledgeSession initializeKnowledgeSession(NsRequest nsRequest) throws BusinessException {
		KnowledgeBase kBase = ValidationEngine.createKnowledgeBase();
		StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession();
		kSession.setGlobal("LOGGER", LOGGER);
		kSession.insert(nsRequest);
		return kSession;
	}

	public void performBusinessRuleValidations(NsRequest nsRequest) throws BusinessException, SystemException {
		// Perform Business Rule Validations try{ kSession =
		StatefulKnowledgeSession kSession = initializeKnowledgeSession(nsRequest);
		
		try {
			kSession.getAgenda().getAgendaGroup("unitNumberValidtion").setFocus();
			kSession.fireAllRules();
			kSession.dispose();
		} catch (ConsequenceException consequenceException) {
			Throwable throwable = consequenceException.getCause();
			if (throwable instanceof BusinessException) {
				throw new BusinessException(((BusinessException) throwable).getExceptionCode());
			}
		} catch (Exception exception) {
			throw new SystemException(exception);
		}
	}
}
