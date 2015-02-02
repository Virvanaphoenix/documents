package com.sweetmanor.program;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gobang {
	JFrame f = new JFrame("五子棋");
	// 下面三个位图分别代表棋盘、黑子、白子
	BufferedImage table;
	BufferedImage black;
	BufferedImage white;
	// 当移动鼠标时的选择框
	BufferedImage selected;
	// 定义棋盘的大小
	private static int BOARD_SIZE = 15;
	// 定义棋盘的宽度和高度
	private final int TABLE_WIDTH = 535;
	private final int TABLE_HEIGHT = 536;
	// 定义棋盘坐标的像素值和棋盘数组之间的比率
	private final int RATE = TABLE_WIDTH / BOARD_SIZE;
	// 定义棋盘坐标的像素值和棋盘数组之间的偏移距离（有图片的边距造成）
	private final int X_OFFSET = 5;
	private final int Y_OFFSET = 6;
	// 定义一个二维数组来充当棋盘,其中空位为0,黑子为1，白子为2
	private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
	// 五子棋游戏棋盘对应的Canvas组件
	ChessBoard chessBoard = new ChessBoard();
	// 当前选中的点
	private int selectedX = -1;
	private int selectedY = -1;
	// 当前下子颜色
	private boolean isBlack = true;

	public void init() throws IOException {
		// 加载图片
		table = ImageIO.read(new File("resource/gobang/board.jpg"));
		black = ImageIO.read(new File("resource/gobang/black.gif"));
		white = ImageIO.read(new File("resource/gobang/white.gif"));
		selected = ImageIO.read(new File("resource/gobang/selected.gif"));
		// 设置绘图区大小
		chessBoard.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		// 添加鼠标单击监听器事件
		chessBoard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 将用户鼠标事件的坐标转换成棋子数组的坐标
				int xPos = (int) ((e.getX() - X_OFFSET) / RATE);
				int yPos = (int) ((e.getY() - Y_OFFSET) / RATE);
				// 判断当前点是否有子，有子应该提示，此处省略
				if (board[xPos][yPos] == 0) {
					// 判断当前位置下子的颜色，轮流下子
					if (isBlack) {
						board[xPos][yPos] = 1;
					} else {
						board[xPos][yPos] = 2;
					}
					// 下子颜色反转
					isBlack = !isBlack;
				}
				// 重绘棋盘
				chessBoard.repaint();
			}

			// 当鼠标离开棋盘区域后，复位选中点坐标
			public void mouseExited(MouseEvent e) {
				selectedX = -1;
				selectedY = -1;
				chessBoard.repaint();
			}
		});
		// 添加鼠标移动监听器事件
		chessBoard.addMouseMotionListener(new MouseMotionAdapter() {
			// 当鼠标移动时，改变选中点坐标
			public void mouseMoved(MouseEvent e) {
				selectedX = (e.getX() - X_OFFSET) / RATE;
				selectedY = (e.getY() - Y_OFFSET) / RATE;
				chessBoard.repaint();
			}
		});

		f.add(chessBoard);

		f.pack();
		// 设置窗体居中
		Dimension screen = f.getToolkit().getScreenSize();
		f.setLocation((int) (screen.getWidth() - f.getWidth()) / 2,
				(int) (screen.getHeight() - f.getHeight()) / 2);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		Gobang gb = new Gobang();
		gb.init();
	}

	class ChessBoard extends JPanel {
		private static final long serialVersionUID = 5140596950567279009L;

		// 重写JPanel的paint方法，实现绘画
		public void paint(Graphics g) {
			// 绘制棋盘
			g.drawImage(table, 0, 0, null);
			// 绘制选择框
			if (selectedX >= 0 && selectedY >= 0) {
				g.drawImage(selected, selectedX * RATE + X_OFFSET, selectedY
						* RATE + Y_OFFSET, null);
			}
			// 遍历数组，绘制棋子
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					if (board[i][j] == 1) {
						g.drawImage(black, i * RATE + X_OFFSET, j * RATE
								+ Y_OFFSET, null);
					}
					if (board[i][j] == 2) {
						g.drawImage(white, i * RATE + X_OFFSET, j * RATE
								+ Y_OFFSET, null);
					}
				}
			}
		}
	}

}
