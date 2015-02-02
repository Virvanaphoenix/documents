package com.sweetmanor.example.gui.awt.clipboard;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统剪贴板使用示例：对HandDrawDemo1类增加图形复制粘贴功能。待重构
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class ClipboardDemo2 {
	Frame f = new Frame("简单手绘程序");
	// 获取系统剪贴板
	private Clipboard clipboard = Toolkit.getDefaultToolkit()
			.getSystemClipboard();
	// 定义画图区的高度、宽度
	private final int AREA_WIDTH = 400;
	private final int AREA_HEIGHT = 300;
	// 下面的priX、priY保存了上一次鼠标拖动事件的鼠标坐标
	private int preX = -1;
	private int preY = -1;
	// 定义右键菜单用于设置画笔颜色
	PopupMenu pop = new PopupMenu();
	MenuItem redItem = new MenuItem("红色");
	MenuItem greenItem = new MenuItem("绿色");
	MenuItem blueItem = new MenuItem("蓝色");
	// 定义一个BufferedImage对象
	BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT,
			BufferedImage.TYPE_INT_BGR);
	Graphics g = image.getGraphics();
	// 定义一个List保存所有粘贴进来的Image，当图层处理
	List<Image> imageList = new ArrayList<Image>();
	private DrawCanvas drawArea = new DrawCanvas();
	// 定义画笔颜色
	private Color foreColor = new Color(255, 0, 0);
	// 定义复制、粘贴按钮
	private Button btCopy = new Button("复制");
	private Button btPaste = new Button("粘贴");

	public void init() {
		// 定义右键菜单事件监听器
		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("红色")) {
					foreColor = new Color(255, 0, 0);
				}
				if (e.getActionCommand().equals("绿色")) {
					foreColor = new Color(0, 255, 0);
				}
				if (e.getActionCommand().equals("蓝色")) {
					foreColor = new Color(0, 0, 255);
				}
			}
		};
		// 为3个菜单添加事件监听器
		redItem.addActionListener(menuListener);
		greenItem.addActionListener(menuListener);
		blueItem.addActionListener(menuListener);
		// 组合右键菜单
		pop.add(redItem);
		pop.add(greenItem);
		pop.add(blueItem);
		drawArea.add(pop);
		// 将image对象填充成白色
		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		drawArea.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
		// 监听鼠标移动动作
		drawArea.addMouseMotionListener(new MouseMotionAdapter() {
			// 鼠标按下并拖动事件
			public void mouseDragged(MouseEvent e) {
				// 如果preX和preY大于0
				if (preX > 0 && preY > 0) {
					g.setColor(foreColor);
					g.drawLine(preX, preY, e.getX(), e.getY());
				}
				preX = e.getX();
				preY = e.getY();
				drawArea.repaint();
			}
		});
		// 监听鼠标事件
		drawArea.addMouseListener(new MouseAdapter() {
			// 监听鼠标松开的事件
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					pop.show(drawArea, e.getX(), e.getY());
				}
				preX = -1;
				preY = -1;
			}
		});
		f.add(drawArea);
		btCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 把image对象分装成ImageSelection对象
				ImageSelection content = new ImageSelection(image);
				clipboard.setContents(content, null);
			}
		});
		btPaste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 如果剪贴板中包含imageFlavor对象
				if (clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)) {
					try {
						imageList.add((Image) clipboard
								.getData(DataFlavor.imageFlavor));
						drawArea.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		Panel p = new Panel();
		p.add(btCopy);
		p.add(btPaste);
		f.add(p, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new ClipboardDemo2().init();
	}

	class DrawCanvas extends Canvas {
		private static final long serialVersionUID = -1050860369826674057L;

		public void paint(Graphics g) {
			g.drawImage(image, 0, 0, null);
			for (Image img : imageList) {
				g.drawImage(img, 0, 0, null);
			}
		}
	}

}
