package com.versawork.http.utils;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;
import com.versawork.http.dataobject.LabResultInfo;

/**
 * @author Sohaib
 * 
 */
public class LabResultInfoListComparator implements Comparator<LabResultInfo> {

	@Override
	public int compare(LabResultInfo o1, LabResultInfo o2) {
		Date date = null, date1 = null;
		try {
			if (o1.getDateAdded() == null) {
				return -1;
			}
			if (o2.getDateAdded() == null) {
				return 1;
			}
			date = DateUtils.getDate(o1.getDateAdded(), "MM/dd/yyyy");
			date1 = DateUtils.getDate(o2.getDateAdded(), "MM/dd/yyyy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (date.equals(date1)) {
			return o1.getLabTest().compareToIgnoreCase(o2.getLabTest());
		} else if (date.after(date1)) {
			return -1;
		} else
			return 1;
	}

}
