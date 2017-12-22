package com.feed.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
 
 
public class ElectronicScaleDES {
 
    public final static String DES_KEY_STRING = "jschrjLE";
     
    public static String encrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
 
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
 
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return encodeBase64(cipher.doFinal(message.getBytes("UTF-8")));
    }
 
    public static String decrypt(String message, String key) throws Exception {
 
        byte[] bytesrc = decodeBase64(message);//convertHexString(message);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte,"utf-8");
    }
 
    public static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
 
        return digest;
    }
 
    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }
 
     
    public static String encodeBase64(byte[] b) {
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
     
    public static byte[] decodeBase64(String base64String) {
        return Base64.decode(base64String, Base64.DEFAULT);
    }
    
    public static void main(String[] args) {
    	try {
			System.out.println(encrypt("jschrj_9773_1482823999616",DES_KEY_STRING));
			System.out.println(decrypt("TfPJA/jCDU9nmGdWyA4LrFjS7PM+yYLM0b3aG22NflCRY33JHJ/xE+Ns9EB7X4CmYIhviEVFXmujKtPACZ5h/5YjuinZUwRFffA8RNthx2X3lEOUkN2akRi7lYnX84M3zxMzv8ALQ56M+LJoNqHPo+ZvGTTr+jFPp1h3GIj1pvJL0SuoxqNvqlrj3l/S+ZlGcVRLj9nLBuHv5/HPCNUf+uj2i630I3xshT/QIPFz3XyGoL1VLdgzEZURHV19C3pTEJwDJ5E4b2kpVnMdr53qB/ajAYA5uzEmVNkEkv57thg4oqMpO6KbqnpsSAmeK+HoQxuviqIHS4dpnMGOVjWvoqCNww9IZEXXluWwJ6Jyg3vTrzCDRGmwcPb6WwK6+8qNwgsvzh6UGMyLf8Sk284vkw==", DES_KEY_STRING));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}