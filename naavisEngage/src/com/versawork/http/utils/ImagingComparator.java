package com.versawork.http.utils;

import java.util.Comparator;
import java.util.Date;

import com.versawork.http.dataobject.PatientImagingObject;

public class ImagingComparator implements Comparator<PatientImagingObject> {

	@Override
	public int compare(PatientImagingObject o1, PatientImagingObject o2) {
		Date date = null, date1 = null;
		try {
			date = DateUtils.getDate(o1.getExamDate(), "MM/dd/yyyy");
			date1 = DateUtils.getDate(o2.getExamDate(), "MM/dd/yyyy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != date && null != date1) {
			if (date.after(date1))
				return -1;
			else
				return 1;
		}
		return 0;
	}
}
