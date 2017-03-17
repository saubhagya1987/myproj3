package com.versawork.http.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.versawork.http.dataobject.AllergiesInfo;
import com.versawork.http.dataobject.CarePlanInfo;
import com.versawork.http.dataobject.CateTeamInfo;
import com.versawork.http.dataobject.LabResultInfo;
import com.versawork.http.dataobject.MedicationInfo;
import com.versawork.http.dataobject.NsRequest;
import com.versawork.http.dataobject.NsResponse;
import com.versawork.http.dataobject.PatientInpatientDiagnosisInfo;
import com.versawork.http.dataobject.PatientInpatientFunctionalStatusInfo;
import com.versawork.http.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.http.dataobject.PatientInpatientMetadataInfo;
import com.versawork.http.dataobject.PatientProblemInfo;

import com.versawork.http.dataobject.ProcedureInfo;
import com.versawork.http.dataobject.VitalSigns;
import com.versawork.http.exception.BusinessException;
import com.versawork.http.exception.SystemException;

/**
 * @author Dheeraj
 * 
 */

@Component
public interface ViewPatientDetailsService {

	public NsResponse downloadMuData(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse viewPatientDetails(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<CateTeamInfo> getProviderList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<AllergiesInfo> getAllergiesList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<MedicationInfo> getMedicationList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<PatientProblemInfo> getProblemList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<ProcedureInfo> getProcedureList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<LabResultInfo> getLabResultList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<CarePlanInfo> getCarePlanList(NsRequest nsRequest) throws BusinessException, SystemException;

	public PatientInpatientMetadataInfo getInpatientMetaData(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<PatientInpatientDiagnosisInfo> getInpatientDiagnosisList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<PatientInpatientFunctionalStatusInfo> getInpatientFunctionalStatusList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<PatientInpatientImmunalizationInfo> getInpatientImmunalizationList(NsRequest nsRequest) throws BusinessException, SystemException;

	public List<VitalSigns> getVitalSigns(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getInpatientHospitalisationReason(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse transmitEHR(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse transmitEHRActivityLog(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse accessHistory(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse vdtReporting(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse vdtReportingAccessHistory(NsRequest nsRequest) throws BusinessException, SystemException;

	public NsResponse getPatientInformation(NsRequest nsRequest)throws BusinessException, SystemException;

	public NsResponse getVisitDetails(NsRequest nsRequest)throws BusinessException, SystemException;
    
	public PatientInpatientMetadataInfo getpatientMetaData(NsRequest nsRequest) throws BusinessException, SystemException;
	
	
}
