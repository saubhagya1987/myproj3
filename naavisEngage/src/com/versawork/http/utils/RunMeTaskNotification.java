package com.versawork.http.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.service.AccountService;

public class RunMeTaskNotification {

	@Autowired
	private AccountService accountServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(RunMeTaskNotification.class);

	public void pushNotificationJob() throws BusinessException, Exception {
		NsResponse nsResponse = accountServiceImpl.pushNotificationThread();
		if (nsResponse == null) {
			LOGGER.info("No new notifications to push");
		} else {
			LOGGER.info("Completed notification Thread");
		}
	}
}