package com.sweetmanor.jfreechart;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RefineryUtilities;

public class ChartDemo2_Bar {

	// 获取柱（条）形图的数据集
	private CategoryDataset getCategoryDataset() {
		// 创建默认数据集
		DefaultKeyedValues keyedValues = new DefaultKeyedValues();
		// 添加数据
		keyedValues.setValue("JAVA从入门到精通", 500);
		keyedValues.setValue("视频学JAVA", 800);
		keyedValues.setValue("JAVA全能速查宝典", 1000);
		// 以默认数据集创建条形数据集
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				"图书销量", keyedValues);
		return dataset;
	}

	// 获取柱形图
	private JFreeChart getJFreeChart() {
		// 获取数据集
		CategoryDataset dataset = getCategoryDataset();
		// 创建图表
		JFreeChart chart = ChartFactory.createBarChart3D("2010年上半年销售情况",
				"图书名称", "销售数量", dataset, PlotOrientation.HORIZONTAL, false,
				true, false);
		return chart;
	}

	/**
	 * 设置图表的字体，以支持中文显示
	 */
	private void setCategoryPoltFont(JFreeChart chart) {
		// 设置标题字体
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.BOLD, 20));
		// 获取柱形图
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		// 设置X轴字体
		CategoryAxis xAxis = categoryPlot.getDomainAxis();
		xAxis.setLabelFont(new Font("宋体", Font.BOLD, 14));
		xAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 14));

		// 设置Y轴字体
		ValueAxis yAxis = categoryPlot.getRangeAxis();
		yAxis.setLabelFont(new Font("宋体", Font.BOLD, 14));
		// yAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 14));
		// 设置Y轴最大值
		yAxis.setUpperBound(2000);

	}

	/**
	 * 设置柱形图标签显示内容
	 */
	public void setCategoryPoltNum(JFreeChart chart) {
		// 图表(饼图)
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		CategoryTextAnnotation annotation1 = new CategoryTextAnnotation(
				"JAVA从入门到精通:500本", "JAVA从入门到精通", 800);
		CategoryTextAnnotation annotation2 = new CategoryTextAnnotation(
				"视频学JAVA:800本", "视频学JAVA", 1100);
		CategoryTextAnnotation annotation3 = new CategoryTextAnnotation(
				"JAVA全能速查宝典:1000本", "JAVA全能速查宝典", 1300);

		categoryPlot.addAnnotation(annotation1);
		categoryPlot.addAnnotation(annotation2);
		categoryPlot.addAnnotation(annotation3);

	}

	public static void main(String[] args) {
		ChartDemo2_Bar demo = new ChartDemo2_Bar();
		// 获取图表
		JFreeChart chart = demo.getJFreeChart();
		// 设置字体以支持中文显示
		demo.setCategoryPoltFont(chart);
		// 设置标签显示的内容
		demo.setCategoryPoltNum(chart);
		// 以图表创建窗体
		ChartFrame frame = new ChartFrame("JFreeChart示例", chart);
		frame.pack();
		// 把窗体显示到显示器中央
		RefineryUtilities.centerFrameOnScreen(frame);
		frame.setVisible(true);
	}

}
