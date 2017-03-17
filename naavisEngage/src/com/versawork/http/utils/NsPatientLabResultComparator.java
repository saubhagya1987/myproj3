package com.versawork.http.utils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import com.versawork.http.dataobject.NsPatientLabResult;

public class NsPatientLabResultComparator implements Comparator<NsPatientLabResult> {
	@Override
	public int compare(NsPatientLabResult o1, NsPatientLabResult o2) {
		try {
			if (o1.getResultDate() == null) {
				return -1;
			}
			if (o2.getResultDate() == null) {
				return 1;
			}
			Date date = DateUtils.getDate(o1.getResultDate(), "MM/dd/yyyy");
			Date date1 = DateUtils.getDate(o2.getResultDate(), "MM/dd/yyyy");

			if (date.after(date1)) {
				return -1;
			} else {
				return 1;
			}
		} catch (Exception e) {

			e.printStackTrace();
			return 1;
		}
	}
}
