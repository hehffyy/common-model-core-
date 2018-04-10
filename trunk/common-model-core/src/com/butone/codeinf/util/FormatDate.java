package com.butone.codeinf.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatDate {

	public FormatDate() {

	}
	
	public static String formatDate(String dateString,String format){
		
        try {
			if(dateString == null || dateString.equals("") ){
				return "";
			}
        	SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
			Date date = bartDateFormat.parse(dateString);
			return bartDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	public  static String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;

	}
	
	public  static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;

	}
	public  static String getCurrentYearToStr(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;

	}
	
	public  static int getCurrentYear(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String mDateTime = formatter.format(cal.getTime());
		return Integer.valueOf(mDateTime);

	}
	
	
	public  static String getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;

	}
	
	public  static int getCurrentMonth(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String mDateTime = formatter.format(cal.getTime());
		return Integer.valueOf(mDateTime);
	}
	
	public  static String getCurrentMonthFillZero(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String mDateTime = formatter.format(cal.getTime());
		int num = Integer.valueOf(mDateTime);
		if(num < 10){
			return String.format("%02d", num);
		}
		return mDateTime;
	}
	
	
	public  static int getCurrentday() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		String mDateTime = formatter.format(cal.getTime());
		return Integer.valueOf(mDateTime);

	}
	
	public  static int getCurrentday(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String mDateTime = formatter.format(cal.getTime());
		return Integer.valueOf(mDateTime);

	}
	public  static String getCurrentdayFillZero(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String mDateTime = formatter.format(cal.getTime());
		int num = Integer.valueOf(mDateTime);
		if(num < 10){
			return String.format("%02d", num);
		}
		return mDateTime;

	}

	
	public  static String getCurrentHour() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("HH");
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;

	}
	
	public  static String getCurrentMinute() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("mm");
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;

	}
	
	public  static int getCurrentWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);

	}
	public  static String getTimeToStr(Date date,String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String mDateTime = formatter.format(date);
		return mDateTime;

	}
	
	
	public static  Date parseDate(String dateStr, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
	public static void main(String[] args) {
//		String str = FormatDate.formatDate("1979-12-05 12:24:00", "yyyy-MM-dd HH:mm:ss");
//		System.out.println(str);
		//int str = FormatDate.getCurrentWeek();
//		Date d = FormatDate.parseDate("20080912","yyyyMMdd");
//		System.out.println(d.getMonth());

		int str = FormatDate.getCurrentMonth("MM");
		System.out.println(str);
		
	
		String str2 = String.format("%02d", 3);

		System.out.println(str2);
	}
	
}

