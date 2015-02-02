package com.sweetmanor.example.graphics.plane;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sweetmanor.util.FrameUtil;

public class Graphics2DDemo13 {
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
		new Graphics2DDemo13().init();
	}

	/**
	 * 绘制一个8*8的棋盘
	 */
	class Panel2D extends JPanel {
		private static final long serialVersionUID = 9065659675184048531L;

		private int chessboardWidth = 400;
		private int chessboardHeight = 400;

		public Panel2D() {
			setPreferredSize(new Dimension(500, 500));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.translate(50, 50);
			int x = 0;
			int y = 0;
			for (int i = 0; i < 8; i++) {
				g2.drawLine(x, y, x + chessboardWidth, y);
				y += chessboardHeight / 7;
			}
			x = 0;
			y = 0;
			for (int i = 0; i < 8; i++) {
				g2.drawLine(x, y, x, y + chessboardHeight);
				x += chessboardWidth / 7;
			}
		}
	}

}
