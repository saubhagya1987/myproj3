package com.versawork.http.caching.files;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class JedisConnectionFactory {
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@PostConstruct
	public void init() {
		// System.out.println("bean successfully initialized");
	}

	@PreDestroy
	public void cleanUp() {
		// System.out.println("clean up called");
	}
}
