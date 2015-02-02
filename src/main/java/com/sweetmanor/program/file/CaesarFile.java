package com.sweetmanor.program.file;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.sweetmanor.example.security.CaesarCiphe;
import com.sweetmanor.util.FileUtil;

/**
 * 对指定类型的文件进行凯撒加密，加密后删除源文件
 * 
 * @author stars
 * 
 */
public class CaesarFile {

	private String DIRPATH = ""; // 要处理的目录，包括子目录
	private int i = 0;

	public void setDIRPATH(String dIRPATH) {
		DIRPATH = dIRPATH;
	}

	public static void main(String[] args) {
		new CaesarFile().task();
	}

	public void task() {
		File dir = new File(DIRPATH);
		String[] extensions = { "c6" };
		File[] files = FileUtil.getFiles(dir, extensions, true);
		System.out.println("获取待处理文件数：" + files.length);
		for (File file : files) {
			undispose(file);
		}
		System.out.println("处理完成。共处理文件：" + files.length + "个");
	}

	private void dispose(File file) {
		System.out.println("当前处理文件：" + (++i));
		String ext = FilenameUtils.getExtension(file.getName());
		File target = null;
		if (ext.equals("avi")) {
			String fileName = file.getAbsolutePath().replace(".avi", ".c6");
			target = new File(fileName);
		}

		CaesarCiphe.encode(file, target);
		FileUtils.deleteQuietly(file);
	}

	private void undispose(File file) {
		System.out.println("当前处理文件：" + (++i));
		String ext = FilenameUtils.getExtension(file.getName());
		File target = null;
		if (ext.equals("c6")) {
			String fileName = file.getAbsolutePath().replace(".c6", ".avi");
			target = new File(fileName);
		}

		CaesarCiphe.decode(file, target);
		FileUtils.deleteQuietly(file);
	}

}
