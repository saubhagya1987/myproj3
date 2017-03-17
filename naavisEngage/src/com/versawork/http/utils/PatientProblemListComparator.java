package com.versawork.http.utils;

import java.util.Comparator;
import java.util.Date;

import com.versawork.http.dataobject.PatientProblemInfo;

/**
 * @author Sohaib
 * 
 */
public class PatientProblemListComparator implements Comparator<PatientProblemInfo> {

	@Override
	public int compare(PatientProblemInfo o1, PatientProblemInfo o2) {
		Date date = null, date1 = null;
		try {
			/*if (o1.getDateAdded() == null) {
				return -1;
			}
			if (o2.getDateAdded() == null) {
				return 1;
			}*/
			date = DateUtils.getDate(o1.getDateAdded(), "MM/dd/yyyy");
			date1 = DateUtils.getDate(o2.getDateAdded(), "MM/dd/yyyy");

		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		if (date.equals(date1)) {
			return o1.getProblemName().compareToIgnoreCase(o2.getProblemName());
		} 
		else if (date.after(date1))
			return -1;
		else
			return 1;
	}

}
