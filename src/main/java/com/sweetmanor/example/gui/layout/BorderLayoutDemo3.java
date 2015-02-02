package com.sweetmanor.example.gui.layout;

import java.awt.Button;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * BorderLayout示例：演示组件单向扩展的使用
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class BorderLayoutDemo3 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("BorderLayout示例");

		Box box = Box.createVerticalBox(); // 垂直Box内部的组件默认会居中显示
		frame.add(box);
		box.setMinimumSize(new Dimension(200, 200));// 最小大小设置无效
		box.setMaximumSize(new Dimension(300, 600));

		Button bt = new Button("按钮");
		box.add(bt);
		bt.setMinimumSize(new Dimension(200, 200)); // 最小大小设置无效
		bt.setMaximumSize(new Dimension(300, 600));

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
