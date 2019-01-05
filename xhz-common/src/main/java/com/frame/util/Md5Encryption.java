package com.frame.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encryption {
	/**
	 * MD5小写32位加密
	 */
	public String Md5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 确定MD5加密计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(str.getBytes());
		StringBuffer stringBuffer = new StringBuffer();
		for (byte b : bytes) {
			int bt = b & 0xff;
			if (bt < 16) {
				stringBuffer.append(0);
			}
			stringBuffer.append(Integer.toHexString(bt));
		}
		// 加密后的密码字符串
		String pass = stringBuffer.toString();
		return pass;
	}
}
