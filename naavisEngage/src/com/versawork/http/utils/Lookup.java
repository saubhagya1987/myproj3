package com.versawork.http.utils;

import org.xbill.DNS.Record;
import org.xbill.DNS.Resolver;

public interface Lookup {
	public void setResolver(Resolver res);

	public Record[] run();
}
