package com.butone.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ZipUtils {
	private final static Log logger = LogFactory.getLog(ZipUtils.class);

	/**
	 * 使用gzip进行压缩
	 */
	public static String gzip(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return StringUtils.base64Encode(out.toByteArray());
	}

	/**
	 * 
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 * 
	 * @param compressedStr
	 * @return
	 */
	public static String gunzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[6144];
			int offset = -1;
			while ((offset = ginzip.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}

	/**
	 * 使用zip进行压缩
	 * 
	 * @param str
	 *            压缩前的文本
	 * @return 返回压缩后的文本
	 */
	public static final String zip(String str) {
		if (str == null)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = StringUtils.base64Encode(compressed);
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return compressedStr;
	}

	/**
	 * 使用zip进行解压缩
	 * 
	 * @param compressed
	 *            压缩后的文本
	 * @return 解压后的字符串
	 */
	public static final String unzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[6144];
			int offset = -1;
			while ((offset = zin.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}

	public static void zip(File inputFile, String zipFileName, String base, Pattern[] excpets) {
		try {
			// 创建文件输出对象out,提示:注意中文支持
			FileOutputStream out = new FileOutputStream(new String(zipFileName.getBytes("UTF-8")));
			// 將文件輸出ZIP输出流接起来
			ZipOutputStream zOut = new ZipOutputStream(out);
			zip(zOut, inputFile, base, excpets);
			zOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Pattern getPattern(String s) {
		// 开始的文件夹
		s = s.replace('.', '#');
		s = s.replaceAll("#", "\\\\.");
		s = s.replace('*', '#');
		s = s.replaceAll("#", ".*");
		s = s.replace('?', '#');
		s = s.replaceAll("#", ".?");
		s = "^" + s + "$";
		Pattern p = Pattern.compile(s);
		return p;
	}

	private static void zip(ZipOutputStream zOut, File file, String base, Pattern[] excpets) {
		if (excpets != null) {
			for (Pattern p : excpets) {
				Matcher fMatcher = p.matcher(file.getName());
				if (fMatcher.matches()) {
					return;
				}
			}
		}
		try {
			// 如果文件句柄是目录
			if (file.isDirectory()) {
				// 获取目录下的文件
				File[] listFiles = file.listFiles();
				// 建立ZIP条目
				if (base != null && !"".equals(base.trim())) {
					zOut.putNextEntry(new ZipEntry(base + "/"));
					base = (base.length() == 0 ? "" : base + "/");
				}

				// 遍历目录下文件
				for (int i = 0; i < listFiles.length; i++) {
					// 递归进入本方法
					zip(zOut, listFiles[i], (base == null ? "" : base) + listFiles[i].getName(), excpets);
				}
			}
			// 如果文件句柄是文件
			else {
				if (!file.exists() || !file.canRead()) {
					logger.warn(file.getAbsolutePath() + "不能压缩");
					return;
				}
				if (base == null || "".equals(base.trim()))
					base = file.getName();

				// 填入文件句柄
				zOut.putNextEntry(new ZipEntry(base));

				// 开始压缩
				// 从文件入流读,写入ZIP 出流
				writeFile(zOut, file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeFile(ZipOutputStream zOut, File file) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			int len;
			while ((len = in.read()) != -1)
				zOut.write(len);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

	}

	public static void main(String[] args) {
		zip(new File("D:\\\\ArcGisWorkspace\\64206cc93d454675a0247fabe4280e9d.gdb"), "D:\\\\ArcGisWorkspace\\64206cc93d454675a0247fabe4280e9d.zip", null, null);

	}

}