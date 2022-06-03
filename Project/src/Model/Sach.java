package Model;

public abstract class Sach {
	private String MaSach;
	private String TenSach;
	private String TheLoai;
	private String MoTa;
	private int SoLuong;
	private String NXB;
	private String TacGia;

	public String getMaSach() {
		return MaSach;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public String getTenSach() {
		return TenSach;
	}

	public void setTenSach(String tenSach) {
		TenSach = tenSach;
	}

	public String getTheLoai() {
		return TheLoai;
	}

	public void setTheLoai(String theLoai) {
		TheLoai = theLoai;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public String getNXB() {
		return NXB;
	}

	public void setNXB(String nXB) {
		NXB = nXB;
	}

	public String getTacGia() {
		return TacGia;
	}

	public void setTacGia(String tacGia) {
		TacGia = tacGia;
	}

	public Sach(String maSach, String tenSach, String theLoai, String moTa, int soLuong, String nXB, String tacGia) {
		super();
		MaSach = maSach;
		TenSach = tenSach;
		TheLoai = theLoai;
		MoTa = moTa;
		SoLuong = soLuong;
		NXB = nXB;
		TacGia = tacGia;
	}

	public Sach() {
		super();
	}

}
