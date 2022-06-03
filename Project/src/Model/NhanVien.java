package Model;

public class NhanVien {
	private String Ma;
	private String Ten;
	private String DiaChi;
	private String Email;
	private String Username;
	private String Password;
	private String Quyen;
	public String getMa() {
		return Ma;
	}
	public void setMa(String ma) {
		Ma = ma;
	}
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getQuyen() {
		return Quyen;
	}
	public void setQuyen(String quyen) {
		Quyen = quyen;
	}
	
	public NhanVien() {}
	public NhanVien(String ma, String ten, String diaChi, String email, String uername, String password, String quyen) {
		super();
		Ma = ma;
		Ten = ten;
		DiaChi = diaChi;
		Email = email;
		Username = uername;
		Password = password;
		Quyen = quyen;
	}
	

}
