package com.versawork.http.utils;

import java.util.Comparator;
import java.util.Date;

import com.versawork.http.dataobject.NsPatientLabResult;

/**
 * @author Sohaib
 * 
 */
public class LabTestListComparator implements Comparator<NsPatientLabResult> {

	@Override
	public int compare(NsPatientLabResult o1, NsPatientLabResult o2) {
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
			date = DateUtils.getDate(o1.getDateAdded(), "MMMM d, yyyy");
			date1 = DateUtils.getDate(o2.getDateAdded(), "MMMM d, yyyy");
		} catch (Exception e) {

			e.printStackTrace();
			return 1;
		}
		if (date.equals(date1)) {
			return o1.getTestName().compareToIgnoreCase(o2.getTestName());
		} else if (date.after(date1)) {
			return -1;
		} else
			return 1;
	}

}
