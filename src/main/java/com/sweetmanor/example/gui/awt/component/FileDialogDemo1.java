package com.sweetmanor.example.gui.awt.component;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * 文件对话框示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class FileDialogDemo1 {
	private JFrame frame = new JFrame("文件对话框示例");

	public void init() {
		final FileDialog openFileDialog = new FileDialog(frame, "打开文件",
				FileDialog.LOAD);// 打开文件对话框
		Button btOpen = new Button("打开文件");
		btOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFileDialog.setVisible(true);
				System.out.println(openFileDialog.getDirectory() + "\t"
						+ openFileDialog.getFile());
			}
		});
		frame.add(btOpen);

		final FileDialog saveFileDialog = new FileDialog(frame, "保存文件",
				FileDialog.SAVE);// 保存文件对话框
		Button btSave = new Button("保存文件");
		btSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFileDialog.setVisible(true);
				System.out.println(saveFileDialog.getDirectory() + "\t"
						+ saveFileDialog.getFile());
			}
		});
		frame.add(btSave, BorderLayout.SOUTH);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new FileDialogDemo1().init();
	}

}
