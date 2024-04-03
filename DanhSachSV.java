package QLSinhVien;

import java.io.Serializable;
import java.util.ArrayList;

import stt108_NguyenHoangNguyenVu_THW3.NhanVien;

public class DanhSachSV implements Serializable{
	private ArrayList<SinhVien> dsSV;
	
	public DanhSachSV() {
		// TODO Auto-generated constructor stub
		dsSV = new ArrayList<SinhVien>();
	}
	
	public ArrayList<SinhVien> getDsSV() {
		return dsSV;
	}

	public void setDsSV(ArrayList<SinhVien> dsSV) {
		this.dsSV = dsSV;
	}


	public boolean themSV(SinhVien sv) {
		if(dsSV.contains(sv)) return false;
		dsSV.add(sv);
		return true;
	}
	
	public boolean xoaSV(String maSV) {
		SinhVien sv = new SinhVien(maSV);
		if(dsSV.contains(sv)) {
			dsSV.remove(sv);
			return true;
		}
		return false;
	}
	
	public SinhVien timKiemSV(String maSV) {
		SinhVien sv = new SinhVien(maSV);
		if(dsSV.contains(sv)) return dsSV.get(dsSV.indexOf(sv));
		return null;
	}
	

	public int getSize() {
		return dsSV.size();
	}
	
	public SinhVien getElement(int index) {
		if(index < 0 || index > dsSV.size()) return null;
		return dsSV.get(index);
	}
	
	public boolean CapNhapSinhVien(String maSinhVienCu, SinhVien sinhVienMoi) {
		SinhVien sinhVienCu = new SinhVien(maSinhVienCu);
		if(dsSV.contains(sinhVienCu)) {
			sinhVienCu = dsSV.get(dsSV.indexOf(sinhVienCu));
			sinhVienCu.setMaSV(sinhVienMoi.getMaSV().trim());
			sinhVienCu.setHoSV(sinhVienMoi.getHoSV().trim());
			sinhVienCu.setTenSV(sinhVienMoi.getTenSV().trim());
			sinhVienCu.setTuoi(sinhVienMoi.getTuoi());
			sinhVienCu.setGioiTinh(sinhVienMoi.isGioiTinh());
			sinhVienCu.setChuyenNganh(sinhVienMoi.getChuyenNganh().trim());
			sinhVienCu.setMaChuyenNganh(sinhVienMoi.getMaChuyenNganh().trim());
			return true;
		}
		
		return false;
	}
}
