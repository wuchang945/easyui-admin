package com.feed.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;


public class SPUtils {
	/**
	 * Map<评分项_参考分值，对应的扣分>
	 */
	private static Map<String, Double> map = new HashMap<String, Double>();


	public static String genRandomNum(int pwd_len) {
		int maxNum = 36;

		int count = 0;
		char[] str = { '0', '1', '2', '3', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			int i = Math.abs(r.nextInt(36));

			if ((i >= 0) && (i < str.length)) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	public static String generatorNO() {
		Date currentDate = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(currentDate) + genRandomNum(6);
	}

	public static String generatorComplainNO() {
		Date currentDate = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(currentDate) + genRandomNum(3);
	}

	public static String getCurrentTime() {
		Date currentDate = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(currentDate);
	};

	public static int getStarNum(Double num) {
		int starNum = 5;
		if (num >= 90) {
			starNum = 5;
		} else if ((num >= 80) && (num < 90)) {
			starNum = 4;
		} else if ((num >= 70) && (num < 80)) {
			starNum = 3;
		} else if ((num >= 60) && (num < 70)) {
			starNum = 2;
		} else {
			starNum = 1;
		}
		return starNum;
	}

	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
																										// }
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	public static String getDdbh() {
		String ddbh = "DD" + generatorNO();
		return ddbh;
	}

	public static String getRkbh() {
		String rkbh = "RK" + generatorNO();
		return rkbh;
	}
	
	public static String getQtrkbh() {
		String qtrkbh = "QTRK" + generatorNO();
		return qtrkbh;
	}
	
	public static String getQtckbh() {
		String qtckbh = "QTCK" + generatorNO();
		return qtckbh;
	}
	public static void main(String[] args) {
		System.out.println(genRandomNum(6));
	}


	
	public static int getShouldCheckNum(String dangerLevel){
		int times=0;
		if("A".equals(dangerLevel)){
			times = 1;
		}else if("B".equals(dangerLevel)){
			times = 2;
		}else if("C".equals(dangerLevel)){
			times = 3;
		}else if("D".equals(dangerLevel)){
			times = 4;
		}
		return times;
	}
	
	
	
}
