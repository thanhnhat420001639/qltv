package Model;

import java.sql.Date;

public class TapChi extends Sach {
	private String ChuDe;
	private Date NgayPH;
	public String getChuDe() {
		return ChuDe;
	}
	public void setChuDe(String chuDe) {
		ChuDe = chuDe;
	}
	public Date getNgayPH() {
		return NgayPH;
	}
	public void setNgayPH(Date ngayPH) {
		NgayPH = ngayPH;
	}
	public TapChi(String maSach, String tenSach, String theLoai, String moTa, int soLuong, String nXB, String tacGia,
			String chuDe, Date ngayPH) {
		super(maSach, tenSach, theLoai, moTa, soLuong, nXB, tacGia);
		ChuDe = chuDe;
		NgayPH = ngayPH;
	}
	public TapChi(String maSach, String tenSach, String theLoai, String moTa, int soLuong, String nXB, String tacGia) {
		super(maSach, tenSach, theLoai, moTa, soLuong, nXB, tacGia);
	}
	
	
}
