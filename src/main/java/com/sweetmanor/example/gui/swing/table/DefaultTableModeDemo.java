package com.sweetmanor.example.gui.swing.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class DefaultTableModeDemo {
	JFrame mainWin = new JFrame("管理数据行、数据列");
	final int COLUMN_COUNT = 5;
	DefaultTableModel model;
	JTable table;
	// 用于保存被隐藏列的List集合
	ArrayList<TableColumn> hiddenColumns = new ArrayList<TableColumn>();

	public void init() {
		model = new DefaultTableModel(COLUMN_COUNT, COLUMN_COUNT);
		for (int i = 0; i < COLUMN_COUNT; i++) {
			for (int j = 0; j < COLUMN_COUNT; j++) {
				model.setValueAt("老单元格值 " + i + "  " + j, i, j);
			}
		}
		table = new JTable(model);
		mainWin.add(new JScrollPane(table));

		// 为窗口安装菜单
		JMenuBar menuBar = new JMenuBar();
		mainWin.setJMenuBar(menuBar);
		JMenu tableMenu = new JMenu("管理");
		menuBar.add(tableMenu);

		// 添加隐藏菜单项
		JMenuItem hideColumnsItme = new JMenuItem("隐藏选中列");
		hideColumnsItme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 获取所有选中列的索引
				int[] selected = table.getSelectedColumns();
				TableColumnModel columnModel = table.getColumnModel();
				// 依次把每一个选中的列隐藏起来，并使用List保存这些列
				for (int i = selected.length - 1; i >= 0; i--) {
					TableColumn column = columnModel.getColumn(selected[i]);
					table.removeColumn(column);
					hiddenColumns.add(column);
				}
			}
		});
		tableMenu.add(hideColumnsItme);

		// 添加显示隐藏菜单项
		JMenuItem showColumnsItem = new JMenuItem("显示隐藏项");
		showColumnsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 把所有隐藏的列依次显示出来
				for (TableColumn tc : hiddenColumns) {
					table.addColumn(tc);
				}
				hiddenColumns.clear();
			}
		});
		tableMenu.add(showColumnsItem);

		// 添加插入菜单项
		JMenuItem addColumnItem = new JMenuItem("插入选中列");
		addColumnItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selected = table.getSelectedColumns();
				TableColumnModel model = table.getColumnModel();
				for (int i = selected.length - 1; i >= 0; i--) {
					TableColumn column = model.getColumn(selected[i]);
					table.addColumn(column);
				}
			}
		});
		tableMenu.add(addColumnItem);

		// 添加增加行菜单项
		JMenuItem addRowItem = new JMenuItem("增加行");
		addRowItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] newCells = new String[COLUMN_COUNT];
				for (int i = 0; i < newCells.length; i++) {
					newCells[i] = "新单元格值 " + model.getRowCount() + "  " + i;

				}
				model.addRow(newCells);
			}
		});
		tableMenu.add(addRowItem);

		// 添加删除行菜单项
		JMenuItem removeRowsItem = new JMenuItem("删除选中行");
		removeRowsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[] selected = table.getSelectedRows();
				for (int i = selected.length - 1; i >= 0; i--) {
					model.removeRow(selected[i]);
				}
			}
		});
		tableMenu.add(removeRowsItem);

		mainWin.pack();
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.setVisible(true);
	}

	public static void main(String[] args) {
		new DefaultTableModeDemo().init();
	}
}
