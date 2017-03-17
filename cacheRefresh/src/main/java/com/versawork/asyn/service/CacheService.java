/**
 * 
 */
package com.versawork.asyn.service;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.versawork.asyn.constant.CacheModule;
import com.versawork.asyn.constant.LogFile;
import com.versawork.asyn.dao.EtlLoggerDaoImpl;
import com.versawork.asyn.dao.FacilityNoticeDaoImpl;
import com.versawork.asyn.dao.FacilityProviderDaoImpl;
import com.versawork.asyn.dao.FacilityServiceDaoImpl;
import com.versawork.asyn.dao.PatientAllergyDaoImpl;
import com.versawork.asyn.dao.PatientCarePlanDaoImpl;
import com.versawork.asyn.dao.PatientCareTeamDaoImpl;
import com.versawork.asyn.dao.PatientDiagnosisDaoImpl;
import com.versawork.asyn.dao.PatientFunctionalStatusDaoImpl;
import com.versawork.asyn.dao.PatientImagingDaoImpl;
import com.versawork.asyn.dao.PatientImmunizationDaoImpl;
import com.versawork.asyn.dao.PatientLabDaoImpl;
import com.versawork.asyn.dao.PatientPrescriptionDaoImpl;
import com.versawork.asyn.dao.PatientProblemDaoImpl;
import com.versawork.asyn.dao.PatientProcedureDaoImpl;
import com.versawork.asyn.dao.PatientVisitDaoImpl;
import com.versawork.asyn.dao.PatientVisitInpatientDaoImpl;
import com.versawork.asyn.dao.PatientVitalSignDaoImpl;
import com.versawork.asyn.dataobject.AllergiesInfo;
import com.versawork.asyn.dataobject.CarePlanInfo;
import com.versawork.asyn.dataobject.CareTeamInfo;
import com.versawork.asyn.dataobject.LabResultInfo;
import com.versawork.asyn.dataobject.MedicationInfo;
import com.versawork.asyn.dataobject.NsPatientLabResult;
import com.versawork.asyn.dataobject.NsPatientPrescription;
import com.versawork.asyn.dataobject.NsPatientVisit;
import com.versawork.asyn.dataobject.PatientImagingInfo;
import com.versawork.asyn.dataobject.PatientInpatientDiagnosisInfo;
import com.versawork.asyn.dataobject.PatientInpatientFunctionalStatusInfo;
import com.versawork.asyn.dataobject.PatientInpatientImmunalizationInfo;
import com.versawork.asyn.dataobject.PatientInpatientMetadataInfo;
import com.versawork.asyn.dataobject.PatientProblemInfo;
import com.versawork.asyn.dataobject.ProcedureInfo;
import com.versawork.asyn.dataobject.VitalSigns;
import com.versawork.asyn.module.FacilityNoticeModule;
import com.versawork.asyn.module.FacilityProviderModule;
import com.versawork.asyn.module.FacilityServiceModule;
import com.versawork.asyn.module.InPatientMetaDataModule;
import com.versawork.asyn.module.Module;
import com.versawork.asyn.module.PatientAllergiesModule;
import com.versawork.asyn.module.PatientCarePlanModule;
import com.versawork.asyn.module.PatientCareTeamModule;
import com.versawork.asyn.module.PatientDiagnosisModule;
import com.versawork.asyn.module.PatientFunctionalStatusModule;
import com.versawork.asyn.module.PatientImagingModule;
import com.versawork.asyn.module.PatientImmunizationModule;
import com.versawork.asyn.module.PatientLabResultModule;
import com.versawork.asyn.module.PatientPrescriptionModule;
import com.versawork.asyn.module.PatientProblemModule;
import com.versawork.asyn.module.PatientProcedureModule;
import com.versawork.asyn.module.PatientVisitModule;
import com.versawork.asyn.module.PatientVitalSignModule;
import com.versawork.http.caching.files.CacheAccessbyJson;
import com.versawork.http.model.EtlLogger;
import com.versawork.http.model.FacilityNotice;
import com.versawork.http.model.FacilityProvider;
import com.versawork.http.model.FacilityService;

/**
 * @author RAHUL BHALLA
 *
 */
@Service
public class CacheService {
	private static Logger LOGGER = LoggerFactory.getLogger(LogFile.ETL_CACHE_LOG_FILE.getFileName());
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private EtlLoggerDaoImpl etlLoggerDao;
	@Autowired
	private CacheAccessbyJson<AllergiesInfo> cacheAccessForAllergies;
	@Autowired
	private CacheAccessbyJson<FacilityNotice> cacheAccessForFacilityNotice;
	@Autowired
	private CacheAccessbyJson<FacilityProvider> cacheAccessForFacilityProvider;
	@Autowired
	private CacheAccessbyJson<FacilityService> cacheAccessForFacilityService;
	@Autowired
	private CacheAccessbyJson<CarePlanInfo> cacheAccessForCarePlanInfo;
	@Autowired
	private CacheAccessbyJson<CareTeamInfo> cacheAccessForCareTeamInfo;
	@Autowired
	private CacheAccessbyJson<PatientInpatientDiagnosisInfo> cacheAccessForDiagnosisInfo;
	@Autowired
	private CacheAccessbyJson<PatientInpatientFunctionalStatusInfo> cacheAccessbyJsonFunctionalStatusInfo;
	@Autowired
	private CacheAccessbyJson<PatientImagingInfo> cacheAccessForPatientImagingInfo;
	@Autowired
	private CacheAccessbyJson<PatientInpatientImmunalizationInfo> cacheAccessForImmunalizationInfo;
	@Autowired
	private CacheAccessbyJson<LabResultInfo> cacheAccessForLabResultInfo;
	@Autowired
	private CacheAccessbyJson<MedicationInfo> cacheAccessForMedicationInfo;
//	@Autowired
//	private CacheAccessbyJson<NsPatientPrescription> cacheAccessForMedicationInfo;
	@Autowired
	private CacheAccessbyJson<NsPatientPrescription> cacheAccessForNsPatientPrescription;
	@Autowired
	private CacheAccessbyJson<PatientInpatientMetadataInfo> cacheAccessInpatientMetadataInfo;
	@Autowired
	private CacheAccessbyJson<PatientProblemInfo> cacheAccessForProblemInfo;
	@Autowired
	private CacheAccessbyJson<ProcedureInfo> cacheAccessForProcedureInfo;
	@Autowired
	private CacheAccessbyJson<VitalSigns> cacheAccessVitalSigns;
	@Autowired
	private CacheAccessbyJson<NsPatientVisit> cacheAccessForNsPatientVisit;
	@Autowired
	private CacheAccessbyJson<NsPatientLabResult> cacheAccess;
	
	@Autowired
	private PatientAllergyDaoImpl cacheRefreshDaoPatientAllergy;
	@Autowired
	private FacilityNoticeDaoImpl cacheRefreshDaoFacilityNotice;
	@Autowired
	private FacilityProviderDaoImpl cacheRefreshDaoFacilityProvider;
	@Autowired
	private FacilityServiceDaoImpl cacheRefreshDaoFacilityService;
	@Autowired
	private PatientVisitInpatientDaoImpl cacheRefreshDaoPatientVisitInpatient;
	@Autowired
	private PatientLabDaoImpl cacheRefreshDaoPatientLab;
	@Autowired
	private PatientCareTeamDaoImpl cacheRefreshDaoPatientCareTeam;
	@Autowired
	private PatientCarePlanDaoImpl cacheRefreshDaoPatientCarePlan;
	@Autowired
	private PatientDiagnosisDaoImpl cacheRefreshDaoPatientDiagnosis;
	@Autowired
	private PatientFunctionalStatusDaoImpl cacheRefreshDaoPatientFunctional;
	@Autowired
	private PatientImagingDaoImpl cacheRefreshDaoPatientImaging;
	@Autowired
	private PatientImmunizationDaoImpl cacheRefreshDaoPatientImmunization;
	@Autowired
	private PatientProblemDaoImpl cacheRefreshDaoPatientProblem;
	@Autowired
	private PatientVisitDaoImpl cacheRefreshDaoPatientVisit;
	@Autowired
	private PatientVitalSignDaoImpl cacheRefreshDaoPatientVitalSign;
	
	@Autowired
	private PatientProcedureDaoImpl cacheRefreshDaoPatientProcedure;
	
	@Autowired
	private PatientPrescriptionDaoImpl cacheRefreshDaoPatientPrescription;
	
	
	
	
	
	
	private static int MAX_RUNNING_THREAD;

	private ExecutorService executorService = null;

	private static String EMPTY_MODULE_LIST;
	private static String SUCCESS_ADD_MODULE_LIST;
	private static String FAIL_TO_ADD_MODULE;

	public List<EtlLogger> getEtlLoggerByClientDatabaseId(int clientDatabaseId) {

	    return etlLoggerDao.getEHRResults("EtlLogger.findByClientDatabaseIdUnprocessed", null, clientDatabaseId, null, null);
	}

	public void startCacheRefresh(List<EtlLogger> etlLoggers) {

		int transactionId = -1;
		String commaSeprateModule = null;
		String modules[] = null;
		int clientDatabaseId = -1;
		Module<?, ?> runnableModule = null;
		CacheModule module = null;

		for (EtlLogger etlLogger : etlLoggers) {
			try {
				transactionId = etlLogger.getEtlLoggerPK().getTransactionId();
				clientDatabaseId = etlLogger.getEtlLoggerPK().getClientDatabaseId();
				LOGGER.info("ETL CACHE REFRESH SERVICE STARTED FOR CLIENT DATABASE ID : " + clientDatabaseId
						+ " TRANSACTION ID : " + transactionId);
				commaSeprateModule = etlLogger.getModuleList();
				if (commaSeprateModule == null || commaSeprateModule.trim().isEmpty()) {
					etlLogger.setStatus(EMPTY_MODULE_LIST);
					LOGGER.info("EMPTY MODULE LIST FOR TRANSACTION " + transactionId + " \n");
					continue;
				} else
					LOGGER.info("-----------------------  LIST OF KEYS ARE CACHED--------------------------------");

				modules = commaSeprateModule.split(",");

				int lenght = modules.length;

				for (int count = 0; count < lenght; count++) {

					module = CacheModule.getCacheModule(modules[count].trim());
					if (module != null) {
						runnableModule = null;
						switch (module) {

						case FACILITY_NOTICE:
							runnableModule = new FacilityNoticeModule(transactionId, clientDatabaseId,
									cacheAccessForFacilityNotice, cacheRefreshDaoFacilityNotice, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case FACILITY_PROVIDER:
							runnableModule = new FacilityProviderModule(transactionId, clientDatabaseId,
									cacheAccessForFacilityProvider, cacheRefreshDaoFacilityProvider, messageSource, etlLogger.getEtlLoggerPK().getClientId());
							break;
						case FACILITY_SERVICE:
							runnableModule = new FacilityServiceModule(transactionId, clientDatabaseId,
									cacheAccessForFacilityService, cacheRefreshDaoFacilityService, messageSource, etlLogger.getEtlLoggerPK().getClientId());
							break;
						case PATIENT_ALLERGY:

							runnableModule = new PatientAllergiesModule(transactionId, clientDatabaseId,
									cacheAccessForAllergies, cacheRefreshDaoPatientAllergy, messageSource, etlLogger.getEtlLoggerPK().getClientId());
							break;
						case PATIENT_CARE_PLAN: 
							runnableModule = new PatientCarePlanModule(transactionId, clientDatabaseId,
									cacheAccessForCarePlanInfo, cacheRefreshDaoPatientCarePlan, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_CARE_TEAM:
							runnableModule = new PatientCareTeamModule(transactionId, clientDatabaseId,
									cacheAccessForCareTeamInfo, cacheRefreshDaoPatientCareTeam, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_DIAGNOSIS:
							runnableModule = new PatientDiagnosisModule(transactionId, clientDatabaseId,
									cacheAccessForDiagnosisInfo, cacheRefreshDaoPatientDiagnosis, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_FUNCTIONAL_STATUS:
							runnableModule = new PatientFunctionalStatusModule(transactionId, clientDatabaseId,
									cacheAccessbyJsonFunctionalStatusInfo, cacheRefreshDaoPatientFunctional, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_IMAGING:
							runnableModule = new PatientImagingModule(transactionId, clientDatabaseId,
									cacheAccessForPatientImagingInfo, cacheRefreshDaoPatientImaging, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_IMMUNIZATION:

							runnableModule = new PatientImmunizationModule(transactionId, clientDatabaseId,
									cacheAccessForImmunalizationInfo, cacheRefreshDaoPatientImmunization, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_LAB:
							runnableModule = new PatientLabResultModule(transactionId, clientDatabaseId,
									cacheAccessForLabResultInfo, cacheRefreshDaoPatientLab, cacheAccess, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_MEDICATION:
							runnableModule = new PatientPrescriptionModule(transactionId, clientDatabaseId,
									cacheAccessForMedicationInfo, cacheRefreshDaoPatientPrescription, cacheAccessForNsPatientPrescription,
									messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_VISIT_INPATIENT:
							runnableModule = new InPatientMetaDataModule(transactionId, clientDatabaseId,
									cacheAccessInpatientMetadataInfo, cacheRefreshDaoPatientVisitInpatient, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_PROBLEM:
							runnableModule = new PatientProblemModule(transactionId, clientDatabaseId,
									cacheAccessForProblemInfo, cacheRefreshDaoPatientProblem, messageSource, etlLogger.getEtlLoggerPK().getClientId());

							break;
						case PATIENT_PROCEDURE:

							runnableModule = new PatientProcedureModule(transactionId, clientDatabaseId,
									cacheAccessForProcedureInfo, cacheRefreshDaoPatientProcedure, messageSource, etlLogger.getEtlLoggerPK().getClientId());
							break;

						case PATIENT_VITAL_SIGN:
							runnableModule = new PatientVitalSignModule(transactionId, clientDatabaseId,
									cacheAccessVitalSigns, cacheRefreshDaoPatientVitalSign, messageSource, etlLogger.getEtlLoggerPK().getClientId());
							break;
						case PATIENT_VISIT:

							runnableModule = new PatientVisitModule(transactionId, clientDatabaseId,
									cacheAccessForNsPatientVisit, cacheRefreshDaoPatientVisit, messageSource, etlLogger.getEtlLoggerPK().getClientId());
							break;
						default:
							LOGGER.info(module.getModuleName() + " MODULE NOT FOUND UNDER CACHE SERVICE");
							break;

						}
						if (runnableModule != null) {
						    LOGGER.info(module.getModuleName() + " MODULE SUBMITTED UNDER CACHE SERVICE");
							executorService.submit(runnableModule);
							

						}
					}

				}
				etlLogger.setStatus(SUCCESS_ADD_MODULE_LIST);

			} catch (RejectedExecutionException ex) {
				LOGGER.error("REJECTED EXECUTION : \n" + ExceptionUtils.getStackTrace(ex));
				etlLogger.setStatus(FAIL_TO_ADD_MODULE);
			} catch (Exception ex) {
				LOGGER.error("EXCEPTION OCCUR : \n" + ExceptionUtils.getStackTrace(ex));
				etlLogger.setStatus(FAIL_TO_ADD_MODULE);
			}
		}
	}

	@PostConstruct
	public void initIt() throws Exception {

		MAX_RUNNING_THREAD = Integer.parseInt(messageSource.getMessage("cache.refresh.max.thread.run", null,
				Locale.getDefault()));

		LOGGER.info("INIT METHOD INITIALIZE EXECUTOR SERVICE OF  " + MAX_RUNNING_THREAD + "SIZE THREAD POOL");

		executorService = Executors.newFixedThreadPool(MAX_RUNNING_THREAD);

		EMPTY_MODULE_LIST = messageSource.getMessage("cache.refresh.no.module.found", null, Locale.getDefault());

		SUCCESS_ADD_MODULE_LIST = messageSource.getMessage("cache.refresh.success.add.module", null,
				Locale.getDefault());
		FAIL_TO_ADD_MODULE = messageSource.getMessage("cache.refresh.fail.add.module", null, Locale.getDefault());

	}

	@PreDestroy
	public void cleanUp() throws Exception {

		LOGGER.info("SPRING CONTAINER IS RUNNING DESTROY! EXECUTOR SERVICE  SHUTTING DOWN");
		executorService.shutdown();
	}

	/**
	 * @param etlLoggers
	 */
	public void updateEtlLoggers(List<EtlLogger> etlLoggers) {

		etlLoggerDao.updateEHR(etlLoggers);

	}
}
