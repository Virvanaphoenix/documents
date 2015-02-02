package com.sweetmanor.example.javademo;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * JDK所支持的字符集
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class CharsetAvailable {

	public static void main(String[] args) {
		SortedMap<String, Charset> charsetsMap = Charset.availableCharsets();
		for (String key : charsetsMap.keySet())
			System.out.println(charsetsMap.get(key));
	}

}
