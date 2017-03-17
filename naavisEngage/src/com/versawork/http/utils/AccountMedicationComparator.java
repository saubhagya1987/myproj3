package com.versawork.http.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import com.versawork.http.dataobject.NsScheduleTime;

/**
 * @author Sohaib
 * 
 */
public class AccountMedicationComparator implements Comparator<NsScheduleTime> {

	@Override
	public int compare(NsScheduleTime o1, NsScheduleTime o2) {
		Date date = null, date1 = null;
		try {
			// DateUtils.getDate(o1.getReminderTime()),"hh:mm a")
			date = DateUtils.getDate(o1.getReminderTime(), "hh:mm a");
			date1 = DateUtils.getDate(o2.getReminderTime(), "hh:mm a");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (date.after(date1))
			return 1;
		else
			return -1;
	}
}
