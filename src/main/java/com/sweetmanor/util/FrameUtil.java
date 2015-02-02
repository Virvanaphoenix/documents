package com.sweetmanor.util;

import java.awt.Container;
import java.awt.Dimension;

/**
 * 窗体工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class FrameUtil {

	/**
	 * 设置窗体居中
	 */
	public static void center(Container frame) {
		center(null, frame);
	}

	/**
	 * 设置窗体居中
	 */
	public static void center(Container owner, Container frame) {
		// 如果要设置的窗体为空，则直接返回
		if (frame == null)
			return;

		// 外层容器的坐标和大小默认设置为屏幕的相应属性
		int x = 0;
		int y = 0;
		Dimension screen = frame.getToolkit().getScreenSize();
		// 如果外层容器不为空
		if (owner != null) {
			// 坐标偏移初始值为容器的坐标值
			x = owner.getX();
			y = owner.getY();
			screen = new Dimension(owner.getWidth(), owner.getHeight());// 设置外层容器大小
		}
		// 设置位置为内外容器差的一半加上坐标偏移值
		frame.setLocation((screen.width - frame.getSize().width) / 2 + x,
				(screen.height - frame.getSize().height) / 2 + y);
	}

}
