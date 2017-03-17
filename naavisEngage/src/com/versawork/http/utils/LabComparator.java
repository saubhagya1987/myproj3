package com.versawork.http.utils;

import java.util.Comparator;


import com.versawork.http.dataobject.TestResultInfo;

public class LabComparator implements Comparator<TestResultInfo> {



	@Override
	public int compare(TestResultInfo o1, TestResultInfo o2) {
		if (o1 == o2)
			return 0;
		if (o1 == null || o1.getTestName() == null)
			return 1;
		if (o2 == null || o2.getTestName() == null)
			return -1;

		return o1.getTestName().compareTo(o2.getTestName());
	}


}
