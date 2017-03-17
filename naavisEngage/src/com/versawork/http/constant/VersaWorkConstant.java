package com.versawork.http.constant;

/**
 * @author Dheeraj
 *
 */

public class VersaWorkConstant {
	public static final Integer RESPONSE_DATA_RESULT_SUCCESS = 0;

	// public static final Integer RESPONSE_DATA_RESULT_FAILURE = 20;
	public enum Status_Codes {
		SUCCESS_RESPONSE_CODE(0), INVALID_ENDPOINT_RESPONSE_CODE(10), FAILURE_RESPONSE_CODE(20), DEVICE_RESET_RESPONSE_CODE(
				40);
		private int code;

		public int getCode() {
			return this.code;
		}

		private Status_Codes(int code) {
			this.code = code;
		}
	};

	public static final String NOT_APPLICABLE = "N/A";
	public static final String SUCCESS_RESPONSE_CODE = "rsp.data.result.success";
	public static final String FAILURE_RESPONSE_CODE = "rsp.data.result.failure";
	public static final String INVALID_ENDPOINT_RESPONSE_CODE = "rsp.data.result.reg.override";
	public static final String DEVICE_RESET_RESPONSE_CODE = "rsp.data.result.device.reset";
	public static final String DEVICE_RESET_RESPONSE_MESSAGE = "device.reset.message";
	public static final String INVALID_ENDPOINT_RESPONSE_MESSAGE = "invalid.endpoint.id";
	public static final String SERVER_GENERAL_FAILURE = "server.general.failure";
	public static final String NULL_POINTER_MESSAGE = "null.pointer.message";
	public static final String UNHANDLED_EXCPETION_MESSAGE = "unhandled.exception.message";
	public static final String NO_IMAGE_FOUND = "no.image.found";

	public static final Integer ACCOUNT_ROLE_PATIENT_ID = 3;
	public static final String ACCOUNT_ROLE_PATIENT = "Patient";
	public static final String ACCOUNT_ROLE_ACCOUNT = "Account";
	public static final String ACCOUNT_ROLE_PROVIDER = "Provider";
	public static final String RESPONSE_DATA_DESCRIPTION_SUCCESS = "Success";
	public static final int attemptCount = 3;

	public static final String KEY_STORE_TYPE = "PKCS12";
	// public static final String DEV_APPLE_NOTIFICATION_CERTIFICATE =
	// "Enterprise_Dev_Push.p12";
	/*
	 * public static final String DEV_APPLE_NOTIFICATION_CERTIFICATE =
	 * "ireslab1.p12"; public static final String
	 * PROD_APPLE_NOTIFICATION_CERTIFICATE = "APNS Production.p12"; public
	 * static final String DEV_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD =
	 * "rahul123"; public static final String
	 * PROD_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD = "ireslab";
	 */

	public static final String APNS_DISTRIBUTION = "apns.distribution";
	public static final String IOS_NOTIFICATION_CERTIFICATE = "ios.notification.certificate";
	public static final String IOS_NOTIFICATION_CERTIFICATE_PASSWORD = "ios.notification.certificate.pasword";

	public static final String MEDICATION_ADDED_SUCCESSFULLY = "med.add.scfly";

	public static final Integer CURRENT_CLIENTDATABASEID = 9;
	/* public static final Integer MAX_INACTIVE_INTERVAL = 300; */

	public static final Integer COUNT_ZERO = 0;
	public static final Integer PATIENT_VISIT_ROLE = 1;
	public static final Integer REP_PATIENT_VISIT_ROLE = 2;
	public static final Integer SIDE_MENU_FEATURE_ID = 3;
	public static final Integer MY_HEALTH_MODULE_ID = 4;
	public static final Integer MANAGE_MODULE_ID = 5;
	public static final Integer HOSPITAL_DIRECTORY_MODULE_ID = 6;
	public static final Integer ABOUT_APP_MODULE_ID = 7;
	public static final Integer TO_DO_MODULE_ID = 8;

	public static final String TIME_FORMAT_12_HOUR = "hh:mm a";
	public static final String TIME_FORMAT_MM_DD_YYYY = "MM/dd/yyyy";

	public static final String NO_RESULT_FOUND = "no.result.found";
	public static final String NO_BILL_SUMMARY_FOUND = "no.bill.summary.found";
	public static final String PAY_MY_BILLS = "pay.my.bill";

	public static final String CASCADE_LOGO_IMAGE_NAME = "cascade.logo.image.name";
	public static final String LINDSBORG_LOGO_IMAGE_NAME = "lindsborg.logo.image.name";
	public static final String NAVIS_LOGO_IMAGE_NAME = "navis.logo.image.name";
	public static final String SALINA_LOGO_IMAGE_NAME = "salina.logo.image.name";
	public static final String MITCHELL_LOGO_IMAGE_NAME = "mitchell.logo.image.name";

	public static final String HOSPITAL_LOGO_IMAGE_TYPE = "hospital.logo.image.type";

	public static final String BILLING_STATUS_APPROVED = "Approved";
	public static final String CLIENT_DATABASE_ID = "client.database.id";

	public static final String CLIENT_DB_ID_SALINA = "client.db.id.salina";
	public static final String CLIENT_DB_ID_CASCADE = "client.db.id.cascade";
	public static final String CLIENT_DB_ID_LINDSBORG = "client.db.id.lindsborg";
	public static final String CLIENT_DB_ID_NAVIS = "client.db.id.navis";
	public static final String CLIENT_DB_ID_MITCHELL = "client.db.id.mitchell";

	public static final String HOSPITAL_SIGNATURE = "hospital.signature";
	
	

}

/*
 * package com.versawork.http.constant;
 * 
 * public class VersaWorkConstant { public static final Integer
 * RESPONSE_DATA_RESULT_SUCCESS = 0; public static final Integer
 * RESPONSE_DATA_RESULT_FAILURE = 20; public static final Integer
 * ACCOUNT_ROLE_PATIENT_ID = 3; public static final String ACCOUNT_ROLE_PATIENT
 * = "Patient"; public static final String ACCOUNT_ROLE_ACCOUNT = "Account";
 * public static final String ACCOUNT_ROLE_PROVIDER = "Provider"; public static
 * final String RESPONSE_DATA_DESCRIPTION_SUCCESS = "Success"; public static
 * final int attemptCount=3;
 * 
 * public static final String KEY_STORE_TYPE = "PKCS12"; public static final
 * String DEV_APPLE_NOTIFICATION_CERTIFICATE = "ireslab1.p12"; public static
 * final String PROD_APPLE_NOTIFICATION_CERTIFICATE = "APNS Production.p12";
 * public static final String DEV_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD =
 * "rahul123"; public static final String
 * PROD_APPLE_NOTIFICATION_CERTIFICATE_PASSWORD = "ireslab";
 * 
 * public static final Integer CURRENT_CLIENTDATABASEID = 9; /*public static
 * final Integer MAX_INACTIVE_INTERVAL = 300;
 * 
 * public static final Integer PATIENT_VISIT_ROLE = 1; public static final
 * Integer REP_PATIENT_VISIT_ROLE = 2; }
 */