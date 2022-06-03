package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Model.DocGiaModel;
import Model.MuonTraModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

@SuppressWarnings("serial")
public class FormThongKe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FormThongKe() {
		setTitle("FormThongKe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 760);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ChartPanel chartPanel = new ChartPanel(createChart());
		chartPanel.setBounds(10, 5, 888, 512);
		chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
		contentPane.add(chartPanel);
		chartPanel.setLayout(null);
		
		JLabel LabelMuon = new JLabel("Số sách đã được mượn");
		LabelMuon.setForeground(new Color(0, 0, 139));
		LabelMuon.setText("  Số sách đã được mượn :    "+MuonTraModel.CountSach1());
		LabelMuon.setFont(new Font("Tahoma", Font.BOLD, 18));
		LabelMuon.setBounds(148, 552, 378, 44);
		contentPane.add(LabelMuon);
		
		JLabel LabelTra = new JLabel("Số sách chưa được trả");
		LabelTra.setForeground(new Color(0, 0, 139));
		LabelTra.setText("   Số sách chưa được trả :    "+MuonTraModel.CountTra());
		LabelTra.setFont(new Font("Tahoma", Font.BOLD, 18));
		LabelTra.setBounds(145, 606, 363, 44);
		contentPane.add(LabelTra);
		
		JLabel LabelDG = new JLabel("Số độc giả hiện đang có");
		LabelDG.setForeground(new Color(0, 0, 139));
		LabelDG.setText("Số độc giả hiện đang có :    "+DocGiaModel.CountDocGia());
		LabelDG.setFont(new Font("Tahoma", Font.BOLD, 18));
		LabelDG.setBounds(148, 669, 411, 44);
		contentPane.add(LabelDG);

	}

	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ SỐ SÁCH ĐƯỢC MƯỢN", "Sách", "Số lần mượn", MuonTraModel.load(),
				PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	/*private static CategoryDataset createDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(68000000, "Số người", "1990");
		dataset.addValue(80000000, "Số người", "2000");
		dataset.addValue(88000000, "Số người", "2010");
		dataset.addValue(95000000, "Số người", "2020");
		return dataset;
	}*/

}
