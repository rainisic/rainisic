/* @(#) DESEncryptor.java
 * 
 * Date: 2012-2-17
 *
 * Author: Rainisic
 *
 * Description: none.
 */
package com.smt.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Rainisic
 * 
 */
public class DESEncryptor {

	/** Define the key. */
	private static Key key;

	/**
	 * Generate the key.
	 */
	private static void generatKey() {

		try {

			// Create a key generator and initialize the generator.
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(new String("micro_blog").getBytes()));

			// Generate a key.
			key = generator.generateKey();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Encrypt the string.
	 * 
	 * @param src
	 * @return
	 */
	public static String encrypt(String src) {

		// Check the key.
		if (key == null) {
			generatKey();
		}

		// Define the result.
		String result = "";

		// Define the encoder.
		BASE64Encoder base64en = new BASE64Encoder();

		try {

			// Define a DES cipher and initialize the cipher.
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			// Encrypt.
			result = base64en.encode(cipher.doFinal(src.getBytes("UTF8")));

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Decrypt the string.
	 * 
	 * @param src
	 * @return
	 */
	public static String decrypt(String src) {

		// Check the key.
		if (key == null) {
			generatKey();
		}
		
		// Define the result.
		String result = "";

		// Define the decoder.
		BASE64Decoder base64De = new BASE64Decoder();

		try {

			// Define a DES cipher and initialize the cipher.
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);

			// Decrypt.
			result = new String(cipher.doFinal(base64De.decodeBuffer(src)),
					"UTF8");

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		String en = DESEncryptor.encrypt("test");
		System.out.println(en);
		System.out.println(DESEncryptor.decrypt(en));
	}
}
