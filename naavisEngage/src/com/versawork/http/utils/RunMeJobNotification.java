package com.versawork.http.utils;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

public class RunMeJobNotification extends QuartzJobBean {
	private RunMeTaskNotification runMeTaskNotification;

	public void setRunMeTask(RunMeTaskNotification runMeTaskNotification) {
		this.runMeTaskNotification = runMeTaskNotification;
	}

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		try {
			runMeTaskNotification.pushNotificationJob();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}