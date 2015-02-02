package com.sweetmanor.example.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.sweetmanor.common.CommonParam;

/**
 * FileChannel实现文件内容的追加。
 */
public class FileChannelDemo2 {

	public static void main(String[] args) throws IOException {
		File f = new File(CommonParam.tempPath + "test.txt");
		RandomAccessFile raf = new RandomAccessFile(f, "rw");// 以读写方式创建随机存取对象
		FileChannel randomChannel = raf.getChannel();// 获取Channel对象
		ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0,
				f.length());// 把FileChannel的全部数据映射成ByteBuffer
		randomChannel.position(f.length());// 移动记录指针到文件最后
		randomChannel.write(buffer);// 将buffer数据写入Channel
	}

}
