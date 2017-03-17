package com.versawork.http.utils;

import java.util.Comparator;
import java.util.Date;

import com.versawork.http.dataobject.AllergiesInfo;

/**
 * @author Sohaib
 * 
 */
public class AllegyListComparator implements Comparator<AllergiesInfo> {

	@Override
	public int compare(AllergiesInfo o1, AllergiesInfo o2) {
		Date date = null, date1 = null;
		try {
			/*if (o1.getDateAdded() == null) {
				return -1;
			}
			if (o2.getDateAdded() == null) {
				return 1;
			}*/
			date = DateUtils.getDate(o1.getDateAdded(), "dd/MM/yyyy");
			date1 = DateUtils.getDate(o2.getDateAdded(), "dd/MM/yyyy");
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		if (date.equals(date1)) {
			return o1.getAllergen().compareToIgnoreCase(o2.getAllergen());
		} 
		else if (date.after(date1))
			return -1;
		else
			return 1;
	}

}
