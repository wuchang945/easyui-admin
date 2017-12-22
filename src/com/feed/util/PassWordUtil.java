package com.feed.util;

import java.util.Random;

public class PassWordUtil {
public static String getPassWord(int length){
	      String val = "";   
	      Random random = new Random();   
	      for(int i = 0; i < length; i++) {   
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字   
	        if("char".equalsIgnoreCase(charOrNum)) { // 字符串   
	        	int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母   
	        	val += (char) (choice + random.nextInt(26));   
	        }   
	        else if("num".equalsIgnoreCase(charOrNum)) {// 数字   
	        	val += String.valueOf(random.nextInt(10));   
	        }   
	      }
	      val=val.toLowerCase();
	      return val;
	  }  
}
