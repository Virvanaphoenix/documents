package com.sweetmanor.example.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetDemo1 {

	public static void main(String[] args) throws CharacterCodingException {
		// 创建GBK字符集对象，Charset类提供了encode和decode方法，如果只是简单的编码、解密，则不必创建CharsetEncoder和CharsetDecoder对象
		Charset charset = Charset.forName("GBK");
		CharsetEncoder encoder = charset.newEncoder();// 创建编码对象
		CharsetDecoder decoder = charset.newDecoder();// 创建解密对象

		// 创建一个CharBuffer对象
		CharBuffer cBuffer = CharBuffer.allocate(8);
		cBuffer.put('孙');
		cBuffer.put('悟');
		cBuffer.put('空');
		cBuffer.flip();// 准备从CharBuffer中读数据

		ByteBuffer bBuffer = encoder.encode(cBuffer);// 编码为ByteBuffer
		// 循环输出每个字节
		for (int i = 0; i < bBuffer.capacity(); i++) {
			System.out.print(bBuffer.get(i) + " ");
		}
		System.out.println("\n" + decoder.decode(bBuffer));// 输出解密后的结果

		// 以下直接使用Charset提供的方法实现以上相同的功能
		bBuffer = charset.encode("猪八戒");
		for (int i = 0; i < bBuffer.capacity(); i++) {
			System.out.print(bBuffer.get(i) + " ");
		}
		System.out.println("\n" + charset.decode(bBuffer));
	}

}
