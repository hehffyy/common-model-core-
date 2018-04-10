package com.butone.model.utils;

public class ValueHelper {
	private static final String STRING_BOOL = "true|y|是|yes|1";

	public static boolean xor(boolean a, boolean b) {
		return a ^ b;
	}

	public static boolean isTrue(Object v) {
		return v != null && v.toString().toLowerCase().matches(STRING_BOOL);
	}

	public static boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}

	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	public static String toUpperCase(String str) {
		return str == null ? null : str.toUpperCase();
	}

	public static String toLowerCase(String str) {
		return str == null ? null : str.toLowerCase();
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}
}
