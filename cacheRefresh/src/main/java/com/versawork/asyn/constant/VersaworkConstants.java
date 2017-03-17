/**
 * 
 */
package com.versawork.asyn.constant;

/**
 * @author RAHUL BHALLA
 *
 */
public enum VersaworkConstants {
	NOT_APPLICABLE("N/A");
	private String message;

	private VersaworkConstants(String message) {

		this.message = message;
	}

	public String getMessage() {

		return this.message;
	}

	public static VersaworkConstants getVersaworkConstant(String message) {

		VersaworkConstants result = null;
		for (VersaworkConstants constants : VersaworkConstants.values()) {
			if (constants.message.equalsIgnoreCase(message))
				result = constants;
		}
		return result;

	}

}
