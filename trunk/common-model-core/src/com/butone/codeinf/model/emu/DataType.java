package com.butone.codeinf.model.emu;


public enum DataType {
	/**
	 * 两位年份小写(yy)
	 */
	IS_Lower_Year_yy,

	/**
	 * 两位年份大写(yy)
	 */
	IS_Capital_Year_yy,

	/**
	 * 四位年份小写(yyyy)
	 */
	IS_Lower_Year_yyyy,

	/**
	 * 四位年份大写(yyyy)
	 */
	IS_Capital_Year_yyyy,

	/**
	 * 不补零月份小写(m)
	 */
	IS_Lower_Month_NotFillZero,

	/**
	 * 补零月份小写 (mm)
	 */
	IS_Lower_Month_FillZero,

	/**
	 * 汉字月份大写(M)
	 */
	IS_Chinese_Month,

	/**
	 * 不补零日期小写(d)
	 */
	IS_Lower_Day_NotFillZero,

	/**
	 * 补零日期小写 (dd)
	 */
	IS_Lower_Day_FillZero,

	/**
	 * 汉字日期大写(D)
	 */
	IS_Chinese_Day
}
