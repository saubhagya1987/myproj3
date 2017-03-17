/**
 * 
 */
package com.versawork.asyn.constant;

/**
 * @author RAHUL BHALLA
 *
 */
public enum LogFile {
	ETL_CACHE_LOG_FILE("EtlCacheLogFile");
	private String fileName;

	private LogFile(String fileName) {

		this.fileName = fileName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {

		return fileName;
	}
}
