package com.sweetmanor.example.gui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JFrame;

import com.sweetmanor.util.FrameUtil;

/**
 * GridLayout示例
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class GridLayoutDemo1 {

	public static void main(String[] args) {
		JFrame frame = new JFrame("GridLayout示例");
		frame.add(new TextField(20), BorderLayout.NORTH);

		String[] btNames = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"+", "-", "*", "/", "." };
		// GridLayout布局:
		// rows:行数3
		// cols:列数5
		// hgap:水平间距6
		// vgap:垂直间距6
		Panel panel = new Panel(new GridLayout(3, 5, 6, 6));
		for (int i = 0; i < btNames.length; i++)
			panel.add(new Button(btNames[i]));
		frame.add(panel);

		frame.setSize(400, 300);
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
