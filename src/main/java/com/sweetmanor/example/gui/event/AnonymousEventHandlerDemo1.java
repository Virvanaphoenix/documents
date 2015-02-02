package com.sweetmanor.example.gui.event;

import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * 匿名事件处理器示例：事件处理器有3种实现方式：匿名内部类、内部类、外部类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class AnonymousEventHandlerDemo1 {
	private JFrame frame = new JFrame("匿名事件处理器示例");

	public void init() {
		final TextArea ta = new TextArea(6, 20);
		// 以匿名内部类形式创建事件监听器
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ta.append("关闭窗口\n");
				System.out.println("关闭窗口");
				System.exit(0);
			}
		});
		frame.add(ta);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new AnonymousEventHandlerDemo1().init();
	}

}
