package com.versawork.http.utils;

import org.xbill.DNS.Name;
import org.xbill.DNS.Record;
import org.xbill.DNS.Resolver;

public class LookupAdapter implements Lookup {

	private final org.xbill.DNS.Lookup lu;

	public LookupAdapter(Name name, int type) {
		lu = new org.xbill.DNS.Lookup(name, type);
	}

	@Override
	public void setResolver(Resolver res) {
		lu.setResolver(res);
	}

	@Override
	public Record[] run() {
		return lu.run();
	}

}