package QLSinhVien;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class SinhVienTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MASV = 1;
	private static final int HOSV = 2;
	private static final int TENSV = 3;
	private static final int TUOI = 4;
	private static final int GIOITINH = 5;
	private static final int CHYENNGANH = 6;
	private static final int MACHUYENNGANH = 7;
	private ArrayList<SinhVien> dsSV;
	private String[] headers = "Mã Sinh Viên;Họ Sinh Viên;Tên Sinh Viên;Tuổi;Giới Tính;Chuyên Ngành;Mã Chuyên Ngành".split(";");
	
	public SinhVienTableModel(ArrayList<SinhVien>dsSV) {
		// TODO Auto-generated constructor stub
		this.dsSV = dsSV;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return dsSV.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		SinhVien sv = dsSV.get(rowIndex);
		switch(columnIndex) {
		case MASV:
			return sv.getMaSV();
		case HOSV:
			return sv.getHoSV();
		case TENSV:
			return sv.getTenSV();
		case TUOI:
			return sv.getTuoi();
		case GIOITINH:
			return sv.isGioiTinh();
		case CHYENNGANH:
			return sv.getChuyenNganh();
		case MACHUYENNGANH:
			return sv.getMaChuyenNganh();
		default:
			return sv;
		}
	}
	
	public String getColumnName(int column) {
		return headers[column];
	}
	
	public Class<?> getColumnClass(int columnIndex){
		if(columnIndex == TUOI) return Integer.class;
		else if(columnIndex == GIOITINH) return Boolean.class;
		else return String.class;
	}

}
