package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.jfree.data.category.DefaultCategoryDataset;

import DAO.connectDatabase;

public class MuonTraModel {
	static Connection conn = connectDatabase.getMySQLConnection();
	public static DefaultCategoryDataset load() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sql = "Select  TenSach,count(b.MaSach) from ctmuontra a,sach b where a.MaSach= b.MaSach group by b.MaSach  ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				dataset.addValue(Integer.parseInt(rs.getString(2)), "Số lượng", rs.getString(1));;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return dataset;
	}
	public static int CountSach1() {
		String sql = "select count(*) from ctmuontra ";
        int temp=0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				temp= Integer.parseInt(rs.getString(1));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return temp;
		
	}
	public static int CountTra() {
		String sql = "select count(*) from ctmuontra where TrangThai = '0'";
        int temp=0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				temp= Integer.parseInt(rs.getString(1));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return temp;
		
	}
	private static String[] header = {"Mã Sách", "Tên Sách"};

	public static ResultSet CountSach() 
	{	
		String sql = "SELECT COUNT(MaSach) FROM sach";
		
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static DefaultTableModel SelectSach()
	{
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		
		String sql = "SELECT MaSach, TenSach FROM sach";
		
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				tableModel.addRow(data);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return tableModel;
	}
	
	public static ResultSet CountSach(String MaSach) 
	{		
		String sql = "SELECT COUNT(MaSach) FROM sach WHERE MaSach = " + MaSach;
		
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static DefaultTableModel SelectSach(String MaSach) 
	{	
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		
		String sql = "SELECT MaSach, TenSach FROM sach WHERE MaSach = " + MaSach;
		
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				tableModel.addRow(data);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return tableModel;
	}

	public static ResultSet CountMuon(String MaMuonTra) 
	{		
		String sql = "SELECT COUNT(MaMuonTra) FROM muontra WHERE MaMuonTra = " + MaMuonTra;
		
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void InsertMuon(String MaMuonTra, String MaDG, Date NgayMuon)
	{
		String sql = "INSERT INTO muontra VALUES(?,?,?)";

		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, MaMuonTra);
			stmt.setString(2, MaDG);
			stmt.setDate(3, NgayMuon);
			stmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void InsertCTMuon(String MaMuonTra, String MaSach, int TrangThai)
	{
		String sql = "INSERT INTO ctmuontra (MaMuonTra, MaSach, TrangThai) VALUES(?,?,?)";
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, MaMuonTra);
			stmt.setString(2, MaSach);
			stmt.setInt(3, TrangThai);
			stmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void UpdateSLS(String MaSach) 
	{
		String sql = "UPDATE sach SET SoLuong = SoLuong - 1 WHERE MaSach = ?";
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, MaSach);
			stmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static boolean traSach(String masach) {
		String sql = "update sach set SoLuong = SoLuong + 1 where MaSach = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, masach);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}
	public static Vector searchByMaSach(String id) {
		Vector data = new Vector();
		String sql = "select * from ctmuontra  where MaSach = ? and TrangThai = 0";


		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector a = new Vector();
				a.add(rs.getString(1));
				a.add(rs.getString(2));
				a.add(rs.getInt(3));
				a.add(rs.getDate(4));
				data.add(a);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}
	
	public static Vector searchByMaMuonTra(String id) {
		Vector data = new Vector();
		String sql = "select * from ctmuontra  where MaMuonTra = ? and TrangThai = 0";


		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector a = new Vector();
				a.add(rs.getString(1));
				a.add(rs.getString(2));
				a.add(rs.getInt(3));
				a.add(rs.getDate(4));
				data.add(a);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}

	public static boolean update(String mamuontra,String masach, String trangthai, Date ngaytra) {
		String sql = "update ctmuontra set TrangThai = ?, NgayTra = ? where MaMuonTra = ? and MaSach = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, trangthai);
			ps.setDate(2, ngaytra);
			ps.setString(3, mamuontra);
			ps.setString(4, masach);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Vector getAll() {
		Vector data = new Vector();
		String sql = "select * from ctmuontra where TrangThai = 0";


		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector a = new Vector();
				a.add(rs.getString(1));
				a.add(rs.getString(2));
				a.add(rs.getInt(3));
				a.add(rs.getDate(4));
				data.add(a);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}


}
