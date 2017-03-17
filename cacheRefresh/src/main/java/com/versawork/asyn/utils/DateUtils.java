/**
 * 
 */
package com.versawork.asyn.utils;

/**
 * @author RAHUL BHALLA
 *
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.exception.ExceptionUtils;

public final class DateUtils {
	private DateUtils() {

	};

	public static Date getDate(Long date) {

		Date date2 = new Date(date);
		return date2;
	}

	public static Date getDate(String dateString, String format) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
		Date date = null;
		try {
			if (dateString != null) {
				date = simpleDateFormat.parse(dateString);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			// throw new BusinessException(exception);
		}
		return date;
	}

	public static String getFormatDate(Date dateString, String format) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
		String date = null;
		try {
			if (dateString != null) {
				date = simpleDateFormat.format(dateString);
			}
		} catch (Exception exception) {
			ExceptionUtils.getStackTrace(exception);
			// throw new BusinessException(exception);
		}
		return date;
	}

	public static Date get24FormatDate(String dateString) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
		Date date = null;
		try {
			if (dateString != null) {
				date = simpleDateFormat.parse(dateString);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			// throw new BusinessException(exception);
		}
		return date;
	}

}
