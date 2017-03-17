package com.versawork.http.utils;

import java.util.Comparator;
import java.util.Date;
import com.versawork.http.dataobject.TestResultInfo;


public class TestResultComparator implements Comparator<TestResultInfo> {

	@Override
	public int compare(TestResultInfo o1, TestResultInfo o2) {
		// SimpleDateFormat simpleDateFormat = new
		// SimpleDateFormat("MMMM d, yyyy");
		Date date;
		Date date1;
		try {
			// return o1.getTestName().compareTo(o2.getTestName());
			/*
			 * if(o1.getGroupDate()==null) { return -1; } if(o2.getGroupDate()
			 * == null) { return 1; }
			 */
			date = DateUtils.getDate(o1.getDate(), "MM/dd/yyyy");
			date1 = DateUtils.getDate(o2.getDate(), "MM/dd/yyyy");
		} catch (Exception e) {

			e.printStackTrace();
			return 1;
		}
		if (date.equals(date1)) {
			return o1.getName().compareToIgnoreCase(o2.getName());
		} else if (date.after(date1)) {
			return -1;
		} else
			return 1;
	}

}
