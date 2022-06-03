package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.connectDatabase;

public class DocGiaModel {
	static Connection conn = connectDatabase.getMySQLConnection();
	public static int CountDocGia() {
		String sql = "select count(*) from docgia";
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

}
