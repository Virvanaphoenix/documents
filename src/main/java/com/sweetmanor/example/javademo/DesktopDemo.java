package com.sweetmanor.example.javademo;

import java.awt.Desktop;
import java.io.File;

/**
 * Desktop类示例：调用系统关联程序打开指定文件，异步调取，不等待返回
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class DesktopDemo {

	public static void main(String[] args) throws Exception {
		Desktop desk = Desktop.getDesktop();
		desk.open(new File(""));// 调用资源管理器打开D盘
		desk.open(new File(""));// 调用帮助文件程序打开JDK文档
	}

}
