package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.tools.Tool;

public class MenuNV extends JFrame {
	private JMenuBar mb;
	private Font mFont;
	private JMenu mFile;
	private JMenu mQLDG;
	private JMenu mQLS;
	private JMenu mQLMT;
	private JMenuItem miExit;
	private JMenuItem miQLDG;
	private JMenuItem miQLS;
	private JMenuItem miQLM;
	private JMenuItem miQLT;
	private JMenuItem miTK;
	private JPanel contentPane;

	public MenuNV() {
		setTitle("Nhân Viên");
		mb = new JMenuBar();

		// File
		mFile = new JMenu("File");
		mFile.setMnemonic(KeyEvent.VK_F);

		miExit = new JMenuItem("Exit");
		// miExit.setMnemonic(KeyEvent.VK_E);
		miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		miExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		mFile.add(miExit);
		mb.add(mFile);

		// Quản Lý Độc Giả
		mQLDG = new JMenu("Quản Lý Độc Giả");
		mQLDG.setMnemonic(KeyEvent.VK_D);

		miQLDG = new JMenuItem("Quản Lý Độc Giả");
		// miDMKH.setMnemonic(KeyEvent.VK_K);
		miQLDG.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		miQLDG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				QLDGForm dgfr = new QLDGForm();
				dgfr.setVisible(true);
			}
		});

		mQLDG.add(miQLDG);
		mb.add(mQLDG);

		// Quản Lý Sách
		mQLS = new JMenu("Quản Lý Sách");
		mQLS.setMnemonic(KeyEvent.VK_S);

		miQLS = new JMenuItem("Quản Lý Sách");
		// miDSDH.setMnemonic(KeyEvent.VK_D);
		miQLS.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		miQLS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FormSach sfr = new FormSach();
				sfr.setVisible(true);
			}
		});

		mQLS.add(miQLS);
		mb.add(mQLS);

		// Quản Lý Mượn Trả Sách
		mQLMT = new JMenu("Quản Lý Mượn-Trả Sách");
		mQLMT.setMnemonic(KeyEvent.VK_M);

		miQLM = new JMenuItem("Quản Lý Mượn Sách");
		// miDSDH.setMnemonic(KeyEvent.VK_D);
		miQLM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		miQLM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				QLMuonForm mfr = new QLMuonForm();
				mfr.setVisible(true);
			}
		});

		miQLT = new JMenuItem("Quản Lý Trả Sách");
		// miDSDH.setMnemonic(KeyEvent.VK_D);
		miQLT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		miQLT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FormTraSach tfr = new FormTraSach();
				tfr.setVisible(true);
			}
		});

		miTK = new JMenuItem("Thống kê");
		// miDSDH.setMnemonic(KeyEvent.VK_D);
		miTK.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		miTK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FormThongKe tkfr = new FormThongKe();
				tkfr.setVisible(true);
			}
		});

		mQLMT.add(miQLM);
		mQLMT.add(miQLT);
		mQLMT.add(miTK);
		mb.add(mQLMT);

		setJMenuBar(mb);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // Lấy kích thước màn hình
		setBounds(0, 0, screenSize.width, screenSize.height); // Căn ra tối đa
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}