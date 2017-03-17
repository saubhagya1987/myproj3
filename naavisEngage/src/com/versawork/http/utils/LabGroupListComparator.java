package com.versawork.http.utils;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;
import com.versawork.http.dataobject.LabGroups;

/**
 * @author Sohaib
 * 
 */
public class LabGroupListComparator implements Comparator<LabGroups> {

	@Override
	public int compare(LabGroups o1, LabGroups o2) {

		Date date = null, date1 = null;
		try {
			if (o1.getGroupDate() == null) {
				return -1;
			}
			if (o2.getGroupDate() == null) {
				return 1;
			}
			date = DateUtils.getDate(o1.getGroupDate(), "MM/dd/yyyy");
			date1 = DateUtils.getDate(o2.getGroupDate(), "MM/dd/yyyy");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (date.after(date1))
			return -1;
		else
			return 1;
	}

}
