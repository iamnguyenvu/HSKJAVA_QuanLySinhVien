package QLSinhVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import QLSach_CharacterStreams.Sach;


public class QLSinhVien_GUI extends JFrame implements ActionListener, MouseListener {
	private JTextField tfMaSV;
	private JTextField tfHoSV;
	private JTextField tfTenSV;
	private JTextField tfTuoi;
	private JTextField tfMess;
	private JComboBox cbChuyenNganh;
	private JComboBox cbGioiTinh;
	private JTextField tfMaChuyenNganh;
	private JButton btAdd;
	private JButton btDelete;
	private JButton btClear;
	private JButton btEdit;
	private JButton btSave;
	private JComboBox<String> cbTimTheoChuyenNganh;
	private JButton btFind;
//	SinhVienTableModel model;
	private JTable table;
	private DanhSachSV listSV;
	private DefaultTableModel model;
	//HashMap để ánh xạ chuyên ngành và mã chuyên ngành tương ứng
	private HashMap<String, String> maChuyenNganhMap;

	public QLSinhVien_GUI() {
		// TODO Auto-generated constructor stub
		setTitle("QLSinhVien");
		setSize(900,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();
	}

	private void initComponent() {
		// TODO Auto-generated method stub
		
		//North
		JPanel pnNorth;
		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setPreferredSize(new Dimension(0, 150));
		pnNorth.setBorder(BorderFactory.createTitledBorder("Record Editors"));
		pnNorth.setLayout(null);
		
		JLabel lbMaSV, lbHoSV, lbTenSV, lbTuoi, lbGioiTinh, lbChuyenNganh, lbMaChuyenNganh;
		pnNorth.add(lbMaSV = new JLabel("Mã Sinh Viên: "));
		pnNorth.add(lbHoSV = new JLabel(" Họ: "));
		pnNorth.add(lbTenSV = new JLabel("Tên Sinh Viên: "));
		pnNorth.add(lbTuoi = new JLabel("Tuổi: "));
		pnNorth.add(lbGioiTinh = new JLabel("Giới tính: "));
		pnNorth.add(lbChuyenNganh = new JLabel("Chuyên Ngành: "));
		pnNorth.add(lbMaChuyenNganh = new JLabel("Mã chuyên ngành"));
		
		String[] gioiTinh = {"Nam", "Nữ"};
		String[] chuyenNganh = {"Kỹ thuật phần mềm", "Khoa học máy tính", "Khoa học dữ liệu", "Hệ thống thông tin", "Công nghệ thông tin"};
		// Khởi tạo HashMap và thêm các ánh xạ chuyên ngành và mã chuyên ngành tương ứng
        maChuyenNganhMap = new HashMap<>();
        maChuyenNganhMap.put("Kỹ thuật phần mềm", "DHKTPM");
        maChuyenNganhMap.put("Khoa học máy tính", "DHKHMT");
        maChuyenNganhMap.put("Khoa học dữ liệu", "DHKHDL");
        maChuyenNganhMap.put("Hệ thống thông tin", "DHHTTT");
        maChuyenNganhMap.put("Công nghệ thông tin", "DHCNTT");
		
		pnNorth.add(tfMaSV = new JTextField());
		pnNorth.add(tfHoSV = new JTextField());
		pnNorth.add(tfTenSV = new JTextField());
		pnNorth.add(tfTuoi = new JTextField());
		pnNorth.add(cbGioiTinh = new JComboBox<String>(gioiTinh));
		pnNorth.add(cbChuyenNganh = new JComboBox<String>(chuyenNganh));
		pnNorth.add(tfMaChuyenNganh = new JTextField());
		
		pnNorth.add(tfMess = new JTextField());
		tfMess.setEditable(false);
		tfMess.setBorder(null);
		tfMess.setFont(new Font("Time New Roman", Font.ITALIC, 12));
		tfMess.setForeground(Color.red);
		
		int w1 = 120, w2 = 240, h = 20;
		lbMaSV.setBounds(20, 20, w1, h); tfMaSV.setBounds(140, 20, w2, h);
		lbHoSV.setBounds(20, 45, w1, h); tfHoSV.setBounds(140, 45, w2, h);
		lbTenSV.setBounds(20, 70, w1, h); tfTenSV.setBounds(140, 70, w2, h);
		lbMaChuyenNganh.setBounds(20, 95, w1, h); tfMaChuyenNganh.setBounds(140, 95, w2, h);
		lbTuoi.setBounds(470, 20, w1, h); tfTuoi.setBounds(570, 20, w2, h);
		lbGioiTinh.setBounds(470, 45, w1, h); cbGioiTinh.setBounds(570, 45, w2, h);
		lbChuyenNganh.setBounds(470, 70, w1, h); cbChuyenNganh.setBounds(570, 70, w2, h);
		tfMess.setBounds(20, 120, 850, h);
		
		//Center
		JPanel pnCenter;
		add(pnCenter = new JPanel(), BorderLayout.CENTER);
		pnCenter.add(btAdd = new JButton("Thêm"));
		pnCenter.add(btClear = new JButton("Xóa trắng"));
		pnCenter.add(btDelete = new JButton("Xóa"));
		pnCenter.add(btEdit = new JButton("Sửa"));
		pnCenter.add(btSave = new JButton("Lưu"));
		pnCenter.add(new JLabel("Tìm theo chuyên ngành: "));
		pnCenter.add(cbTimTheoChuyenNganh = new JComboBox<String>());
		cbTimTheoChuyenNganh.setPreferredSize(new Dimension(180, 25));
		pnCenter.add(btFind = new JButton("Tìm theo mã chuyên ngành"));
		
		//South
		JScrollPane spSouth;
		String[] headers = {"Mã Sinh Viên", "Họ Sinh Viên", "Tên Sinh Viên", "Tuổi", "Giới Tính", "Chuyên Ngành", "Mã Chuyên Ngành"};
		model = new DefaultTableModel(headers, 0);
		add(spSouth = new JScrollPane(table = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		spSouth.setBorder(BorderFactory.createTitledBorder("Danh sách sinh viên"));
		table.setRowHeight(20);
		spSouth.setPreferredSize(new Dimension(0, 350));
		
		//======================
		listSV = new DanhSachSV();
		LuuTru lt = new LuuTru();
		try {
			listSV = (DanhSachSV)lt.DocFile("D:\\Download\\108_NguyenHoangNguyenVu\\src\\stt108_NguyenHoangNguyenVu\\data\\SinhVien.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Không tìm thấy file!");
		}
		
		DocDuLieuTuArrayListVaoModel();
		
		btAdd.addActionListener(this);
		btClear.addActionListener(this);
		btDelete.addActionListener(this);
		btEdit.addActionListener(this);
		btSave.addActionListener(this);
		btFind.addActionListener(this);
		table.addMouseListener(this);
//		cbChuyenNganh.addActionListener(this);
		
		pack();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new QLSinhVien_GUI().setVisible(true);

	}
	
	public void DocDuLieuTuArrayListVaoModel() {
		for(int i = 0; i < listSV.getSize(); i++) {
			SinhVien sv = listSV.getElement(i);
			model.addRow(new Object[] {sv.getMaSV(), sv.getHoSV(), sv.getTenSV(), sv.getTuoi(), 
					(sv.isGioiTinh() ? "Nam": "Nữ"), sv.getChuyenNganh(), sv.getMaChuyenNganh()});
		}
	}
	
	public void XoaDuLieuModel() {
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.getDataVector().removeAllElements();
	}
	
	private void CapNhapComboboxMaChuyenNganh() {
		int n = listSV.getSize(); //Tổng số các cuốn sách
		String []items = new String[n];
		int i = 0;
		for (SinhVien sv : listSV.getDsSV()) {
			items[i++] = sv.getMaChuyenNganh();
		}
		cbTimTheoChuyenNganh.setModel(new DefaultComboBoxModel<String>(items));
	}
	
	private SinhVien LayThongTinSinhTuTF() {
		String maSV = tfMaSV.getText().trim();
		String hoSV = tfHoSV.getText().trim();
		String tenSV = tfTenSV.getText().trim();
		int tuoi = Integer.parseInt(tfTuoi.getText().trim());
		Boolean gioiTinh;
		gioiTinh = (cbGioiTinh.getSelectedIndex() == 0) ? true : false;
		String chuyenNganh = (String) cbChuyenNganh.getSelectedItem();
		String maChuyenNganh = tfMaChuyenNganh.getText().trim();
		
		SinhVien sv = new SinhVien(maSV, hoSV, tenSV, tuoi, gioiTinh, chuyenNganh, maChuyenNganh);
		return sv;
	}
	
	public void clearText() {
		tfMaSV.setText("");
		tfHoSV.setText("");
		tfTenSV.setText("");
		tfTuoi.setText("");
		cbGioiTinh.setSelectedIndex(0);
		cbChuyenNganh.setSelectedIndex(0);
		tfMaChuyenNganh.setText("");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		tfMaSV.setText(model.getValueAt(row, 0).toString());
		tfHoSV.setText(model.getValueAt(row, 1).toString());
		tfTenSV.setText(model.getValueAt(row, 2).toString());
		tfTuoi.setText(model.getValueAt(row, 3).toString());
		cbGioiTinh.setSelectedIndex(model.getValueAt(row, 4).toString().equals("Nam") ? 0 : 1);
		cbChuyenNganh.setSelectedItem(model.getValueAt(row, 5).toString());
		tfMaChuyenNganh.setText(model.getValueAt(row, 6).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btAdd)) {
			if(isValidData()) {
//				String maSV = tfMaSV.getText().trim();
//				String hoSV = tfHoSV.getText().trim();
//				String tenSV = tfTenSV.getText().trim();
//				int tuoi = Integer.parseInt(tfTuoi.getText().trim());
//				Boolean gioiTinh;
//				gioiTinh = (cbGioiTinh.getSelectedIndex() == 0) ? true : false;
//				String chuyenNganh = (String) cbChuyenNganh.getSelectedItem();
//				String maChuyenNganh = tfMaChuyenNganh.getText().trim();
//				
//				SinhVien sv = new SinhVien(maSV, hoSV, tenSV, tuoi, gioiTinh, chuyenNganh, maChuyenNganh);
				
				SinhVien sv = LayThongTinSinhTuTF();
				
				if(!listSV.themSV(sv)) {
					tfMess.setText("Trùng Sinh Viên!");
				} else {
					model.setRowCount(0);
					DocDuLieuTuArrayListVaoModel();
					CapNhapComboboxMaChuyenNganh();
					clearText();
//					tfMess.setText("Thêm Sinh Viên thành công!");
				}	
			}
		}
		
		if (e.getSource().equals(btClear)) {
			clearText();
		}
		
		if(e.getSource().equals(btDelete)) {
			int row = table.getSelectedRow();
			if(row != -1) {
				if(JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if(listSV.xoaSV(model.getValueAt(row, 0).toString())) {
						tfMess.setText("Đã xóa Sinh Viên");
						model.setRowCount(0);
						DocDuLieuTuArrayListVaoModel();
						clearText();
					}
				}
			}
		}
		
		if (e.getSource().equals(btSave)) {
			LuuTru l = new LuuTru();
			try {
				l.LuuFile(listSV, "data/SinhVien.txt");
				JOptionPane.showMessageDialog(this, "Lưu thành công");
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(this, "Lưu thất bại!");
			}
		}
		
		if (e.getSource().equals(btEdit)) {
		    int selectedRow = table.getSelectedRow(); // Lấy chỉ mục của dòng được chọn
		    if (selectedRow != -1) { // Kiểm tra xem có dòng nào được chọn không
		        if (isValidData()) { // Kiểm tra tính hợp lệ của dữ liệu
		            String maSVCu = model.getValueAt(selectedRow, 0).toString(); // Lấy mã sinh viên cũ
		            SinhVien svm = LayThongTinSinhTuTF(); // Lấy thông tin sinh viên mới từ các ô văn bản và combobox
		            if (listSV.CapNhapSinhVien(maSVCu, svm)) { // Cập nhật sinh viên trong danh sách sinh viên
		                JOptionPane.showMessageDialog(this, "Cập nhật Sinh Viên thành công!");
		                clearText(); // Xóa các ô văn bản và combobox
		                model.setRowCount(0); // Xóa tất cả các dòng trong bảng
		                DocDuLieuTuArrayListVaoModel(); // Cập nhật lại model của bảng
		                table.setRowSelectionInterval(selectedRow, selectedRow); // Chọn lại dòng vừa cập nhật trong bảng
		                model.fireTableDataChanged(); // Thông báo cho bảng biết dữ liệu đã thay đổi
		            } else {
		                JOptionPane.showMessageDialog(this, "Cập nhật Sinh Viên thất bại!");
		            }

		        } else {
		            tfMess.setText("Hãy chọn Sinh Viên để sửa!");
		        }
		    } else {
		        JOptionPane.showMessageDialog(this, "Hãy chọn Sinh Viên để sửa!");
		    }
		}
//		
//		if(e.getSource().equals(btFind)) {
//			String regex = JOptionPane.showInputDialog("Nhập ")
//		}
		
		
		
	}
	
	public boolean isValidData() {
		String maSV = tfMaSV.getText().trim();
		String hoSV = tfHoSV.getText().trim();
		String tenSV = tfTenSV.getText().trim();
		String tuoi = tfTuoi.getText().trim();
		String maChuyenNganh = tfMaChuyenNganh.getText().trim();
		
		if(maSV.length() < 1) {
			tfMess.setText("Chưa nhập Mã Sinh Viên!");
			tfMaSV.requestFocus();
			return false;
		} else {
			if(maSV.matches("^22(\\d){6}$")) {
				tfMess.setText("");
			} else {
				tfMess.setText("Mã Sinh Vien gồm 8 kí số bắt đầu bằng 22!");
				tfMaSV.setText("22");
				return false;
			}
		}
		
		if(hoSV.length() < 1) {
			tfMess.setText("Chưa nhập Họ Sinh Viên!");
			tfHoSV.requestFocus();
			return false;
		} else {
			if(hoSV.matches("^[A-Z][a-z]*(?: [A-Z][a-z]*)*$")) {
				tfMess.setText("");
			} else {
				tfMess.setText("Họ Sinh Viên gồm các từ là kí tự chữ, "
						+ "mỗi từ bắt đầu bằng chữ in hoa, giữa mỗi từ ngăn cách bằng 1 khoảng trắng!");
				tfHoSV.setText("");
				return false;
			}
		}
		
		if(tenSV.length() < 1) {
			tfMess.setText("Chưa nhập Tên Sinh Viên!");
			tfTenSV.requestFocus();
			return false;
		} else {
			if(tenSV.matches("^[A-Z][a-z]+$")) {
				tfMess.setText("");
			} else {
				tfMess.setText("Tên Sinh Viên là một từ gồm các kí tự chữ"
						+ " bắt đầu bằng chữ in hoa!");
				tfTenSV.setText("");
				return false;
			}
		}
		
		if(tuoi.length() < 1) {
			tfMess.setText("Chưa nhập Tuổi Sinh Viên!");
			tfTuoi.requestFocus();
			return false;
		} else {
			int tuoiSV = Integer.parseInt(tuoi);
			if(tuoiSV >= 18 && tuoiSV <= 26) {
				tfMess.setText("");
			} else {
				tfMess.setText("Tuổi Sinh Viên từ 18 đến 26 tuổi!");
				tfTuoi.setText("");
				return false;
			}
		}
		
		if(maChuyenNganh.length() < 1) {
			tfMess.setText("Chưa nhập Mã Chuyên Ngành!");
			tfMaChuyenNganh.requestFocus();
			return false;
		} else {
			String chuyenNganh = (String) cbChuyenNganh.getSelectedItem();
            String maCN = tfMaChuyenNganh.getText().toUpperCase();
            if (!maChuyenNganhMap.containsKey(chuyenNganh) || !maCN.startsWith(maChuyenNganhMap.get(chuyenNganh))) {
                // Nếu không tương ứng, thông báo lỗi
                tfMess.setText("Mã Chuyên Ngành không tương ứng với Chuyên Ngành đã chọn!");
                tfMaChuyenNganh.setText("");
                return false;
            } else {
                // Nếu tương ứng, xóa thông báo lỗi
            	if (maChuyenNganh.matches("^DH(KTPM|KHMT|KHDL|HTTT|CNTT)(14|15|16|17|18|19)(A|B|C)(TT)?$")) {
                    tfMess.setText("");
                } else {
                    tfMess.setText("Mã Chuyên Ngành không hợp lệ!");
                    tfMaChuyenNganh.setText("");
                    return false;
                }
            }
		}
		
		return true;
	}
	
	
}
