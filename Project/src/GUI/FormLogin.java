package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.NhanVienModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class FormLogin extends JFrame {

	private static JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FormLogin() {
		setTitle("FormLogin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 412);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("QU\u1EA2N L\u00CD TH\u01AF VI\u1EC6N");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(150, 50, 208, 47);
		contentPane.add(lblNewLabel);

		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBackground(SystemColor.inactiveCaptionBorder);
		rdbtnAdmin.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnAdmin.setBounds(197, 129, 113, 34);
		contentPane.add(rdbtnAdmin);

		JRadioButton rdbtnNhanVien = new JRadioButton("Nh\u00E2n Vi\u00EAn");
		rdbtnNhanVien.setBackground(SystemColor.inactiveCaptionBorder);
		rdbtnNhanVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnNhanVien.setBounds(339, 129, 107, 34);
		contentPane.add(rdbtnNhanVien);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnAdmin);
		buttonGroup.add(rdbtnNhanVien);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(39, 204, 120, 28);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(39, 255, 120, 28);
		contentPane.add(lblNewLabel_1_1);

		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(187, 204, 289, 28);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(187, 255, 289, 28);
		contentPane.add(textFieldPassword);

		JButton btnNewButton = new JButton("\u0110\u0103ng Nh\u1EADp");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Username = textFieldUsername.getText().trim();
				String Password = textFieldPassword.getText().trim();
				String Quyen = "";
				if (!checkUsername(Username) || !checkPassword(Password)) {
				} else {
					if (rdbtnAdmin.isSelected()) {
						Quyen = "AD";
						if (NhanVienModel.checkNV(Username, Password, Quyen).getRowCount() == 0) {
							showError("Đăng nhập thất bại!");
						} else {
							FormNhanVien fnv = new FormNhanVien();
							fnv.setVisible(true);

						}
					} else {
						Quyen = "NV";
						if (NhanVienModel.checkNV(Username, Password, Quyen).getRowCount() == 0) {
							showError("Đăng nhập thất bại!");
						} else {
							MenuNV mn = new MenuNV();
							mn.setVisible(true);
						}

					}

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(339, 319, 137, 34);
		contentPane.add(btnNewButton);
	}

	public static void showError(String message) {
		JOptionPane.showMessageDialog(contentPane, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}
	public static void showWarning(String message) {
		JOptionPane.showMessageDialog(contentPane, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
	}
	public static boolean checkUsername(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập Username !");
			return false;
		} else {
			return true;
		}
	}
	public static boolean checkPassword(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập Password !");
			return false;
		} else {
			return true;
		}
	}
}
