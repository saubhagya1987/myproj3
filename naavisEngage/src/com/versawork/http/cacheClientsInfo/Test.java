package com.versawork.http.cacheClientsInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

import com.versawork.http.exception.BusinessException;

public class Test {

	/**
	 * @param args
	 * @throws BusinessException
	 * 
	 *             new, cont, disc, changed
	 */
	public enum prescriptionOrder {
		New, Continue, Discontinue;
	}

	public static void main(String[] args) throws BusinessException {
		/*
		 * String abc = "a,b,c,d"; StringTokenizer st = new StringTokenizer(abc,
		 * ","); while (st.hasMoreTokens()) {
		 * System.out.println(st.nextToken()); }
		 */
		Locale locale = new Locale("es");
		// System.out.println(locale.getDisplayCountry());

		Set<String> prescriptionActions1 = new HashSet<String>();
		// List<String> prescriptionActions2 = new ArrayList<String>();
		// prescriptionActions1.add("Changed but continue");
		System.out.println("max value : " + Integer.MAX_VALUE);
		System.out.println("min value : " + Integer.MIN_VALUE);
		prescriptionActions1.add("Discontinue");
		prescriptionActions1.add("Continue");
		prescriptionActions1.add("New");
		Iterator<String> iter = prescriptionActionListSorter(prescriptionActions1).iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	public static List<String> prescriptionActionListSorter(Set<String> prescriptionActions1) {
		{
			List<String> prescriptionActions2 = new ArrayList<String>();
			List<String> prescriptionActions = new ArrayList<String>();
			prescriptionActions.add("New");
			prescriptionActions.add("Continue");
			prescriptionActions.add("Discontinue");
			prescriptionActions.add("Changed but continue");
			// Collections.sort(prescriptionActions, new
			// PrescActionComparator());
			Iterator<String> prescActionIter = prescriptionActions.iterator();
			while (prescActionIter.hasNext()) {
				String value = prescActionIter.next();
				if (prescriptionActions1.contains(value)) {
					prescriptionActions2.add(value);
				}
			}
			return prescriptionActions2;
		}
	}
}