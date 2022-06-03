package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.SachGiaoTrinh;
import Model.SachModel;
import Model.TapChi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class FormSach extends JFrame {

	private static JPanel contentPane;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtTheLoai;
	private JTextField txtMoTa;
	private JTextField txtSoLuong;
	private JTextField txtNXB;
	private JTextField txtTacGia;
	private JTextField txtMonHoc;
	private JTextField txtCapDo;
	private JTextField txtChuDe;
	private JTextField txtNgayPH;
	private JTable tableGT;
	private JTextField txtTimGT;
	private JTextField txtTC;
	private JTable tableTC;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FormSach() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ButtonGroup buttonGroup = new ButtonGroup();

		Vector<String> headerGT = new Vector<String>();
		headerGT.add("Mã Sách");
		headerGT.add("Tên Sách");
		headerGT.add("Thể Loại");
		headerGT.add("Mô Tả");
		headerGT.add("Số Lượng");
		headerGT.add("NXB");
		headerGT.add("Tác Giả");
		headerGT.add("Môn Học");
		headerGT.add("Cấp Độ");

		Vector dataGT = SachModel.getAllGT();

		Vector<String> headerTC = new Vector<String>();
		headerTC.add("Mã Sách");
		headerTC.add("Tên Sách");
		headerTC.add("Thể Loại");
		headerTC.add("Mô Tả");
		headerTC.add("Số Lượng");
		headerTC.add("NXB");
		headerTC.add("Tác Giả");
		headerTC.add("Chủ Đề");
		headerTC.add("Ngày PH");

		Vector dataTC = SachModel.getAllTC();

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel_2.setBounds(619, 37, 374, 200);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JRadioButton radioGT = new JRadioButton("Giáo Trình");
		radioGT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtCapDo.setEditable(true);
				txtMonHoc.setEditable(true);
				txtChuDe.setEditable(false);
				txtNgayPH.setEditable(false);

			}
		});

		radioGT.setBounds(13, 8, 115, 30);
		panel_2.add(radioGT);
		radioGT.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		radioGT.setSelected(true);
		buttonGroup.add(radioGT);

		JRadioButton radioTC = new JRadioButton("Tạp Chí");

		radioTC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChuDe.setEditable(true);
				txtNgayPH.setEditable(true);
				txtCapDo.setEditable(false);
				txtMonHoc.setEditable(false);
			}
		});
		radioTC.setBounds(141, 8, 115, 30);
		panel_2.add(radioTC);
		radioTC.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		buttonGroup.add(radioTC);

		JLabel lblTcGi_1 = new JLabel("Môn Học");
		lblTcGi_1.setBounds(13, 46, 100, 30);
		panel_2.add(lblTcGi_1);
		lblTcGi_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JButton btnThem = new JButton("THÊM");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MaSach = txtMaSach.getText();
				String TenSach = txtTenSach.getText();
				String TheLoai = txtTheLoai.getText();
				String MoTa = txtMoTa.getText();
				String SoLuong = txtSoLuong.getText();
				String NXB = txtNXB.getText();
				String TacGia = txtTacGia.getText();

				if (!checkMaSach(MaSach) || !checkTenSach(TenSach) || !checkTheLoai(TheLoai) || !checkMoTa(MoTa)
						|| !checkSoLuong(SoLuong) || !checkNXB(NXB) || !checkTacGia(TacGia)) {

				} else {
					if (radioGT.isSelected()) {
						String MonHoc = txtMonHoc.getText();
						String CapDo = txtCapDo.getText();
						if (!checkMonHoc(MonHoc) || !checkCapDo(CapDo)) {

						} else {
							if (!SachModel.exist(MaSach)) {
								SachGiaoTrinh a = new SachGiaoTrinh(MaSach, TenSach, TheLoai, MoTa,
										Integer.parseInt(SoLuong), NXB, TacGia, MonHoc, CapDo);
								if (SachModel.insertGT(a)) {
									showInformation("Thêm thành công!");
									tableGT.setModel(new DefaultTableModel(SachModel.getAllGT(), headerGT));
								} else {
									showError("Thêm thất bại!");
								}
							} else {
								showError("Mã số đã tồn tại!");
							}
						}
					} else if (radioTC.isSelected()) {
						String ChuDe = txtChuDe.getText();
						String ngayph = txtNgayPH.getText();

						if (!checkChuDe(ChuDe) || !checkNgayPH(ngayph)) {

						} else {
							Date NgayPH = Date.valueOf(ngayph);
							if (!SachModel.exist(MaSach)) {
								TapChi a = new TapChi(MaSach, TenSach, TheLoai, MoTa, Integer.parseInt(SoLuong), NXB,
										TacGia, ChuDe, NgayPH);
								if (SachModel.insertTC(a)) {
									showInformation("Thêm thành công!");
									tableTC.setModel(new DefaultTableModel(SachModel.getAllTC(), headerTC));
								} else {
									showError("Thêm thất bại!");
								}
							} else {
								showError("Mã số đã tồn tại!");
							}
						}
					}
				}
			}
		});
		btnThem.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnThem.setBounds(1013, 37, 150, 50);
		contentPane.add(btnThem);

		JButton btnXoa = new JButton("XOÁ");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioGT.isSelected()) {
					int row = tableGT.getSelectedRow();
					if (row == -1) {
						showWarning("Hãy chọn trong bảng giáo trình!");
					} else {
						String id = (String.valueOf(tableGT.getValueAt(row, 0)));
						if (showConfirm("Bạn chắc chắn sẽ xoá chứ?")) {
							SachModel.deleteGT(id);
							showInformation("Xoá thành công!");

							tableGT.setModel(new DefaultTableModel(SachModel.getAllGT(), headerGT));
						} else {
							showError("Xoá thất bại!");
						}
					}
				} else if (radioTC.isSelected()) {
					int row = tableTC.getSelectedRow();
					if (row == -1) {
						showWarning("Hãy chọn trong bảng tạp chí!");
					} else {
						String id = (String.valueOf(tableTC.getValueAt(row, 0)));
						if (showConfirm("Bạn chắc chắn sẽ xoá chứ?")) {
							SachModel.deleteTC(id);
							showInformation("Xoá thành công!");

							tableTC.setModel(new DefaultTableModel(SachModel.getAllTC(), headerTC));
						} else {
							showError("Xoá thất bại!");
						}
					}
				}
			}
		});
		btnXoa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnXoa.setBounds(1013, 115, 150, 50);
		contentPane.add(btnXoa);

		JButton btnSua = new JButton("SỬA");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String MaSach = txtMaSach.getText();
				String TenSach = txtTenSach.getText();
				String TheLoai = txtTheLoai.getText();
				String MoTa = txtMoTa.getText();
				String SoLuong = txtSoLuong.getText();
				String NXB = txtNXB.getText();
				String TacGia = txtTacGia.getText();
				if (!checkMaSach(MaSach) || !checkTenSach(TenSach) || !checkTheLoai(TheLoai) || !checkMoTa(MoTa)
						|| !checkSoLuong(SoLuong) || !checkNXB(NXB) || !checkTacGia(TacGia)) {

				} else {
					if (radioGT.isSelected()) {
						String CapDo = txtCapDo.getText();
						String MonHoc = txtMonHoc.getText();
						if (!checkMonHoc(MonHoc) || !checkCapDo(CapDo)) {

						} else {
							SachGiaoTrinh a = new SachGiaoTrinh(MaSach, TenSach, TheLoai, MoTa,
									Integer.parseInt(SoLuong), NXB, TacGia, MonHoc, CapDo);
							if (SachModel.updateGT(a)) {
								showInformation("Sửa thành công!");

								tableGT.setModel(new DefaultTableModel(SachModel.getAllGT(), headerGT));
							} else {
								showError("Sửa thất bại!");
							}
						}
					} else if (radioTC.isSelected()) {
						String ChuDe = txtChuDe.getText();
						String ngayph = txtNgayPH.getText();
						if (!checkChuDe(ChuDe) || !checkNgayPH(ngayph)) {

						} else {
							Date NgayPH = Date.valueOf(ngayph);
							TapChi a = new TapChi(MaSach, TenSach, TheLoai, MoTa, Integer.parseInt(SoLuong), NXB,
									TacGia, ChuDe, NgayPH);
							if (SachModel.updateTC(a)) {
								showInformation("Sửa thành công!");

								tableTC.setModel(new DefaultTableModel(SachModel.getAllTC(), headerTC));
							} else {
								showError("Sửa thất bại!");
							}
						}
					}
				}
			}
		});
		btnSua.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSua.setBounds(1013, 187, 150, 50);
		contentPane.add(btnSua);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(20, 37, 579, 200);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblTnSch = new JLabel("Tên Sách");
		lblTnSch.setBounds(23, 61, 85, 27);
		panel_1.add(lblTnSch);
		lblTnSch.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("Mã Sách");
		lblNewLabel.setBounds(23, 17, 85, 27);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblThLoi = new JLabel("Thể Loại");
		lblThLoi.setBounds(23, 105, 85, 30);
		panel_1.add(lblThLoi);
		lblThLoi.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblMT = new JLabel("Mô Tả");
		lblMT.setBounds(23, 152, 85, 30);
		panel_1.add(lblMT);
		lblMT.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtMaSach = new JTextField();
		txtMaSach.setBounds(118, 16, 150, 30);
		panel_1.add(txtMaSach);
		txtMaSach.setColumns(10);

		JLabel lblSLng = new JLabel("Số Lượng");
		lblSLng.setBounds(293, 27, 100, 30);
		panel_1.add(lblSLng);
		lblSLng.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblNxb = new JLabel("NXB");
		lblNxb.setBounds(293, 84, 100, 30);
		panel_1.add(lblNxb);
		lblNxb.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblTcGi = new JLabel("Tác Giả");
		lblTcGi.setBounds(293, 141, 100, 30);
		panel_1.add(lblTcGi);
		lblTcGi.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(403, 27, 150, 30);
		panel_1.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		txtNXB = new JTextField();
		txtNXB.setBounds(403, 84, 150, 30);
		panel_1.add(txtNXB);
		txtNXB.setColumns(10);

		txtTacGia = new JTextField();
		txtTacGia.setBounds(403, 141, 150, 30);
		panel_1.add(txtTacGia);
		txtTacGia.setColumns(10);

		txtTenSach = new JTextField();
		txtTenSach.setBounds(118, 62, 150, 30);
		panel_1.add(txtTenSach);
		txtTenSach.setColumns(10);

		txtTheLoai = new JTextField();
		txtTheLoai.setBounds(118, 108, 150, 30);
		panel_1.add(txtTheLoai);
		txtTheLoai.setColumns(10);

		txtMoTa = new JTextField();
		txtMoTa.setBounds(118, 154, 150, 30);
		panel_1.add(txtMoTa);
		txtMoTa.setColumns(10);

		txtMonHoc = new JTextField();
		txtMonHoc.setBounds(123, 46, 150, 30);
		panel_2.add(txtMonHoc);
		txtMonHoc.setColumns(10);

		JLabel lblTcGi_2 = new JLabel("Cấp Độ");
		lblTcGi_2.setBounds(13, 84, 100, 30);
		panel_2.add(lblTcGi_2);
		lblTcGi_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtCapDo = new JTextField();
		txtCapDo.setBounds(123, 84, 150, 30);
		panel_2.add(txtCapDo);
		txtCapDo.setColumns(10);

		JLabel lblTcGi_3 = new JLabel("Chủ Đề");
		lblTcGi_3.setBounds(13, 122, 100, 30);
		panel_2.add(lblTcGi_3);
		lblTcGi_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtChuDe = new JTextField();
		txtChuDe.setBounds(123, 122, 150, 30);
		txtChuDe.setEditable(false);

		panel_2.add(txtChuDe);
		txtChuDe.setColumns(10);

		JLabel lblTcGi_4 = new JLabel("Ngày PH");
		lblTcGi_4.setBounds(13, 160, 100, 30);
		panel_2.add(lblTcGi_4);
		lblTcGi_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtNgayPH = new JTextField();
		txtNgayPH.setBounds(123, 160, 150, 30);
		txtNgayPH.setEditable(false);
		panel_2.add(txtNgayPH);
		txtNgayPH.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel_3.setBounds(8, 274, 580, 350);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblGioTrnh = new JLabel("Giáo Trình");
		lblGioTrnh.setBounds(10, 16, 100, 30);
		panel_3.add(lblGioTrnh);
		lblGioTrnh.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtTimGT = new JTextField();
		txtTimGT.setBounds(110, 16, 350, 30);
		panel_3.add(txtTimGT);
		txtTimGT.setColumns(10);

		JButton btnTimGT = new JButton("TÌM");
		btnTimGT.setBounds(470, 16, 90, 30);
		panel_3.add(btnTimGT);
		btnTimGT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = txtTimGT.getText();
				if (!checkTimGT(text)) {

				} else {
					Vector a = SachModel.searchGTByName(text);
					if (!a.isEmpty()) {
						tableGT.setModel(new DefaultTableModel(a, headerGT));
					} else {
						showError("Sách không tồn tại!");
						tableGT.setModel(new DefaultTableModel(SachModel.getAllGT(), headerGT));
					}
				}
			}
		});
		btnTimGT.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 560, 270);
		panel_3.add(scrollPane);

		tableGT = new JTable();
		tableGT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				radioGT.setSelected(true);
				txtChuDe.setEditable(false);
				txtNgayPH.setEditable(false);
				txtMonHoc.setEditable(true);
				txtCapDo.setEditable(true);
				int Row = tableGT.getSelectedRow();
				txtMaSach.setText(String.valueOf(tableGT.getValueAt(Row, 0)));
				txtTenSach.setText(String.valueOf(tableGT.getValueAt(Row, 1)));
				txtTheLoai.setText(String.valueOf(tableGT.getValueAt(Row, 2)));
				txtMoTa.setText(String.valueOf(tableGT.getValueAt(Row, 3)));
				txtSoLuong.setText(String.valueOf(tableGT.getValueAt(Row, 4)));
				txtNXB.setText(String.valueOf(tableGT.getValueAt(Row, 5)));
				txtTacGia.setText(String.valueOf(tableGT.getValueAt(Row, 6)));
				txtMonHoc.setText(String.valueOf(tableGT.getValueAt(Row, 7)));
				txtCapDo.setText(String.valueOf(tableGT.getValueAt(Row, 8)));

			}
		});

		tableGT.setModel(new DefaultTableModel(dataGT, headerGT));

		scrollPane.setViewportView(tableGT);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 5, true));
		panel.setBounds(596, 274, 580, 350);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblGioTrnh_1 = new JLabel("Tạp Chí");
		lblGioTrnh_1.setBounds(10, 16, 100, 30);
		panel.add(lblGioTrnh_1);
		lblGioTrnh_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtTC = new JTextField();
		txtTC.setBounds(120, 16, 350, 30);
		panel.add(txtTC);
		txtTC.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 62, 560, 270);
		panel.add(scrollPane_1);

		tableTC = new JTable();
		tableTC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				radioTC.setSelected(true);
				txtChuDe.setEditable(true);
				txtNgayPH.setEditable(true);
				txtMonHoc.setEditable(false);
				txtCapDo.setEditable(false);
				int Row = tableTC.getSelectedRow();
				txtMaSach.setText(String.valueOf(tableTC.getValueAt(Row, 0)));
				txtTenSach.setText(String.valueOf(tableTC.getValueAt(Row, 1)));
				txtTheLoai.setText(String.valueOf(tableTC.getValueAt(Row, 2)));
				txtMoTa.setText(String.valueOf(tableTC.getValueAt(Row, 3)));
				txtSoLuong.setText(String.valueOf(tableTC.getValueAt(Row, 4)));
				txtNXB.setText(String.valueOf(tableTC.getValueAt(Row, 5)));
				txtTacGia.setText(String.valueOf(tableTC.getValueAt(Row, 6)));
				txtChuDe.setText(String.valueOf(tableTC.getValueAt(Row, 7)));
				txtNgayPH.setText(String.valueOf(tableTC.getValueAt(Row, 8)));
			}
		});

		tableTC.setModel(new DefaultTableModel(dataTC, headerTC));

		scrollPane_1.setViewportView(tableTC);

		JButton btnTimTC = new JButton("TÌM");
		btnTimTC.setBounds(480, 16, 90, 30);
		panel.add(btnTimTC);
		btnTimTC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = txtTC.getText();
				if (!checkTimTC(text)) {

				} else {
					Vector a = SachModel.searchTCByName(text);
					if (!a.isEmpty()) {
						tableTC.setModel(new DefaultTableModel(a, headerTC));
					} else {
						showError("Sách không tồn tại!");
						tableTC.setModel(new DefaultTableModel(SachModel.getAllTC(), headerTC));
					}
				}
			}
		});
		btnTimTC.setFont(new Font("Segoe UI", Font.PLAIN, 20));

	}

	protected boolean checkMaSach(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập mã sách!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkTenSach(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập tên sách!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkTheLoai(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập thể loại!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkMoTa(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập mô tả!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkSoLuong(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập số lượng (số)!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkTacGia(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập tác giả!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkNXB(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập nhà xuất bản!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkMonHoc(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập môn học!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkChuDe(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập chủ đề!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkCapDo(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập cấp độ!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkNgayPH(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập ngày phát hành!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkTimGT(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập tên sách giáo trình!");
			return false;
		} else {
			return true;
		}
	}

	protected boolean checkTimTC(String id) {
		if (id.trim().equals("")) {
			showWarning("Hãy nhập tên tạp chí!");
			return false;
		} else {
			return true;
		}
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
}
