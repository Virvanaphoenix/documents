package com.sweetmanor.example.applet;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JApplet;

/**
 * Applet示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class AppletDemo1 extends JApplet {
	private static final long serialVersionUID = 1L;
	private String text = "这是一个简单的Applet小程序。";
	private int count = 0;

	// init方法：在applet被加载到系统中时执行一次
	public void init() {
		System.out.println(count + "init()");
		count++;
		setBackground(Color.cyan);
	}

	// start方法：在init方法调用后以及在Web页中每次重新访问applet时调用。
	public void start() {
		System.out.println(count + "start()");
		count++;
	}

	// stop方法：在包含此applet的Web页已经被其他页替换时，在applet被销毁前调用此方法。
	public void stop() {
		System.out.println(count + "stop()");
		count++;
	}

	// destroy方法：通知此 applet 它正在被回收，它应该销毁分配给它的任何资源。stop 方法总是在 destroy 之前被调用。
	public void destroy() {
		System.out.println(count + "destroy()");
		count++;
	}

	// paint方法：负责绘制容器。
	public void paint(Graphics g) {
		System.out.println(count + "pain()");
		count++;
		g.clearRect(0, 0, getSize().width, getSize().height);
		g.setColor(Color.blue);
		g.drawRect(5, 5, getSize().width - 10, getSize().height - 10);
		g.setColor(Color.red);
		g.drawString(text, 15, 25);
	}

}
