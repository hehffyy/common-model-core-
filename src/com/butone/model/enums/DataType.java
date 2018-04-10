package com.butone.model.enums;

import java.sql.Types;

/**
 * 字段数据类型，控制生成物理表列的数据类型
 * 
 * @author Administrator
 * 
 */
public enum DataType {
	String, Integer, Boolean, Float, DateTime, Date, Time, Decimal, Blob, Text;
	public static DataType getDataType(Class<?> cls) {
		if (cls == java.lang.String.class) {
			return String;
		} else if (cls == java.util.Date.class) {
			return DateTime;
		} else if (cls == java.lang.Integer.class || cls == int.class) {
			return Integer;
		} else if (cls == java.lang.Boolean.class || cls == boolean.class) {
			return Boolean;
		} else if (cls == java.lang.Double.class || cls == double.class
				|| cls == java.math.BigDecimal.class
				|| cls == java.math.BigInteger.class) {
			return Decimal;
		} else if (cls == java.lang.Float.class || cls == float.class) {
			return Float;
		}
		return null;
	}

	public String getDisplayName() {
		switch (this) {
		case String:
			return "字符";
		case Integer:
			return "整形";
		case Boolean:
			return "布尔";
		case Float:
			return "浮点";
		case DateTime:
			return "日期时间";
		case Date:
			return "日期";
		case Time:
			return "时间";
		case Decimal:
			return "数字";
		case Blob:
			return "BLOB";
		case Text:
			return "文本";
		default:
			throw new RuntimeException(this.toString() + "未申明显示名称");
		}

	}

	public static DataType transSqlType(int paramInt) {

		switch (paramInt) {
		case Types.BIT:
		case Types.TINYINT:
		case Types.BIGINT:
		case Types.INTEGER:
		case Types.SMALLINT:
			return DataType.Integer;
		case Types.CHAR:
		case Types.VARCHAR:
			return DataType.String;
		case Types.BLOB:
			return DataType.Blob;
		case Types.CLOB:
			return DataType.Text;
		case Types.DATE:
			return DataType.Date;
		case Types.TIMESTAMP:
			return DataType.DateTime;
		case Types.TIME:
			return DataType.Time;
		case Types.NUMERIC:
		case Types.DECIMAL:
		case Types.FLOAT:
		case Types.REAL:
		case Types.DOUBLE:
			return DataType.Decimal;
		}
		throw new RuntimeException("不支持的SQL类型：" + paramInt);
	}
}
