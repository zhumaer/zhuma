package com.zm.zhuma.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * @desc AES加、解密工具类
 * 
 * @author zhumaer
 * @since 6/20/2017 3:00 PM
 */
@Slf4j
public class AESUtil {

	public static final String DEFAULT_CODING = "utf-8";

	/**
	 * REVIEW
	 * @Description: 加密
	 * @param data 待加密字符串
	 * @param secretKey 秘钥
	 * @return 加密后的字符串
	 * @author zhumaer
	 */
	public static String encrypt(String data, String secretKey) {
		String encryptedData = null;
		try {
			byte[] input = data.getBytes(DEFAULT_CODING);

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(secretKey.getBytes(DEFAULT_CODING));
			SecretKeySpec skc = new SecretKeySpec(digest, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skc);

			byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
			encryptedData = parseByte2HexStr(cipherText);
		} catch (Exception e) {
			log.error("encrypt occurs exception, caused by:", e);
		}

		return encryptedData;
	}

	/**
	 * REVIEW
	 * @Description: 解密
	 * @param encryptedData 已加密的字符串
	 * @param secretKey 秘钥
	 * @return 解密后的字符串
	 * @author zhumaer
	 */
	public static String decrypt(String encryptedData, String secretKey) {
		String originalData = null;
		try {
			byte[] secretKeyByte = secretKey.getBytes(DEFAULT_CODING);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(secretKeyByte);
			SecretKeySpec secretKeySpec = new SecretKeySpec(digest, "AES");
			Cipher dCipher = Cipher.getInstance("AES");
			dCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

			byte[] clearByte = dCipher.doFinal(toByte(encryptedData));
			originalData = new String(clearByte);
		} catch (Exception e) {
			log.error("decrypt occurs exception, encryptedData:{}, caused by:{}", encryptedData, e);
		}

		return originalData;
	}

	/**
	 * 字符串转字节数组 
	 */
	private static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		}
		return result;
	}

	/**
	 * 字节转16进制字符串
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}

}
