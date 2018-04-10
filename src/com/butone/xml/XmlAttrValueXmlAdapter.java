package com.butone.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlAttrValueXmlAdapter extends XmlAdapter<String, String> {
	/**
	 * 将字符串src中的子字符串fnd全部替换为新子字符串rep.<br>
	 * 功能相当于java sdk 1.4的String.replaceAll方法.<br>
	 * 不同之处在于查找时不是使用正则表达式而是普通字符串.
	 */
	private String replaceAll(String src, String fnd, String rep) {
		if (src == null || src.equals("")) {
			return "";
		}

		String dst = src;

		int idx = dst.indexOf(fnd);

		while (idx >= 0) {
			dst = dst.substring(0, idx) + rep
					+ dst.substring(idx + fnd.length(), dst.length());
			idx = dst.indexOf(fnd, idx + rep.length());
		}

		return dst;
	}

	/**
	 * 转换为XML编码.<br>
	 */
	private String xmlEncode(String src) {
		if (src == null || src.equals("")) {
			return "";
		}

		String dst = src;
		dst = replaceAll(dst, "&", "&amp;");
		dst = replaceAll(dst, "<", "&lt;");
		dst = replaceAll(dst, ">", "&gt;");
		dst = replaceAll(dst, "\"", "&quot;");
		// dst = replaceAll(dst, "\'", "&apos;");// &acute;

		return dst;
	}

	/**
	 * 转换为XML编码.<br>
	 */
	private String xmlDecode(String src) {
		if (src == null || src.equals("")) {
			return "";
		}

		String dst = src;
		dst = replaceAll(dst, "&amp;", "&");
		dst = replaceAll(dst, "&lt;", "<");
		dst = replaceAll(dst, "&gt;", ">");
		dst = replaceAll(dst, "&quot;", "\"");
		// dst = replaceAll(dst, "&apos;", "\'");// &acute;

		return dst;
	}

	@Override
	// java to xml
	public String marshal(String v) throws Exception {
		if (v == null)
			return null;
		return xmlEncode(v);
	}

	@Override
	// xml - java
	public String unmarshal(String v) throws Exception {
		if (v == null)
			return null;
		return xmlDecode(v);
	}
}
