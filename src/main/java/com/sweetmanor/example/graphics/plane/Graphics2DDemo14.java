package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo14 {
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
		new Graphics2DDemo14().init();
	}

	/**
	 * 绘制八卦图案
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = -8192033597678679530L;

		public Panel2D() {
			setPreferredSize(new Dimension(400, 400));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			Ellipse2D e = new Ellipse2D.Double(-150, -150, 300, 300); // 构建图形
			QuadCurve2D q1 = new QuadCurve2D.Double(0, -150, 100, -75, 0, 0);
			QuadCurve2D q2 = new QuadCurve2D.Double(0, 150, -100, 75, 0, 0);
			g2.translate(200, 200);
			g2.draw(e);
			g2.draw(q1);
			g2.draw(q2);

			// 绘制第1个坐标点
			Shape ellipse = new Ellipse2D.Double(-5, -5, 10, 10);
			g2.translate(0, 75);
			g2.setColor(Color.red);
			g2.fill(ellipse);

			// 绘制第2个坐标点
			ellipse = new Ellipse2D.Double(-5, -5, 10, 10);
			g2.translate(0, -150);
			g2.setColor(Color.black);
			g2.fill(ellipse);
		}
	}

}
