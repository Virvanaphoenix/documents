package com.sweetmanor.example.applet;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.JApplet;

/**
 * Applet处理图片、声音示例：本例从一本古老的示例书中抄的，没跑起来，不整理了
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ImageButtonDemo1 extends JApplet {
	private static final long serialVersionUID = 1L;

	private String resource = "";// 定义资源加载路径
	private Image image;// 定义背景图像，此处用小图标代替了，效果不好
	private Image img1, img2, img3;// 定义三个图像对象，在它们直接替换显示
	private AudioClip soundA, soundB;// 定义两个声音对象，用于播放提示音

	@Override
	public void init() {
		try {
			// 加载图像对象
			img1 = getImage(new URL(resource + "ico/ok.png"));
			img2 = getImage(new URL(resource + "ico/heart.png"));
			img3 = getImage(new URL(resource + "ico/male.png"));
			// 加载声音对象
			soundA = getAudioClip(new URL(resource + "mid/车站.mid"));
			soundB = getAudioClip(new URL(resource + "mid/大地.mid"));
			// 添加鼠标事件监听器
			addMouseListener(new MouseAdapter() {
				// 监听鼠标按下事件：显示图像，播放声音
				@Override
				public void mousePressed(MouseEvent e) {
					image = img2;
					repaint();
					// 本次播放声音前先把前面的停了
					soundA.stop();
					soundB.stop();
					soundA.play();
				}

				// 监听鼠标释放事件：显示图像，播放声音
				@Override
				public void mouseReleased(MouseEvent e) {
					image = img3;
					repaint();
					soundA.stop();
					soundB.stop();
					soundB.play();
				}

				// 鼠标进入窗体事件
				@Override
				public void mouseEntered(MouseEvent e) {
					image = img3;
					repaint();
				}

				// 鼠标离开窗体事件
				@Override
				public void mouseExited(MouseEvent e) {
					image = img1;
					repaint();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		image = img1;// 初始化图像
		repaint();
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());// 清空窗体容器
		g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null),
				null);// 绘制图像
	}

}
