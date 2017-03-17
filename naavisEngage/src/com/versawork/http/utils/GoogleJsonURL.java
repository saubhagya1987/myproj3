package com.versawork.http.utils;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GoogleJsonURL {

	private static void getLatLongValues(String originLatLong, String destLong) {
		final String uri = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + originLatLong + "&"
				+ "destinations=" + destLong;

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

	}

	public static void main(String[] args) {
		getLatLongValues("28.6100,77.2300", "19.07283,72.88261|28.5700,77.3200");
	}

}
