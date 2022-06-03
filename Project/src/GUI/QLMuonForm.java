package GUI;

import Model.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLMuonForm extends JFrame 
{
	private JPanel contentPane;
	private JTextField IDTextField;
	private JTextField IDDGTextField;
	private JTextField IDSachTextField;
	private JTextField DateTextField;
	private JTable table = new JTable();
	private JTextField SearchTextField;

	public QLMuonForm()
	{
		setResizable(false);
		setTitle("Quản Lý Mượn Sách");
		setForeground(SystemColor.window);
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 437, 810);
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

		JLabel lblNewLabel = new JLabel("Mã Mượn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 15, 140, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã Độc Giả");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 50, 140, 30);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Mã Sách Mượn");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 85, 140, 30);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ngày Mượn");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 120, 140, 30);
		contentPane.add(lblNewLabel_3);

		IDTextField = new JTextField();
		IDTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		IDTextField.setBounds(160, 15, 248, 27);
		contentPane.add(IDTextField);
		IDTextField.setColumns(10);

		IDDGTextField = new JTextField();
		IDDGTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		IDDGTextField.setBounds(160, 50, 248, 27);
		contentPane.add(IDDGTextField);
		IDDGTextField.setColumns(10);

		IDSachTextField = new JTextField();
		IDSachTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		IDSachTextField.setBounds(160, 85, 248, 27);
		contentPane.add(IDSachTextField);
		IDSachTextField.setColumns(10);

		DateTextField = new JTextField();
		DateTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DateTextField.setBounds(160, 120, 248, 27);
		contentPane.add(DateTextField);
		DateTextField.setColumns(10);
		DateTextField.setText(java.time.LocalDate.now().toString());
		
		DateTextField.setEditable(false);

		SearchTextField = new JTextField();
		SearchTextField.setBounds(160, 181, 248, 27);
		contentPane.add(SearchTextField);
		SearchTextField.setColumns(10);

		JButton SearchButton = new JButton("Tìm Sách");
		SearchButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ResultSet rs = MuonTraModel.CountSach(SearchTextField.getText());
				try 
				{
					if (rs.next()) 
					{
						int t = rs.getInt(1);
						if (t == 0) 
						{
							JOptionPane.showMessageDialog(contentPane, "Không Tìm Thấy Sách Có Mã " + SearchTextField.getText(), "Tìm Sách", JOptionPane.ERROR_MESSAGE);
						} 
						else
						{
							table.setModel(MuonTraModel.SelectSach(SearchTextField.getText()));
						}
					}
				} catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		SearchButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		SearchButton.setBounds(10, 171, 140, 43);
		contentPane.add(SearchButton);
		
		JButton ListButton = new JButton("DS");
		ListButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				table.setModel(MuonTraModel.SelectSach());
			}
		});
		ListButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		ListButton.setBounds(34, 652, 140, 43);
		contentPane.add(ListButton);
		
		JButton AddButton = new JButton("Mượn Sách");
		AddButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Object[] options = { "Có", "Không" };
				if (JOptionPane.showOptionDialog(contentPane, "Bạn Có Chắc Muốn Thêm Thông Tin Mượn Sách", "Mượn Sách", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]) == JOptionPane.YES_OPTION) 
				{
					if(IDTextField.getText().trim().equals("") || IDDGTextField.getText().trim().equals("") || IDSachTextField.getText().trim().equals("") || DateTextField.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(contentPane, "Còn Ô Trống\nXin Vui Lòng Nhập Hết Các Ô", "Mượn Sách", JOptionPane.WARNING_MESSAGE);
					}
					else
					{		
						Date NgayM = Date.valueOf(DateTextField.getText());
						try 
						{
							ResultSet rs = MuonTraModel.CountMuon(IDTextField.getText());
							rs.next();
							if(rs.getInt(1) == 0)
							{
								MuonTraModel.InsertMuon(IDTextField.getText(), IDDGTextField.getText(), NgayM);
							}
						} catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
						MuonTraModel.InsertCTMuon(IDTextField.getText(), IDSachTextField.getText(), 0);
						MuonTraModel.UpdateSLS(IDSachTextField.getText());
					}
				}
			}
		});
		AddButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		AddButton.setBounds(230, 652, 140, 43);
		contentPane.add(AddButton);

		JButton ExitButton = new JButton("Thoát");
		ExitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Object[] options = { "Có", "Không"};
				if (JOptionPane.showOptionDialog(contentPane, "Bạn Có Chắc Muốn Thoát Chương Trình", "Thoát Chương Trình", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]) == JOptionPane.YES_OPTION) 
				{
					System.exit(0);
				}
			}
		});
		ExitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		ExitButton.setBounds(130, 710, 140, 43);
		contentPane.add(ExitButton);

		ResultSet rs = MuonTraModel.CountSach();
		try 
		{
			if (rs.next()) 
			{
				table.setModel(MuonTraModel.SelectSach());
				table.setFont(new Font("Tahoma", Font.PLAIN, 16));
				table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
				table.getColumnModel().getColumn(0).setPreferredWidth(15);
				table.getColumnModel().getColumn(1).setPreferredWidth(40);
				table.setFillsViewportHeight(true);
				table.addMouseListener(new MouseAdapter() 
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						int row = table.getSelectedRow();
						String ma = table.getValueAt(row, 0).toString();
						IDSachTextField.setText(ma);
					}
				});
			}
		} catch (SQLException e1) 
		{
			e1.printStackTrace();
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 227, 398, 412);
		contentPane.add(scrollPane);
	}
}