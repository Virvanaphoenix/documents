package com.sweetmanor.example.io;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.sweetmanor.common.CommonParam;

/**
 * 追加文件内容
 */
public class RandomAccessFileDemo2 {

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(CommonParam.tempPath
				+ "test.txt", "rw");// 以读写方式打开文件
		raf.seek(raf.length());// 移动文件指针到文件末尾
		raf.write("追加的内容\r\n".getBytes());// 输出追加内容，\r\n为控制换行
	}

}
