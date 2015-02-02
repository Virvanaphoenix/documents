package com.sweetmanor.program.file;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;

import com.sweetmanor.example.security.CaesarCiphe;
import com.sweetmanor.util.FrameUtil;

public class CaesarFileView {
	JFrame frame = new JFrame("文件浏览器");
	JMenuBar mb = new JMenuBar();
	JFileChooser fileChooser = new JFileChooser("d:");// 以当前路径创建文件选择器
	ExtensionFileFilter filter = new ExtensionFileFilter();// 创建文件过滤器，以文件扩展名进行过滤

	public void init() {
		// 设置文件过滤器属性
		filter.addExtension("c1");
		filter.addExtension("c2");
		filter.setDescription("凯撒文件浏览器(*.c1,*.c2)");

		// 设置文件选择器属性
		// 取消“所有文件”选项。如果要添加过滤器，一定要先取消此选项再添加过滤器，否则默认显示空项
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filter);// 添加文件过滤器

		// 组合菜单项
		JMenu file = new JMenu("文件");

		JMenuItem openItem = new JMenuItem("打开");// 创建打开菜单
		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showDialog(frame, "打开图片文件");
				// 如果点击的是“确定”按钮
				if (result == JFileChooser.APPROVE_OPTION) {
					String fileName = fileChooser.getSelectedFile().getPath();// 获取文件路径
					view(fileName);
				}
			}
		});
		file.add(openItem);

		JMenuItem exitItem = new JMenuItem("退出");// 创建退出菜单
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(exitItem);

		mb.add(file);
		frame.setJMenuBar(mb);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new CaesarFileView().init();
	}

	private static void view(String fileName) {
		String ext = FilenameUtils.getExtension(fileName);
		File source = new File(fileName);
		File temFile = null;
		try {
			if (ext.equals("c1")) {
				temFile = File.createTempFile("Caesar", ".txt");
			}
			temFile.deleteOnExit();
			CaesarCiphe.decode(source, temFile);
			Desktop desk = Desktop.getDesktop();
			desk.open(temFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件过滤器，以文件扩展名进行过滤。
	 */
	class ExtensionFileFilter extends FileFilter {
		private String description;// 描述文件
		private ArrayList<String> extensions = new ArrayList<String>();// 可接受扩展名列表，列表中全部以小写字符保存

		// 自定义添加可接受扩展名类型
		public void addExtension(String extension) {
			if (!extension.startsWith(".")) {
				extension = "." + extension;
			}
			extensions.add(extension.toLowerCase());
		}

		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * 对文件夹和在可接受扩展名列表中的文件返回true
		 */
		@Override
		public boolean accept(File f) {
			// 可接受文件夹
			if (f.isDirectory()) {
				return true;
			}
			String fileName = f.getName().toLowerCase();// 获取文件名
			for (String extension : extensions) {
				// 如果文件扩展名在可接受列表中，返回true
				if (fileName.endsWith(extension)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public String getDescription() {
			return description;
		}
	}

}
