/**
 * 
 */

/**
 * @author RAHUL BHALLA
 *
 */
package com.versawork.asyn.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String exceptionCode;
	private String exceptionMessage;

	public BusinessException() {

	}

	public BusinessException(String exceptionCode) {

		super(exceptionCode);
		this.setExceptionCode(exceptionCode);
	}

	public BusinessException(String s, Throwable throwable) {

		super(s, throwable);
	}

	public BusinessException(Throwable throwable) {

		super(throwable);
	}

	public void setExceptionCode(String exceptionCode) {

		this.exceptionCode = exceptionCode;
	}

	public String getExceptionCode() {

		return exceptionCode;
	}

	public String getExceptionMessage() {

		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {

		this.exceptionMessage = exceptionMessage;
	}
}
