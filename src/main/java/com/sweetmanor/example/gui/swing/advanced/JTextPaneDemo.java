package com.sweetmanor.example.gui.swing.advanced;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.sweetmanor.util.FrameUtil;

/**
 * JTextPane示例：格式化文档
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class JTextPaneDemo {
	JFrame frame = new JFrame("JTextPane示例");

	public void init() {
		JTextPane txtPanel = new JTextPane();
		frame.add(txtPanel);
		txtPanel.setEditable(false);
		txtPanel.setText("疯狂Android讲义\n" + "疯狂Java讲义\n" + "轻量级Java EE企业应用实战\n");

		StyledDocument style = txtPanel.getStyledDocument();// 文档格式

		SimpleAttributeSet android = new SimpleAttributeSet();
		StyleConstants.setForeground(android, Color.RED);
		StyleConstants.setFontSize(android, 24);
		StyleConstants.setFontFamily(android, "Dialog");
		StyleConstants.setUnderline(android, true);
		style.setCharacterAttributes(0, 12, android, true);// 此格式适用前12个字符

		SimpleAttributeSet java = new SimpleAttributeSet();
		StyleConstants.setForeground(java, Color.BLUE);
		StyleConstants.setFontSize(java, 30);
		StyleConstants.setFontFamily(java, "Arial Black");
		StyleConstants.setBold(java, true);
		style.setCharacterAttributes(12, 8, java, true);

		SimpleAttributeSet javaee = new SimpleAttributeSet();
		StyleConstants.setForeground(javaee, Color.GREEN);
		StyleConstants.setFontSize(javaee, 40);
		StyleConstants.setItalic(javaee, true);
		style.setCharacterAttributes(21, 10, javaee, true);

		frame.setSize(600, 400);
		FrameUtil.center(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new JTextPaneDemo().init();
	}

}
