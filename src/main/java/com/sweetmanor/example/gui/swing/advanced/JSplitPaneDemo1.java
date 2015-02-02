package com.sweetmanor.example.gui.swing.advanced;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.util.FrameUtil;

/**
 * 分割面板示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class JSplitPaneDemo1 {
	JFrame frame = new JFrame("分割面板示例");

	public void init() {
		Book[] books = new Book[] {
				new Book("疯狂Java讲义", new ImageIcon(CommonParam.resourcePath
						+ "ico/java.png"), "介绍Java基础知识的入门书籍"),
				new Book("轻量级Java EE企业应用实战", new ImageIcon(
						CommonParam.resourcePath + "ico/ee.png"),
						"全面介绍轻量级Java EE开发的三个常用框架"),
				new Book("疯狂Android讲义", new ImageIcon(CommonParam.resourcePath
						+ "ico/android.png"), "介绍Android开发的基础知识") };
		final JList<Book> bookList = new JList<Book>(books);// 以数组创建列表对象

		final JLabel bookCover = new JLabel();// 封面图片
		bookCover.setPreferredSize(new Dimension(200, 260));
		bookCover.setHorizontalAlignment(SwingConstants.CENTER);// 内容水平居中

		final JTextArea bookDesc = new JTextArea(3, 14);// 书的描述文本
		bookDesc.setLineWrap(true);// 自动换行

		// 加载图片和描述文本
		bookList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Book book = bookList.getSelectedValue();
				bookCover.setIcon(book.getIcon());
				bookDesc.setText(book.getDesc());
			}
		});

		JSplitPane left = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
				bookCover, bookDesc); // 左侧的上下分割面板，true为连续显示
		left.setOneTouchExpandable(true);// 打开“一触即展“特性
		left.resetToPreferredSizes();// 根据子组件大小重新调整分隔条位置

		JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left,
				bookList);
		frame.add(content);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new JSplitPaneDemo1().init();
	}

	class Book {
		private String name;
		private Icon icon;
		private String desc;

		public Book(String name, Icon icon, String desc) {
			this.name = name;
			this.icon = icon;
			this.desc = desc;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Icon getIcon() {
			return icon;
		}

		public void setIcon(Icon icon) {
			this.icon = icon;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return "Book [ name = " + name + " ]";
		}
	}

}
