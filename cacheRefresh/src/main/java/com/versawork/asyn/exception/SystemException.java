/**
 * 
 */
package com.versawork.asyn.exception;

/**
 * @author RAHUL BHALLA
 *
 */

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SystemException() {

	}

	public SystemException(String s) {

		super(s);
	}

	public SystemException(String s, Throwable throwable) {

		super(s, throwable);
	}

	public SystemException(Throwable throwable) {

		super(throwable);
	}
}
