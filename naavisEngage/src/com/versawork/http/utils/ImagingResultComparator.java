package com.versawork.http.utils;

import java.util.Comparator;


import com.versawork.http.dataobject.TestResultInfo;

public class ImagingResultComparator implements Comparator<TestResultInfo>{
	@Override
	public int compare(TestResultInfo o1, TestResultInfo o2) {
		if (o1 == o2)
			return 0;
		if (o1 == null || o1.getOrderingProvider()== null)
			return 1;
		if (o2 == null || o2.getOrderingProvider() == null)
			return -1;

		return o1.getOrderingProvider().compareTo(o2.getOrderingProvider());
	}


}
