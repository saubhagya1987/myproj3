package com.versawork.http.service;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.Account;
import com.versawork.http.model.PatientUpcomingAppointment;

@Component
public interface AccountService extends Serializable {

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse emailValidation(NsRequest nsRequest) throws BusinessException, SystemException;

	/*
	 * public NsResponse createAccount(NsRequest nsRequest) throws
	 * BusinessException, SystemException;
	 * 
	 * public NsResponse validateUnitNumber(NsRequest nsRequest) throws
	 * BusinessException, SystemException;
	 */
	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse saveAccountPatientAppointment(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getAccountPatientAppointment(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse resetAccountPin(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getPatientLabResult(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getPatientPrescription(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse updateAccountPhone(NsRequest nsRequest) throws BusinessException, SystemException;

	/*
	 * public NsResponse validateUserAccount(NsRequest nsRequest) throws
	 * BusinessException, SystemException;
	 */
	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse updateAccountPin(NsRequest nsRequest) throws BusinessException, SystemException;

	/*
	 * public NsResponse upgradeAccount(NsRequest nsRequest) throws
	 * BusinessException, SystemException;
	 */
	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse appointmentConfirmation(NsRequest nsRequest) throws BusinessException, Exception;

	/**
	 * @param request
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	/*
	 * public NsResponse getConfirmationAppointment(HttpServletRequest request)
	 * throws BusinessException, SystemException;
	 */

	/**
	 * @param patientUpcomingAppointments
	 * @param account
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	NsResponse upcomingAppointmentConfirmation(PatientUpcomingAppointment patientUpcomingAppointments, Account account, String clientDb)
			throws BusinessException, Exception;

	/**
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	NsResponse appointmentConfirmationThread() throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse genRandomAccountPassword(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse updateAccountPhoneNumber(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse updateAccountEmail(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse confirmScheduledAppointment(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getPatientLabGroups(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getPatientLabTestHistory(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	NsResponse getPatientPrescriptionGroups(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getAccountImagingDetails(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getPatientProblems(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse getPatientAllergies(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @param nsRequest
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	public NsResponse savepreferredlanguage(NsRequest nsRequest) throws BusinessException, SystemException;

	/**
	 * @return
	 * @throws BusinessException
	 * @throws SystemException
	 */
	NsResponse pushNotificationThread() throws BusinessException, Exception;

	public NsResponse getPatientImmunalization(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getTestResult(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getTestResultInfo(NsRequest nsRequest)throws BusinessException, SystemException;
}
