package com.sweetmanor.example.io;

import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterDemo1 {

	public static void main(String[] args) {
		File file = new File(".");
		String[] fileList = file.list(new MyFilenameFilter());// 以自定义过滤器列出当前目录符合要求的文件路径
		for (String filePaht : fileList) {
			System.out.println(filePaht);
		}
	}

}

class MyFilenameFilter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		// System.out.println(dir.getAbsolutePath());
		return name.endsWith("java") || new File(name).isDirectory();// 包含所有目录和以java结尾的文件
	}

}
