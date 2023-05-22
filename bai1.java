
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public abstract class PhongHoc {
	protected String maPhong;
	protected String dayNha;
	protected double dienTich;
	protected int soBongDen;
	
	abstract boolean xetDatChuan();
	
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) throws Exception {
		if(maPhong.length() > 0)
			this.maPhong = maPhong;
		else
			throw new Exception("Ma phong khong duoc trong!");
	}
	public String getDayNha() {
		return dayNha;
	}
	public void setDayNha(String dayNha) throws Exception {
		if(dayNha.length() > 0)
			this.dayNha = dayNha;
		else
			throw new Exception("Day nha khong duoc trong");
	}
	public double getDienTich() {
		return dienTich;
	}
	public void setDienTich(double dienTich) throws Exception {
		if(dienTich >= 50)
			this.dienTich = dienTich;
		else
			throw new Exception("dien tich lon hon 50!");
	}
	public int getSoBongDen() {
		return soBongDen;
	}
	public void setSoBongDen(int soBongDen) throws Exception {
		if(soBongDen > 0)
			this.soBongDen = soBongDen;
		else
			throw new Exception("So bong den khac 0!");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhong == null) ? 0 : maPhong.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongHoc other = (PhongHoc) obj;
		if (maPhong == null) {
			if (other.maPhong != null)
				return false;
		} else if (!maPhong.equals(other.maPhong))
			return false;
		return true;
	}
	
	public PhongHoc(String maPhong, String dayNha, double dienTich, int soBongDen) {
		super();
		this.maPhong = maPhong;
		this.dayNha = dayNha;
		this.dienTich = dienTich;
		this.soBongDen = soBongDen;
	}
	
	public PhongHoc() {
		super();
	}
	
	public boolean duAnhSang()
	{
		if(dienTich/soBongDen > 10)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("|"+"%-15s"+"|"+"%-10s"+"|"+"%15.0f"+"|"+"%15d"+"|", maPhong, dayNha, dienTich, soBongDen);
	}
}
 class ThiNghiem extends PhongHoc {
	private int sucChua;
	private boolean bonRua;
	
	@Override
	boolean xetDatChuan() {
		if(duAnhSang() && bonRua == true)
			return true;
		return false;
	}

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) throws Exception {
		if(sucChua > 50)
			this.sucChua = sucChua;
		else
			throw new Exception("Suc chua lon hon 50!");
	}

	public boolean isBonRua() {
		return bonRua;
	}

	public void setBonRua(boolean bonRua) {
		this.bonRua = bonRua;
	}

	public ThiNghiem(String maPhong, String dayNha, double dienTich, int soBongDen, int sucChua, boolean bonRua) {
		super(maPhong, dayNha, dienTich, soBongDen);
		this.sucChua = sucChua;
		this.bonRua = bonRua;
	}

	public ThiNghiem() {
		super();
	}

	public ThiNghiem(String maPhong, String dayNha, double dienTich, int soBongDen) {
		super(maPhong, dayNha, dienTich, soBongDen);
	}
	

	@Override
	public String toString() {
		String danhGia, bonRua;
		if(xetDatChuan())
			danhGia = "Dat";
		else
			danhGia = "Khong dat";
		
		if(this.bonRua)
			bonRua = "Co";
		else
			bonRua = "Khong";
		
		return super.toString()+String.format("%15d"+"|"+"%-15s"+"|"+"%-15s"+"|", sucChua, bonRua, danhGia);
	}
}
 class MayTinh extends PhongHoc {
	private int soMayTinh;
	
	@Override
	boolean xetDatChuan() {
		if(duAnhSang() && dienTich/soMayTinh <= 1.5)
			return true;
		return false;
	}

	public int getSoMayTinh() {
		return soMayTinh;
	}

	public void setSoMayTinh(int soMayTinh) throws Exception {
		if(soMayTinh > 0)
			this.soMayTinh = soMayTinh;
		else
			throw new Exception("So may tinh lon hon 0!");
	}

	public MayTinh(String maPhong, String dayNha, double dienTich, int soBongDen, int soMayTinh) {
		super(maPhong, dayNha, dienTich, soBongDen);
		this.soMayTinh = soMayTinh;
	}

	public MayTinh() {
		super();
	}

	public MayTinh(String maPhong, String dayNha, double dienTich, int soBongDen) {
		super(maPhong, dayNha, dienTich, soBongDen);
	}

	@Override
	public String toString() {
		String danhGia;
		if(xetDatChuan())
			danhGia = "Dat";
		else
			danhGia = "Khong dat";
		
		return super.toString()+String.format("%15d"+"|"+"%-15s"+"|", soMayTinh, danhGia);
	}
}
public class LyThuyet extends PhongHoc {
	private boolean mayChieu;
	
	@Override
	boolean xetDatChuan() {
		if(duAnhSang() && mayChieu == true)
			return true;
		return false;
	}

	public boolean isMayChieu() {
		return mayChieu;
	}

	public void setMayChieu(boolean mayChieu) {
		this.mayChieu = mayChieu;
	}

	public LyThuyet(String maPhong, String dayNha, double dienTich, int soBongDen, boolean mayChieu) {
		super(maPhong, dayNha, dienTich, soBongDen);
		this.mayChieu = mayChieu;
	}

	public LyThuyet() {
		super();
	}

	public LyThuyet(String maPhong, String dayNha, double dienTich, int soBongDen) {
		super(maPhong, dayNha, dienTich, soBongDen);
	}

	@Override
	public String toString() {
		String danhGia, mayChieu;
		
		if(xetDatChuan())
			danhGia = "Dat";
		else
			danhGia = "Khong dat";
		
		if(this.mayChieu)
			mayChieu = "Có";
	 class DanhSachPhongHoc {
	ArrayList<PhongHoc> listPH;
	
	public DanhSachPhongHoc()
	{
		listPH = new ArrayList<PhongHoc>();
	}
	
	public ArrayList<PhongHoc> getAllPH()
	{
		return listPH;
	}
	
	public boolean themPhongHoc(PhongHoc ph) throws Exception
	{
		if(listPH.contains(ph))
			throw new Exception("Trung ma phong!");
		else
			listPH.add(ph);
		return true;
	}
	
	public void xoaPH(PhongHoc ph)
	{
		listPH.remove(ph);
	}
	
	public void suaThongTinPH(PhongHoc ph)
	{
		int vt = listPH.indexOf(ph);
		if(vt>=0)
			listPH.set(vt, ph);
	}
	
	public PhongHoc timPH(String ma)
	{
		for (PhongHoc ph : listPH) {
			if(ph.maPhong.equalsIgnoreCase(ma))
				return ph;
		}
		return null;
	}
	
	public ArrayList<PhongHoc> timPHDatChuan()
	{
		int dem = 0;
		ArrayList<PhongHoc> list = new ArrayList<PhongHoc>();
		for (PhongHoc ph : listPH) {
			if(ph.xetDatChuan())
			{
				list.add(ph);
				dem++;
			}
		}
		if(dem==0)
			return null;
		return list;
	}
	
	public ArrayList<PhongHoc> timPHTheoSoMayTinh(int soBongDen)
	{
		int dem = 0;
		ArrayList<PhongHoc> list = new ArrayList<PhongHoc>();
		for (PhongHoc ph : listPH) {
			if(ph instanceof MayTinh)
			{
				if(ph.getSoBongDen() >= soBongDen)
				{
					list.add(ph);
					dem++;
				}
			}
		}
		if(dem==0)
			return null;
		return list;
	}
	
	public void sapXepDayNha()
	{
		Collections.sort(listPH, new Comparator<PhongHoc>(){
			public int compare(PhongHoc ph1, PhongHoc ph2) 
			{
				return ph1.getDayNha().compareToIgnoreCase(ph2.getDayNha());
			}
		});
	}
	
	public void sapXepDienTichGiamDan()
	{
		Collections.sort(listPH, new Comparator<PhongHoc>() {
			public int compare(PhongHoc ph1, PhongHoc ph2)
			{
				if(ph1.getDienTich() == ph2.getDienTich())
					return 0;
				else if(ph1.getDienTich() < ph2.getDienTich())
					return 1;
				else
					return -1;
			}
		});
	}
	
	public void sapXepBongDenTangDan()
	{
		Collections.sort(listPH, new Comparator<PhongHoc>() {
			public int compare(PhongHoc ph1, PhongHoc ph2)
			{
				Integer i1 = new Integer(ph1.getSoBongDen());
				Integer i2 = new Integer(ph2.getSoBongDen());
				return i1.compareTo(i2);
			}
		});
	}
	
	public int tongPhongHoc()
	{
		int sum = 0;
		for (PhongHoc ph : listPH)
		{
			sum++;
		}
		return sum;
	}
	
	public int tongPhongHocLT()
	{
		int sum = 0;
		for (PhongHoc ph : listPH)
		{
			if(ph instanceof LyThuyet)
				sum++;
		}
		return sum;
	}
	
	public int tongPhongHocMT()
	{
		int sum = 0;
		for (PhongHoc ph : listPH)
		{
			if(ph instanceof MayTinh)
				sum++;
		}
		return sum;
	}
	
	public int tongPhongHocTN()
	{
		int sum = 0;
		for (PhongHoc ph : listPH)
		{
			if(ph instanceof ThiNghiem)
				sum++;
		}
		return sum;
	}
}


public class bai1 {
	static DanhSachPhongHoc listPH = new DanhSachPhongHoc();
	public static void main(String[] args) throws Exception {
		System.out.println("==========Quan Li Phong Hoc==========");
		
		nhapCung();
		
		int chon = -1;
		do
		{
			chon = menu();
			switch (chon)
			{
			case 1:				
				try {
					nhapDuLieu();
					System.out.println("Them phong thanh cong !");
					xuatDanhSach(listPH.getAllPH());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				xuatDanhSach(listPH.getAllPH());
				break;
			case 3:
				System.out.println("Danh sach cac phong dat chuan:");
				xuatDanhSach(listPH.timPHDatChuan());
				break;
			case 4:
				xuatDSTheoSoMayTinh();
				break;
			case 5:
				listPH.sapXepDayNha();
				xuatDanhSach(listPH.getAllPH());
				break;
			case 6:
				listPH.sapXepDienTichGiamDan();
				xuatDanhSach(listPH.getAllPH());
				break;
			case 7:
				listPH.sapXepBongDenTangDan();
				xuatDanhSach(listPH.getAllPH());
				break;
			case 8:
				System.out.println("Tong so phong hoc: "+listPH.tongPhongHoc());
				break;
			case 9:
				System.out.println("tong so phong ly thuyet: "+listPH.tongPhongHocLT());
				break;
			case 10:
				System.out.println("Tong so phong may tinh: "+listPH.tongPhongHocMT());
				break;
			case 11:
				System.out.println("Tong os phong thi nghiem : "+listPH.tongPhongHocTN());
				break;
			case 12:
				timKiemPH();
				break;
			case 13:
				xoaPH();
				break;
			case 14:
				capNhatSoMayTinh();
				break;
			}
		}while(chon != 0);
		System.out.println("*** Rat cam on !!! ***");
	}
	
	public static int menu()
	{
		Scanner nhap = new Scanner(System.in);
		System.out.println("");
		System.out.println("###################### MENU #####################");
		System.out.println("##                                             ##");
		System.out.println("## 1.  Them phong hoc                          ##");
		System.out.println("## 2.  In thong tin                            ##");
		System.out.println("## 3.  In thong tin tat ca cac phong           ##");
		System.out.println("## 4.  In thong tin cac phong co n may tinh    ##");
		System.out.println("## 5.  Sap xep theo day tang dan               ##");
		System.out.println("## 6.  Sap xep theo dien tich giam dan         ##");
		System.out.println("## 7.  Sap xep thep so luong bong den tang dan ##");
		System.out.println("## 8.  Tong so phong hoc                       ##");
		System.out.println("## 9.  Tong so phong ly thuyet                 ##");
		System.out.println("## 10. Tog so phong may tinh                   ##");
		System.out.println("## 11. Tong so phong thi nghiem                ##");
		System.out.println("## 12. Tim kiem phong hoc                      ##");
		System.out.println("## 13. Xoa phong hoc                           ##");
		System.out.println("## 14. Cap nhat so may tinh                    ##");
		System.out.println("## 0.  Thoat                                   ##");
		System.out.println("##                                             ##");
		System.out.println("#################################################");
		System.out.println("Nhap chuc nang ban muon chon :");
		int chon = nhap.nextInt();
		return chon;
	}
	
	public static void nhapCung() throws Exception
	{
		PhongHoc ph1 = new LyThuyet("PH001", "Day A", 150, 10, true);
		listPH.themPhongHoc(ph1);
		PhongHoc ph2 = new LyThuyet("PH002", "Day A", 200, 20, true);
		listPH.themPhongHoc(ph2);
		PhongHoc ph3 = new LyThuyet("PH003", "Day B", 150, 17, false);
		listPH.themPhongHoc(ph3);
		PhongHoc ph4 = new LyThuyet("PH004", "Day C", 200, 25, true);
		listPH.themPhongHoc(ph4);
		
		PhongHoc ph5 = new MayTinh("PH005", "Day B", 50, 10, 34);
		listPH.themPhongHoc(ph5);
		PhongHoc ph6 = new MayTinh("PH006", "Day B", 100, 10, 70);
		listPH.themPhongHoc(ph6);
		PhongHoc ph7 = new MayTinh("PH007", "Day C", 100, 10, 30);
		listPH.themPhongHoc(ph7);
		PhongHoc ph8 = new MayTinh("PH008", "Day C", 50, 10, 35);
		listPH.themPhongHoc(ph8);
		
		PhongHoc ph9 = new ThiNghiem("PH009", "Day D", 200, 20, 150, true);
		listPH.themPhongHoc(ph9);
		PhongHoc ph10 = new ThiNghiem("PH010", "Day D", 200, 20, 150, false);
		listPH.themPhongHoc(ph10);
		PhongHoc ph11 = new ThiNghiem("PH011", "Day D", 200, 20, 150, true);
		listPH.themPhongHoc(ph11);
		PhongHoc ph12 = new ThiNghiem("PH012", "Day D", 200, 15, 150, true);
		listPH.themPhongHoc(ph12);
	}
	
	public static void nhapDuLieu() throws Exception
	{
		String maPhong, dayNha;
		int soBongDen, soMayTinh, sucChua, loaiPH, chon;
		double dienTich;
		boolean mayChieu, bonRua;
		
		PhongHoc ph1 = new LyThuyet();
		PhongHoc ph2 = new MayTinh();
		PhongHoc ph3 = new ThiNghiem();
		
		Scanner nhap = new Scanner(System.in);
		
		try {
			System.out.println("Nhap ma hong hoc:");
			maPhong = nhap.nextLine();
			if(listPH.timPH(maPhong)==null)
			{
				System.out.println("Nhap day nha:");
				dayNha = nhap.nextLine();
				System.out.println("Nhap dien tich:");
				dienTich = nhap.nextDouble();
				System.out.println("Nhap so bong den:");
				soBongDen = nhap.nextInt();
				System.out.println("Chon loai phong hoc:");
				System.out.println("	1.  Phong ly thuyet");
				System.out.println("	2.  Phong may tinh");
				System.out.println("	3.  Phong thi nghiem");
				loaiPH = nhap.nextInt();
				switch (loaiPH)
				{
				case 1:
					System.out.println("Phong cos trang bi may chieu khong?");
					System.out.println("	1.  co");
					System.out.println("	2.  Khong");
					chon = nhap.nextInt();
					if(chon == 1)
						mayChieu = true;
					else
						mayChieu = false;
					try {
						ph1.setMaPhong(maPhong);
						ph1.setDayNha(dayNha);
						ph1.setDienTich(dienTich);
						ph1.setSoBongDen(soBongDen);
					} catch (Exception e) {
						throw new Exception(e.getMessage());
					}
					ph1 = new LyThuyet(maPhong, dayNha, dienTich, soBongDen, mayChieu);
					listPH.themPhongHoc(ph1);
					break;
				case 2:
					System.out.println("Nhap so may tinh:");
					soMayTinh = nhap.nextInt();
					try {
						ph2.setMaPhong(maPhong);
						ph2.setDayNha(dayNha);
						ph2.setDienTich(dienTich);
						ph2.setSoBongDen(soBongDen);
					} catch (Exception e) {
						throw new Exception(e.getMessage());
					}
					ph2 = new MayTinh(maPhong, dayNha, dienTich, soBongDen, soMayTinh);
					listPH.themPhongHoc(ph2);
					break;
				case 3:
					System.out.println("Nhap suc chua:");
					sucChua = nhap.nextInt();
					System.out.println("Phong co bon rua tay khong?");
					System.out.println("	1.  Co");
					System.out.println("	2.  Khong");
					chon = nhap.nextInt();
					if(chon == 1)
						bonRua = true;
					else
						bonRua = false;
					try {
						ph3.setMaPhong(maPhong);
						ph3.setDayNha(dayNha);
						ph3.setDienTich(dienTich);
						ph3.setSoBongDen(soBongDen);
					} catch (Exception e) {
						throw new Exception(e.getMessage());
					}
					ph3 = new ThiNghiem(maPhong, dayNha, dienTich, soBongDen, sucChua, bonRua);
					listPH.themPhongHoc(ph3);
					break;
				}
			}
			else
				throw new Exception("Ma phong hoc da ton tai!");
		} catch (Exception e) {
			System.out.println("Loi: "+e.getMessage());
			throw new Exception("Them thanh cong!");
		}
	}
	
	public static void xuatTieuDePhongLT()
	{
		inGachNgang1();
		System.out.printf("|%-15s|%-10s|%-15s|%-15s|%-15s|%-15s|\n", "Ma phong hoc", "Day nha", "Dien tich(m^2)", "So bong đen", "May chieu", "Đanh gia");
		inGachNgang1();
	}
	
	public static void xuatTieuDePhongMT()
	{
		inGachNgang1();
		System.out.printf("|%-15s|%-10s|%-15s|%-15s|%-15s|%-15s|\n", "Ma phong hoc", "Day nha", "Dien tich(m^2)", "So bong đen", "May chieu", "Đanh gia");
		inGachNgang1();
	}
	
	public static void xuatTieuDePhongTN()
	{
		inGachNgang2();
		System.out.printf("|%-15s|%-10s|%-15s|%-15s|%-15s|%-15s|%-15s|\n", "Ma phong hoc", "Day nha", "Dien tich(m^2)", "So bong đen", "May chieu", "Đanh gia");
		inGachNgang2();
	}
	
	public static void inGachNgang1()
	{
		for(int i = 0; i < 92; i++)
		{
			System.out.print("-");
		}
		System.out.print("\n");
	}
	
	public static void inGachNgang2()
	{
		for(int i = 0; i < 108; i++)
		{
			System.out.print("-");
		}
		System.out.print("\n");
	}
	
	public static void xuatDanhSach(ArrayList<PhongHoc> list)
	{
		System.out.println("Danh sach tat ca cac phong:");
		System.out.println("Phong ly thuyet:");
		xuatTieuDePhongLT();
		for (PhongHoc ph : list) 
		{
			if(ph instanceof LyThuyet)
			{
				System.out.println(ph);
				inGachNgang1();
			}
		}
		
		System.out.println("Phong may tinh:");
		xuatTieuDePhongMT();
		for (PhongHoc ph : list) 
		{
			if(ph instanceof MayTinh)
			{
				System.out.println(ph);
				inGachNgang1();
			}
		}
		
		System.out.println("Phong thi nghiem:");
		xuatTieuDePhongTN();
		for (PhongHoc ph : list) 
		{
			if(ph instanceof ThiNghiem)
			{
				System.out.println(ph);
				inGachNgang2();
			}
		}
	}
	
	public static void timKiemPH()
	{
		Scanner nhap = new Scanner(System.in);
		String ma;
		System.out.println("Nhap ma phong can tim :");
		ma = nhap.nextLine();
		PhongHoc phTim = listPH.timPH(ma);
		if(phTim==null)
			System.out.println("Ma phong da ton tai!");
		else
		{
			if(phTim instanceof LyThuyet)
			{
				xuatTieuDePhongLT();
				System.out.println(phTim);
				inGachNgang1();
			}
			else if(phTim instanceof MayTinh)
			{
				xuatTieuDePhongMT();
				System.out.println(phTim);
				inGachNgang1();
			}
			else
			{
				xuatTieuDePhongTN();
				System.out.println(phTim);
				inGachNgang2();
			}
		}
	}
	
	public static void xoaPH() 
	{
		int chon;
		Scanner nhap = new Scanner(System.in);
		System.out.println("Nhap ma phong can xoa:");
		String ma = nhap.nextLine();
		PhongHoc phXoa = listPH.timPH(ma);
		if(phXoa!=null)
		{
			System.out.println("Ban co chac muon xoa?");
			System.out.println("	1.  Co");
			System.out.println("	2.  Khong");
			chon = nhap.nextInt();
			if(chon == 1)
			{
				listPH.xoaPH(phXoa);
				System.out.println("Xoa thanh cong!");
				System.out.println("Danh sach phong sau khi xoa:");
				xuatDanhSach(listPH.getAllPH());
			}
			else
				System.out.println("Ban da huy thao tac!");
		}
		else
			System.out.println("Ma phong khong ton tai!");
	}
	
	public static void capNhatSoMayTinh() throws Exception
	{
		String ma;
		int soMay;
		
		Scanner nhap = new Scanner(System.in);
		System.out.println("Nhap ma phong hoc can tim:");
		ma = nhap.nextLine();
		PhongHoc phTim = listPH.timPH(ma);
		if(phTim==null)
			System.out.println("Ma phong hoc khong ton tai!");
		else if(phTim instanceof MayTinh)
		{
			System.out.println("Thong tin phong truocc cap nhat:");
			xuatTieuDePhongMT();
			System.out.println(phTim);
			inGachNgang1();
			System.out.println("Nhap so luong may tinh:");
			soMay = nhap.nextInt();
			((MayTinh) phTim).setSoMayTinh(soMay);
			System.out.println("Cap nhajt thanh cong !");
			System.out.println("Thong tin phong sau khi cap nhat:");
			xuatTieuDePhongMT();
			System.out.println(phTim);
			inGachNgang1();
		}
		else
			System.out.println("Khong phai ma phong may tinh!");
	}
	
	public static void xuatDSTheoSoMayTinh()
	{
		int soMay;
		
		Scanner nhap = new Scanner(System.in);
		
		System.out.println("Nhap so luong may tinh:");
		soMay = nhap.nextInt();
		ArrayList<PhongHoc> list = listPH.timPHTheoSoMayTinh(soMay);
		System.out.println("Danh sach cac phong may tinh co so may tu "+soMay+" tro len:");
		xuatTieuDePhongMT();
		for (PhongHoc ph : list) {
			{
				System.out.println(ph);
				inGachNgang1();
			}
		}
	}
}

}