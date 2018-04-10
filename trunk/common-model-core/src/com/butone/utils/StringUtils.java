package com.butone.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import net.sourceforge.pinyin4j.PinyinHelper;

import com.butone.model.utils.ValueHelper;

public class StringUtils {

	public static void main(String[] params) {
		String gbkStr = " I love You ！唐雯筠";
		System.out.println(convertGbkToPinyin(gbkStr));
		System.out.println(convertGbkToPinyin2(gbkStr));
		System.out
				.println(convertToJavaPackageName(convertGbkToPinyin(gbkStr)));
		System.out
				.println(convertToJavaPackageName(convertGbkToPinyin2(gbkStr)));
	}

	public static String getNewGuid32() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 产生GUID,最短12位
	 * 
	 * @param prev
	 * @param length
	 * @return
	 */
	public static String createNewGuid(String prev, int length) {
		String s = prev + UUIDMaker.generate();
		return StringUtils.pad(s.toUpperCase(), length, '0', false);
	}

	public static String getMD5String(byte[] bytes) {
		if (bytes == null)
			return null;
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(bytes);
			return bufferToHex(mdInst.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getFileMD5String(File file) throws IOException,
			NoSuchAlgorithmException {
		FileInputStream in = new FileInputStream(file);
		try {
			FileChannel ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY,
					0, file.length());
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(byteBuffer);

			return bufferToHex(mdInst.digest());
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private final static char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static String getMD5String(String s) throws NoSuchAlgorithmException {
		return getMD5String(s.getBytes());
	}

	// public static String MD5(String s) {
	// char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	// 'A', 'B', 'C', 'D', 'E', 'F' };
	// try {
	// byte[] btInput = s.getBytes();
	// // 获得MD5摘要算法的 MessageDigest 对象
	// MessageDigest mdInst = MessageDigest.getInstance("MD5");
	// // 使用指定的字节更新摘要
	// mdInst.update(btInput);
	// // 获得密文
	// byte[] md = mdInst.digest();
	// // 把密文转换成十六进制的字符串形式
	// int j = md.length;
	// char str[] = new char[j * 2];
	// int k = 0;
	// for (int i = 0; i < j; i++) {
	// byte byte0 = md[i];
	// str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	// str[k++] = hexDigits[byte0 & 0xf];
	// }
	// return new String(str);
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	public static String append(String str, int size, char p, boolean isprefixed) {
		StringBuffer retvalue = new StringBuffer();
		if (!isprefixed) {
			retvalue.append(str);
		}
		for (int i = 0; i < size; i++) {
			retvalue.append(p);
		}
		if (isprefixed) {
			retvalue.append(str);
		}
		return retvalue.toString();
	}

	/**
	 * 使用p扩充字符串str到size长度
	 * 
	 * @param str
	 * @param size
	 * @param p
	 * @param isprefixed
	 * @return
	 */
	public static String pad(String str, int length, char p, boolean isprefixed) {
		if (str == null)
			str = "";
		int str_size = str.length();
		int pad_len = length - str_size;
		StringBuffer retvalue = new StringBuffer();
		for (int i = 0; i < pad_len; i++) {
			retvalue.append(p);
		}
		if (isprefixed)
			return retvalue.append(str).toString();
		return retvalue.insert(0, str).toString();
	}

	/**
	 * 判断是否中文字符
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 字节截断
	 * 
	 * @param original
	 * @param byteLen
	 * @param charsetName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String cutString(String original, int byteLen,
			String charsetName) throws UnsupportedEncodingException {
		if (original == null || byteLen <= 0) {
			return null;
		}
		byte[] bt = original.getBytes(charsetName);
		if (byteLen > bt.length) {
			return original;
		}
		// 按照指定字节长度截断，再转成临时String，计算长度。
		int tempLen = new String(bt, 0, byteLen, charsetName).length();
		// 根据该长度右截取原字符串。
		String result = original.substring(0, tempLen);
		// 超过预订字节长度，则减一个字符截取。
		if (result != null && !"".equals(result.trim())
				&& result.getBytes(charsetName).length > byteLen) { // 判断字节长度。
			result = original.substring(0, tempLen - 1); // 超长，去掉一个字符
		}
		return result;
	}

	// *****************************拼音首字母********************************

	// 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
	private static int BEGIN = 45217;
	private static int END = 63486;
	// 按照声母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
	// 字母Z使用了两个标签，这里有27个值
	// i, u, v都不做声母, 跟随前面的字母
	private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',
			'哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',
			'塌', '挖', '昔', '压', '匝', '座' };
	// 二十六个字母区间对应二十七个端点
	// GB2312码汉字区间十进制表示
	private static int[] table = new int[27];
	// 对应首字母区间表
	private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			't', 't', 'w', 'x', 'y', 'z', };
	// 初始化
	static {
		for (int i = 0; i < 26; i++) {
			table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
		}
		table[26] = END;// 区间表结尾
	}

	/**
	 * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出
	 */
	public static String convertGbkToPinyin(String SourceStr) {
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				Result += Char2Initial(SourceStr.charAt(i));
			}
		} catch (Exception e) {
			Result = "";
		}
		return Result;
	}

	/**
	 * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0'
	 * 
	 */
	private static char Char2Initial(char ch) {
		// 对英文字母的处理：小写字母转换为大写，大写的直接返回
		if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z')
			return ch;

		// 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
		// 若不是，则直接返回。
		// 若是，则在码表内的进行判断。
		int gb = gbValue(ch);// 汉字转换首字母
		if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
			return ch;
		int i;
		for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
			if (match(i, gb))
				break;

		}

		if (gb == END) {// 补上GB2312区间最右端
			i = 25;
		}
		return initialtable[i]; // 在码表区间中，返回首字母
	}

	private static boolean match(int i, int gb) {
		// if ((gb >= table[i]) && (gb < table[i + 1]))
		// break;
		if (gb < table[i])
			return false;
		int j = i + 1;
		// 字母Z使用了两个标签
		while (j < 26 && (table[j] == table[i]))
			++j;
		if (j == 26)
			return gb <= table[j];
		else
			return gb < table[j];
	}

	/**
	 * 取出汉字的编码 cn 汉字
	 */
	private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}

	// ***************************集成pinyin4j的汉字首字母***********************
	public static String convertGbkToPinyin2(String gbkStr) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < gbkStr.length(); i++) {
			char c = gbkStr.charAt(i);
			String[] ss = PinyinHelper.toHanyuPinyinStringArray(c);
			if (ss != null && ss.length > 0)
				s.append(ss[0].charAt(0));
			else
				s.append(c);
		}
		return s.toString();
	}

	/**
	 * 转换为java包名
	 * 
	 * @param enStr
	 * @return
	 */
	public static String convertToJavaPackageName(String enStr) {
		if (ValueHelper.isEmpty(enStr))
			return enStr;
		StringBuffer s = new StringBuffer();
		char c = enStr.charAt(0);
		if (Character.isJavaIdentifierStart(c)) {
			s.append(c);
		} else {
			s.append("_");
		}
		for (int i = 1; i < enStr.length(); i++) {
			c = enStr.charAt(i);
			if (Character.isJavaIdentifierPart(c)) {
				s.append(c);
			} else {
				s.append("_");
			}
		}
		return s.toString();
	}

	/**
	 * 转换为java包名
	 * 
	 * @param enStr
	 * @return
	 */
	public static String convertZHCNToJavaPackageName(String zhcn) {
		String enStr = convertGbkToPinyin2(zhcn);
		return convertToJavaPackageName(enStr);
	}

	// ********************************Base64编码处理**************************
	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/' };

	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
			60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
			-1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
			38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
			-1, -1 };

	public static String base64Encode(byte[] data) {
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;

		while (i < len) {
			b1 = data[i++] & 0xff;
			try {
				if (i == len) {
					sb.append(base64EncodeChars[b1 >>> 2]);
					sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
					sb.append("==");
					break;
				}
				b2 = data[i++] & 0xff;
				if (i == len) {
					sb.append(base64EncodeChars[b1 >>> 2]);
					sb.append(base64EncodeChars[((b1 & 0x03) << 4)
							| ((b2 & 0xf0) >>> 4)]);
					sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
					sb.append("=");
					break;
				}
				b3 = data[i++] & 0xff;
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4)
						| ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[((b2 & 0x0f) << 2)
						| ((b3 & 0xc0) >>> 6)]);
				sb.append(base64EncodeChars[b3 & 0x3f]);
			} catch (Exception e) {
			}

		}
		return sb.toString();
	}

	public static byte[] base64Decode(String str) {
		byte[] data = str.getBytes();
		return base64Decode(data);
	}

	public static byte[] base64Decode(byte[] data) {
		int len = data.length;
		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		int b1, b2, b3, b4;

		while (i < len) {
			/* b1 */
			do {
				b1 = base64DecodeChars[data[i++]];
			} while (i < len && b1 == -1);
			if (b1 == -1)
				break;

			/* b2 */
			do {
				b2 = base64DecodeChars[data[i++]];
			} while (i < len && b2 == -1);
			if (b2 == -1)
				break;
			buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

			/* b3 */
			do {
				b3 = data[i++];
				if (b3 == 61)
					return buf.toByteArray();
				b3 = base64DecodeChars[b3];
			} while (i < len && b3 == -1);
			if (b3 == -1)
				break;
			buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

			/* b4 */
			do {
				b4 = data[i++];
				if (b4 == 61)
					return buf.toByteArray();
				b4 = base64DecodeChars[b4];
			} while (i < len && b4 == -1);
			if (b4 == -1)
				break;
			buf.write((int) (((b3 & 0x03) << 6) | b4));
		}
		return buf.toByteArray();
	}

	/**
	 * 字符串编码器类，将字符串转换为指定格式.<br>
	 * <br>
	 * 参数字典:<br>
	 * src - source 来源的简写<br>
	 * dst - destnation 目的的简写<br>
	 * fnd - find 查找的简写<br>
	 * rep - replace 替换的简写<br>
	 * idx - index 索引，下标的简写<br>
	 * enc - encoding 编码的简写<br>
	 * <br>
	 * 例子:<br>
	 * <%=ArticleFormat.htmlTextEncoder(yourString)%>
	 */
	public static class StringConvert {
		/**
		 * 将字符串src中的子字符串fnd全部替换为新子字符串rep.<br>
		 * 功能相当于java sdk 1.4的String.replaceAll方法.<br>
		 * 不同之处在于查找时不是使用正则表达式而是普通字符串.
		 */
		public static String replaceAll(String src, String fnd, String rep) {
			if (src == null) {
				return null;
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

		// CDATA扩起来
		// /**
		// * 替换字符集里指定的字符,如解决读取资源xml配置文件时，过滤掉\n\r\t等影响生成结果的特殊字符
		// * @param src 准备格式化的字符串
		// * @param fnds 需要过滤的字符串数组
		// * @param rep 替换后字符格式
		// * @return 过滤格式化后的字符串
		// */
		// public static String replaceFlogs(String src,String[] fnds, String
		// rep) {
		// if (src == null) {
		// return null;
		// }
		// String dst = src;
		// for (int i = 0; i < fnds.length; i++) {
		// dst = dst.replace(fnds[i], rep);
		// }
		// return dst;
		// }

		/**
		 * 转换为HTML编码.<br>
		 */
		public static String htmlEncoder(String src) {
			if (src == null) {
				return null;
			}
			String dst = src;
			dst = replaceAll(dst, "<", "&lt;");
			dst = replaceAll(dst, ">", "&gt;");
			dst = replaceAll(dst, "\"", "&quot;");
			dst = replaceAll(dst, "'", "&#039;");

			return dst;
		}

		/**
		 * 转换为HTML编码.<br>
		 */
		public static String htmlDecoder(String src) {
			if (src == null) {
				return null;
			}
			String dst = src;
			dst = replaceAll(dst, "&lt;", "<");
			dst = replaceAll(dst, "&gt;", ">");
			dst = replaceAll(dst, "&quot;", "\"");
			dst = replaceAll(dst, "&#039;", "'");

			return dst;
		}

		/**
		 * 转换为HTML文字编码.<br>
		 */
		public static String htmlTextEncoder(String src) {
			if (src == null) {
				return null;
			}

			String dst = src;
			dst = replaceAll(dst, "<", "&lt;");
			dst = replaceAll(dst, ">", "&gt;");
			dst = replaceAll(dst, "\"", "&quot;");
			dst = replaceAll(dst, "'", "&#039;");
			dst = replaceAll(dst, " ", "&nbsp;");
			dst = replaceAll(dst, "\r\n", "<br>");
			dst = replaceAll(dst, "\r", "<br>");
			dst = replaceAll(dst, "\n", "<br>");

			return dst;
		}

		/**
		 * 转换为URL编码.<br>
		 */
		public static String urlEncoder(String src, String enc)
				throws Exception {
			return java.net.URLEncoder.encode(src, enc);
		}

		/**
		 * 转换为XML编码.<br>
		 */
		public static String xmlEncode(String src) {
			if (src == null) {
				return null;
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
		public static String xmlDecode(String src) {
			if (src == null) {
				return null;
			}

			String dst = src;
			dst = replaceAll(dst, "&amp;", "&");
			dst = replaceAll(dst, "&lt;", "<");
			dst = replaceAll(dst, "&gt;", ">");
			dst = replaceAll(dst, "&quot;", "\"");
			// dst = replaceAll(dst, "&apos;", "\'");// &acute;

			return dst;
		}

		/**
		 * 转换为SQL编码.<br>
		 */
		public static String sqlEncoder(String src) {
			if (src == null) {
				return null;
			}

			return replaceAll(src, "'", "''");
		}

		/**
		 * 转换为javascript编码.<br>
		 */
		public static String jsEncoder(String src) {
			if (src == null) {
				return null;
			}

			String dst = src;
			dst = replaceAll(dst, "\\", "\\\\");// dst = replaceAll(dst, "\\",
												// "\\\\\\")
			dst = replaceAll(dst, "'", "\\'");
			dst = replaceAll(dst, "\"", "\\\"");
			// dst = replaceAll(dst, "\r\n", "\\\n"); // 和\n转换有冲突
			dst = dst.replaceAll("\n", "\\\\n");
			dst = dst.replaceAll("\r", "\\\\r");
			return dst;
		}

	}

}
