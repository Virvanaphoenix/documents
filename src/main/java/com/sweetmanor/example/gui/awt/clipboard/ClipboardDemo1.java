package com.sweetmanor.example.gui.awt.clipboard;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * 系统剪贴板使用示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ClipboardDemo1 {
	private JFrame frame = new JFrame("简单的剪贴板程序");

	public void init() {
		final Clipboard clipboard = Toolkit.getDefaultToolkit()
				.getSystemClipboard(); // 获取系统剪贴板
		// final Clipboard clipboard = new Clipboard("cb"); // 创建本地剪贴板

		Box top = Box.createHorizontalBox();
		frame.add(top);

		final TextArea source = new TextArea(5, 20);
		source.append("平静由内而生。\n不要从外追寻。");
		top.add(source);
		final TextArea target = new TextArea(5, 20);
		top.add(target);

		Panel btPanel = new Panel();
		frame.add(btPanel, BorderLayout.SOUTH);

		Button btCopy = new Button("复制");
		btCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringSelection contents = new StringSelection(source.getText());
				clipboard.setContents(contents, null);// 设置剪贴板的当前内容，剪贴板的所有者为null
			}
		});
		btPanel.add(btCopy);

		Button btPaste = new Button("粘贴");
		btPaste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
					try {
						String content = (String) clipboard
								.getData(DataFlavor.stringFlavor);// 获取剪贴板的字符串内容
						target.append(content);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btPanel.add(btPaste);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new ClipboardDemo1().init();
	}

}
