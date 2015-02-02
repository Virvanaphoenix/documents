package com.sweetmanor.example.javademo;

import javax.imageio.ImageIO;

/**
 * ImageIO类支持的读、写文件类型
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ImageIOSupportFileFormat {

	public static void main(String[] args) {
		String[] readFormat = ImageIO.getReaderFileSuffixes();
		System.out.println("支持读取的图片格式：");
		for (String format : readFormat)
			System.out.println(format);

		String[] writeFormat = ImageIO.getWriterFileSuffixes();
		System.out.println("\n支持写入的图片格式：");
		for (String format : writeFormat)
			System.out.println(format);
	}

}
