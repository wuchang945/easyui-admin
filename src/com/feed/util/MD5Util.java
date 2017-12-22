package com.feed.util;

import java.security.MessageDigest;

import sun.misc.BASE64Decoder;

/**
 * @ClassName: MD5Util
 * @author: 周伟 @date: 2016-3-2
 * @Description: MD5加密工具
 */
public class MD5Util {
	
	/**
	 * @param inStr  
	 * 			原始密码
	 * @return
	 * @throws Exception
	 */
	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = md5Bytes[i] & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	/**
	 * @param inStr  
	 * 			原始密码
	 * @return
	 * @throws Exception
	 */
	public static String BASE64Encode(String ins){
		byte[] md = null;
		try {
			//String md5string = md5Encode("admin");
			md = (new BASE64Decoder()).decodeBuffer(ins);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md.toString();
	}
}
