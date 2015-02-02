package com.sweetmanor.example.graphics.plane;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo8 {
	JFrame frame = new JFrame("绘图窗体");
	JPanel panel = new Panel2D();

	private void init() {
		frame.add(panel);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Graphics2DDemo8().init();
	}

	class Panel2D extends JPanel {
		private static final long serialVersionUID = -1134900850243408944L;

		public Panel2D() {
			setPreferredSize(new Dimension(800, 400));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			Ellipse2D s1 = new Ellipse2D.Double(0, 0, 100, 100);
			Ellipse2D s2 = new Ellipse2D.Double(60, 0, 100, 100);
			Area a2 = new Area(s2);
			// 绘制两个圆形
			g2.translate(20, 50);
			g2.draw(s1);
			g2.draw(s2);

			// 填充两个圆的交集
			Area a1 = new Area(s1);
			a1.add(a2);
			g2.translate(0, 200);
			g2.fill(a1);

			// 填充两个圆的并集
			a1 = new Area(s1);
			a1.intersect(a2);
			g2.translate(180, 0);
			g2.fill(a1);

			// 添加两个圆的差
			a1 = new Area(s1);
			a1.subtract(a2);
			g2.translate(180, 0);
			g2.fill(a1);

			// 添加两个圆的异或
			a1 = new Area(s1);
			a1.exclusiveOr(a2);
			g2.translate(180, 0);
			g2.fill(a1);
		}
	}
}
