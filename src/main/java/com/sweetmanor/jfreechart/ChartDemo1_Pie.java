package com.sweetmanor.jfreechart;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ChartDemo1_Pie {

	// 获取饼图的数据集
	private PieDataset getPieDataset() {
		// 创建默认饼图数据集
		DefaultPieDataset dataset = new DefaultPieDataset();
		// 添加数据
		dataset.setValue("JAVA从入门到精通", 500);
		dataset.setValue("视频学JAVA", 800);
		dataset.setValue("JAVA全能速查宝典", 1000);
		return dataset;
	}

	// 获取饼图
	private JFreeChart getJFreeChart() {
		// 获取数据集
		PieDataset dataset = getPieDataset();
		// 创建图表
		JFreeChart chart = ChartFactory.createPieChart("2010.8月份销售排行", dataset,
				true, true, false);
		return chart;
	}

	/**
	 * 设置图表的字体，以支持中文显示
	 */
	private void setPiePoltFont(JFreeChart chart) {
		// 设置图表字体
		PiePlot piePlot = (PiePlot) chart.getPlot();
		piePlot.setLabelFont(new Font("宋体", Font.PLAIN, 14));
		// 设置标题字体
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.BOLD, 20));
		// 设置图示字体
		LegendTitle legendTitle = chart.getLegend();
		legendTitle.setItemFont(new Font("宋体", Font.PLAIN, 12));
	}

	/**
	 * 设置饼图标签显示内容
	 */
	public void setPiePoltNum(JFreeChart chart) {
		// 图表(饼图)
		PiePlot piePlot = (PiePlot) chart.getPlot();
		// 标签显示内容支持4个占位符，分别是：{0}类别名称、{1}数量、{2}占比、{3}数量总和
		// 其他所有字符当作普通字符处理，可以加逗号分隔符等，可以加单位等
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}:{1}本"));
	}

	public static void main(String[] args) {
		ChartDemo1_Pie demo = new ChartDemo1_Pie();
		// 获取图表
		JFreeChart chart = demo.getJFreeChart();
		// 设置字体以支持中文显示
		demo.setPiePoltFont(chart);
		// 设置标签显示的内容
		demo.setPiePoltNum(chart);
		// 以图表创建窗体
		ChartFrame frame = new ChartFrame("JFreeChart示例", chart);
		frame.pack();
		frame.setVisible(true);
	}

}
