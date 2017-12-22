package com.feed.ecp.common.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.feed.ecp.common.modelDTO.TimePeriod;



/**
 * 日期格式类
 * @author AmoX
 *
 */
public class DateUtil {
	
	private static final Logger LOGGER = Logger.getLogger(DateUtil.class);
	
	public static SimpleDateFormat CUR_YEAR = new SimpleDateFormat("yyyy");
	
	public static SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");
	
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	
	public static SimpleDateFormat FIRST_DAY_OF_YEAR = new SimpleDateFormat("yyyy-01-01");
	
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	
	public static SimpleDateFormat DAY_FORMAT = new SimpleDateFormat(PATTERN_DATE);
	
	public static SimpleDateFormat DAY_FORMAT_CN = new SimpleDateFormat("yyyy年MM月dd日");
	
	public static SimpleDateFormat MONTH_FORMAT_CN = new SimpleDateFormat("yyyy年MM月");
	
	public static SimpleDateFormat FULL_DATE =  new SimpleDateFormat(PATTERN_STANDARD);
	
	/**
	 * 得到系统时间字符串，线程安全
	 * @return
	 */
	public static Long curTimeBuffer() {
		ThreadUtil.LOCK.lock();
		Long curTime = System.currentTimeMillis();
		ThreadUtil.LOCK.unlock();
		return curTime;
	}
	
	public static String getCurrentDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_STANDARD);
		return sdf.format(new Date());
	}
	
	public static String transferLongToDate(String dateFormat,Long millSec){
	     SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	     Date date= new Date(millSec);
	     return sdf.format(date);
	    }

	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(timestamp.getTime()));
	}

	public static String date2String(java.util.Date date, String pattern) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String currentTimestamp2String(String pattern) {
		return timestamp2String(currentTimestamp(), pattern);
	}

	public static Timestamp string2Timestamp(String strDateTime, String pattern) {
		if (strDateTime == null || strDateTime.equals("")) {
			throw new java.lang.IllegalArgumentException("Date Time Null Illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDateTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return new Timestamp(date.getTime());
	}

	public static Date string2Date(String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = sdf.parse(strDate);
			return date;
		} catch (ParseException e) {
			LOGGER.error("时间格式化错误[" + pattern + "] "+new Date());
		}
		
		try {
			date = new SimpleDateFormat(DateUtil.PATTERN_DATE).parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		return date;
	}

	public static String stringToYear(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return String.valueOf(c.get(Calendar.YEAR));
	}

	public static String stringToMonth(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// return String.valueOf(c.get(Calendar.MONTH));
		int month = c.get(Calendar.MONTH);
		month = month + 1;
		if (month < 10) {
			return "0" + month;
		}
		return String.valueOf(month);
	}

	public static String stringToDay(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			return "0" + day;
		}
		return "" + day;
	}

	public static Date getFirstDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		c.set(year, month, day, 0, 0, 0);
		return c.getTime();
	}

	public static Date getLastDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = 1;
		if (month > 11) {
			month = 0;
			year = year + 1;
		}
		c.set(year, month, day - 1, 0, 0, 0);
		return c.getTime();
	}

	public static String date2GregorianCalendarString(Date date) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("Date is null");
		}
		long tmp = date.getTime();
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTimeInMillis(tmp);
		try {
			XMLGregorianCalendar t_XMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(ca);
			return t_XMLGregorianCalendar.normalize().toString();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			throw new java.lang.IllegalArgumentException("Date is null");
		}

	}

	public static boolean compareDate(Date firstDate, Date secondDate) {
		if (firstDate == null || secondDate == null) {
			throw new java.lang.RuntimeException();
		}

		String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
		String strSecondDate = date2String(secondDate, "yyyy-MM-dd");
		if (strFirstDate.equals(strSecondDate)) {
			return true;
		}
		return false;
	}
	
	public static Date getStartTimeOfDate(Date currentDate) {
		Assert.notNull(currentDate);
		String strDateTime = date2String(currentDate,"yyyy-MM-dd") + " 00:00:00";
		return string2Date(strDateTime,"yyyy-MM-dd hh:mm:ss");
	}
	
	public static Date getEndTimeOfDate(Date currentDate) {
		Assert.notNull(currentDate);
		String strDateTime = date2String(currentDate,"yyyy-MM-dd") + " 23:59:59";
		return string2Date(strDateTime,"yyyy-MM-dd hh:mm:ss");
	}
	
	/**
	 * 获取当年的第一天
	 * @param year
	 * @return
	 */
	public static Date getCurrYearFirst(){
		Calendar currCal=Calendar.getInstance();  
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}
	
	/**
	 * 获取当年的最后一天
	 * @param year
	 * @return
	 */
	public static Date getCurrYearLast(){
		Calendar currCal=Calendar.getInstance();  
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}
	
	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}
	
	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		String source = year + "-12-31 23:59:59";
		Date currYearLast = null;
		try {
			currYearLast = FULL_DATE.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return currYearLast;
	}
	
	/**
	 * 
	 * 在日期指定的字段上增加指定的数量后返回日期
	 * 
	 * @param date
	 * @param field
	 * @param num
	 * @return
	 *
	 * @变更记录 2014-5-5 下午4:24:32 LiMiao 创建
	 *
	 */
	public static Date dateAdd(Date date, int field, int num) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(field, num);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return gc.getTime();
	}
	
	/**
	 * 
	 * 在日期指定的字段上增加指定的数量后返回日期字符串
	 * 
	 * @param field
	 * @param date
	 * @param num
	 * @return
	 *
	 * @变更记录 2014-5-5 下午4:25:23 LiMiao 创建
	 *
	 */
	public static String dateAdd(int field, Date date, int num) {
		return dateAdd(date, field, num, FULL_DATE);
	}
	
	/**
	 * 
	 * 在日期指定的字段上增加指定的数量并按照指定的格式格式化后返回
	 * 
	 * @param date
	 * @param field
	 * @param num
	 * @param format
	 * @return
	 *
	 * @变更记录 2014-5-5 下午4:25:40 LiMiao 创建
	 *
	 */
	public static String dateAdd(Date date, int field, int num, SimpleDateFormat format) {
		Date rtDate = dateAdd(date, field, num);
		String limitTime = format.format(rtDate);
		return limitTime;
	}
	
	/**
	 * 
	 * 根据起始日期和截止日期 返回之间每个月起止时间段
	 * @return TimePeriod[]
	 */
	public static TimePeriod[] getTimePeriods(Date date1,Date date2) {
		List<TimePeriod> listTimePeriod = new ArrayList<TimePeriod>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		//对传入参数进行比较，总是将较小的Date赋于c1，将较大的Date赋于c2
		try{
			if(date1.getTime() <= date2.getTime()){
				c1.setTime(date1);
				c2.setTime(date2);
			}else{
				c1.setTime(date2);
				c2.setTime(date1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
		//计算出两个日期之间的月数
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH) ;
		int month2 = c2.get(Calendar.MONTH) ;
		
		int totalMonthCount = (year2-year1)*12 + (month2 - month1) + 1;
		
		//对获得的每个月份进行解析并封装成TimePeriod对象
		for (int i = 0; i < totalMonthCount; i++) {
			
			Calendar tempCalendar = Calendar.getInstance();
			tempCalendar.set(Calendar.YEAR, year1);
			tempCalendar.set(Calendar.MONTH, month1);
			
			if(i == 0){
				//起始日期
				tempCalendar.set(Calendar.DATE, c1.get(Calendar.DATE));
			}else{
				//每个月的第一天
				tempCalendar.set(Calendar.DATE, 1);
			}
			
			String firstDate = sdf.format(tempCalendar.getTime())+" 00:00:00";
			
			Date visitTimeLower = null;
			Date visitTimeUpper = null;
			try {
				visitTimeLower = sdf1.parse(firstDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			TimePeriod tp = new TimePeriod();
			tp.setVisitTimeLower(visitTimeLower);
			
			if(i == totalMonthCount - 1){
				//结束日期
				String lastDate = sdf.format(c2.getTime()) +" 23:59:59";
				try {
					visitTimeUpper = sdf1.parse(lastDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				tp.setVisitTimeUpper(visitTimeUpper);
			}else{
				//每个月的最后一天
				int date = tempCalendar.getActualMaximum(Calendar.DATE);
				tempCalendar.set(Calendar.DATE, date);
				String lastDate1 = sdf.format(tempCalendar.getTime()) +" 23:59:59";
				try {
					visitTimeUpper = sdf1.parse(lastDate1);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				tp.setVisitTimeUpper(visitTimeUpper);
			}
			
			tp.setCurMonth(tempCalendar.get(Calendar.MONTH) + 1);
			
			listTimePeriod.add(tp);
			
			//由起始日期所在月份开始累计，calendar对象会自动维护年份信息
			month1 += 1;
			
		}
		
		//TimePeriod[] timePeriods = listTimePeriod.toArray(new TimePeriod[0]);
		TimePeriod[] timePeriods = new TimePeriod[listTimePeriod.size()];
		for(int i=0;i<listTimePeriod.size();i++){
			timePeriods[i] = listTimePeriod.get(i);
		}
		return timePeriods;
	}
	
	/**
	 * 
	 * 根据起始日期和截止日期 返回之间每个月起止时间段
	 * @return TimePeriod[]
	 */
	public static TimePeriod[] getOneYearTimePeriods(Date date1,Date date2) {
		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		if(c1.get(Calendar.YEAR) < c2.get(Calendar.YEAR)){
			int year = c1.get(Calendar.YEAR);
			date2 = DateUtil.getYearLast(year);
		}else if(c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)){
			int year = c2.get(Calendar.YEAR);
			Date tempDate = date2;
			date2 = DateUtil.getYearLast(year);
			date1 = tempDate;
		}
		
		TimePeriod[] timePeriods = DateUtil.getTimePeriods(date1, date2);
		
		return timePeriods;
	}
	
	public static int getWeeksOfMonth(int year,int month){
		  Calendar c = Calendar.getInstance();
	        c.set(Calendar.YEAR, year); 
	        c.set(Calendar.MONTH, month-1);
	        return c.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}
}
