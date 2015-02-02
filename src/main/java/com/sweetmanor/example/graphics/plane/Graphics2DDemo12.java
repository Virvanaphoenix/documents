package com.sweetmanor.example.graphics.plane;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo12 {
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
		new Graphics2DDemo12().init();
	}

	/**
	 * 绘制y=x * x 的图像，绘制y = x * x * x的图像
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = -3500220212195354730L;
		int nPoints = 10;// 绘制点的个数

		public Panel2D() {
			setPreferredSize(new Dimension(300, 600));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(150, 350); // 坐标系平移
			g2.drawLine(-140, 0, 140, 0);// 绘制X轴
			g2.drawLine(0, -330, 0, 240);// 绘制Y轴
			// 绘制第一象限图像
			int x1 = 0;
			int y1 = 0;
			int x2;
			int y2;
			for (int i = 0; i < nPoints; i++) {
				x2 = x1 + 1;
				y2 = -(x2 * x2);
				g2.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
			x1 = 0;
			y1 = 0;
			for (int i = 0; i < nPoints; i++) {
				x2 = x1 + 1;
				y2 = -(x2 * x2 * x2);
				g2.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
			// 绘制第二象限图像
			x1 = 0;
			y1 = 0;
			for (int i = 0; i < nPoints; i++) {
				x2 = x1 - 1;
				y2 = -(x2 * x2);
				g2.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
			x1 = 0;
			y1 = 0;
			for (int i = 0; i < nPoints; i++) {
				x2 = x1 - 1;
				y2 = -(x2 * x2 * x2);
				g2.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
		}
	}

}
