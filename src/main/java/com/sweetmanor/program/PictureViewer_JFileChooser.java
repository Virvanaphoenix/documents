package com.sweetmanor.program;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.util.FrameUtil;

/**
 * 图片浏览器
 * 
 * @version 1.0 2014-10-02
 * @author ijlhjj
 */
public class PictureViewer_JFileChooser {
	JFrame frame = new JFrame("图片浏览器");
	JMenuBar mb = new JMenuBar();
	JFileChooser fileChooser = new JFileChooser(".");// 以当前路径创建文件选择器
	ExtensionFileFilter filter = new ExtensionFileFilter();// 创建文件过滤器，以文件扩展名进行过滤
	final int PREVIEW_SIZE = 100;// 定义图片预览组件大小
	JLabel accessory = new JLabel();// 图片预览组件
	JLabel label = new JLabel();// 图片显示组件

	public void init() {
		// 设置文件过滤器属性
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.addExtension("gif");
		filter.addExtension("png");
		filter.setDescription("图片文件(*.jpg,*.jpeg,*.gif,*.png)");

		// 设置文件选择器属性
		// 取消“所有文件”选项。如果要添加过滤器，一定要先取消此选项再添加过滤器，否则默认显示空项
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filter);// 添加文件过滤器
		fileChooser.setFileView(new FileIconView());// 设置文件选择器显示
		fileChooser.setAccessory(accessory);// 设置文件选择器预览附件
		accessory.setPreferredSize(new Dimension(PREVIEW_SIZE, PREVIEW_SIZE));// 设置预览组件大小
		accessory.setBorder(BorderFactory.createEtchedBorder());// 设置预览组件边框
		// 添加文件选择器属性改变监听器
		fileChooser.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// 如果选择文件改变
				if (evt.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
					File f = (File) evt.getNewValue();// 获取改变后的文件
					// 如果没有选定文件，预览组件设置为空图标
					if (f == null) {
						accessory.setIcon(null);
						return;
					}
					ImageIcon icon = new ImageIcon(f.getPath());// 获取预览图像
					// 如果预览图像太大，进行缩放
					if (icon.getIconWidth() > PREVIEW_SIZE) {
						// 以默认算法进行缩放，保持原宽高比，宽度设置为指定宽度
						icon = new ImageIcon(icon.getImage().getScaledInstance(
								PREVIEW_SIZE, -1, Image.SCALE_DEFAULT));
					}
					accessory.setIcon(icon);// 设置预览组件图标
				}
			}
		});

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
					label.setIcon(new ImageIcon(fileName));// 显示图像
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

		frame.add(new JScrollPane(label));// 把显示组件添加到窗体中

		frame.setSize(500, 400);
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new PictureViewer_JFileChooser().init();
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

	// 视图风格扩展，用于显示自定义图标
	class FileIconView extends FileView {
		public Icon getIcon(File f) {
			// 如果是文件夹，要判断是磁盘目录还是文件夹目录
			if (f.isDirectory()) {
				File[] fileList = File.listRoots();// 获取磁盘目录
				// 遍历磁盘目录，如果是磁盘目录，返回磁盘图标
				for (File file : fileList) {
					if (file.equals(f)) {
						return new ImageIcon(CommonParam.resourcePath
								+ "ico/dsk.png");
					}
				}
				return new ImageIcon(CommonParam.resourcePath
						+ "ico/folder.png");// 返回文件夹图标
			}
			return new ImageIcon(CommonParam.resourcePath + "ico/pict.png");// 如果是可接受文件，返回可接受文件图标
		}
	}

}
