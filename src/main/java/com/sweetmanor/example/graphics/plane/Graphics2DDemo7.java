package com.sweetmanor.example.graphics.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo7 {
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
		new Graphics2DDemo7().init();
	}

	/**
	 * 绘制图案
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = -536500973894973988L;

		public Panel2D() {
			setPreferredSize(new Dimension(200, 200));
		}

		@Override
		public void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			Graphics2D g2 = (Graphics2D) graphics;
			Ellipse2D ellise = new Ellipse2D.Double(-80, 5, 160, 10);
			g2.translate(100, 100);
			Random random = new Random();
			for (int i = 0; i < 100; i++) {
				int r = random.nextInt(256);
				int g = random.nextInt(256);
				int b = random.nextInt(256);
				Color color = new Color(r, g, b);
				g2.setColor(color);
				g2.rotate(10);
				g2.draw(ellise);
			}
		}
	}

}
