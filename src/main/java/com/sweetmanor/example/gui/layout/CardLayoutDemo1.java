package com.sweetmanor.example.gui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * CardLayout示例：底层采用循环链表保存标签页
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class CardLayoutDemo1 {
	JFrame frame = new JFrame("CardLayout示例");

	public void init() {
		// CardLayout布局：hgap:水平间距20；vgap:垂直间距30
		final CardLayout cardLayout = new CardLayout(20, 30);

		String[] tabNames = { "第一张", "第二张", "第三张", "第四张", "第五张" };

		final Panel tabPanel = new Panel(cardLayout);
		frame.add(tabPanel);
		for (int i = 0; i < tabNames.length; i++) {
			// 以指定名称将组件添加到容器中，CardLayout的show方法将使用此名称
			// 此方法已过时
			tabPanel.add(tabNames[i], new Button(tabNames[i]));
		}

		Panel btPanel = new Panel();
		frame.add(btPanel, BorderLayout.SOUTH);

		Button btPrevious = new Button("上一张");
		btPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.previous(tabPanel);
			}
		});
		btPanel.add(btPrevious);

		Button btNext = new Button("下一张");
		btNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(tabPanel);
			}
		});
		btPanel.add(btNext);

		Button btFirst = new Button("第一张");
		btFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.first(tabPanel);
			}
		});
		btPanel.add(btFirst);

		Button btLast = new Button("最后一张");
		btLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.last(tabPanel);
			}
		});
		btPanel.add(btLast);

		Button btThird = new Button("第三张");
		btThird.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(tabPanel, "第三张");
			}
		});
		btPanel.add(btThird);

		frame.setSize(400, 300);
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		CardLayoutDemo1 cardLayout = new CardLayoutDemo1();
		cardLayout.init();
	}

}
