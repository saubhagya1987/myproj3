package com.versawork.http.utils;

import java.util.ArrayList;
import java.util.List;

import org.xbill.DNS.Name;

///CLOVER:OFF
public class LookupFactory {
	private List<Lookup> overrideImplementations = new ArrayList<Lookup>();

	public Lookup getInstance(Name name, int type) {

		if (overrideImplementations.size() > 0) {
			return overrideImplementations.get(overrideImplementations.size() - 1);
		} else {
			return new LookupAdapter(name, type);
		}
	}

	public void removeOverrideImplementation() {
		if (overrideImplementations.size() > 0) {
			overrideImplementations.remove(overrideImplementations.size() - 1);
		}
	}

	public void addOverrideImplementation(Lookup overrideImplementation) {
		overrideImplementations.add(overrideImplementation);
	}

	private static LookupFactory factoryInstance = new LookupFactory();

	public static LookupFactory getFactory() {
		return factoryInstance;
	}
}
// /CLOVER:ON