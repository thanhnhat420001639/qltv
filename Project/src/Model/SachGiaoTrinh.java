package Model;

public class SachGiaoTrinh extends Sach {
	private String MonHoc;
	private String CapDo;

	public String getMonHoc() {
		return MonHoc;
	}

	public void setMonHoc(String monHoc) {
		MonHoc = monHoc;
	}

	public String getCapDo() {
		return CapDo;
	}

	public void setCapDo(String capDo) {
		CapDo = capDo;
	}

	public SachGiaoTrinh(String maSach, String tenSach, String theLoai, String moTa, int soLuong, String nXB,
			String tacGia, String monHoc, String capDo) {
		super(maSach, tenSach, theLoai, moTa, soLuong, nXB, tacGia);
		MonHoc = monHoc;
		CapDo = capDo;
	}

	public SachGiaoTrinh(String maSach, String tenSach, String theLoai, String moTa, int soLuong, String nXB,
			String tacGia) {
		super(maSach, tenSach, theLoai, moTa, soLuong, nXB, tacGia);
	}

}
