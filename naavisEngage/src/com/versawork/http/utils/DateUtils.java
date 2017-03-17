package com.versawork.http.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.versawork.http.exception.BusinessException;

@Component
public class DateUtils {

	public static final String DATE_ADDED_FORMAT = "MM/dd/yyyy";
	public static final String DATE_ADDED_FORMAT_12_HOUR = "MM/dd/yyyy hh:mm a";
	public static final String DATE_ADDED_UTC_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS ";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			DATE_ADDED_UTC_FORMAT, Locale.ENGLISH);

	public static Date getDate(Long date) {
		Date date2 = new Date(date);
		return date2;
	}

	public static Date getDate(String dateString, String format)
			throws BusinessException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format,
				Locale.ENGLISH);
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

	public static String getFormatDate(Date dateString, String format)
			throws BusinessException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format,
				Locale.ENGLISH);
		String date = null;
		try {
			if (dateString != null) {
				date = simpleDateFormat.format(dateString);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			// throw new BusinessException(exception);
		}
		return date;
	}

	public static Date get24FormatDate(String dateString)
			throws BusinessException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a",
				Locale.ENGLISH);
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

	/*
	 * public static Date getCurrentUTCDate(){ Date date= null; try {
	 * SimpleDateFormat format = new
	 * SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
	 * format.setTimeZone(TimeZone.getTimeZone("UTC")); date = format.parse((new
	 * Date()).toString());
	 * 
	 * } catch (ParseException e) {
	 * LOGGER.error("Error while getCurrentUTCTime"+
	 * ExceptionUtils.getFullStackTrace(e)); } return date; }
	 */

	public static Date getCurrentUTCDate() {
		long ts = System.currentTimeMillis();
		Date localTime = new Date(ts);
		String format = "yyyy/MM/dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		// Convert Local Time to UTC (Works Fine)
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		@SuppressWarnings("deprecation")
		Date gmtTime = new Date(sdf.format(localTime));
		return gmtTime;
	}

	public static String getDateWithChar(String dateString)
			throws BusinessException {
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format,
		// Locale.ENGLISH);
		String token = null;
		try {
			if (dateString != null) {
				String delim = "-";
				String str = dateString;
				StringTokenizer st = new StringTokenizer(str, delim);
				while (st.hasMoreElements()) {
					st.nextElement();
				}
				String tokens[] = str.split(delim);
				// System.out.println(tokens);
				token = tokens[0].concat(delim).concat(tokens[1])
						.concat(tokens[2]);
			}
		} catch (Exception exception) {

			// exception.printStackTrace();
			return null;
			// throw new BusinessException(exception);
		}
		return token;

	}

	public static String getDateConvertedFormat(String dateString,
			String currentFormat, String returnFormat) {

		simpleDateFormat.applyPattern(currentFormat);
		Date date;
		try {
			date = simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		simpleDateFormat.applyPattern(returnFormat);
		return simpleDateFormat.format(date);
	}
	
	
	public static DateRange getDateRange(Date current, int startDay, int endDay) {
		Calendar calender = Calendar.getInstance();
		if (current == null) {
			current = new Date();
		}
		DateRange dateRange = new DateRange();
		calender.setTime(current);
		calender.set(Calendar.MILLISECOND, 999);
		calender.set(Calendar.SECOND, 59);
		calender.set(Calendar.MINUTE, 59);
		calender.set(Calendar.HOUR, 23);
		dateRange.setEndDate(calender.getTime());
		
		calender.add(Calendar.DAY_OF_WEEK, -7);
		
		calender.add(Calendar.MILLISECOND, 1);		
		dateRange.setStartDate(calender.getTime());		

		return dateRange;

	}

	/*public static DateRange getDateRange(Date current, int startDay, int endDay) {
		Calendar calender = Calendar.getInstance();
		calender.setFirstDayOfWeek(Calendar.MONDAY);
		if (current == null) {
			current = new Date();
		}
		DateRange dateRange = new DateRange();
		calender.setTime(current);
		calender.set(Calendar.DAY_OF_WEEK, startDay);
		dateRange.setStartDate(calender.getTime());
		calender.set(Calendar.DAY_OF_WEEK, endDay);
		dateRange.setEndDate(calender.getTime());

		return dateRange;

	}*/
	
	
	
	public static DateRange getSingleDateRange(Date current) {
		Calendar calender = Calendar.getInstance();
		calender.setFirstDayOfWeek(Calendar.MONDAY);
		if (current == null) {
			current = new Date();
		}
		DateRange dateRange = new DateRange();
		calender.setTime(current);
		calender.set(Calendar.MILLISECOND, 0);
		calender.set(Calendar.SECOND, 0);
		calender.set(Calendar.MINUTE, 0);
		calender.set(Calendar.HOUR, 0);
		dateRange.setStartDate(calender.getTime());
		calender.set(Calendar.MILLISECOND, 999);
		calender.set(Calendar.SECOND, 59);
		calender.set(Calendar.MINUTE, 59);
		calender.set(Calendar.HOUR, 23);
		dateRange.setEndDate(calender.getTime());

		return dateRange;

	}
	
	
	

}