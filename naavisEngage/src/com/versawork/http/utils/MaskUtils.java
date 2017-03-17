package com.versawork.http.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

/**
 * @author nitin.malik
 * 
 */

@Component
public class MaskUtils {

	private static final String DIGEST_ALGORITHM = "SHA-256";
	public static char NIBBLE_TO_CHAR[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	/**
	 * Generates and returns the random string.
	 * 
	 * Generated password must conform to following rules :- 1. Must be of
	 * specified length 2. Must contains atleast 1 UpperCase Character 3. Must
	 * contains atleast 1 Special Character from allowed list of characters 4.
	 * Must contains atleast 1 Numeric Character
	 * 
	 * @return
	 */
	public static String generateSecurityCode(Integer passLength) {

		/*
		 * int noOfCAPSAlpha = 1; int noOfDigits = 1; int noOfSplChars = 1;
		 */
		SecureRandom rnd = new SecureRandom();
		char[] securityCode = new char[passLength];

		int index = 0;

		/*
		 * for (int i = 0; i < noOfCAPSAlpha; i++) { index = getNextIndex(rnd,
		 * passLength, securityCode); securityCode[index] =
		 * ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length())); }
		 */
		for (int i = 0; i < passLength; i++) {
			index = getNextIndex(rnd, passLength, securityCode);
			securityCode[index] = NUM.charAt(rnd.nextInt(NUM.length()));
		}
		/*
		 * for (int i = 0; i < noOfSplChars; i++) { index = getNextIndex(rnd,
		 * passLength, securityCode); securityCode[index] =
		 * SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length())); } for(int i = 0; i
		 * < passLength; i++) { if(securityCode[i] == 0) { securityCode[i] =
		 * ALPHA.charAt(rnd.nextInt(ALPHA.length())); } }
		 */
		return new String(securityCode);
	}

	public static String generateEndpointUserId(Integer passLength) {

		int noOfCAPSAlpha = 1;
		int noOfDigits = 2;
		int noOfSplChars = 0;

		SecureRandom rnd = new SecureRandom();
		char[] tempPass = new char[passLength];

		int index = 0;

		for (int i = 0; i < noOfCAPSAlpha; i++) {
			index = getNextIndex(rnd, passLength, tempPass);
			tempPass[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
		}
		for (int i = 0; i < noOfDigits; i++) {
			index = getNextIndex(rnd, passLength, tempPass);
			tempPass[index] = NUM.charAt(rnd.nextInt(NUM.length()));
		}
		for (int i = 0; i < noOfSplChars; i++) {
			index = getNextIndex(rnd, passLength, tempPass);
			tempPass[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
		}
		for (int i = 0; i < passLength; i++) {
			if (tempPass[i] == 0) {
				tempPass[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
			}
		}
		return new String(tempPass);
	}

	/**
	 * Calculate and return the digest of given bytes
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getDigest(byte[] bytes) {

		try {
			MessageDigest messageDigest = MessageDigest.getInstance(DIGEST_ALGORITHM);
			return byteArrayToHexString(messageDigest.digest(bytes));
		} catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * Convert and returns the bytearray into hex string
	 * 
	 * @param data
	 * @return
	 */
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

	public static String generateRandomSalt() {

		byte[] bytes = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		bytes = secureRandom.generateSeed(16);

		return byteArrayToHexString(bytes);
	}

	public static String generateRandomSaltBySize() {

		byte[] bytes = new byte[3];
		SecureRandom secureRandom = new SecureRandom();
		bytes = secureRandom.generateSeed(3);

		return byteArrayToHexString(bytes);
	}

	private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUM = "0123456789";
	private static final String SPL_CHARS = "!@#$%^&";

	/**
	 * Generates and returns the temporary password.
	 * 
	 * Generated password must conform to following rules :- 1. Must be of
	 * specified length 2. Must contains atleast 1 UpperCase Character 3. Must
	 * contains atleast 1 Special Character from allowed list of characters 4.
	 * Must contains atleast 1 Numeric Character
	 * 
	 * @return
	 */
	public static String generateTempPassword() {

		int noOfCAPSAlpha = 1;
		int noOfDigits = 1;
		int noOfSplChars = 1;
		int passLength = 8;

		SecureRandom rnd = new SecureRandom();
		char[] tempPass = new char[passLength];

		int index = 0;

		for (int i = 0; i < noOfCAPSAlpha; i++) {
			index = getNextIndex(rnd, passLength, tempPass);
			tempPass[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
		}
		for (int i = 0; i < noOfDigits; i++) {
			index = getNextIndex(rnd, passLength, tempPass);
			tempPass[index] = NUM.charAt(rnd.nextInt(NUM.length()));
		}
		for (int i = 0; i < noOfSplChars; i++) {
			index = getNextIndex(rnd, passLength, tempPass);
			tempPass[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
		}
		for (int i = 0; i < passLength; i++) {
			if (tempPass[i] == 0) {
				tempPass[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
			}
		}
		return new String(tempPass);
	}

	/**
	 * @param rnd
	 * @param len
	 * @param pswd
	 * @return
	 */
	private static int getNextIndex(SecureRandom rnd, int len, char[] pswd) {

		int index = rnd.nextInt(len);

		while (pswd[index] != 0) {
			index = rnd.nextInt(len);
		}
		return index;
	}

	public static void main(String[] args) {
	}
}
