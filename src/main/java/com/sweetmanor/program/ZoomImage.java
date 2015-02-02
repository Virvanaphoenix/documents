package com.sweetmanor.program;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 缩放图片的小程序，修改后可作为获取缩略图工具，或做网站的批量图片缩放
 * 
 */
public class ZoomImage {
	// 定义图片缩放后的大小
	private final int WIDTH = 80;
	private final int HEIGHT = 60;
	// BufferedImage用于保存缩放后的位图
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();

	public void zoom() {
		try {
			// 读取原始位图
			Image srcImage = ImageIO.read(new File("resource/zoom.png"));
			// 将原始位图缩放后绘制到iamge中
			g.drawImage(srcImage, 0, 0, WIDTH, HEIGHT, null);
			// 将image对象输出到磁盘文件中
			ImageIO.write(image, "png",
					new File("resource/" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ZoomImage().zoom();
	}
}
