package com.sweetmanor.example.gui.swing.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

public class AdjustingWidth {
	JFrame jf = new JFrame("调整表格列宽");
	JMenuBar menuBar = new JMenuBar();
	JMenu adjustModeMenu = new JMenu("调整方式");
	JMenu selectUnitMenu = new JMenu("选择单元");
	JMenu selectModeMenu = new JMenu("选择方式");
	// 定义5个单选按钮，用以控制表格的宽度调整方式
	JRadioButtonMenuItem[] adjustModesItem = new JRadioButtonMenuItem[5];
	// 定义3个单选按钮，用以控制表格的选择方式
	JRadioButtonMenuItem[] selectModesItem = new JRadioButtonMenuItem[3];
	// 定义3个复选框，用以控制表格的单元选择
	JCheckBoxMenuItem rowsItem = new JCheckBoxMenuItem("选择行");
	JCheckBoxMenuItem columnsItem = new JCheckBoxMenuItem("选择列");
	JCheckBoxMenuItem cellsItem = new JCheckBoxMenuItem("选择单元格");

	ButtonGroup adjustBg = new ButtonGroup();
	ButtonGroup selectBg = new ButtonGroup();

	// 定义一个int类型的数组，用于保存表格所有的宽度调整方式
	int[] adjustModes = new int[] { JTable.AUTO_RESIZE_OFF,
			JTable.AUTO_RESIZE_NEXT_COLUMN,
			JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS,
			JTable.AUTO_RESIZE_LAST_COLUMN, JTable.AUTO_RESIZE_ALL_COLUMNS };

	// 定义一个int类型的数组，用于保存表格所有的选择方式
	int[] selectModes = new int[] {
			ListSelectionModel.MULTIPLE_INTERVAL_SELECTION,
			ListSelectionModel.SINGLE_INTERVAL_SELECTION,
			ListSelectionModel.SINGLE_SELECTION };

	JTable table;
	// 定义二维数组作为表格数据
	Object[][] tableData = { new Object[] { "李清照", 29, "女" },
			new Object[] { "苏格拉底", 58, "男" }, new Object[] { "李白", 35, "男" },
			new Object[] { "弄玉", 18, "女" }, new Object[] { "虎头", 2, "男" } };
	// 定义一维数据作为列标题
	Object[] columnTitle = { "姓名", "年龄", "性别" };

	public void init() {
		table = new JTable(tableData, columnTitle);

		// ----------------------为窗口安装设置表格调整方式的菜单
		adjustModesItem[0] = new JRadioButtonMenuItem("只调整表格");
		adjustModesItem[1] = new JRadioButtonMenuItem("只调整下一列");
		adjustModesItem[2] = new JRadioButtonMenuItem("平均调整余下列");
		adjustModesItem[3] = new JRadioButtonMenuItem("只调整最后一列");
		adjustModesItem[4] = new JRadioButtonMenuItem("平均调整所有列");
		menuBar.add(adjustModeMenu);
		for (int i = 0; i < adjustModesItem.length; i++) {
			// 默认选中第3个菜单，即对应表格默认的宽度调整方式
			if (i == 2) {
				adjustModesItem[i].setSelected(true);
			}
			adjustBg.add(adjustModesItem[i]);
			adjustModeMenu.add(adjustModesItem[i]);
			final int index = i;
			// 为设置调整方式的菜单添加监听器
			adjustModesItem[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (adjustModesItem[index].isSelected()) {
						table.setAutoResizeMode(adjustModes[index]);
					}
				}
			});
		}

		// -----------------------------为窗口安装设置表格选择方式的菜单
		selectModesItem[0] = new JRadioButtonMenuItem("无限制");
		selectModesItem[1] = new JRadioButtonMenuItem("单独的连续区");
		selectModesItem[2] = new JRadioButtonMenuItem("单选");
		menuBar.add(selectModeMenu);
		for (int i = 0; i < selectModesItem.length; i++) {
			// 默认选中第一个菜单项，即对应表格默认的选择方式
			if (i == 0) {
				selectModesItem[i].setSelected(true);
			}
			selectBg.add(selectModesItem[i]);
			selectModeMenu.add(selectModesItem[i]);
			final int index = i;
			// 为设置选择方式的菜单添加监听器
			selectModesItem[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (selectModesItem[index].isSelected()) {
						table.getSelectionModel().setSelectionMode(
								selectModes[index]);
					}
				}
			});
		}
		// --------------------为窗口安装设置表格选择单元的菜单
		menuBar.add(selectUnitMenu);
		rowsItem.setSelected(table.getRowSelectionAllowed());
		columnsItem.setSelected(table.getColumnSelectionAllowed());
		cellsItem.setSelected(table.getCellSelectionEnabled());
		rowsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 清空选择状态
				table.clearSelection();
				// 如果该菜单处于选中状态，设置表格的选择单元是行
				table.setRowSelectionAllowed(rowsItem.isSelected());
				// 如果行、列同时被选中，其实质是选择单元格
				cellsItem.setSelected(table.getCellSelectionEnabled());
			}
		});
		selectUnitMenu.add(rowsItem);

		columnsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.clearSelection();
				table.setColumnSelectionAllowed(columnsItem.isSelected());
				cellsItem.setSelected(table.getCellSelectionEnabled());
			}
		});
		selectUnitMenu.add(columnsItem);
		cellsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.clearSelection();
				table.setCellSelectionEnabled(cellsItem.isSelected());
				rowsItem.setSelected(table.getRowSelectionAllowed());
				columnsItem.setSelected(table.getColumnSelectionAllowed());
			}
		});
		selectUnitMenu.add(cellsItem);
		jf.setJMenuBar(menuBar);
		// 分别获取表格的三个表格列，并设置三列的最小宽度、最佳宽度、最大宽度
		TableColumn nameColumn = table.getColumn(columnTitle[0]);
		nameColumn.setMinWidth(100);
		TableColumn ageColumn = table.getColumn(columnTitle[1]);
		ageColumn.setPreferredWidth(50);
		TableColumn genderColumn = table.getColumn(columnTitle[2]);
		genderColumn.setMaxWidth(50);

		jf.add(new JScrollPane(table));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

	}

	public static void main(String[] args) {
		new AdjustingWidth().init();
	}

}
