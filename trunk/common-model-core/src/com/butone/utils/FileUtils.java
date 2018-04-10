package com.butone.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {

	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[6144];
			int len;
			while ((len = inBuff.read(b, 0, b.length)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	public static void copyFile(File sourceFile, File targetFile,
			String sourceEncodeing, String encoding) throws IOException {
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
				targetFile), encoding);
		
		
		InputStreamReader isr = new InputStreamReader(new FileInputStream(
				sourceFile), sourceEncodeing);

		char[] chars = new char[1024];
		int len = -1;
		while ((len = isr.read(chars)) != -1) {
			osw.write(chars, 0, len);
		}
		osw.flush();
		osw.close();
		isr.close();
	}

	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir)
						.getAbsolutePath()
						+ File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 创建查询指定目录下文件的方法
	 * 
	 * @param allList
	 *            指定目录
	 * @param endName
	 *            指定以“”结尾的文件
	 * @return 得到的文件数目
	 */
	public int findTxtFileCount(File allList, String endName) {
		//
		int textCount = 0;
		// 创建fileArray名字的数组
		File[] fileArray = allList.listFiles();
		// 如果传进来一个以文件作为对象的allList 返回0
		if (null == fileArray) {
			return 0;
		}
		// 偏历目录下的文件
		for (int i = 0; i < fileArray.length; i++) {
			// 如果是个目录
			if (fileArray[i].isDirectory()) {
				// System.out.println("目录: "+fileArray[i].getAbsolutePath());
				// 递归调用
				textCount += findTxtFileCount(fileArray[i].getAbsoluteFile(),
						endName);
				// 如果是文件
			} else if (fileArray[i].isFile()) {
				// 如果是以“”结尾的文件
				if (fileArray[i].getName().endsWith(endName)) {
					// 展示文件
					System.out.println("exe文件: "
							+ fileArray[i].getAbsolutePath());
					// 所有以“”结尾的文件相加
					textCount++;
				}
			}
		}
		return textCount;
	}

	/**
	 * 在本文件夹下查找
	 * 
	 * @param s
	 *            String 文件名
	 * @return File[] 找到的文件
	 */
	public static File[] getFiles(String s) {
		return getFiles("./", s, true);
	}

	/**
	 * 获取文件 可以根据正则表达式查找
	 * 
	 * @param dir
	 *            String 文件夹名称
	 * @param s
	 *            String 查找文件名，可带*.?进行模糊查询
	 * @return File[] 找到的文件
	 */
	public static File[] getFiles(String dir, String s, boolean scanSubDir) {
		// 开始的文件夹
		File file = new File(dir);
		s = s.replace('.', '#');
		s = s.replaceAll("#", "\\\\.");
		s = s.replace('*', '#');
		s = s.replaceAll("#", ".*");
		s = s.replace('?', '#');
		s = s.replaceAll("#", ".?");
		s = "^" + s + "$";
		Pattern p = Pattern.compile(s);
		ArrayList<File> list = filePattern(file, p, scanSubDir);
		File[] result = new File[list.size()];
		list.toArray(result);
		return result;
	}

	/**
	 * @param file
	 *            File 起始文件夹
	 * @param p
	 *            Pattern 匹配类型
	 * @return ArrayList 其文件夹下的文件夹
	 */

	private static ArrayList<File> filePattern(File dir, Pattern p,
			boolean scanSubDir) {
		ArrayList<File> list = new ArrayList<File>();
		if (dir != null && dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files != null && files.length > 0) {
				for (File subFile : files) {
					if (subFile.isFile()) {
						Matcher fMatcher = p.matcher(subFile.getName());
						if (fMatcher.matches()) {
							list.add(subFile);
						}
					} else if (scanSubDir) {
						ArrayList<File> rlist = filePattern(subFile, p,
								scanSubDir);
						if (rlist != null) {
							list.addAll(rlist);
						}
					}

				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		

//		File[] files = getFiles("./", "*.*", false);
//		for (File file : files) {
//			System.out.println(file);
//		}

	}
}
