package com.sweetmanor.example.javademo;

import java.util.Locale;

/**
 * JDK所支持的Locales
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class LocaleAvailable {

	public static void main(String[] args) {
		Locale[] localelist = Locale.getAvailableLocales();
		for (int i = 0; i < localelist.length; i++)
			System.out.println(localelist[i].getDisplayCountry() + "="
					+ localelist[i].getCountry() + "  "
					+ localelist[i].getDisplayLanguage() + "="
					+ localelist[i].getLanguage());
	}

}