package com.sweetmanor.example.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import com.sweetmanor.common.CommonParam;

/**
 * 用FileChannel实现文件的复制。FileChannel即可用于输入，也可用于输出，但使用方式受所包装的节点流的限制
 */
public class FileChannelDemo1 {

	public static void main(String[] args) throws Exception {
		File f = new File(CommonParam.resourcePath + "oracle.ini");// 创建源文件
		FileChannel inChannel = new FileInputStream(f).getChannel();// 以源文件输入流获取Channel
		FileChannel outChannel = new FileOutputStream(CommonParam.tempPath
				+ "oracle_bak.ini").getChannel();// 以文件输出流创建Channel
		MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY,
				0, f.length());// 把FileChannel的全部数据映射成ByteBuffer
		outChannel.write(buffer);// 把ByteBuffer中的数据写入FileChannel，实现文件的复制
		buffer.clear();// 此处是要把指针位置还原，为什么调用clear方法不知道，测试flip方法也能实现同样的效果。

		Charset charset = Charset.forName("GBK");// 创建一个GBK字符集
		CharsetDecoder decoder = charset.newDecoder();// 创建GBK解码器
		CharBuffer charBuffer = decoder.decode(buffer);// 用GBK解码文件内容
		System.out.println(charBuffer);// 输出解码字符串
	}

}
