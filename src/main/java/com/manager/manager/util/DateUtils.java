package com.manager.manager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static final String FORMATE_1 = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATE_2 = "yyyy-MM-dd";
	public static final String FORMATE_3 = "yyyyMMddHHmmss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String FORMATE_4 = "yyyy-MM";

	public static String getNowDesc() {
		SimpleDateFormat df = new SimpleDateFormat(FORMATE_3);
		return df.format(new Date());
	}

	public static String getYYYYMMDD() {
		SimpleDateFormat df = new SimpleDateFormat(YYYYMMDD);
		return df.format(new Date());
	}

	// 获取本周周一
	public static Date getThisWeekMonday() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(FORMATE_2);
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		return cal.getTime();
	}
	public static Date getThisWeekSunday(Date monday) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(monday);
		cal.add(Calendar.DATE, +6);
		return cal.getTime();
	}

	// 获取上周周一
	public static Date getLastWeekMonday(Date monday) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(monday);
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}
	// 获取上周周日
	public static Date getLastWeekSunday(Date monday) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(monday);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	// 判断是否已经达到7天
	public static boolean isEnd(Date enDate) {
		long endLong = DateUtils.getDateOf235959(enDate).getTime() + 24 * 7 * 3600 * 1000;
		return System.currentTimeMillis() - endLong > 0;
	}

	// 判断是否为周一
	public static boolean isMonday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return week == 1;
	}
	// 判断是否为周二
	public static boolean isTuesday() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return week == 2;
	}
	
	public static int getWeekDayIsNum(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return week ;
	}
	
	
	
	// 计算当前时间的 前几天
	public static String getNowBeforeDateStr(int beforeNum) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_2);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -beforeNum);
		date = calendar.getTime();
		return sdf.format(date);
	}

	public static Date getNowBeforeDate(int beforeNum) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -beforeNum);
		date = calendar.getTime();
		return date;
	}

	public static Date changeToDate(String dateStr, String formate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			Date date = sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static String changeToString(Date date, String formate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		// 一 monday
		// 二 tuesday
		// 三 wednesday
		// 四thursday
		// 五 friday
		// 六 saturday
		// 七 日 sunday
		String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

//	public static void main(String[] args) {
//		Date beginDate = null;
//		Date enDate = DateUtils.getDateOf235959(DateUtils.getNowBeforeDate(1));
//		if (DateUtils.isMonday()) {//是否为周一
//			//周一
//			beginDate = DateUtils.getDateOf000000(DateUtils.getLastWeekMonday());
//		}else{
//			beginDate = DateUtils.getDateOf000000(DateUtils.getThisWeekMon());
//		}
//		
//		System.out.println(beginDate);
//		System.out.println(enDate);
//	}
	
	public static Date getMondayByDate(Date time) {  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式  
	    Calendar cal = Calendar.getInstance();  
	    cal.setTime(time);  
	    // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
	    int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	    if (1 == dayWeek) {  
	        cal.add(Calendar.DAY_OF_MONTH, -1);  
	    }  
	    System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
	    cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
	    int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
	    cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
	    return cal.getTime();
	} 
	public static void main(String[] args) {
		
		System.out.println(getMondayByDate(changeToDate("2017-11-06 00:01:00", FORMATE_1)));;
		
		Date beginDate = null;
		Date enDate = null;
		Date classEndDateAfter7 = changeToDate("2017-11-07 00:01:00", FORMATE_1);
		int weekNum = DateUtils.getWeekDayIsNum(classEndDateAfter7);
		
		if (weekNum ==1) {//设置结束日期为周一
			//找上周的
			beginDate = DateUtils.getLastWeekMonday(classEndDateAfter7);
			enDate = DateUtils.getLastWeekSunday(classEndDateAfter7);
		}else{
			//周一
			Date monday = DateUtils.getMondayByDate(classEndDateAfter7);
			//找本周的
			beginDate = monday;
			enDate = DateUtils.getThisWeekSunday(monday);
		}
		System.out.println(beginDate);
		System.out.println(enDate);
	}

	// 获取执行时间的00:00:00
	public static Date getDateOf000000(Date date) {
		String dateStr = changeToString(date, FORMATE_2);
		dateStr = dateStr + " 00:00:00";
		return changeToDate(dateStr, FORMATE_1);
	}

	// 获取执行时间的23:59:59
	public static Date getDateOf235959(Date date) {
		String dateStr = changeToString(date, FORMATE_2);
		dateStr = dateStr + " 23:59:59";
		return changeToDate(dateStr, FORMATE_1);
	}

}
