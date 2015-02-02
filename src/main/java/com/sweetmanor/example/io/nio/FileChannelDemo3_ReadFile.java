package com.sweetmanor.example.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import com.sweetmanor.common.CommonParam;

/**
 * 用FileChannel读取文件内容的标准流程。为防止文件过大造成一次读取全部内容困难，本程序采用按块读取方式。
 */
public class FileChannelDemo3_ReadFile {

	public static void main(String[] args) {
		FileChannel channel = null;// 创建FileChannel对象，用FileChannel包装其他对象，在释放时只释放FileChannel对象即可？
		try {
			FileInputStream fis = new FileInputStream(CommonParam.resourcePath
					+ "oracle.ini");// 创建文件输入节点流
			channel = fis.getChannel();// 获取FileChannel对象
			ByteBuffer bBuffer = ByteBuffer.allocate(1024);// 创建一个1024大小的ByteBuffer缓冲区
			// 循环读取文件内容
			while (channel.read(bBuffer) != -1) {
				bBuffer.flip();// 为从ByteBuffer中读数据做准备
				Charset charset = Charset.forName("gb2312");// 创建gb2312字符集
				CharsetDecoder decoder = charset.newDecoder();// 创建解码器
				CharBuffer cBuffer = decoder.decode(bBuffer);// 获取解密后的字符
				System.out.println(cBuffer);
				bBuffer.clear();// 为下一次向ByteBuffer中写数据做准备
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 如果FileChannel不为空，释放FileChannel对象
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
