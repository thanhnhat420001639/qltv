package Model;

import java.util.Scanner;

public class QLDG 
{
	static Scanner sc = new Scanner(System.in);
	
	private String Ma;
	private String Ten;
	private String DiaChi;
	private String SoDT;
	
	public QLDG() 
	{
	}
	
	public QLDG(String ma, String ten, String diaChi, String soDT) 
	{
		Ma = ma;
		Ten = ten;
		DiaChi = diaChi;
		SoDT = soDT;
	}
	
	public String getMa() 
	{
		return Ma;
	}
	
	public void setMa(String ma) 
	{
		Ma = ma;
	}
	
	public String getTen() 
	{
		return Ten;
	}
	
	public void setTen(String ten) 
	{
		Ten = ten;
	}
	
	public String getDiaChi() 
	{
		return DiaChi;
	}
	
	public void setDiaChi(String diaChi) 
	{
		DiaChi = diaChi;
	}
	
	public String getSoDT() 
	{
		return SoDT;
	}
	
	public void setSoDT(String soDT) 
	{
		SoDT = soDT;
	}
}