package com.sweetmanor.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

/**
 * 压缩文件工具类：只支持ZIP格式压缩文件，暂不支持RAR格式
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ZipUtil {
	public final static String encoding = "UTF-8"; // 文件编码格式

	/**
	 * 压缩ZIP文件
	 * 
	 * @param srcPathname
	 *            需要压缩的文件或目录
	 * @param zipFilepath
	 *            压缩文件存放路径
	 */
	public static void zip(String srcPathname, String zipFilepath) {
		// 如果压缩源不存在，抛出RuntimeException
		File file = new File(srcPathname);
		if (!file.exists())
			throw new RuntimeException("文件或目录" + srcPathname + "不存在！");

		Project project = new Project();
		FileSet fileSet = new FileSet();// 创建文件集
		fileSet.setProject(project);
		// 判断是目录还是文件
		if (file.isDirectory()) {
			fileSet.setDir(file);
			// ant中include/exclude规则在此都可以使用
			// 比如:
			// fileSet.setExcludes("**/*.txt");
			// fileSet.setIncludes("**/*.xls");
		} else {
			fileSet.setFile(file);
		}
		Zip zip = new Zip();// 创建压缩包
		zip.setProject(project);
		zip.setDestFile(new File(zipFilepath));
		zip.addFileset(fileSet);
		zip.setEncoding(encoding);// 设置文件编码格式
		zip.execute();
	}

	/**
	 * 压缩ZIP文件：待添加
	 * 
	 * @param sourceDir
	 *            需要压缩的文件或目录
	 * @param zipFilepath
	 *            压缩文件存放路径
	 * @param onlyFiles
	 *            如果传入的sourceDir参数是一个目录，此参数控制压缩文件中是包含此目录还是只包含此目录下的文件。true为只包含文件
	 */
	public static void zip(String sourceDir, String zipFilepath,
			boolean onlyFiles) {

	}

	/**
	 * 解压ZIP文件
	 * 
	 * @param zipFilepath
	 *            压缩文件路径
	 * @param destDir
	 *            解压目标路径
	 */
	public static void unzip(String zipFilepath, String destDir) {
		// 如果解压文件不存在，抛出RuntimeException异常
		if (!new File(zipFilepath).exists())
			throw new RuntimeException("压缩文件" + zipFilepath + "不存在！");

		Project project = new Project(); // 创建虚拟工程
		Expand expand = new Expand(); // 创建解压任务
		expand.setProject(project);
		expand.setTaskType("unzip"); // 设置任务类型
		expand.setTaskName("unzip"); // 设置任务名称
		expand.setEncoding(encoding); // 设置文件编码
		expand.setSrc(new File(zipFilepath)); // 设置解压文件
		expand.setDest(new File(destDir)); // 设置解压目录
		expand.execute();
	}

	public static void main(String[] args) {
		unzip("", "");
	}

}
