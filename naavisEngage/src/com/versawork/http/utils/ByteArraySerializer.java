package com.versawork.http.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class ByteArraySerializer extends JsonSerializer<byte[]> {

	private static int unsignedToBytes(byte b) {
		return b & 0xFF;
	}

	@Override
	public void serialize(byte[] bytes, org.codehaus.jackson.JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeStartArray();

		for (byte b : bytes) {
			jgen.writeNumber(unsignedToBytes(b));
		}

		jgen.writeEndArray();

	}

}