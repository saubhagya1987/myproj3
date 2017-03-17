package com.versawork.http.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class MaskUtil {

	private static final String[] DIGEST_ALGORITHM = { "SHA-256", "MD5", "SHA-1" };
	// MD5 SHA-1 SHA-256

	public static char NIBBLE_TO_CHAR[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	public static String getDigest(byte[] bytes, int instance) {

		try {
			MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM[instance]);
			return byteArrayToHexString(messageDigest.digest(bytes));
		} catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public static String byteArrayToHexString(byte[] data) {
		StringBuffer buffer = new StringBuffer();

		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				byte b = data[i];
				int I = ((char) b) & 0xFF;
				buffer.append(NIBBLE_TO_CHAR[I >>> 4]);
				buffer.append(NIBBLE_TO_CHAR[I & 0x0F]);

			}
		}
		return buffer.toString();
	}

	/**
	 * @param rnd
	 * @param len
	 * @param pswd
	 * @return
	 */
	public static int getNextIndex(SecureRandom rnd, int len, char[] pswd) {

		int index = rnd.nextInt(len);

		while (pswd[index] != 0) {
			index = rnd.nextInt(len);
		}
		return index;
	}

	public static void main(String[] args) {
	}
}
