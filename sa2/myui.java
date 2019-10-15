package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class myui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myui frame = new myui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public myui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(106, 35, 263, 24);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(106, 80, 263, 24);
		contentPane.add(textArea_1);
		
	 	JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textArea.getText();
				textArea_1.setText(BLU.get_result(text));
		        //System.out.println(BLU.get_result(text));
			}
		});
		button.setBounds(25, 143, 71, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("绘图");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				// 步骤1：创建CategoryDataset对象（准备数据）
				CategoryDataset dataset = BLU.createDataset();
				// 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
				JFreeChart freeChart = BLU.createChart(dataset);
				// 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
				BLU.saveAsFile(freeChart, "M:\\2.jpg", 1000, 800);
		        //System.out.println("折线图已成功生成");
			}
		});
		button_1.setBounds(141, 143, 71, 23);
		contentPane.add(button_1);
		
		JLabel label = new JLabel("\u8F93\u5165\u65E5\u671F\uFF1A");
		label.setBounds(25, 40, 71, 24);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8F93\u51FA\u663E\u793A\uFF1A");
		label_1.setBounds(26, 85, 70, 19);
		contentPane.add(label_1);
		
		JButton button_2 = new JButton("计算平均成交量");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				Double avg;
				avg=BLU.getavg();
				textArea_1.setText(String.valueOf(avg));
			}
		});
		button_2.setBounds(244, 142, 125, 24);
		contentPane.add(button_2);
	}
}
