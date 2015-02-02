package com.sweetmanor.tool;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;

import com.sweetmanor.common.Log;
import com.sweetmanor.util.FileUtil;
import com.sweetmanor.util.FrameUtil;

/**
 * 小工具集：查找重复文件
 * 
 * @version 1.0 2014-10-10
 * @author ijlhjj
 */
public class FindRepeatFile {
	private List<String> searchDir = new ArrayList<String>();
	private List<File> fileList = new ArrayList<File>();// 文件对比库
	private Map<String, List<File>> repeatFile = new HashMap<String, List<File>>();// 重复文件库，以对比文件的绝对路径为key
	private int fileCount; // 对比文件总数
	private int compareCount;// 已对比文件数
	private JProgressBar progress;

	private JTextArea logText = new JTextArea(10, 50);
	private JLabel statusBar = new JLabel("提示：可添加多个目录或文件进行对比，删除时多个重复文件只保留第一个！");

	public FindRepeatFile() {
		// EventQueue.invokeLater(new Runnable() {
		// @Override
		// public void run() {}
		// });
		//

		JFrame frame = new JFrame("查找重复文件");
		Box container = Box.createVerticalBox();
		frame.add(container);

		final JFileChooser fileChooser = new JFileChooser(".");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setDialogTitle("添加目录或文件");

		Box top = Box.createHorizontalBox();
		container.add(top);
		final JTextArea taFindPath = new JTextArea(20, 50);
		top.add(new JScrollPane(taFindPath));

		Box topRight = Box.createVerticalBox();
		top.add(topRight);

		JButton btAddDir = new JButton("添加查找目录");
		btAddDir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showDialog(null, "添加");
				if (result == JFileChooser.APPROVE_OPTION) {
					searchDir.add(fileChooser.getSelectedFile()
							.getAbsolutePath());
					taFindPath.setText(taFindPath.getText()
							+ fileChooser.getSelectedFile().getAbsolutePath()
							+ "\n");
				}
			}
		});
		topRight.add(btAddDir);

		JButton btSearch = new JButton("开始查找重复文件");
		btSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findRepeatFile();
				viewRepeatFile();
			}
		});
		topRight.add(btSearch);

		JButton btDelete = new JButton("删除重复文件");
		btDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteRepeatFile();
			}
		});
		topRight.add(btDelete);

		container.add(new JScrollPane(logText));
		frame.add(statusBar, BorderLayout.SOUTH);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	/**
	 * 查找重复文件
	 */
	private void findRepeatFile() {
		fileCount = 0;
		for (String filePath : searchDir)
			fileCount += FileUtil.countFiles(new File(filePath));
		compareCount = 0;
		for (String filePath : searchDir)
			listFiles(new File(filePath));
	}

	/**
	 * 循环遍历所有文件，包含子目录
	 */
	private void listFiles(File file) {
		if (file.isFile())
			compareFile(file);
		else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files)
				listFiles(f);
		}
	}

	/**
	 * 对比文件：重复文件添加到repeatFile；不重复则添加到fileList
	 */
	private void compareFile(File target) {
		if (target.isFile()) {
			compareCount++;
			if (fileList.size() < 1) { // 第一个文件直接加入对比库
				fileList.add(target);
				statusBar
						.setText("文件已比对：" + (compareCount) + " / " + fileCount);
				return;
			}
			// 遍历对比库中的所有文件
			for (File src : fileList) {
				try {
					// 如果文件相同,则添加到repeatFile
					if (FileUtils.contentEquals(src, target)) {
						List<File> sameFiles = repeatFile.get(src
								.getAbsolutePath());// 尝试从重复文件库中获取重复文件列表
						// 如果没有获取到，则重新创建
						if (sameFiles == null) {
							sameFiles = new ArrayList<File>();
							sameFiles.add(src);// 首先把源文件加入列表
						}
						sameFiles.add(target);// 把对比文件加入列表
						repeatFile.put(src.getAbsolutePath(), sameFiles);// 放入重复文件库
						// statusBar.setText("文件已比对：" + (compareCount) + " / " +
						// fileCount);
						System.out.println("文件已比对：" + (compareCount) + " / "
								+ fileCount);

						return;
					}
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "文件比对发生错误！\n错误信息：\n"
							+ ex.getMessage());
					ex.printStackTrace();
					System.exit(1);
				}
			}
			fileList.add(target);// 如果文件不同,则添加到fileList列表中
			statusBar.setText("文件已比对：" + (compareCount) + " / " + fileCount);

			// System.out.println("文件已比对：" + (compareCount) + " / " +
			// fileCount);
		}
	}

	private void viewRepeatFile() {
		int index = 0;
		for (String keys : repeatFile.keySet()) {
			logText.setText(logText.getText() + "重复文件" + (++index) + ":\n");
			List<File> sameList = repeatFile.get(keys);
			for (File filePath : sameList)
				logText.setText(logText.getText() + filePath.getAbsolutePath()
						+ ":\n");
			logText.setText(logText.getText() + "\n");
		}
	}

	private void deleteRepeatFile() {
		int index = 0;
		for (String key : repeatFile.keySet()) {
			List<File> sameList = repeatFile.get(key);
			statusBar.setText("文件已删除（组）：" + (index++) + " / "
					+ repeatFile.size());
			System.out.println("文件已删除（组）：" + (index++) + " / "
					+ repeatFile.size());
			for (int i = 1; i < sameList.size(); i++) {
				File file = sameList.get(i);
				FileUtils.deleteQuietly(file);
				Log.logger.info(file.getAbsoluteFile());
			}
		}
	}

	public static void main(String[] args) {
		new FindRepeatFile();
	}

}
