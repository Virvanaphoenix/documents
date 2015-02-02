package com.sweetmanor.example.gui.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import com.sweetmanor.util.FrameUtil;

/**
 * 各类边框使用示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class BorderDemo1 {
	JFrame frame = new JFrame("边框使用示例");

	public void init() {
		frame.setLayout(new GridLayout(2, 4));

		Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED,
				Color.RED, Color.GREEN, Color.BLUE, Color.GRAY);// BevelBorder：双线边框
		frame.add(getPanelWithBorder(border, "BevelBorder双线边框"));

		border = BorderFactory.createLineBorder(Color.ORANGE, 10);// LineBorder：单线边框
		frame.add(getPanelWithBorder(border, "LineBorder单线边框"));

		border = BorderFactory.createTitledBorder(border, "边框标题",
				TitledBorder.LEFT, TitledBorder.BOTTOM, new Font("宋体",
						Font.BOLD, 10), Color.BLUE);// TitledBorder：带标题的边框。底端左对齐
		frame.add(getPanelWithBorder(border, "TitledBorder带标题的边框"));

		border = BorderFactory.createCompoundBorder(new LineBorder(Color.GREEN,
				8), border);// CompoundBorder：组合边框，内外两个边框
		frame.add(getPanelWithBorder(border, "CompoundBorder组合边框"));

		border = BorderFactory.createEmptyBorder(20, 5, 10, 30);// EmptyBorder：空边框，就是在组件四周留出空白区域
		frame.add(getPanelWithBorder(border, "EmptyBorder空白边框"));

		border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
				Color.RED, Color.GREEN);// EtchedBorder：蚀刻式边框
		frame.add(getPanelWithBorder(border, "EtchedBorder蚀刻式边框"));

		MatteBorder mb = new MatteBorder(20, 5, 10, 30, Color.GREEN);// MatteBorder：是EmptyBorder的子类，可以在留空区域填充颜色或图标背景
		frame.add(getPanelWithBorder(mb, "MatteBorder颜色填充边框"));

		frame.pack();
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// 创建一个带边框的Panel
	public JPanel getPanelWithBorder(Border border, String content) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(content));
		panel.setBorder(border);
		return panel;
	}

	public static void main(String[] args) {
		new BorderDemo1().init();
	}

}
