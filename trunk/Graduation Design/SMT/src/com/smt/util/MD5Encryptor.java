/* @(#) MD5Encryptor.java
 * 
 * Date: 2012-1-14
 *
 * Author: Rainisic
 */
package com.smt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Rainisic
 * 
 */
public class MD5Encryptor {

	/**
	 * Encrypt by MD5.
	 * @param value
	 * @return
	 */
	public static String encrypt(String value) {
		return md5Encrypt(md5Encrypt(value));
	}

	/**
	 * MD5 Encrypt algorithm.
	 * @param value
	 * @return
	 */
	private static String md5Encrypt(String value) {

		// Define the result string.
		StringBuffer result = new StringBuffer();

		// Define the encrypted byte array.
		byte[] encryptedBytes = null;

		try {
			
			// Encrypt.
			encryptedBytes = MessageDigest.getInstance("MD5").digest(value.getBytes());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < encryptedBytes.length; i++) {
			
			// Transfer to string.
			if (Integer.toHexString(0xFF & encryptedBytes[i]).length() == 1) {
				result.append("0").append(Integer.toHexString(0xFF & encryptedBytes[i]));
			} else {
				result.append(Integer.toHexString(0xFF & encryptedBytes[i]));
			}
		}
		
		return result.toString();
	}
}
