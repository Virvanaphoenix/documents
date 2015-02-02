package com.sweetmanor.example.graphics.plane;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo5_Epicycloid {
	JFrame frame = new JFrame("绘图窗体");
	JPanel panel = new Panel2D();

	public void init() {
		frame.add(panel);

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Graphics2DDemo5_Epicycloid().init();
	}

	/**
	 * 绘制外摆线
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = -8846111364277429855L;

		int nPoints = 1000;// 绘制点的个数
		double r1 = 60;
		double r2 = 50;
		double p = 70;

		public Panel2D() {
			setPreferredSize(new Dimension(400, 400));// 设置组件默认大小
		}

		// 重写组件绘制方法
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(200, 200);
			int x1 = (int) (r1 + r2 - p);
			int y1 = 0;
			int x2;
			int y2;
			for (int i = 0; i < nPoints; i++) {
				double t = i * Math.PI / 90;
				x2 = (int) ((r1 + r2) * Math.cos(t) - p
						* Math.cos((r1 + r2) * t / r2));
				y2 = (int) ((r1 + r2) * Math.sin(t) - p
						* Math.sin((r1 + r2) * t / r2));
				g2.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
		}
	}

}
