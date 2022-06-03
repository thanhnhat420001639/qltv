package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import DAO.connectDatabase;

public class SachModel {

	public static Vector searchTCByName(String tenSach) {
		Vector data = new Vector();
		String sql = "select * from sach a join tapchi b on a.MaSach = b.MaSach where a.TenSach = ?";

		Connection con = connectDatabase.getMySQLConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenSach);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector a = new Vector();
				a.add(rs.getString(1));
				a.add(rs.getString(2));
				a.add(rs.getString(3));
				a.add(rs.getString(4));
				a.add(rs.getInt(5));
				a.add(rs.getString(6));
				a.add(rs.getString(7));
				a.add(rs.getString(9));
				a.add(rs.getString(10));
				data.add(a);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}

	public static Vector searchGTByName(String tenSach) {
		Vector data = new Vector();
		String sql = "select * from sach a join sachgiaotrinh b on a.MaSach = b.MaSach where a.TenSach = ?";

		Connection con = connectDatabase.getMySQLConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tenSach);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector a = new Vector();
				a.add(rs.getString(1));
				a.add(rs.getString(2));
				a.add(rs.getString(3));
				a.add(rs.getString(4));
				a.add(rs.getInt(5));
				a.add(rs.getString(6));
				a.add(rs.getString(7));
				a.add(rs.getString(9));
				a.add(rs.getString(10));
				data.add(a);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}

	public static boolean updateTC(TapChi tapchi) {
		String sql1 = "UPDATE sach SET TenSach = ?, TheLoai = ?, MoTa = ?, SoLuong = ?, NXB = ?, TacGia = ? WHERE MaSach = ?";
		String sql2 = "UPDATE tapchi SET ChuDe = ?, NgayPH = ? WHERE MaSach = ?";

		Connection con = connectDatabase.getMySQLConnection();
		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, tapchi.getTenSach());
			ps1.setString(2, tapchi.getTheLoai());
			ps1.setString(3, tapchi.getMoTa());
			ps1.setInt(4, tapchi.getSoLuong());
			ps1.setString(5, tapchi.getNXB());
			ps1.setString(6, tapchi.getTacGia());
			ps1.setString(7, tapchi.getMaSach());
			ps1.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, tapchi.getChuDe());
			ps2.setDate(2, tapchi.getNgayPH());
			ps2.setString(3, tapchi.getMaSach());
			ps2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean updateGT(SachGiaoTrinh sachgiaotrinh) {
		String sql1 = "UPDATE sach SET TenSach = ?, TheLoai = ?, MoTa = ?, SoLuong = ?, NXB = ?, TacGia = ? WHERE MaSach = ?";
		String sql2 = "UPDATE sachgiaotrinh SET MonHoc = ?, CapDo = ? WHERE MaSach = ?";

		Connection con = connectDatabase.getMySQLConnection();
		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, sachgiaotrinh.getTenSach());
			ps1.setString(2, sachgiaotrinh.getTheLoai());
			ps1.setString(3, sachgiaotrinh.getMoTa());
			ps1.setInt(4, sachgiaotrinh.getSoLuong());
			ps1.setString(5, sachgiaotrinh.getNXB());
			ps1.setString(6, sachgiaotrinh.getTacGia());
			ps1.setString(7, sachgiaotrinh.getMaSach());
			ps1.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, sachgiaotrinh.getMonHoc());
			ps2.setString(2, sachgiaotrinh.getCapDo());
			ps2.setString(3, sachgiaotrinh.getMaSach());
			ps2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteTC(String id) {
		String sql1 = "DELETE FROM sach WHERE MaSach = ?";
		String sql2 = "DELETE FROM tapchi WHERE MaSach = ?";
		Connection con = connectDatabase.getMySQLConnection();
		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, id);
			ps1.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, id);
			ps2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteGT(String id) {
		String sql1 = "DELETE FROM sach WHERE MaSach = ?";
		String sql2 = "DELETE FROM sachgiaotrinh WHERE MaSach = ?";
		Connection con = connectDatabase.getMySQLConnection();
		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, id);
			ps1.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, id);
			ps2.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean exist(String id) {
		String sql = "select * from sach where MaSach = ?";
		Connection con = connectDatabase.getMySQLConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Vector searchGTByID(String maSach) {
		Vector data = new Vector();
		String sql = "select * from sach a join sachgiaotrinh b on a.MaSach = b.MaSach where a.MaSach = ?";

		Connection con = connectDatabase.getMySQLConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, maSach);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector a = new Vector();
				a.add(rs.getString(1));
				a.add(rs.getString(2));
				a.add(rs.getString(3));
				a.add(rs.getString(4));
				a.add(rs.getInt(5));
				a.add(rs.getString(6));
				a.add(rs.getString(7));
				a.add(rs.getString(9));
				a.add(rs.getString(10));
				data.add(a);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}

	public static boolean insertTC(TapChi tapchi) {
		String sql2 = "insert into tapchi(MaSach, ChuDe, NgayPH) values (?, ?, ?);";
		String sql1 = "insert into sach(MaSach, TenSach, TheLoai, MoTa, SoLuong, NXB, TacGia) values (?, ?, ?, ?, ?, ?, ?); ";
		Connection con = connectDatabase.getMySQLConnection();

		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, tapchi.getMaSach());
			ps1.setString(2, tapchi.getTenSach());
			ps1.setString(3, tapchi.getTheLoai());
			ps1.setString(4, tapchi.getMoTa());
			ps1.setInt(5, tapchi.getSoLuong());
			ps1.setString(6, tapchi.getNXB());
			ps1.setString(7, tapchi.getTacGia());
			ps1.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, tapchi.getMaSach());
			ps2.setString(2, tapchi.getChuDe());
			ps2.setDate(3, tapchi.getNgayPH());
			ps2.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean insertGT(SachGiaoTrinh sachgiaotrinh) {
		String sql2 = "insert into sachgiaotrinh(MaSach, MonHoc, CapDo) values (?, ?, ?);";
		String sql1 = "insert into sach(MaSach, TenSach, TheLoai, MoTa, SoLuong, NXB, TacGia) values (?, ?, ?, ?, ?, ?, ?); ";
		Connection con = connectDatabase.getMySQLConnection();

		try {
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, sachgiaotrinh.getMaSach());
			ps1.setString(2, sachgiaotrinh.getTenSach());
			ps1.setString(3, sachgiaotrinh.getTheLoai());
			ps1.setString(4, sachgiaotrinh.getMoTa());
			ps1.setInt(5, sachgiaotrinh.getSoLuong());
			ps1.setString(6, sachgiaotrinh.getNXB());
			ps1.setString(7, sachgiaotrinh.getTacGia());
			ps1.executeUpdate();

			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, sachgiaotrinh.getMaSach());
			ps2.setString(2, sachgiaotrinh.getMonHoc());
			ps2.setString(3, sachgiaotrinh.getCapDo());
			ps2.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Vector getAllTC() {
		Vector data = new Vector();
		String sql = "select a.MaSach, TenSach, TheLoai, MoTa, SoLuong, NXB, TacGia, ChuDe, NgayPH from sach a join tapchi b on a.MaSach = b.MaSach";

		Connection con = connectDatabase.getMySQLConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector a = new Vector();
				a.add(rs.getString(1));
				a.add(rs.getString(2));
				a.add(rs.getString(3));
				a.add(rs.getString(4));
				a.add(rs.getInt(5));
				a.add(rs.getString(6));
				a.add(rs.getString(7));
				a.add(rs.getString(8));
				a.add(rs.getString(9));
				data.add(a);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}

	public static Vector getAllGT() {
		Vector data = new Vector();
		String sql = "select a.MaSach, TenSach, TheLoai, MoTa, SoLuong, NXB, TacGia, MonHoc, CapDo from sach a join sachgiaotrinh b on a.MaSach = b.MaSach";

		Connection con = connectDatabase.getMySQLConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vector a = new Vector();
				a.add(rs.getString(1));
				a.add(rs.getString(2));
				a.add(rs.getString(3));
				a.add(rs.getString(4));
				a.add(rs.getInt(5));
				a.add(rs.getString(6));
				a.add(rs.getString(7));
				a.add(rs.getString(8));
				a.add(rs.getString(9));
				data.add(a);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return data;
	}
}
