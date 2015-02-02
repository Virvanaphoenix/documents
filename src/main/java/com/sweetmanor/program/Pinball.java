package com.sweetmanor.program;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.Timer;

/**
 * @author Stars
 * 
 *         弹球游戏
 */
public class Pinball {
	private Frame f = new Frame("弹球游戏");
	// 定义游戏窗体的宽度和高度
	private final int TABLE_WIDTH = 300;
	private final int TABLE_HEIGHT = 400;
	// 定义球拍的宽度和高度
	private final int RACKET_WIDTH = 60;
	private final int RACKET_HEIGHT = 20;
	// 定义球拍的垂直位置
	private final int RACKET_Y = 340;
	// 定义小球的大小
	private final int BALL_SIZE = 16;

	/**
	 * 小球的移动速度是需要考虑的一个问题
	 */
	Random random = new Random();
	// 定义一个-0.5——0.5的比率，用于控制小球的运行方向
	private double xyRate = random.nextDouble() - 0.5;
	// 定义小球垂直方向的移动速度
	private int ySpeed = 10;
	// 定义小球水平方向的移动速度
	private int xSpeed = (int) (ySpeed * xyRate * 2);
	// 定义小球的初始坐标
	private int ballX = random.nextInt(200) + 20;
	private int ballY = random.nextInt(10) + 20;
	// 定义球拍的初始水平位置
	private int racketX = random.nextInt(200);
	private MyCanvas tableArea = new MyCanvas();

	Timer timer;
	// 游戏是否结束的旗标
	private boolean isLose = false;

	public void init() {
		// 设置桌面区域的大小
		tableArea.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		f.add(tableArea);
		// 定义键盘监听器
		KeyAdapter keyProcessor = new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
					if (racketX > 0) {
						racketX -= 10;
					}
				}
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (racketX < TABLE_WIDTH - RACKET_WIDTH) {
						racketX += 10;
					}
				}
			}
		};
		// 为窗体和tableArea对象添加键盘监听器
		f.addKeyListener(keyProcessor);
		tableArea.addKeyListener(keyProcessor);
		// 定义每0.1秒执行一次的事件监听器
		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 如果小球碰到左右边框，水平方向反转
				if (ballX <= 0 || ballX >= TABLE_WIDTH - BALL_SIZE) {
					xSpeed = -xSpeed;
				}
				// 如果小球垂直位置超出球拍，且水平位置不在球拍位置内，游戏结束
				if (ballY >= RACKET_Y - BALL_SIZE
						&& (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {
					timer.stop();
					// 设置游戏结束旗标为true
					isLose = true;
					tableArea.repaint();
				}
				// 如果小球达到顶部边缘，或达到球拍位置，且水平位置在球拍内，垂直方向反转
				else if (ballY <= 0
						|| (ballY >= RACKET_Y - BALL_SIZE && ballX >= racketX && ballX <= racketX
								+ RACKET_WIDTH)) {
					ySpeed = -ySpeed;
				}
				// 小球移动
				ballX += xSpeed;
				ballY += ySpeed;
				tableArea.repaint();
			}
		};
		// 启动定时任务
		timer = new Timer(100, taskPerformer);
		timer.start();
		f.pack();
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new Pinball().init();
	}

	class MyCanvas extends Canvas {
		private static final long serialVersionUID = -6506999010185744149L;

		public void paint(Graphics g) {
			if (isLose) {
				g.setColor(new Color(255, 0, 0));
				g.setFont(new Font("Times", Font.BOLD, 30));
				g.drawString("游戏已结束", 50, 200);

			} else {
				// 设置颜色，绘制小球
				g.setColor(new Color(240, 240, 80));
				g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
				// 设置颜色，绘制球拍
				g.setColor(new Color(80, 80, 200));
				g.fillRect(racketX, RACKET_Y, RACKET_WIDTH, RACKET_HEIGHT);
			}
		}
	}

}
