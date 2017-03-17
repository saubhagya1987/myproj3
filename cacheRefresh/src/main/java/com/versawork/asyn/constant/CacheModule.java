package com.versawork.asyn.constant;

/**
 * 
 * @author RAHUL BHALLA 
 * FNO, FPR, FSE, PLAB, PPROB, PVIS, PIMG, PMED, PVSI, PMED, PCTE, PDIA, PFST, PPROC, PIMM, PALL, PCPL, PVIN
 */

public enum CacheModule {

	FACILITY_NOTICE("FNO"), FACILITY_PROVIDER("FPR"), FACILITY_SERVICE("FSE"), PATIENT_LAB("PLAB"), PATIENT_MEDICATION(
			"PMED"), PATIENT_IMAGING("PIMG"), PATIENT_ALLERGY("PALL"), PATIENT_PROBLEM("PPROB"), PATIENT_VISIT("PVIS"), PATIENT_FEEDBACK(
			"PFED"), PATIENT_CARE_PLAN("PCPL"), PATIENT_CARE_TEAM("PCTE"), PATIENT_DIAGNOSIS("PDIA"), PATIENT_FUNCTIONAL_STATUS(
			"PFST"), PATIENT_IMMUNIZATION("PIMM"), PATIENT_PROCEDURE("PPROC"), PATIENT_VISIT_INPATIENT("PVIN"), PATIENT_VITAL_SIGN(
			"PVSI"), PATIENT_VERIFICATION("PVT"), PATIENT_LABHISTORY("PLHIS"),

	// NOT CACHED SINCE IT IS UPDATED BY SCHEDULER EVERY FEW MINUTES
	PATIENT_UPCOMINNG_APPOINTMENT("PUA");

	private String module;

	private CacheModule(String module) {

		this.module = module;
	}

	public String getModuleName() {
		return module;
	}

	public void setCode(String module) {

		this.module = module;
	}

	public static CacheModule getCacheModule(String module) {

		CacheModule result = null;
		for (CacheModule cacheModule : CacheModule.values()) {
			if (cacheModule.module.equalsIgnoreCase(module)) {
				result = cacheModule;
				break;
			}
		}
		return result;
	}

}
