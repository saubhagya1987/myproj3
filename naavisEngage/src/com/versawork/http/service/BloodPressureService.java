package com.versawork.http.service;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.PatientStepCountInfo;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;
import com.versawork.http.model.PatientActiveTime;
import com.versawork.http.model.PatientCalorieCount;
import com.versawork.http.model.PatientHeartAge;
import com.versawork.http.model.PatientHeartRate;
import com.versawork.http.model.PatientSleep;

@Component
public interface BloodPressureService extends Serializable {

	public NsResponse saveBloodPressure(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getBloodPressure(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse editBloodPressure(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse deleteBloodPressure(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse saveBloodPressureReminder(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getBloodPressureReminders(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse deleteBloodPressureReminder(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse editBloodPressureReminder(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse saveBloodPressureResponse(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse restoreBloodPressureReminders(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getHealthLog(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse addWeight(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse getWeight(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse deleteWeight(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse updateWeight(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse getPatientOverallStress(NsRequest nsRequest)throws BusinessException, SystemException;
	
	public NsResponse getPatientActiveTime(NsRequest nsRequest)throws BusinessException, SystemException;
	
	public NsResponse getPatientCalorieCount(NsRequest nsRequest)throws BusinessException, SystemException;
	
	public NsResponse getPatientHeartRate(NsRequest nsRequest)throws BusinessException, SystemException;
	
	public NsResponse getPatientHeartAge(NsRequest nsRequest)throws BusinessException, SystemException;
	
	public NsResponse getPatientStepCount(NsRequest nsRequest)throws BusinessException, SystemException;
	
	public NsResponse getPatientSleep(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse getPatientDistance(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse savePatientOverallStress(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse savePatientHeartRate(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse getMyHealth(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse savePatientHeartAge(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse savePatientStepCount(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse savePatientCalorieCount(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse savePatientSleep(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse savePatientActiveTime(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse savePatientDistance(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse getMyHealthInfo(NsRequest nsRequest)throws BusinessException, SystemException;

	

	
	
}
