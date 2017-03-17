package com.versawork.http.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.WarningMessages;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface NotificationService extends Serializable {

	public NsResponse getBloodPressureReminderResponse(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getMedicationReminderResponse(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getPatientScheduledAppointments(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getPatientConfirmedAppointments(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<WarningMessages> getBloodPressureNotificationInactiveAlert(NsRequest nsRequest)
			throws BusinessException, SystemException;

	public List<WarningMessages> getMedicationNotificationInactiveAlert(NsRequest nsRequest) throws BusinessException,
			SystemException;

	public NsResponse getWarningMessages(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getNotificationsCount(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse setViewedTrue(NsRequest nsRequest) throws BusinessException, SystemException;

	NsResponse getFutureScheduledAppointments(NsRequest nsRequest) throws BusinessException, SystemException;

	NsResponse getFuturePatientConfirmedAppointments(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse setNotificationViewedTrue(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getWarningMessagesOnLogin(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse flushNotificationsForAndroid(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getPatientAppointments(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse loadMorePatientAppointments(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse searchDetails(NsRequest nsRequest)throws BusinessException, SystemException;
}
