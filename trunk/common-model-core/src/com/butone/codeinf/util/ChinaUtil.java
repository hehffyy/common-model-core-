package com.butone.codeinf.util;

import java.util.Calendar;
import java.util.Date;

/**
 *  大写操作工具类,网络copy,没验证稳定性
 * @author HQ
 * @version  1.0, 2014-3-12 下午12:17:20
 */
public class ChinaUtil {
	public static void main(String[] args) {
		String year = ChinaUtil.numToUpper(2014);
		String month = ChinaUtil.monthToUppder(3);
		String day = ChinaUtil.dayToUppder(23);
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
	}

	/**
	 * 日期转化为大小写
	 * @param date
	 * @return
	 */
	public static String dataToUpper(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		return numToUpper(year) + "年" + monthToUppder(month) + "月" + dayToUppder(day) + "日";
	}

	/**
	 * 将数字转化为大写
	 * @param num
	 * @return
	 */
	public static String numToUpper(int num) {
		// String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
		String u[] = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = String.valueOf(num).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	/**
	 * 将数字转化为大写补零
	 * @param num
	 * @param numlength 位数
	 * @return
	 */
	public static String numToUpper(int num, int numlength) {
		// String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
		String u[] = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = String.valueOf(num).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		//补零
		if (str.length < numlength) {
			int fillLength = numlength - str.length;
			for (int i = 0; i < fillLength; i++) {
				rstr = "零" + rstr;
			}
		}

		return rstr;
	}

	/**
	 * 月转化为大写
	 */
	public static String monthToUppder(int month) {
		if (month < 10) {
			return numToUpper(month);
		} else if (month == 10) {
			return "十";
		} else {
			return "十" + numToUpper(month - 10);
		}
	}

	/**
	 * 日转化为大写
	 * @param day
	 * @return
	 */
	public static String dayToUppder(int day) {
		if (day < 20) {
			return monthToUppder(day);
		} else {
			char[] str = String.valueOf(day).toCharArray();
			if (str[1] == '0') {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十";
			} else {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十" + numToUpper(Integer.parseInt(str[1] + ""));
			}
		}
	}
}
