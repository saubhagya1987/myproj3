package com.versawork.http.utils;

public interface Activity {

	public static String VERIFY_USER = "Verify user";
	public static String VALIDATE_USER = "Validate User";
	public static String SEND_SECURITY_CODE = "Generate security code";
	public static String VALIDATE_SECURITY_CODE = "Validate security code";
	public static String AUTHENTICATE_USER_LOGIN = "Authenticate User Login";
	public static String SEND_SECURITY_CODE_FOR_PHONE_NUMBER_CHANGE = "Send Security Code For Phone Number Change";
	public static String SEND_SECURITY_CODE_FOR_PHONE_PROFILE_EDIT = "Send Security Code For Profile Edit";
	public static String LOGIN = "Login";
	public static String LOG_OUT = "LogOut";
	public static String SESSION_EXPIRE = "Session Expired";
	public static String EMAIL_VALIDATION = "Email validation";
	public static String VALIDATE_ACCOUNT_EMAIL = "Validate Account Email";
	public static String SAVE_PREFERED_LANGUAGE = "Save Preferred Language";
	public static String GET_ACCOUNT_IMAGING_DETAILS = "getAccountImagingDetails";
	public static String CREATE_ACCOUNT = "Create account";
	public static String UPDATE_ACCOUNT_PASSWORD = "Update account password";
	public static String RESET_ACCOUNT_PASSWORD = "Reset account password";
	public static String VALIDATE_UNIT_NUMBER = "Validate unit number";
	public static String UPGRADE_ACCOUNT = "Upgrade account";
	public static String SAVE_PATIENT_APPOINTMENT = "Save patient appointment";
	public static String GET_PATIENT_APPOINTMENT = "Get patient appointment";
	public static String GET_PATIENT_LAB_RESULTS = "Get patient lab result";
	public static String GET_PATIENT_LAB_GROUPS = "Get patient lab groups";
	public static String GET_PATIENT_PRESCRIPTION = "Get patient prescription";
	public static String GET_PATIENT_PRESCRIPTION_GROUPS = "Get patient prescription groups";
	public static String VALIDATE_USER_ACCOUNT = "Validate user account";
	public static String APPOINTMENT_CONFIRMATION = "Appointment Confirmation";
	public static String GET_PATIENT_LAB_TEST_HISTORY = "getPatientLabTestHistory";

	// Blood Pressure

	public static String SAVE_BLOODPRESSURE = "Save blood pressure";
	public static String GET_BLOODPRESSURE = "Get blood pressure";
	public static String EDIT_BLOODPRESSURE = "Edit blood pressure";
	public static String DELETE_BLOODPRESSURE = "Delete blood pressure";
	public static String SAVE_BLOODPRESSURE_REMINDER = "Save blood pressure reminder";
	public static String GET_BLOODPRESSURE_REMINDER = "Get blood pressure reminder";
	public static String EDIT_BLOODPRESSURE_REMINDER = "Edit blood pressure reminder";
	public static String DELETE_BLOODPRESSURE_REMINDER = "Delete blood pressure reminder";
	public static String RESTORE_BLOODPRESSURE_REMINDER = "Restore blood pressure reminder";
	public static String SAVE_BLOODPRESSURE_RESPONSE = "Save blood pressure response";
	public static String CONFIRM_SCHEDULED_APPOINTMENT = "Confirm scheduled appointment";

	public static String SAVE_FEEDBACK = "Save feedback";
	public static String GET_DOCTORS_LIST = "Get doctors list";
	public static String GET_PROFILE_INFO = "Get profile info";
	public static String GET_SERVICES_lIST = "Get services list";
	public static String GET_VISIT_lIST = "Get visit list";
	public static String GET_HOSPITAL_NOTICE = "Get hospital notices";
	public static String GET_HOSPITAL_NOTICE_FOR_GUEST = "Get Hospital Notice For Guest";
	public static String HOSPITAL_NOTICE_VIEWED = "Hospital Notice Viewed";
	public static String GET_HOSPITAL_LIST = "Get Hospital List";

	// Medication management

	public static String SAVE_MEDICATION = "Save medication";
	public static String GET_MEDICATION_LIST = "Get medication list";
	public static String EDIT_MEDICATION = "Edit medication";
	public static String DELETE_MEDICATION = "Delete medication";
	public static String SAVE_MEDICATION_REMINDER = "Save medication remindet";
	public static String GET_MEDICATION_REMINDER_LIST = "Get medication reminder list";
	public static String EDIT_MEDICATION_REMINDER = "Edit medication reminder";
	public static String DELETE_MEDICATION_REMINDER = "Delete reminder";
	public static String SAVE_MEDICATION_REMINDER_RESPONSE = "Save bloodpressure reminder response";
	public static String GET_MEDICATION_REMINDER_RESPONSE = "Get medication remider resposne";
	public static String GET_MEDICATION_FREQUENCY = "Get Medication Frequency";
	public static String GET_MEDICATION_METHOD_DESCRIPTION = "Get Medication Method Description";
	public static String GET_MEDICATION_KIND_DESCRIPTION = "Get Medication Kind Description";
	public static String GET_MEDICATION_DOSAGE_DESCRIPTION = "Get Medication Dosage Description";

	// Notification Services Pages

	public static String GET_BLOODPRESSURE_REMINDER_RESPONSE_NOTIFICATION = "Get blood pressure reminser response notification";
	public static String GET_MEDICATION_REMINDER_RESPONSE_NOTIFICATION = "Get medication reminder response notification";
	public static String GET_PATIENT_SCHEDULE_APPTS = "Get patient scheduled appointments";
	public static String GET_PATIENT_FUTURE_SCHEDULE_APPTS = "Get patient future dated scheduled appointments";
	public static String GET_PATIENT_FUTURE_CONFIRMED_APPTS = "Get patient future dated confirmed appointments";
	public static String GET_PATIENT_CONFIRMED_APPTS = "Get patient confirmed appointments";
	public static String GET_BLOODPRRESSURE_NOTIFICATION_INACTIVE_ALERT = "Get BloodPressure notification inactive alert";
	public static String GET_MEDICATION_NOTIFICATION_INACTIVE_ALERT = "getMedicationNotificationInactiveAlert";
	public static String FLUSH_ACTIVE_NOTIFICATION_ALERT = "All active notification flushed";
	public static String GET_NOTIFICATION_COUNT = "Get Notification Count";
	public static String SER_NOTIFICATION_VIEWED_TRUE = "Set Notification Viewed True";
	public static String SER_VIEWED_TRUE = "Set Viewed True";

	public static String GET_WARNING_MESSAGES = "Get warning message";
	public static String TRANSMIT_EHR = "Transmit EHR";
	public static String TRANSMIT_EHR_ACTIVITY_LOG = "Transmit EHR Activity Log";
	public static String ACCESS_HISTORY = "Access history";

	public static String GET_PAST_APPOINTMENTS = "Get past appointments";
	public static String GET_VISITORS_FEEDBACK = "Get visitors feedback";

	public static String GET_SECURITY_QUE_FOR_USER = "Get security question for user";
	public static String UPDATE_DEVICE_TOKEN = "Update device token";
	public static String UPDATE_PROFILE_INFO = "Update device token";
	public static String UPDATE_ACCOUNT_PHONE = "Update account phone";
	public static String UPDATE_ACCOUNT_EMAIL = "Update Account Email";

	// Patient Details

	public static String DOWNLOAD_MUDATA = "Download MU data";
	public static String VIEW_PATIENT_DETAILS = "View patient details";
	public static String GET_VITAL_SIGNS = "Get vital signs";
	public static String GET_PROVIDER_LIST = "Get provider list";
	public static String GET_ALLERGIES_LIST = "Get allergies list";
	public static String GET_INPATIENT_MEDICATION_LIST = "Get medication list";
	public static String GET_PROBLEM_LIST = "Get problem list";
	public static String GET_PROCEDURE_LIST = "Get procedure list";
	public static String GET_LAB_RESULT_LIST = "Get lab result list";
	public static String GET_CAREPLAN_LIST = "Get patient care plan list";
	public static String GET_INPATIENT_METADATA = "Get inpatient metadata";
	public static String GET_INPATIENT_DIAGNOSIS_LIST = "Get inpatient diagnosis list";
	public static String GET_INPATIENT_FUNCTIONAL_STATUS_LIST = "Get inpatient functional status list";
	public static String GET_INPATIENT_IMMUNALIZATION_LIST = "Get inpatient immunization list";
	public static String GET_INPATIENT_HOSPITALIZATION_REASON = "Get hospitalization reason";

	public static String GET_DIRECT_ADR_INFO = "Get direct address information";
	public static String GET_NTP_SYSTEM_TIME = "Get NTP system time";

	public static String VDT_REPORTING = "VDT Reporting";
	public static String VDT_ACCESS_HISTORY = "VDT access history";

	public static String GET_LOGIN_ADDONS = "Get login addons";
	public static String SAVE_PROFILE_IMAGE = "Save profile image";
	public static String GET_PATIENT_ALLERGY = "Get patient allergies";
	public static String GET_PATIENT_ALLERGY_GROUPS = "Get patient allergie groups";
	public static String GET_PATIENT_PROBLEMS = "Get patient problems";
	public static String GET_PATIENT_PROBLEM_GROUPS = "Get patient problem groups";

	/* Link Log */
	public static String VERIFY_LINKED_USER = "Verify Linked User";
	public static String LINK_SECONDARY_ACCOUNT = "User linked secondary account";
	public static String UNLINK__ACCOUNT = "Unink User account";

	public static String GET_REGISTERED_FACILITIES = "Get registered facilities";
	public static String GET_REGISTERED_FACILITIES_DETAILS = "Get registered facilities details";
	public static String SEARCH_FACILITIES = "search facilities";
	public static String GET_HOSPITAL_LOGO = "Get Hospital Logo";
	public static String BILL_PAY_SUMMARY = "User Payable Bill Summary";
	public static String BILL_PAY_DETAIL = "User Payable Bill Detail";
	public static String GET_TRANSACTION_LOG = "Get transaction log";
}
