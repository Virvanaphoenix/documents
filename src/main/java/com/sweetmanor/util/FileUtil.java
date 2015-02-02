package com.sweetmanor.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * 文件工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class FileUtil {

	/**
	 * 判断指定文件是否存在
	 */
	public static boolean isExist(String filePath) {
		if (filePath == null)
			return false;
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 获取文件名，不包括扩展名
	 */
	public static String getBaseName(String fileName) {
		return FilenameUtils.getBaseName(fileName);
	}

	/**
	 * 统计文件（夹）包含文件个数
	 * 
	 * @param file
	 * @return count[0]目录个数，count[1]文件个数
	 */
	public static int countFiles(File file) {
		return countDirAndFiles(file)[1];
	}

	/**
	 * 统计文件（夹）包含目录个数
	 * 
	 * @param file
	 * @return count[0]目录个数，count[1]文件个数
	 */
	public static int countDirs(File file) {
		return countDirAndFiles(file)[0];
	}

	/**
	 * 统计文件（夹）包含目录和文件个数
	 * 
	 * @param file
	 * @return count[0]目录个数，count[1]文件个数
	 */
	public static int[] countDirAndFiles(File file) {
		int[] count = new int[2];
		countDirAndFiles(file, count);
		if (file.isDirectory()) // 如果是目录，要减去递归中计算的本目录
			count[0]--;
		return count;
	}

	/**
	 * 统计文件（夹）包含目录和文件个数
	 */
	private static void countDirAndFiles(File file, int[] count) {
		if (file.isFile())
			count[1]++;
		else if (file.isDirectory()) {
			count[0]++;
			File[] files = file.listFiles();
			for (File f : files)
				countDirAndFiles(f, count);
		}
	};

	/**
	 * 查找包含指定扩展名的文件
	 * 
	 * @param dir
	 *            查找的目录
	 * @param extensions
	 *            扩展名数组
	 * @param recursive
	 *            递归遍历子目录
	 * @return 返回结果文件数组
	 */
	public static File[] getFiles(File dir, String[] extensions,
			boolean recursive) {
		Collection<File> files = FileUtils
				.listFiles(dir, extensions, recursive); // 获取指定扩展名文件集合
		return FileUtils.convertFileCollectionToFileArray(files);// 将文件集合转换为数组
	}

	/**
	 * 文件拷贝：不能将目录拷贝到文件
	 * 
	 * @throws IOException
	 */
	public static void copyFile(String sourceName, String targetName)
			throws IOException {
		copyFile(new File(sourceName), new File(targetName));
	}

	/**
	 * 文件拷贝：不能将目录拷贝到文件
	 * 
	 * @throws IOException
	 */
	public static void copyFile(File source, File target) throws IOException {
		if (source.isDirectory() && target.isFile())
			throw new IOException("不能将目录拷贝到文件！");
		try {
			if (source.isDirectory() && target.isDirectory())
				FileUtils.copyDirectory(source, target);
			else if (source.isFile() && target.isDirectory())
				FileUtils.copyFileToDirectory(source, target);
			else if (source.isFile() && target.isFile())
				FileUtils.copyFile(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
