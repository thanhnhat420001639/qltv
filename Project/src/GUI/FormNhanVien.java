package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.NhanVien;
import Model.NhanVienModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.SystemColor;

@SuppressWarnings({ "unused", "serial" })
public class FormNhanVien extends JFrame {

	private static JPanel contentPane;
	private JTextField textFieldMa;
	private JTextField textFieldTen;
	private JTextField textFieldDiaChi;
	private JTextField textFieldEmail;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldQuyen;
	private static JTable table;
	private JTextField textFieldSearch;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public FormNhanVien() {
		setTitle("FormNhanVien");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 497);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("M\u00E3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 27, 86, 25);
		contentPane.add(lblNewLabel);

		JLabel lblTn = new JLabel("T\u00EAn");
		lblTn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTn.setBounds(31, 71, 86, 25);
		contentPane.add(lblTn);

		JLabel lblaCh = new JLabel("\u0110\u1ECBa Ch\u1EC9");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblaCh.setBounds(31, 119, 86, 25);
		contentPane.add(lblaCh);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(31, 162, 86, 25);
		contentPane.add(lblEmail);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(432, 27, 86, 25);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(432, 71, 86, 25);
		contentPane.add(lblPassword);

		JLabel lblQuyn = new JLabel("Quy\u1EC1n");
		lblQuyn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuyn.setBounds(432, 119, 86, 25);
		contentPane.add(lblQuyn);

		textFieldMa = new JTextField();
		textFieldMa.setBounds(136, 27, 234, 25);
		contentPane.add(textFieldMa);
		textFieldMa.setColumns(10);

		textFieldTen = new JTextField();
		textFieldTen.setColumns(10);
		textFieldTen.setBounds(136, 71, 234, 25);
		contentPane.add(textFieldTen);

		textFieldDiaChi = new JTextField();
		textFieldDiaChi.setColumns(10);
		textFieldDiaChi.setBounds(136, 119, 234, 25);
		contentPane.add(textFieldDiaChi);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(136, 164, 234, 25);
		contentPane.add(textFieldEmail);

		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(541, 27, 234, 25);
		contentPane.add(textFieldUsername);

		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(541, 71, 234, 25);
		contentPane.add(textFieldPassword);

		textFieldQuyen = new JTextField();
		textFieldQuyen.setColumns(10);
		textFieldQuyen.setBounds(541, 119, 234, 25);
		contentPane.add(textFieldQuyen);

		JButton btnInsert = new JButton("Th\u00EAm");
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Ma = textFieldMa.getText().trim();
				String Ten = textFieldTen.getText().trim();
				String DiaChi = textFieldDiaChi.getText().trim();
				String Email = textFieldEmail.getText().trim();
				String Username = textFieldUsername.getText().trim();
				String Password = textFieldPassword.getText().trim();
				String Quyen = textFieldQuyen.getText().trim();

				if (!checkMa(Ma) || !checkTen(Ten) || !checkDiaChi(DiaChi) || !checkEmail(Email)
						|| !checkUsername(Username) || !checkPassword(Password) || !checkQuyen(Quyen)) {

				} else {

					if (NhanVienModel.checkMa(Ma).getRowCount() != 0) {
						showError("Mã số đã tồn tại!");

					} else {
						if (NhanVienModel.checkUsername(Username).getRowCount() != 0) {
							showError("Username đã tồn tại!");
						} else {
							NhanVien N = new NhanVien(Ma, Ten, DiaChi, Email, Username, Password, Quyen);
							if (NhanVienModel.insert(N)) {
								showInformation("Thêm thành công!");
								view();
							} else {
								showError("Thêm thất bại!");
							}
						}
					}

				}
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnInsert.setBounds(31, 235, 85, 33);
		contentPane.add(btnInsert);

		JButton btnDelete = new JButton("X\u00F3a");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					showWarning("Hãy chọn các dòng cần xoá!");
				} else {
					String id = (String.valueOf(table.getValueAt(row, 0)));
					if (showConfirm("Bạn chắc chắn sẽ xoá chứ?")) {
						NhanVienModel.delete(id);
						showInformation("Xoá thành công!");
						view();
					} else {
						showError("Xoá thất bại!");
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBounds(136, 235, 85, 33);
		contentPane.add(btnDelete);

		JButton btnUpdate = new JButton("S\u1EEDa");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Ma = textFieldMa.getText().trim();
				String Ten = textFieldTen.getText().trim();
				String DiaChi = textFieldDiaChi.getText().trim();
				String Email = textFieldEmail.getText().trim();
				String Username = textFieldUsername.getText().trim();
				String Password = textFieldPassword.getText().trim();
				String Quyen = textFieldQuyen.getText().trim();

				if (!checkMa(Ma) || !checkTen(Ten) || !checkDiaChi(DiaChi) || !checkEmail(Email)
						|| !checkUsername(Username) || !checkPassword(Password) || !checkQuyen(Quyen)) {

				} else {
					NhanVien N = new NhanVien(Ma, Ten, DiaChi, Email, Username, Password, Quyen);
					if (NhanVienModel.update(N)) {
						showInformation("Thêm thành công!");
						view();
					} else {
						showError("Thêm thất bại!");
					}

				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(242, 235, 85, 33);
		contentPane.add(btnUpdate);

		JButton btnSearch = new JButton("T\u00ECm");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Ten = textFieldSearch.getText().trim();
				if ( !checkSearch(Ten) ) {

				} else {
					table.setModel(NhanVienModel.search(Ten));
				}
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.setBounds(455, 235, 85, 33);
		contentPane.add(btnSearch);

		JButton btnLoad = new JButton("Load");
		btnLoad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view();
			}
		});
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLoad.setBounds(348, 235, 85, 33);
		contentPane.add(btnLoad);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 301, 727, 136);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String Ma = (String.valueOf(table.getValueAt(row, 0)));
				NhanVien N = new NhanVien();
				N = NhanVienModel.getMa(Ma);
				textFieldMa.setText(N.getMa());
				textFieldTen.setText(N.getTen());
				textFieldDiaChi.setText(N.getDiaChi());
				textFieldEmail.setText(N.getEmail());
				textFieldUsername.setText(N.getUsername());
				textFieldPassword.setText(N.getPassword());
				textFieldQuyen.setText(N.getQuyen());
				
			}
		});

		scrollPane.setColumnHeaderView(table);
		view();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		scrollPane.setViewportView(table);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(550, 237, 225, 31);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);

	}

	public static void view() {
		table.setModel(NhanVienModel.load());
	}

	public static boolean checkMa(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập mã nhân viên!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkTen(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập tên nhân viên!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkDiaChi(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập địa chỉ!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkEmail(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập email!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkUsername(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập username!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkPassword(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập password!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkQuyen(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập quyền!");
			return false;
		} else {
			return true;
		}
	}
	public static boolean checkSearch(String id) {
		if (id.equals("")) {
			showWarning("Hãy nhập tên nhân viên muốn tìm !");
			return false;
		} else {
			return true;
		}
	}

	public static boolean showConfirm(String message) {
		int a = JOptionPane.showConfirmDialog(contentPane, message, "Lựa chọn", JOptionPane.YES_NO_OPTION);
		if (a == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static void showError(String message) {
		JOptionPane.showMessageDialog(contentPane, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
	}

	public static void showWarning(String message) {
		JOptionPane.showMessageDialog(contentPane, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
	}

	public static void showInformation(String message) {
		JOptionPane.showMessageDialog(contentPane, message, "Thông tin", JOptionPane.INFORMATION_MESSAGE);
	}
}
