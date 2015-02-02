package com.sweetmanor.example.gui.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.sweetmanor.common.CommonParam;
import com.sweetmanor.util.FrameUtil;

/**
 * Swing常用组件使用示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class SwingComponentDemo1 {
	JFrame frame = new JFrame("测试窗体");
	JMenuBar menuBar = new JMenuBar();
	JPopupMenu popMenu = new JPopupMenu();

	public void init() {
		// ------顶部区域
		Box top = Box.createHorizontalBox();
		frame.add(top);

		Box topLeft = Box.createVerticalBox();// 左上Box
		top.add(topLeft);

		final JTextArea ta = new JTextArea(8, 20);// 创建一个多行文本域
		JScrollPane jsp = new JScrollPane(ta);// 创建一个JScrollPane（带滚动条的Panel），用于包装多行文本域
		topLeft.add(jsp);

		JPanel checkPanel = new JPanel();
		topLeft.add(checkPanel);

		String[] colors = { "红色", "绿色", "蓝色" };
		JComboBox<String> colorChooser = new JComboBox<String>(colors);// 创建一个下拉列表
		checkPanel.add(colorChooser);

		JRadioButton male = new JRadioButton("男", true);// 创建单选按钮，初始处于选中状态
		checkPanel.add(male);
		JRadioButton female = new JRadioButton("女", false);
		checkPanel.add(female);
		ButtonGroup bg = new ButtonGroup(); // 创建一个按钮组，用于组合性别单选按钮
		bg.add(male);
		bg.add(female);

		JCheckBox married = new JCheckBox("是否已婚？", false);// 创建复选框，初始处于没有选中状态
		checkPanel.add(married);

		JList<String> colorList = new JList<String>(colors);// 创建一个列表
		top.add(colorList);

		// ------底部区域
		JPanel bottom = new JPanel();
		frame.add(bottom, BorderLayout.SOUTH);

		Icon okIcon = new ImageIcon(CommonParam.resourcePath + "ico/ok.png");
		JButton btOk = new JButton("确认", okIcon); // 创建一个按钮，并指定图标，Java不支持ico格式图标
		bottom.add(btOk);

		JTextField name = new JTextField(40);// 创建一个单行文本域
		bottom.add(name);

		/*----------------     设置右键菜单     ----------------*/
		// 创建一个单击事件监听器，用于监听风格菜单项单击事件
		ActionListener flavorListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// 调用相应的方法改变组件外观风格
					if ("Metal风格".equals(e.getActionCommand()))
						changeFlavor(1);
					else if ("Windows风格".equals(e.getActionCommand()))
						changeFlavor(2);
					else if ("Motif风格".equals(e.getActionCommand()))
						changeFlavor(3);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		JRadioButtonMenuItem metalItem = new JRadioButtonMenuItem("Metal风格",
				true);// 创建三个风格单选菜单项
		metalItem.addActionListener(flavorListener);
		popMenu.add(metalItem);
		JRadioButtonMenuItem windowsItem = new JRadioButtonMenuItem("Windows风格");
		windowsItem.addActionListener(flavorListener);
		popMenu.add(windowsItem);
		JRadioButtonMenuItem motifItem = new JRadioButtonMenuItem("Motif风格");
		motifItem.addActionListener(flavorListener);
		popMenu.add(motifItem);
		// 把三个单选菜单加入同一个按钮组，形成单选按钮菜单组
		ButtonGroup flavorGroup = new ButtonGroup();
		flavorGroup.add(metalItem);
		flavorGroup.add(windowsItem);
		flavorGroup.add(motifItem);
		ta.setComponentPopupMenu(popMenu);// 为多行文本域设置右键菜单

		/*----------------     设置菜单     ----------------*/
		// ------ 文件菜单
		JMenu file = new JMenu("文件");
		menuBar.add(file);

		Icon newIcon = new ImageIcon(CommonParam.resourcePath + "ico/new.png");
		JMenuItem newItem = new JMenuItem("新建", newIcon); // 创建“新建”菜单项，并指定图标
		file.add(newItem);
		newItem.setAccelerator(KeyStroke
				.getKeyStroke('N', InputEvent.CTRL_MASK));// 为“新建”菜单项指定快捷键，字符'N'必须使用大写
		// 为新建菜单添加单击事件监听器
		newItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.append("用户单击了“新建”菜单\n");// 单击新建菜单时在多行文本域追加一行文字
			}
		});

		JMenuItem saveItem = new JMenuItem("保存", new ImageIcon(
				CommonParam.resourcePath + "ico/save.png"));// 创建“保存”菜单，并指定图标
		file.add(saveItem);

		JMenuItem exitItem = new JMenuItem("退出", new ImageIcon(
				CommonParam.resourcePath + "ico/exit.png"));
		file.add(exitItem);

		// ------编辑菜单
		JMenu edit = new JMenu("编辑");
		menuBar.add(edit);

		JCheckBoxMenuItem autoWrap = new JCheckBoxMenuItem("自动换行");// 创建复选框菜单项
		edit.add(autoWrap);
		edit.addSeparator();// 添加分隔符

		JMenuItem copyItem = new JMenuItem("复制", new ImageIcon(
				CommonParam.resourcePath + "ico/copy.png"));
		edit.add(copyItem);

		JMenuItem pasteItem = new JMenuItem("粘贴", new ImageIcon(
				CommonParam.resourcePath + "ico/paste.png"));
		edit.add(pasteItem);

		edit.add(new JMenuItem("-"));// 与AWT不同，Swing菜单不可以使用这种方式添加分隔符，此处会添加一个“-”的菜单项

		JMenu format = new JMenu("格式");// 创建二级级联菜单
		edit.add(format);

		JMenuItem commentItem = new JMenuItem("注释");// 创建两个下级菜单项
		commentItem.setToolTipText("将程序代码注释起来");// 添加提示文本
		format.add(commentItem);

		JMenuItem cancelItem = new JMenuItem("取消注释");
		format.add(cancelItem);

		frame.setJMenuBar(menuBar);

		frame.pack();// 设置窗体自适应大小，按其内部组件的大小自动调整窗体的大小
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// 改变组件外观风格的方法
	private void changeFlavor(int flavor) throws Exception {
		switch (flavor) {
		case 1:
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			break;
		case 2:
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			break;
		case 3:
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			break;
		}
		/*
		 * 更新窗体显示使用frame.getContentPane()，而不使用frame本身，因为JFrame依然部分依赖于本地平台图形组件
		 * 当强制将JFrame设置成Windows等风格时，将使窗体失去标题栏和边框。
		 * frame.getContentPane()返回窗体的顶级容器，顶级容器会包含JFrame中所显示的所有非菜单组件。所以需单独更新菜单显示
		 */
		SwingUtilities.updateComponentTreeUI(frame.getContentPane());
		SwingUtilities.updateComponentTreeUI(menuBar);
		SwingUtilities.updateComponentTreeUI(popMenu);
	}

	public static void main(String[] args) {
		new SwingComponentDemo1().init();
	}

}
