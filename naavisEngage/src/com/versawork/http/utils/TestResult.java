package com.versawork.http.utils;

import java.util.Comparator;

import com.versawork.http.dataobject.TestResultInfo;



public class TestResult implements Comparator<TestResultInfo> {

	@Override
	public int compare(TestResultInfo o1, TestResultInfo o2) {
		
		return o1.getName().compareTo(o2.getName());
		

	}

}
