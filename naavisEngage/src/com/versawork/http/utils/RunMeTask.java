package com.versawork.http.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.service.AccountService;

public class RunMeTask {

	@Autowired
	private AccountService accountServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(RunMeTask.class);

	public void appointmentConfirmationJob() throws BusinessException, SystemException {

		NsResponse nsResponse = accountServiceImpl.appointmentConfirmationThread();
		if (nsResponse == null) {
			LOGGER.info("No new Appointments to push");
		} else {
			LOGGER.info("Completed Appointment Confirmation Thread");
		}
	}
}