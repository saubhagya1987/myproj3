package com.versawork.http.service;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

@Component
public interface MedicationManagementService {

	public NsResponse saveMedicatnInfo(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getMedicatnList(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse editMedicatnMgmtInfo(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse deleteMedication(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse editMedicatnReminder(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getFrequencyDesc(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getMedicationMethodDesc() throws BusinessException, SystemException;

	public NsResponse getMedicationKindDesc() throws BusinessException, SystemException;

	public NsResponse getMedicationDosageDesc() throws BusinessException, SystemException;

	public NsResponse saveMedicatnReminder(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getMedicatnReminder(NsRequest nsRequest) throws BusinessException, SystemException;

	public void deleteReminder(Integer medicationManagementId,Integer accountId) throws BusinessException, SystemException;

	public NsResponse saveReminderResponse(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getReminderResponse(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getMedicationImage(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse restoreMedicatnList(NsRequest nsRequest) throws BusinessException, SystemException;
    
	public NsResponse saveMedicationManagement(NsRequest nsRequest) throws BusinessException, SystemException;
	
	public NsResponse getMedicationManagement(NsRequest nsRequest) throws BusinessException, SystemException;
	
	public NsResponse editMedicationManagement(NsRequest nsRequest) throws BusinessException, SystemException;
	
	public NsResponse checkRefillDate(NsRequest nsRequest) throws BusinessException, SystemException;
	
	
}
