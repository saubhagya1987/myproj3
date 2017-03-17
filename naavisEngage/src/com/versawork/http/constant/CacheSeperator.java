package com.versawork.http.constant;

public enum CacheSeperator {

	KEYTOKEN('_');
	private Character token;

	private CacheSeperator(Character token) {
		this.token = token;
	}

	public Character getToken() {
		return token;
	}

}
