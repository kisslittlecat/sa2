package sa2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import sa2.dbconnection;

public class BLU {
	private static ArrayList <ArrayList<String>> data;
	public static String get_result(String text) {
		data=new ArrayList<ArrayList<String>>();
		ArrayList<String> res = new ArrayList<String>();
		String r = null;
		data=dbconnection.get_data();
		//System.out.print(data);
		for(int i=0;i<data.size();i++) {
			res=data.get(i);
			//System.out.print(res);
			if(res.get(0).equals(text)) 
				r=res.get(1);
		}
		return r;
	}
	
	public static void saveAsFile(JFreeChart chart, String outputPath,
			int weight, int height) {
		FileOutputStream out = null;
		try {
			File outFile = new File(outputPath);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			out = new FileOutputStream(outputPath);
			// 保存为PNG
			// ChartUtilities.writeChartAsPNG(out, chart, weight, height);
			// 保存为JPEG
			ChartUtilities.writeChartAsJPEG(out, chart,weight, height);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}
 
	// 根据CategoryDataset创建JFreeChart对象
	public static JFreeChart createChart(CategoryDataset dataset) {
		// 创建JFreeChart对象：ChartFactory.createLineChart
		JFreeChart jfreechart = ChartFactory.createLineChart("Yearly change in volume", // 标题
				"date", // categoryAxisLabel （category轴，横轴，X轴标签）
				"turnover", // valueAxisLabel（value轴，纵轴，Y轴的标签）
				dataset, // dataset
				PlotOrientation.VERTICAL, true, // legend
				false, // tooltips
				false); // URLs
		// 使用CategoryPlot设置各种参数。以下设置可以省略。
		CategoryPlot plot = (CategoryPlot)jfreechart.getPlot();
		// 背景色 透明度
		plot.setBackgroundAlpha(0.5f);
		// 前景色 透明度
		plot.setForegroundAlpha(0.5f);
		// 其他设置 参考 CategoryPlot类
		LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
		renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
		renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
		renderer.setUseSeriesOffset(true); // 设置偏移量
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		return jfreechart;
	}
 
	/**
	 * 创建CategoryDataset对象
	 * @return 
	 *  
	 */
	public static CategoryDataset createDataset() {
		data=new ArrayList<ArrayList<String>>();
		ArrayList<String> res = new ArrayList<String>();
		ArrayList<String> re1 = new ArrayList<String>();
		ArrayList<String> re2 = new ArrayList<String>();
		String r1 = null;
		String r2 = null;
		data=dbconnection.get_data();
		CategoryDataset ress = null;
		String[] rowKeys = {"turnover of the bank"};
		int j=0;
		do {
		res=data.get(j);
		r1=res.get(0);
		r2=res.get(1);
		re1.add(r1);
		re2.add(r2);
		j++;
		}while(j<data.size());
		String[] colKeys = {re1.get(0),re1.get(1),re1.get(2),re1.get(3),re1.get(4),re1.get(5),re1.get(6),re1.get(7)};
		//System.out.println(re1.toString());
		//System.out.println(re2.toString());
		double[][] chartdata = {{Double.valueOf(re2.get(0)),Double.valueOf(re2.get(1)),Double.valueOf(re2.get(2)),
			Double.valueOf(re2.get(3)),Double.valueOf(re2.get(4)),Double.valueOf(re2.get(5)),
			Double.valueOf(re2.get(6)),Double.valueOf(re2.get(7))},};
		ress=DatasetUtilities.createCategoryDataset(rowKeys, colKeys, chartdata);
		return ress;
	}
	public static Double getavg() {
		data=new ArrayList<ArrayList<String>>();
		ArrayList<String> res = new ArrayList<String>();
		ArrayList<String> re2 = new ArrayList<String>();
		String r2 = null;
		data=dbconnection.get_data();
		
		double sum = 0;
		int j=0;
		do {
			res=data.get(j);
			r2=res.get(1);
			re2.add(r2);
			sum +=Double.valueOf(re2.get(j));
			j++;
			}while(j<data.size());
		int len = re2.size();
		double avg = sum/len;
		return avg;
	}
}