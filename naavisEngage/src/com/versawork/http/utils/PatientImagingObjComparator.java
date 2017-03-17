package com.versawork.http.utils;

import java.util.Comparator;
import java.util.Date;

import com.versawork.http.dataobject.PatientImagingObj;

public class PatientImagingObjComparator implements Comparator<PatientImagingObj> {

	@Override
	public int compare(PatientImagingObj o1, PatientImagingObj o2) {
		Date date = null, date1 = null;
		try {
			if (o1.getExamDate() == null) {
				return -1;
			}
			if (o2.getExamDate() == null) {
				return 1;
			}
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
