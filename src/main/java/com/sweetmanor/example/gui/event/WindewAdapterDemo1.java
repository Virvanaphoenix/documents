package com.sweetmanor.example.gui.event;

import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * 窗体事件适配器示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class WindewAdapterDemo1 {
	JFrame frame = new JFrame("窗体事件适配器示例");

	public void init() {
		final TextArea ta = new TextArea(6, 20);
		frame.add(ta);

		frame.addWindowListener(new WindowAdapter() {
			// 单击窗体右上角关闭按钮事件
			@Override
			public void windowClosing(WindowEvent e) {
				ta.append("关闭窗口\n");
				System.out.println("关闭窗口");
				System.exit(0);
			}
		});

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new WindewAdapterDemo1().init();
	}

}
