package com.sweetmanor.program.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.sweetmanor.util.FileUtil;

public class StatisticsFileLine {
	private String DIRPATH = ""; // 要处理的目录，包括子目录

	public static void main(String[] args) {
		new StatisticsFileLine().tast();
	}

	public void tast() {
		File dir = new File(DIRPATH);
		String[] extensions = { "txt", "java" };
		File[] files = FileUtil.getFiles(dir, extensions, true);
		System.out.println("获取处理文件：" + files.length + "个");
		for (File file : files) {
			dispose(file);
		}
		System.out.println("处理完成。共处理文件：" + files.length + "个");

	}

	private void dispose(File file) {
		List<String> content;
		try {
			content = FileUtils.readLines(file);
			if (content.size() > 300)
				System.out.println(file.getName() + ":\t\t" + content.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
