package com.versawork.http.utils;

import java.util.Comparator;
import java.util.Date;

import com.versawork.http.dataobject.VitalSigns;

/**
 * @author Sohaib
 * 
 */
public class VitalSignListComparator implements Comparator<VitalSigns> {

	@Override
	public int compare(VitalSigns o1, VitalSigns o2) {
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
		if (date.after(date1))
			return -1;
		else
			return 1;
	}

}
