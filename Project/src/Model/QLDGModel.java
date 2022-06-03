package Model;

import DAO.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class QLDGModel 
{
	private static String[] header = {"Mã Độc Giả", "Tên Độc Giả", "Địa Chỉ", "Số Điện Thoại"};
	static Connection con = connectDatabase.getMySQLConnection();
	
	public static ResultSet CountDG() 
	{	
		String sql = "SELECT COUNT(Ma) FROM docgia";
		
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static DefaultTableModel SelectDG() 
	{
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		
		String sql = "SELECT * FROM docgia";
		
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				tableModel.addRow(data);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return tableModel;
	}
	
	public static ResultSet CountDG(String Ten) 
	{		
		String sql = "SELECT COUNT(Ten) FROM docgia WHERE Ten LIKE '%" + Ten + "%'";
		
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static DefaultTableModel SelectDG(String Ten) 
	{	
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		
		String sql = "SELECT * FROM docgia WHERE Ten LIKE '%" + Ten + "%'";
		
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) 
			{
				Vector data = new Vector();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				tableModel.addRow(data);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return tableModel;
	}
	
	public static ResultSet CountDGByMa(String Ma)
	{
		String sql = "SELECT COUNT(Ma) FROM docgia WHERE Ma = " + Ma;
		
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ResultSet SelectDGByMa(String Ma) 
	{
		String sql = "SELECT * FROM docgia WHERE Ma = " + Ma;
		
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void InsertDG(String Ma, String Ten, String DiaChi, String SoDT) 
	{
		String sql = "INSERT INTO docgia VALUES(?,?,?,?)";
		
		try
		{
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Ma);
			stmt.setString(2, Ten);
			stmt.setString(3, DiaChi);
			stmt.setString(4, SoDT);
			stmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void DeleteDG(String Ma) 
	{
		String sql = "DELETE FROM docgia WHERE Ma = ?";
		
		try
		{
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Ma);
			stmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void UpdateDG(String Ma, String Ten, String DiaChi, String SoDT) 
	{
		String sql = "UPDATE docgia SET Ten = ?, DiaChi = ?, SoDT = ? WHERE Ma = ?";
		
		try
		{
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, Ten);
			stmt.setString(2, DiaChi);
			stmt.setString(3, SoDT);
			stmt.setString(4, Ma);
			stmt.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}