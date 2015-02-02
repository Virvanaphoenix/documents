package com.sweetmanor.example.graphics.plane;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DrawDemo1 {
	private final String RECT_SHAPE = "rect";
	private final String OVAL_SHAPE = "oval";
	private Frame f = new Frame("简单绘图");
	private Button rect = new Button("绘制矩形");
	private Button oval = new Button("绘制圆形");
	private MyCanvas drawArea = new MyCanvas();
	// 用于保存需要绘制什么图形的字符串属性
	private String shape = "";

	public void init() {
		// 绘制填充矩形的监听器
		rect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 设置shape属性为RECT_SHAPE
				shape = RECT_SHAPE;
				// 重绘Canvas对象
				drawArea.repaint();
			}
		});
		// 绘制填充圆形的监听器
		oval.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 设置shape属性为OVAL_SHAPE
				shape = OVAL_SHAPE;
				// 重绘Canvas对象
				drawArea.repaint();
			}
		});

		Panel p = new Panel();
		p.add(rect);
		p.add(oval);
		f.add(p, BorderLayout.SOUTH);

		drawArea.setPreferredSize(new Dimension(250, 180));
		f.add(drawArea);

		f.setBounds(400, 300, 400, 300);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new DrawDemo1().init();
	}

	class MyCanvas extends Canvas {
		private static final long serialVersionUID = -1309451826299594803L;

		@Override
		// 重新Canvas的paint方法，实现绘画
		public void paint(Graphics g) {
			Random rand = new Random();
			if (shape.equals(RECT_SHAPE)) {
				// 设置画笔颜色
				g.setColor(new Color(220, 100, 80));
				// 随机绘制一个矩形
				g.fillRect(rand.nextInt(200), rand.nextInt(120), 40, 60);

			}
			if (shape.equals(OVAL_SHAPE)) {
				// 设置画笔颜色
				g.setColor(new Color(80, 100, 200));
				// 随机绘制一个圆形
				g.fillOval(rand.nextInt(200), rand.nextInt(120), 50, 40);
			}
		}
	}

}
