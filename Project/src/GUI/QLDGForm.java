package GUI;

import Model.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLDGForm extends JFrame {
	private JPanel contentPane;
	private JTextField IDTextField;
	private JTextField NameTextField;
	private JTextField AddressTextField;
	private JTextField PhoneTextField;
	private JTable table = new JTable();

	public QLDGForm() {
		setResizable(false);
		setTitle("Quản Lý Độc Giả");
		setForeground(SystemColor.window);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 959, 745);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
		int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
		setLocation(x, y);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã Độc Giả");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 15, 140, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tên Độc Giả");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 50, 140, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Địa Chỉ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 85, 140, 30);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Số Điện Thoại");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 120, 140, 30);
		contentPane.add(lblNewLabel_3);

		IDTextField = new JTextField();
		IDTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		IDTextField.setBounds(160, 15, 248, 27);
		contentPane.add(IDTextField);
		IDTextField.setColumns(10);

		NameTextField = new JTextField();
		NameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		NameTextField.setBounds(160, 50, 405, 27);
		contentPane.add(NameTextField);
		NameTextField.setColumns(10);

		AddressTextField = new JTextField();
		AddressTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		AddressTextField.setBounds(160, 85, 771, 27);
		contentPane.add(AddressTextField);
		AddressTextField.setColumns(10);

		PhoneTextField = new JTextField();
		PhoneTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PhoneTextField.setBounds(160, 120, 248, 27);
		contentPane.add(PhoneTextField);
		PhoneTextField.setColumns(10);

		JButton ListButton = new JButton("Danh Sách");
		ListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(QLDGModel.SelectDG());
			}
		});
		ListButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		ListButton.setBounds(16, 624, 140, 43);
		contentPane.add(ListButton);

		JButton SearchButton = new JButton("Tìm");
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = QLDGModel.CountDG(NameTextField.getText());
				try {
					if (rs.next()) {
						int t = rs.getInt(1);
						if (t == 0) {
							JOptionPane.showMessageDialog(contentPane,
									"Không Tìm Thấy Độc Giả Có Tên " + NameTextField.getText(), "Tìm Độc Giả",
									JOptionPane.ERROR_MESSAGE);
						} else {
							table.setModel(QLDGModel.SelectDG(NameTextField.getText()));
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		SearchButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		SearchButton.setBounds(171, 624, 140, 43);
		contentPane.add(SearchButton);

		JButton AddButton = new JButton("Thêm");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Có", "Không" };
				if (JOptionPane.showOptionDialog(contentPane,
						"Bạn Có Chắc Muốn Thêm Độc Giả " + NameTextField.getText(), "Thêm Độc Giả",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[1]) == JOptionPane.YES_OPTION) {
					if (IDTextField.getText().trim().equals("") || NameTextField.getText().trim().equals("")
							|| AddressTextField.getText().trim().equals("")
							|| PhoneTextField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Còn Ô Trống\nXin Vui Lòng Nhập Hết Các Ô",
								"Thêm Độc Giả", JOptionPane.WARNING_MESSAGE);
					} else {
						ResultSet rs = QLDGModel.CountDGByMa(IDTextField.getText());
						try {
							if (rs.next()) {
								int t = rs.getInt(1);
								if (t == 0) {
									QLDGModel.InsertDG(IDTextField.getText(), NameTextField.getText(),
											AddressTextField.getText(), PhoneTextField.getText());
									table.setModel(QLDGModel.SelectDG());
								} else {
									JOptionPane.showMessageDialog(contentPane,
											"Đã Tồn Tại Độc Giả Có Mã " + IDTextField.getText()
													+ "\nXin Vui Lòng Nhập Lại Mã Độc Giả Khác",
											"Thêm Độc Giả", JOptionPane.ERROR_MESSAGE);
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		AddButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		AddButton.setBounds(326, 624, 140, 43);
		contentPane.add(AddButton);

		JButton DeleteButton = new JButton("Xóa");
		DeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Có", "Không" };
				if (JOptionPane.showOptionDialog(contentPane, "Bạn Có Chắc Muốn Xóa Độc Giả " + NameTextField.getText(),
						"Xóa Độc Giả", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[1]) == JOptionPane.YES_OPTION) {
					if (IDTextField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Mã Độc Giả Còn Trống\nXin Vui Lòng Nhập Mã Độc Giả",
								"Xóa Độc Giả", JOptionPane.WARNING_MESSAGE);
					} else {
						ResultSet rs = QLDGModel.CountDGByMa(IDTextField.getText());
						try {
							if (rs.next()) {
								int t = rs.getInt(1);
								if (t == 0) {
									JOptionPane.showMessageDialog(contentPane,
											"Không Tìm Thấy Độc Giả Có Mã " + IDTextField.getText()
													+ "\nXin Vui Lòng Nhập Lại Mã Độc Giả Khác",
											"Xóa Độc Giả", JOptionPane.ERROR_MESSAGE);
								} else {
									QLDGModel.DeleteDG(IDTextField.getText());
									table.setModel(QLDGModel.SelectDG());
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		DeleteButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		DeleteButton.setBounds(481, 624, 140, 43);
		contentPane.add(DeleteButton);

		JButton UpdateButton = new JButton("Sửa");
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Có", "Không" };
				if (JOptionPane.showOptionDialog(contentPane,
						"Bạn Có Chắc Muốn Sửa Thông Tin Độc Giả " + NameTextField.getText(), "Sửa Độc Giả",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[1]) == JOptionPane.YES_OPTION) {
					if (IDTextField.getText().trim().equals("") || NameTextField.getText().trim().equals("")
							|| AddressTextField.getText().trim().equals("")
							|| PhoneTextField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Còn Ô Trống\nXin Vui Lòng Nhập Hết Các Ô",
								"Sửa Độc Giả", JOptionPane.WARNING_MESSAGE);
					} else {
						ResultSet rs = QLDGModel.CountDGByMa(IDTextField.getText());
						try {
							if (rs.next()) {
								int t = rs.getInt(1);
								if (t == 0) {
									JOptionPane.showMessageDialog(contentPane,
											"Không Tìm Thấy Độc Giả Có Mã " + IDTextField.getText()
													+ "\nXin Vui Lòng Nhập Lại Mã Độc Giả Khác",
											"Sửa Độc Giả", JOptionPane.ERROR_MESSAGE);
								} else {
									QLDGModel.UpdateDG(IDTextField.getText(), NameTextField.getText(),
											AddressTextField.getText(), PhoneTextField.getText());
									table.setModel(QLDGModel.SelectDG());
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		UpdateButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		UpdateButton.setBounds(636, 624, 140, 43);
		contentPane.add(UpdateButton);

		JButton ExitButton = new JButton("Thoát");
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Có", "Không" };
				if (JOptionPane.showOptionDialog(contentPane, "Bạn Có Chắc Muốn Thoát Chương Trình",
						"Thoát Chương Trình", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[1]) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		ExitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		ExitButton.setBounds(791, 624, 140, 43);
		contentPane.add(ExitButton);

		ResultSet rs = QLDGModel.CountDG();
		try {
			if (rs.next()) {
				table.setModel(QLDGModel.SelectDG());
				table.setFont(new Font("Tahoma", Font.PLAIN, 16));
				table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
				table.getColumnModel().getColumn(0).setPreferredWidth(15);
				table.getColumnModel().getColumn(1).setPreferredWidth(40);
				table.getColumnModel().getColumn(2).setPreferredWidth(80);
				table.getColumnModel().getColumn(3).setPreferredWidth(15);
				table.setFillsViewportHeight(true);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
							int row = table.getSelectedRow();
							String ma = table.getValueAt(row, 0).toString();
							ResultSet rs = QLDGModel.SelectDGByMa(ma);
							if (rs.next()) {
								IDTextField.setText(rs.getString(1));
								NameTextField.setText(rs.getString(2));
								AddressTextField.setText(rs.getString(3));
								PhoneTextField.setText(rs.getString(4));
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 165, 921, 428);
		contentPane.add(scrollPane);
	}
}