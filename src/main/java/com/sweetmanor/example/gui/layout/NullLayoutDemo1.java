package com.sweetmanor.example.gui.layout;

import java.awt.Button;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * NullLayout示例：绝对布局
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class NullLayoutDemo1 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("NullLayout示例");
		frame.setLayout(null);// 绝对布局必须在添加组件前设置位置和大小（位置允许重叠）

		Button bt1 = new Button("第一个按钮");
		bt1.setBounds(50, 80, 100, 80);
		frame.add(bt1);

		Button bt2 = new Button("第二个按钮");
		bt2.setBounds(100, 50, 150, 100);
		frame.add(bt2);

		frame.setSize(400, 300);
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
