package com.sweetmanor.example.graphics.plane;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class HandDrawDemo1 {
	private Frame f = new Frame("简单手绘程序");
	// 定义画图区的大小
	private final int AREA_WIDTH = 500;
	private final int AREA_HEIGHT = 400;
	// 定义保存上一次鼠标拖动事件鼠标坐标的变量
	private int preX = -1;
	private int preY = -1;
	// 定义一个右键菜单用于设置画笔颜色
	PopupMenu pop = new PopupMenu();
	MenuItem redItem = new MenuItem("红色");
	MenuItem greenItem = new MenuItem("绿色");
	MenuItem blueItem = new MenuItem("蓝色");
	// 定义一个BufferedImage对象
	BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT,
			BufferedImage.TYPE_INT_BGR);
	// 获取image对象的Graphics
	Graphics g = image.getGraphics();
	private DrawCanvas drawArea = new DrawCanvas();
	// 定义画笔颜色
	private Color foreColor = new Color(255, 0, 0);

	public void init() {
		// 定义右键菜单的事件监听器
		ActionListener menuListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("绿色")) {
					foreColor = new Color(0, 255, 0);
				}
				if (e.getActionCommand().equals("红色")) {
					foreColor = new Color(255, 0, 0);
				}
				if (e.getActionCommand().equals("蓝色")) {
					foreColor = new Color(0, 0, 255);
				}
			}
		};
		// 为三个菜单添加事件监听器
		redItem.addActionListener(menuListener);
		greenItem.addActionListener(menuListener);
		blueItem.addActionListener(menuListener);
		// 组合右键菜单
		pop.add(redItem);
		pop.add(greenItem);
		pop.add(blueItem);
		// 添加右键菜单到drawArea对象中
		drawArea.add(pop);
		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		drawArea.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
		// 监听鼠标拖动事件
		drawArea.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (preX > 0 && preY > 0) {
					g.setColor(foreColor);
					g.drawLine(preX, preY, e.getX(), e.getY());
				}
				preX = e.getX();
				preY = e.getY();
				drawArea.repaint();
			}
		});
		// 监听鼠标释放事件
		drawArea.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					pop.show(drawArea, e.getX(), e.getY());
				}
				preX = -1;
				preY = -1;
			}
		});
		f.add(drawArea);
		f.pack();
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new HandDrawDemo1().init();
	}

	class DrawCanvas extends Canvas {
		private static final long serialVersionUID = -539279748738461266L;

		public void paint(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}
	}

}
