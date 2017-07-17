package com.hjl.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hjl on 2016/12/21.
 */
public final class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);


	/* 时间格式：yyyy-MM-dd HH:mm:ss */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	/* 时间格式：yyyy-MM-dd HH:mm:ss.s */
	public static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.s";

	/* 时间格式：yyyy-MM-dd */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	/* 时间格式：yyyyMMdd HH:mm:ss */
	public static final String DATE_FORMAT_03 = "yyyyMMdd HH:mm:ss";

	/* 时间格式：yyyyMMdd */
	public static final String DATE_FORMAT_04 = "yyyyMMdd";

	/* 时间格式：yyyyMM */
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

	/* 时间格式：YYYYMMDDHHMMSS */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/* 时间格式：YYYYMMDDHHMMSSSSS */
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

	/* 一小时：3600 * 1000 毫秒 */
	public static final long HOUR = 3600000l;

	public static final String PERIOD_UNIT_MINUTE = "m";
	public static final String PERIOD_UNIT_HOUR = "h";
	public static final String PERIOD_UNIT_DAY = "d";
	public static final String PERIOD_UNIT_MONTH = "M";



	/**
	 * 时间格式化
	 *
	 * @param date
	 * @param
	 * @return
	 */
	public static String format(Date date) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
			returnValue = df.format(date);
		}
		return returnValue;
	}
	/**
	 * 时间格式化
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return returnValue;
	}

	/**
	 * 时间格式化
	 *
	 * @param utcTime
	 * @param pattern
	 * @return
	 */
	public static String format(String utcTime, String pattern) {

		return format(Long.parseLong(utcTime) ,pattern);
	}

	public static String format(Long utcTime, String pattern) {
		String returnValue = "";
		if (utcTime != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(utcTime);
		}
		return returnValue;
	}

	/**
	 * 日期格式化
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern, Locale locale) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern, locale);
			returnValue = df.format(date);
		}
		return (returnValue);
	}

	/**
	 * 日期格式转换
	 *
	 * @param dateStr
	 * @param fromPattern
	 * @param toPattern
	 * @return
	 */
	public static String changeFormat(String dateStr, String fromPattern, String toPattern) {
		Date date = null;
		if (StringUtils.isNotEmpty(dateStr)) {
			date = parse(dateStr , fromPattern);
		}
		return format(date ,toPattern);
	}

	/**
	 * 将字符串类型的日期转化成date格式
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parse(String dateStr, String pattern) {
		Date date = null;
		if (StringUtils.isNotEmpty(dateStr)) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			try {
				date = df.parse(dateStr);
			} catch (ParseException e) {
				logger.error("method parse(String ,String) failed" ,e);
				return null;
			}
		}
		return date;
	}



	/**
	 * 将字符串类型的日期转化成UTC时间格式
	 * 精确到grade等级，如grade=1精确到毫秒，grade=1000精确到秒
	 *
	 * @param strDate
	 * @param pattern
	 * @param grade
	 * @return String [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String parseTime(String strDate , String pattern, long grade) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = df.parse(strDate);
			long time = date.getTime() / grade;
			return time + "";
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String parseTime(String strDate) {
		return parseTime(strDate , YYYY_MM_DD_HH_MM_SS ,1000L);
	}

	public static String parseTime(String strDate ,String pattern) {
		return parseTime(strDate , pattern ,1000L);
	}

	/**
	 * 获取时间戳  精确到毫秒
	 *
	 */
	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS_S);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 得到年
	 * @param date
	 * @return
	 */
	public static String getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	/**
	 * 按默认格式的字符串距离今天的天数
	 *
	 * @param date 日期字符串
	 * @return
	 */
	public static int countDays(String date) {
		return countDays(date , YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 按用户格式的字符串距离今天的天数
	 *
	 * @param date 日期字符串
	 * @return
	 */
	public static int countDays(String date , String pattern) {
		long t = Calendar.getInstance().getTime().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(parse(date, pattern));
		long t1 = c.getTime().getTime();
		return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
	}

	/**
	 * 获取某天凌晨时间
	 * <一句话功能简述>
	 * <功能详细描述>
	 *
	 * @param date
	 * @return Date [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static Date getMoring(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		return c.getTime();
	}

	/**
	 * 获取当前时间的时间戳，精确到毫秒
	 * <功能详细描述>
	 *
	 * @return String [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getCurrentTimeMillis(){
		return format(new Date() , DATE_FORMAT_YYYYMMDDHHMMSSSSS);
	}

	/**
	 * 获取多少天之后的日期
	 * <功能详细描述>
	 *
	 * @param startDate
	 * @param days
	 * @return String [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getAfterDate(String startDate, int days) {
		Date date;
		Calendar cal = Calendar.getInstance();
		try {
			date = (new SimpleDateFormat(YYYY_MM_DD)).parse(startDate);
			cal.setTime(date);
			cal.add(Calendar.DATE, days);
		} catch (ParseException e) {
			logger.error("method getAfterDate(String startDate, int days) failed" , e);
		}
		return format(cal.getTime() , YYYY_MM_DD);
	}

	/**
	 * 日期比较
	 * <功能详细描述>
	 *
	 * @param startDate
	 * @param endDate
	 * @return String [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean compareDate(String startDate, String endDate, String format) {
		Date newDate = parse(startDate, format);
		Date newDate2 = parse(endDate, format);
		return compareDate(newDate, newDate2);
	}

	/**
	 *  日期比较
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2){
		return date1.compareTo(date2) > 0;
	}


	/**
	 * 获取当前时间
	 * <功能详细描述>
	 *
	 * @return String [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String now(){
		return format(new Date() , YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 获取几分钟后
	 * <功能详细描述>
	 *
	 * @param date
	 * @param minute
	 * @return String [返回类型说明]
	 * @throws throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String afterMinute(int minute) {
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MINUTE, minute);
		return format(cale.getTime() , YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 *  获取指定日期 后几月的月初
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getAfterMonthStart(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期 几月后的日期
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getAfterMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 获取几天后的日期
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getAfterDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);

		return calendar.getTime();
	}

	/**
	 * Date转换为Timestamp
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Timestamp getDateToTimestamp(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String time;
		time = sdf.format(date);
		return Timestamp.valueOf(time);
	}

	/**
	 * String 转换 Timestamp
	 * @param str
	 * @return
	 */
	public static Timestamp getStrToTimestamp(String str) {
		Date date = parse(str, YYYY_MM_DD_HH_MM_SS);
		Timestamp ts = getDateToTimestamp(date,YYYY_MM_DD_HH_MM_SS);
		return ts;
	}

	/**
	 * Timestamp 转 String
	 */
	public static String timestampToString(Timestamp timestamp) {
		return timestampToString(timestamp, YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * Timestamp 转 String
	 */
	public static String timestampToString(Timestamp timestamp, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(timestamp);
	}

	/**
	 * 计算时间差
	 * @param time1 时间1
	 * @param time2 时间2
	 * @return 相差的月份
	 */
	public static int isCompareDate(String time1, String time2) {
		int month;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse(time1);
			Date date2 = sdf.parse(time2);
			long time = date1.getTime() - date2.getTime();
			month = (int) (time / 1000 / 3600 / 24 / 30);
		} catch (Exception e) {
			month = 0;
		}
		return month;
	}
	/**
	 * 以当前周类推，获取前几，后几周的星期一的最早时间
	 * @param n
	 * @return
	 */
	public static String getWeekMonday(int n){
		//n为推迟的周数，0本周，-1向前推迟一周，1下周，依次类推
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.DATE, n * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return format(cal.getTime(),"YYYY-MM-dd HH:mm:ss");

	}
	/**
	 * 以当前周类推，获取前几，后几周的星期天的最晚时间
	 * @param n
	 * @return
	 */
	public static String getWeekSunday(int n){
		//n为推迟的周数，0本周，-1向前推迟一周，1下周，依次类推
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.DATE, (n + 1) * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return format(cal.getTime(),"YYYY-MM-dd HH:mm:ss");

	}
	/**
	 * 以当前月类推，获取前几，后几月的第一天的最早时间
	 * @param n
	 * @return
	 */
	public static String getMonthFirstDay(int n){
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.MONTH, n);
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return format(cal.getTime(),"YYYY-MM-dd HH:mm:ss");
	}
	/**
	 * 以当前月类推，获取前几，后几月的最后一天的最晚时间
	 * @param n
	 * @return
	 */
	public static String getMonthLastDay(int n){
		Calendar cal =Calendar.getInstance();
		cal.add(Calendar.MONTH, n);
		cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return format(cal.getTime(),"YYYY-MM-dd HH:mm:ss");
	}


	public static void main(String[] args){

		System.out.println(parse("2016-18-00" , YYYY_MM_DD));

	}

}
