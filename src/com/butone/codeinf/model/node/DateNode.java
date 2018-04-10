package com.butone.codeinf.model.node;

import com.butone.codeinf.model.emu.DataType;

/**
 *日期时间节点
 * @author HQ
 * @version  1.0, 2014-3-9 下午10:23:06
 */
public class DateNode extends Node {

	private static final long serialVersionUID = -2245683914402446020L;

	//是否显示年份格式
	private boolean useYear;
	//年格式类型
	private String yearCodeType = DataType.IS_Lower_Year_yy.name();

	//是否显示月份格式
	private boolean useMonth;
	//月格式类型
	private String monthCodeType = DataType.IS_Lower_Month_NotFillZero.name();

	//是否显示日格式
	private boolean useDay;
	//日格式类型
	private String dayCodeType = DataType.IS_Lower_Day_NotFillZero.name();

	//两位年份小写(yy)
	//两位年份大写(yy)
	//四位年份小写(yyyy)
	//四位年份大写(yyyy)

	//不补零月份小写(m)
	//补零月份小写 (mm)
	//汉字月份大写(M)

	//不补零日期小写(d)
	//补零日期小写 (dd)
	//汉字日期大写(D)

	public boolean isUseYear() {
		return useYear;
	}

	public void setUseYear(boolean useYear) {
		this.useYear = useYear;
	}

	public String getYearCodeType() {
		return yearCodeType;
	}

	public void setYearCodeType(String yearCodeType) {
		this.yearCodeType = yearCodeType;
	}

	public boolean isUseMonth() {
		return useMonth;
	}

	public void setUseMonth(boolean useMonth) {
		this.useMonth = useMonth;
	}

	public String getMonthCodeType() {
		return monthCodeType;
	}

	public void setMonthCodeType(String monthCodeType) {
		this.monthCodeType = monthCodeType;
	}

	public boolean isUseDay() {
		return useDay;
	}

	public void setUseDay(boolean useDay) {
		this.useDay = useDay;
	}

	public String getDayCodeType() {
		return dayCodeType;
	}

	public void setDayCodeType(String dayCodeType) {
		this.dayCodeType = dayCodeType;
	}
}
