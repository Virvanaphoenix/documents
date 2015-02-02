package com.sweetmanor.example.graphics.plane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphics2DDemo2 extends JApplet {

	private static final long serialVersionUID = -8416057874918473950L;

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		JApplet applet = new Graphics2DDemo2();
		applet.init(); // 调用方法进行初始化
		frame.add(applet);

		frame.setTitle("Java 2D Demo");
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// 重写初始化方法
	@Override
	public void init() {
		JPanel panel = new Panel2D();
		getContentPane().add(panel);
	}

	/**
	 * 设置画笔的线型和连接方式
	 * 
	 * @author Stars
	 * 
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = -5734196903739003726L;

		// 无参构造函数
		public Panel2D() {
			setPreferredSize(new Dimension(350, 200)); // 设置大小
			setBackground(Color.white); // 设置背景色
		}

		// 重写绘制组件方法
		@Override
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			// 设置笔画样式为平头样式，斜线连接
			BasicStroke stroke = new BasicStroke(10, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_BEVEL);
			g2.setStroke(stroke);
			Rectangle2D.Float rect = new Rectangle2D.Float(20, 60, 80, 50);
			g2.drawString("斜角连接", 35, 50);
			g2.draw(rect);
			// 设置笔画样式为平头样式，尖角连接
			stroke = new BasicStroke(10, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER);
			g2.setStroke(stroke);
			rect = new Rectangle2D.Float(120, 60, 80, 50);
			g2.drawString("尖角连接", 135, 50);
			g2.draw(rect);
			// 设置笔画样式为平头样式，圆角连接
			stroke = new BasicStroke(10, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_ROUND);
			g2.setStroke(stroke);
			rect = new Rectangle2D.Float(220, 60, 80, 50);
			g2.drawString("圆角连接", 235, 50);
			g2.draw(rect);
		}
	}
}
