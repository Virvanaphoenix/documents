package com.sweetmanor.example.gui.swing.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.sweetmanor.util.FrameUtil;

public class DirectoryTreeDemo {
	JFrame frame = new JFrame("系统目录");

	public void init() {
		File[] files = File.listRoots();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("计算机");
		for (File file : files) {
			DefaultMutableTreeNode newChild = createTreeNode(file);
			root.add(newChild);
		}

		final JTree tree = new JTree(root);

		MouseListener ml = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) {
					if (e.getClickCount() == 1) {
						System.out.println("单击事件:" + selRow + selPath);
					} else if (e.getClickCount() == 2) {
						System.out.println("双击事件:" + selRow + selPath);
					}
				}
			}
		};
		tree.addMouseListener(ml);

		// tree.putClientProperty("JTree.lineStyle", "None");// 设置连接线类型
		// tree.setRootVisible(false);// 设置根节点是否可见
		tree.setShowsRootHandles(true);// 设置根节点显示折叠图标
		tree.setEditable(true);// 设置树的可编辑状态，默认不可编辑

		frame.add(new JScrollPane(tree));

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public DefaultMutableTreeNode createTreeNode(File file) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(
				file.getAbsolutePath());
		return node;

	}

	public static void main(String[] args) {
		new DirectoryTreeDemo().init();
	}

}
