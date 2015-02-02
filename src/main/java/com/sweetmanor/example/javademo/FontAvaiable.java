package com.sweetmanor.example.javademo;

import java.awt.GraphicsEnvironment;

/**
 * 系统所有可用字体
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class FontAvaiable {

	public static void main(String[] args) {
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
		for (String fontName : fontNames)
			System.out.println(fontName);
	}

}
