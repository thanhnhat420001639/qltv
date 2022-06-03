package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DAO.connectDatabase;


public class NhanVienModel {
	private static String[] header = { "Mã Nhân Viên", "Tên Nhân Viên","Địa Chỉ","Email","Username","Password","Quyền"};
	static Connection conn = connectDatabase.getMySQLConnection();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static DefaultTableModel load() {
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);

		String sql = "select * from nhanvien";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				data.add(rs.getString(5));
				data.add(rs.getString(6));
				data.add(rs.getString(7));
				tableModel.addRow(data);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return tableModel;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static DefaultTableModel search(String ten) {
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);

		String sql = "select * from nhanvien where Ten like '%"+ten+"%'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				data.add(rs.getString(5));
				data.add(rs.getString(6));
				data.add(rs.getString(7));
				tableModel.addRow(data);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return tableModel;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DefaultTableModel checkMa(String ma) {
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);

		String sql = "select * from nhanvien where Ma= '"+ma+"'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				data.add(rs.getString(5));
				data.add(rs.getString(6));
				data.add(rs.getString(7));
				tableModel.addRow(data);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return tableModel;
	}
	public static NhanVien getMa(String ma) {
		NhanVien N = new NhanVien();

		String sql = "select * from nhanvien where Ma= '"+ma+"'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				N.setMa(rs.getString(1));
				N.setTen(rs.getString(2));
				N.setDiaChi(rs.getString(3));
				N.setEmail(rs.getString(4));
				N.setUsername(rs.getString(5));
				N.setPassword(rs.getString(6));
				N.setQuyen(rs.getString(7));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return N;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DefaultTableModel checkUsername(String ma) {
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);

		String sql = "select * from nhanvien where  Username= '"+ma+"'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				data.add(rs.getString(5));
				data.add(rs.getString(6));
				data.add(rs.getString(7));
				tableModel.addRow(data);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return tableModel;
	}
	public static boolean insert(NhanVien N) {
		String sql = "Insert into nhanvien values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, N.getMa());
			stmt.setString(2, N.getTen());
			stmt.setString(3, N.getDiaChi());
			stmt.setString(4, N.getEmail());
			stmt.setString(5, N.getUsername());
			stmt.setString(6, N.getPassword());
			stmt.setString(7, N.getQuyen());
			stmt.executeUpdate();
			return true;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static DefaultTableModel checkNV(String Username,String Password,String Quyen)
	{
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		String sql = "Select * from nhanvien where Username = '"+Username+"' and Password = '"+Password+"' and Quyen ='"+Quyen+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				data.add(rs.getString(5));
				data.add(rs.getString(6));
				data.add(rs.getString(7));
				tableModel.addRow(data);
				
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return tableModel;
	}
	public static boolean delete(String ma) {
		String sql = "delete from nhanvien where Ma = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ma);
			stmt.executeUpdate();
			return true;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static boolean update(NhanVien N) {
		String sql = "update nhanvien "
				+ "set Ten = ? ,DiaChi = ?, Email = ?,Username = ?, Password=?,Quyen=? "
				+ "where Ma = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, N.getTen());
			stmt.setString(2, N.getDiaChi());
			stmt.setString(3, N.getEmail());
			stmt.setString(4, N.getUsername());
			stmt.setString(5, N.getPassword());
			stmt.setString(6, N.getQuyen());
			stmt.setString(7, N.getMa());
			stmt.executeUpdate();
			return true;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
