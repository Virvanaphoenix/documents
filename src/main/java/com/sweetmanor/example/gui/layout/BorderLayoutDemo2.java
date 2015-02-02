package com.sweetmanor.example.gui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

/**
 * BorderLayout示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class BorderLayoutDemo2 {

	public static void main(String[] args) {
		Frame frame = new Frame("BorderLayout示例");
		frame.setLayout(new BorderLayout(20, 5));

		// BorderLayout布局没有放置组件的区域将自动被其他区域占据，每个区域可以放置一个组件（容器）
		frame.add(new Button("北"), BorderLayout.NORTH);
		frame.add(new Button("南"), BorderLayout.SOUTH);

		// 在区域中添加容器，容器中就可以放置多个组件（盒子套盒子，盒子套盒子，猜猜最里面是什么，呵呵...）
		Panel center = new Panel();
		center.add(new TextField(20));
		center.add(new Button("单击我"));
		frame.add(center);

		frame.add(new Button("东"), BorderLayout.EAST);

		frame.setBounds(400, 200, 400, 200);
		frame.setVisible(true);
	}

}
