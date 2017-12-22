package com.feed.ecp.common.util;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;


public class StringUtil {
	
	/**
	 * 把第一个字母转为大写字母，其他字母全为小写
	 * @param str 字符串
	 * @return 新字符串
	 */
	public static String formatFirstUpper(String str) {
		 str = str.toLowerCase();
		return str.replaceFirst(str.charAt(0) +"", (str.charAt(0) +"").toUpperCase());
	}
	
	/**
	 * 把第一个字母转为大写字母
	 * @param str 字符串
	 * @return 新字符串
	 */
	public static String parseFirstUpper(String str) {
		return str.replaceFirst(str.charAt(0) +"", (str.charAt(0) +"").toUpperCase());
	}
	
	/**
	 * 把第一个字母转为小写字母
	 * @param str 字符串
	 * @return 新字符串
	 */
	public static String parseFirstLower(String str) {
		return str.replaceFirst(str.charAt(0) +"", (str.charAt(0) +"").toLowerCase());
	}
	
	/**
	 * 判断一个字符串是否为空
	 * @param str 字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		if(null == str || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断一堆字符串是否全为空
	 * @param strings 字符串
	 * @return 是否为空
	 */
	public static boolean isAllEmpty(String...strings) {
		boolean rt = true;
		for(String str : strings) {
			rt = rt && isEmpty(str);
		}
		return rt;
	}
	
	/**
	 * 是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isAllNum(String str) {
		if(null == str || "".equals(str.trim())) {
			return false;
		}
		
		char[]array = str.toCharArray();
		boolean isAllNum = true;
		for(int i=0;i<array.length;i++){
			if(array[i] < '0' || array[i] > '9'){
				isAllNum = false;
				break;
			}
		}
		return isAllNum;
	}
	
	/**
	 * 判断一堆字符串是否存在一个为空
	 * @param strings 字符串
	 * @return 是否为空
	 */
	public static boolean hasOneEmpty(String...strings) {
		boolean rt = false;
		for(String str : strings) {
			rt = rt || isEmpty(str);
		}
		return rt;
	}
	
	/**
	 * 手机号验证
	 * 
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}
	
	/**
	 * 过滤字段，
	 * @param str
	 * @return
	 */
	public static String filterStr(String str, String filter) {
		if(null == filter || "".equals(filter)) {
			return str;
		}
		
		if(filter.length() == 1) {
			str.replace(filter, "*");
		}
		
		if(filter.length() > 1) {
			str.replace(filter, new StringBuffer(filter).insert(1, " "));
		}
		
		return str;
	}
	
	/**
	 * 
	 * 将List中所有Bean的指定属性值按照给定的分隔符拼接起来
	 * 
	 * @param pojoList
	 * @param clazz
	 * @param propName
	 * @param separator
	 * @param formate
	 * @return
	 *
	 * @变更记录 2014-4-20 下午5:32:08 LiMiao 创建
	 *
	 */
	public static String beanPropToString(List<?> pojoList, Class<?> clazz, String propName, String separator, SimpleDateFormat formate) {
		StringBuilder sb = new StringBuilder("");
		if(!CollectionUtils.isEmpty(pojoList) && !StringUtils.isEmpty(propName)) {
			try {
				PropertyDescriptor propDesc = new PropertyDescriptor(propName, clazz);
				Method method = propDesc.getReadMethod();
				
				int size = pojoList.size();
				for(int i = 0; i < size; i++) {
					Object value = method.invoke(pojoList.get(i));
					if (value == null) {
						continue;
					}
					if(value.getClass() == Date.class) {
						value = formate.format(value);
					}
					sb.append(value).append(separator);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - separator.length());
	}
	
	/**
	 * 
	 * 将List中所有Bean的指定属性值按照给定的分隔符拼接起来
	 * 
	 * @param pojoList
	 * @param clazz
	 * @param propName
	 * @return
	 *
	 * @变更记录 2014-4-20 下午5:33:05 LiMiao 创建
	 *
	 */
	public static String beanPropToString(List<?> pojoList,Class<?> clazz, String propName) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy年MM月dd日");
		return beanPropToString(pojoList, clazz, propName, ",", formate);
	}
	
	/**
	 * 
	 * 将List中所有Bean的指定属性值按照给定的分隔符拼接起来
	 * 
	 * @param pojoList
	 * @param propName
	 * @return
	 *
	 * @变更记录 2014-4-20 下午5:33:14 LiMiao 创建
	 *
	 */
	public static String beanPropToString(List<?> pojoList, String propName) {
		ParameterizedType type = (ParameterizedType) pojoList.getClass()
				.getGenericSuperclass();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy年MM月dd日");
		Class<?> clazz = (Class<?>)type.getActualTypeArguments()[0];
		return beanPropToString(pojoList, clazz, propName, ",", formate);
	}
	
	/**
	 * 
	 * 将字符串转换为Unicode字符串
	 * 
	 * @param str
	 * @return
	 *
	 * @变更记录 2014-4-24 下午4:45:00 LiMiao 创建
	 *
	 */
	public static String toUnicode(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuilder sb = new StringBuilder();
		char c;
		int i, j;
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1) {
				sb.append("0");
			}
			sb.append(tmp);
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * 产生一个包含字母或数字的指定长度的字符串
	 * 
	 * @param length
	 * @return
	 *
	 * @变更记录 2014-6-25 上午10:56:07 LiMiao 创建
	 *
	 */
	public static String generateRandomData(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++) {
			int index = random.nextInt(base.length());
			sb.append(base.charAt(index));
		}
		return sb.toString();
	}
	
	/**
	 * 截取文件名后辍
	 * @param resFileName
	 * @return
	 */
	public static String findFileSuffix(String resFileName) {
        // 取得文件名和后辍
        String suffix = "";
        if (resFileName.indexOf(".") != -1)
        {
          suffix = resFileName.substring(resFileName.lastIndexOf("."));
        }
        
        return suffix;
	}
	
	/**
	 * 截取文件名前辍
	 * @param resFileName
	 * @return
	 */
	public static String findFilePrefix(String resFileName) {
		// 取得文件名和后辍
		String prefix = resFileName;
		if (resFileName.indexOf(".") != -1)
		{
			prefix = resFileName.substring(0, resFileName.lastIndexOf("."));
		}
		
		return prefix;
	}
	
	public static void main(String[] args) {
		System.out.println(isAllNum("adfasdxx.txt"));
//		System.out.println(findFileSuffix("adfasd.txt"));
	}
}
