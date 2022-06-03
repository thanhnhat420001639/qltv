package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Model.MuonTraModel;
import Model.SachModel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class FormTraSach extends JFrame {

	private static JPanel contentPane;
	private JTable table;
	private JTextField txtMaMuonTra;
	private JTextField txtMaSach;
	private JTextField txtTrangThai;
	private JTextField txtNgayTra;
	private JTextField txtTim;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FormTraSach() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel_2 = new JPanel();
		JRadioButton radioMaMuonTra = new JRadioButton("Mã Mượn Trả");
		radioMaMuonTra.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		radioMaMuonTra.setBounds(42, 7, 150, 30);
		radioMaMuonTra.setSelected(true);
		panel_2.add(radioMaMuonTra);

		JRadioButton radioMaSach = new JRadioButton("Mã Sách");
		radioMaSach.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		radioMaSach.setBounds(234, 7, 150, 30);
		panel_2.add(radioMaSach);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioMaMuonTra);
		buttonGroup.add(radioMaSach);

		Vector<String> header = new Vector<String>();
		header.add("Mã Mượn Trả");
		header.add("Mã Sách");
		header.add("Trạng Thái");
		header.add("Ngày Trả");

		Vector data = MuonTraModel.getAll();

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel.setBounds(80, 12, 504, 170);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã Mượn Trả");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel.setBounds(13, 10, 150, 30);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã Sách");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(13, 50, 150, 30);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Trạng Thái");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(13, 90, 150, 30);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ngày Trả");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(13, 130, 150, 26);
		panel.add(lblNewLabel_3);

		txtMaMuonTra = new JTextField();
		txtMaMuonTra.setEditable(false);
		txtMaMuonTra.setBounds(176, 10, 200, 30);
		panel.add(txtMaMuonTra);
		txtMaMuonTra.setColumns(10);

		txtMaSach = new JTextField();
		txtMaSach.setEditable(false);
		txtMaSach.setColumns(10);
		txtMaSach.setBounds(176, 50, 200, 30);
		panel.add(txtMaSach);

		txtTrangThai = new JTextField();
		txtTrangThai.setColumns(10);
		txtTrangThai.setBounds(176, 90, 200, 30);
		panel.add(txtTrangThai);

		txtNgayTra = new JTextField();
		txtNgayTra.setColumns(10);
		txtNgayTra.setBounds(176, 130, 200, 30);
		panel.add(txtNgayTra);

		JButton btnTra = new JButton("TRẢ");
		btnTra.setBounds(389, 35, 100, 100);
		panel.add(btnTra);
		btnTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MaMuonTra = txtMaMuonTra.getText();
				String TrangThai = txtTrangThai.getText();
				String ngaytra = txtNgayTra.getText();
				String MaSach = txtMaSach.getText();
				if (!checkMaMuonTra(MaMuonTra) || !checkTrangThai(TrangThai) || !checkNgayTra(ngaytra)) {

				} else {
					Date NgayTra = Date.valueOf(ngaytra);
					if (MuonTraModel.update(MaMuonTra,MaSach, TrangThai, NgayTra)) {
						if (MuonTraModel.traSach(MaSach)) {
							showInformation("Trả thành công!");

							table.setModel(new DefaultTableModel(MuonTraModel.getAll(), header));
						}
					} else {
						showError("Trả thất bại!");
					}
				}
			}

		});
		btnTra.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel_1.setBounds(32, 299, 600, 250);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 580, 230);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int Row = table.getSelectedRow();
				txtMaMuonTra.setText(String.valueOf(table.getValueAt(Row, 0)));
				txtMaSach.setText(String.valueOf(table.getValueAt(Row, 1)));
				txtTrangThai.setText(String.valueOf(table.getValueAt(Row, 2)));
				txtNgayTra.setText(String.valueOf(table.getValueAt(Row, 3)));
			}
		});

		table.setModel(new DefaultTableModel(data, header));
		scrollPane.setViewportView(table);

		panel_2.setBorder(new LineBorder(new Color(192, 192, 192), 5, true));
		panel_2.setBounds(119, 194, 426, 93);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		txtTim = new JTextField();
		txtTim.setBounds(10, 52, 296, 30);
		panel_2.add(txtTim);
		txtTim.setColumns(10);

		JButton btnTim = new JButton("TÌM");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text = txtTim.getText();
				if (!checkTim(text)) {

				} else {
					if (radioMaMuonTra.isSelected()) {
						Vector a = MuonTraModel.searchByMaMuonTra(text);
						if (!a.isEmpty()) {
							table.setModel(new DefaultTableModel(a, header));
						} else {
							showError("Mã không tồn tại!");
							table.setModel(new DefaultTableModel(MuonTraModel.getAll(), header));
						}
					} else if (radioMaSach.isSelected()) {
						Vector a = MuonTraModel.searchByMaSach(text);
						if (!a.isEmpty()) {
							table.setModel(new DefaultTableModel(a, header));
						} else {
							showError("Mã không tồn tại!");
							table.setModel(new DefaultTableModel(MuonTraModel.getAll(), header));
						}
					}
				}
			}
		});
		btnTim.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnTim.setBounds(316, 52, 100, 30);
		panel_2.add(btnTim);

	}

	public static void showError(String message) {
		JOptionPane.showMessageDialog(contentPane, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	public static void showWarning(String message) {
		JOptionPane.showMessageDialog(contentPane, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
	}

	public static boolean showConfirm(String message) {
		int a = JOptionPane.showConfirmDialog(contentPane, message, "Lựa chọn", JOptionPane.YES_NO_OPTION);
		if (a == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static void showInformation(String message) {
		JOptionPane.showMessageDialog(contentPane, message, "Thông tin", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean checkTrangThai(String id) {
		if (id.trim().equals("") || id.trim().equals("0")) {
			showWarning("Hãy nhập trạng thái! (1: trả, 0: chưa trả)");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkNgayTra(String id) {
		if (id.trim().equals("") || id.trim().equals("null")) {
			showWarning("Hãy nhập ngày trả! (năm-tháng-ngày)");
			return false;
		} else {
			return true;
		}
	}

	private static boolean checkMaMuonTra(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy chọn mã mượn từ bảng dưới!");
			return false;
		} else {
			return true;
		}
	}

	private static boolean checkTim(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập mã sách hoặc mã mượn trả!");
			return false;
		} else {
			return true;
		}
	}
}
