package com.sweetmanor.program.file;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;

import com.sweetmanor.util.FileUtil;
import com.sweetmanor.util.FrameUtil;

public class CollectionFile {
	JFrame frame = new JFrame("文件收集器");

	// 初始化窗体
	public void init() {
		Box north = Box.createVerticalBox();

		JPanel source = new JPanel();
		source.add(new JLabel("收集目录"));
		final JTextField filePath = new JTextField(50);
		source.add(filePath);
		north.add(source);

		final JTextArea idCard = new JTextArea(20, 12);
		frame.add(new JScrollPane(idCard));

		JPanel target = new JPanel();
		target.add(new JLabel("存放目录"));
		final JTextField targetPath = new JTextField(50);
		target.add(targetPath);
		north.add(target);

		JButton button = new JButton("开始收集");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePa = filePath.getText();
				File[] files = coll(filePa);
				String[] ids = idCard.getText().split("\n");
				String targe = targetPath.getText();
				try {
					dis(ids, files, targe);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "文件拷贝报错");
				}
			}
		});
		north.add(button);
		frame.add(north, BorderLayout.NORTH);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体关闭操作
		frame.setVisible(true);
	}

	public File[] coll(String filePath) {
		File dir = new File(filePath);
		return FileUtil.getFiles(dir, new String[] { "xls", "xlsx" }, true);
	}

	public void dis(String[] ids, File[] files, String tarDir)
			throws IOException {
		for (File file : files) {
			String fileName = file.getName();
			for (String id : ids) {
				if (fileName.contains(id)) {
					String tarName = tarDir + "\\" + fileName;
					File tf = new File(tarName);
					FileUtils.copyFile(file, tf);
				}
			}
		}

	}

	public static void main(String[] args) {
		new CollectionFile().init();
	}

}
