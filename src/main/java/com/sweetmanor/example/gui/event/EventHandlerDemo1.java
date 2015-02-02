package com.sweetmanor.example.gui.event;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * 内部类事件处理器示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class EventHandlerDemo1 {
	JFrame frame = new JFrame("内部类事件处理器示例");
	TextField tf = new TextField(30);

	public void init() {
		frame.add(tf);

		Button ok = new Button("确定");
		frame.add(ok, BorderLayout.SOUTH);
		ok.addActionListener(new OkListener()); // 注册事件监听器

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new EventHandlerDemo1().init();
	}

	// 事件监听器类
	class OkListener implements ActionListener {
		int count = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("用户第 " + (++count) + " 次单击OK按钮");
			tf.setText("Hello World! " + (count));
		}
	}

}
