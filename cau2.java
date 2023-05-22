import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
public class DanhSachKhachHang implements Serializable{

    private static final long serialVersionUID = 1L;
    private Queue<KhachHang> listDoiVe, listCoVe;

    public DanhSachKhachHang()
    {
        listDoiVe = new LinkedList<KhachHang>();
        listCoVe = new LinkedList<KhachHang>();
    }

    public Queue<KhachHang> getAllKHDoiVe()
    {
        return listDoiVe;
    }

    public Queue<KhachHang> getAllKHCoVe()
    {
        return listCoVe;
    }

    public boolean themKHVaoListDoi(KhachHang kh) throws Exception
    {
        if(listDoiVe.contains(kh))
            throw new Exception("Trùng số CMND!");
        listDoiVe.add(kh);
        return true;
    }

    public boolean xoaKH(KhachHang kh)
    {
        listDoiVe.remove(kh);
        return true;
    }

    public KhachHang timKH(String cMND)
    {
        for (KhachHang kh : listDoiVe)
        {
            if(kh.getcMND().equalsIgnoreCase(cMND))
                return kh;
        }
        return null;
    }

    public boolean banVe() {
        if(listDoiVe.size() > 0)
        {
            KhachHang kh = listDoiVe.poll(); // Lấy khách hàng xếp hàng trước trong list đợi mua vé
            listCoVe.add(kh);
            return true;
        }
        return false;
    }

    public int tongSoKHDoiVe()
    {
        return listDoiVe.size();
    }

    public int tongSoKHCoVe()
    {
        return listCoVe.size();
    }

    public double tongTien()
    {
        double sum = 0;
        for (KhachHang kh : listCoVe)
        {
            sum += kh.getGiaTien();
        }
        return sum;
    }

    public Set<String> danhSachGaDoiVe()
    {
        Set<String> listGa = new TreeSet<String>();
        for(KhachHang kh: listDoiVe)
            listGa.add(kh.getGaDen());
        return listGa;
    }

    public int tongSoKHChoTungGa(String ga)
    {
        int dem = 0;
        for (KhachHang kh : listDoiVe) {
            if(kh.getGaDen().equalsIgnoreCase(ga))
                dem++;
        }
        return dem;
    }

    public Map<String, Integer> thongKeSoVeTungGa()
    {
        Map<String, Integer> bangThongKe = new TreeMap<String, Integer>();
        for(KhachHang kh : listCoVe)
        {
            String ga = kh.getGaDen();
            if(bangThongKe.containsKey(ga)) // kiểm tra trong bảng thống kê có ga này hay không?
                bangThongKe.put(ga, bangThongKe.get(ga) + 1); // Nếu có: ga là key, và lấy ra value của ga này cộng thêm 1
            else
                bangThongKe.put(ga, 1); // Nếu không: thêm key mới là tên ga này, gán value bằng 1
        }
        return bangThongKe;
    }

    // Lưu file
    public void saveDsDoiVe(String fileName)
    {
        SaveLoadFile.save(listDoiVe, fileName);
    }

    // Đọc file
    public void loadDsDoiVe(String fileName)
    {
        listDoiVe = (Queue<KhachHang>)SaveLoadFile.load(fileName);
    }
}


public class KhachHang implements Serializable{

    private static final long serialVersionUID = 1L;
    private String cMND, tenKH, gaDen;
    private double giaTien;

    public String getcMND() {
        return cMND;
    }
    public void setcMND(String cMND) throws Exception {
        if(cMND.length() > 0)
            this.cMND = cMND;
        else
            throw new Exception("Số CMND không được để rỗng!");
    }
    public String getTenKH() {
        return tenKH;
    }
    public void setTenKH(String tenKH) throws Exception {
        if(tenKH.length() > 0)
            this.tenKH = tenKH;
        else
            throw new Exception("Tên khách hàng không được để rỗng!");
    }
    public String getGaDen() {
        return gaDen;
    }
    public void setGaDen(String gaDen) throws Exception {
        if(gaDen.length() > 0)
            this.gaDen = gaDen;
        else
            throw new Exception("Ga đến không được để rỗng!");
    }
    public double getGiaTien() {
        return giaTien;
    }
    public void setGiaTien(double giaTien) throws Exception {
        if(giaTien > 0)
            this.giaTien = giaTien;
        else
            throw new Exception("Giá tiền phải lớn hơn 0!");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cMND == null) ? 0 : cMND.hashCode());
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
        KhachHang other = (KhachHang) obj;
        if (cMND == null) {
            if (other.cMND != null)
                return false;
        } else if (!cMND.equals(other.cMND))
            return false;
        return true;
    }

    public KhachHang(String cMND, String tenKH, String gaDen, double giaTien) {
        super();
        this.cMND = cMND;
        this.tenKH = tenKH;
        this.gaDen = gaDen;
        this.giaTien = giaTien;
    }

    public KhachHang() {
        super();
    }

    @Override
    public String toString() {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String gia = currencyVN.format(giaTien);
        return String.format("|"+"%-10s"+"|"+"%-30s"+"|"+"%-15s"+"|"+"%15s"+"|", cMND, tenKH, gaDen, gia);
    }
}
public class SaveLoadFile {
    public static void save(Object obj, String fileName) {
        try{
            FileOutputStream fos=new FileOutputStream(fileName);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object load(String fileName) {
        Object obj=null;
        try{
            FileInputStream fis=new FileInputStream(fileName);
            ObjectInputStream ois=new ObjectInputStream(fis);
            obj=ois.readObject();
            ois.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}

public class cau2 {
    private static final String fileName = "listKHChoVe";
    static DanhSachKhachHang listKH = new DanhSachKhachHang();
    public static void main(String[] args) throws Exception {
        System.out.println("*** CHƯƠNG TRÌNH NHẬP XUẤT VÀ XỬ LÝ THÔNG TIN VÉ NHÀ GA ***");

        boolean flag = true;


        if(new File(fileName).exists()) // Đọc dữ liệu từ file lưu
            listKH.loadDsDoiVe(fileName);

        int chon = -1;
        do
        {
            chon = menu();
            switch (chon)
            {
                case 1:
                    try {
                        System.out.println("Tổng số khách hàng chờ mua vé trước khi thêm: "+listKH.tongSoKHDoiVe());
                        listKH.themKHVaoListDoi(nhapDuLieu());
                        System.out.println("Thêm thành công!");
                        flag = false;
                        System.out.println("Tổng số khách hàng chờ mua vé sau khi thêm: "+listKH.tongSoKHDoiVe());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("Thêm không thành công!");
                    }
                    break;
                case 2:
                    banVe();
                    flag = false;
                    break;
                case 3:
                    xuatDanhSach(listKH.getAllKHDoiVe());
                    break;
                case 4:
                    xoaKH();
                    flag = false;
                    break;
                case 5:
                    thongKe();
                    break;
                case 6:
                    try {
                        listKH.saveDsDoiVe(fileName);
                        System.out.println("Lưu thành công!");
                        flag = true;
                    } catch (Exception e) {
                        System.out.println("Lưu không thành công!");
                    }
                    break;
                case 7:
                    xuatDSCacGaDoiVe();
                    break;
                case 8:
                    thongKeTungGa();
                    break;
            }
        }while(chon != 0);

        if(!flag)
        {
            System.out.println("Cảnh báo: Bạn chưa lưu danh sách khách hàng chờ vào file!");
            System.out.println("      1. Lưu và kết thúc chương trình");
            System.out.println("      2. Kết thúc chương trình");
            Scanner nhap = new Scanner(System.in);
            chon = nhap.nextInt();
            if(chon == 1)
            {
                listKH.saveDsDoiVe(fileName);
                System.out.println("Lưu thành công!");
                flag = true;
            }
        }
        System.out.println("*** XIN CÁM ƠN !!! ***");
    }

    public static int menu()
    {
        Scanner nhap = new Scanner(System.in);
        System.out.println("");
        System.out.println("######################## MENU #######################");
        System.out.println("##                                                 ##");
        System.out.println("## 1.  Thêm khách hàng vào hàng chờ mua vé         ##");
        System.out.println("## 2.  Bán vé cho khách hàng đăng ký trước         ##");
        System.out.println("## 3.  Hiển thị danh sách khách hàng chờ mua vé    ##");
        System.out.println("## 4.  Xóa 1 khách hàng khỏi hàng chờ              ##");
        System.out.println("## 5.  Thống kê tình hình bán vé                   ##");
        System.out.println("## 6.  Lưu danh sách vào file                      ##");
        System.out.println("## 7.  Hiển thị danh sách các ga chờ mua vé        ##");
        System.out.println("## 8.  Thống kê số vé đã bán tương ứng của từng ga ##");
        System.out.println("## 0.  Kết thúc                                    ##");
        System.out.println("##                                                 ##");
        System.out.println("#####################################################");
        System.out.println("Nhập chọn các chức năng bằng số:");
        int chon = nhap.nextInt();
        return chon;
    }

    public static void xuatTieuDe()
    {
        inGachNgang();
        System.out.printf("|%-10s|%-30s|%-15s|%-15s|\n", "Số CMND", "Tên khách hàng", "Ga đến", "Giá tiền");
        inGachNgang();
    }

    public static void inGachNgang()
    {
        for(int i = 0; i < 75; i++)
            System.out.print("-");
        System.out.println("");
    }

    public static void nhapCung() throws Exception
    {
        KhachHang kh = new KhachHang("111111111", "Nguyễn Chí Khang", "Ga-001", 100000);
        listKH.themKHVaoListDoi(kh);
        kh = new KhachHang("222222222", "Mai Xuân Nguyên", "Ga-005", 500000);
        listKH.themKHVaoListDoi(kh);
        kh = new KhachHang("333333333", "Lê Thị Thanh Ngân", "Ga-003", 300000);
        listKH.themKHVaoListDoi(kh);
        kh = new KhachHang("444444444", "Trần Mạnh Cường", "Ga-001", 100000);
        listKH.themKHVaoListDoi(kh);
        kh = new KhachHang("555555555", "Ngô Minh Hiếu", "Ga-002", 200000);
        listKH.themKHVaoListDoi(kh);
        kh = new KhachHang("666666666", "Phó Ái Vy", "Ga-001", 100000);
        listKH.themKHVaoListDoi(kh);
        kh = new KhachHang("777777777", "Trương Thị Mỹ Diệu", "Ga-003", 300000);
        listKH.themKHVaoListDoi(kh);
        kh = new KhachHang("888888888", "Lê Chí Hào", "Ga-005", 500000);
        listKH.themKHVaoListDoi(kh);
        kh = new KhachHang("999999999", "Ngô Văn Hòa Thuận", "Ga-004", 350000);
        listKH.themKHVaoListDoi(kh);
    }

    public static KhachHang nhapDuLieu() throws Exception
    {
        String cMND, tenKH, gaDen;
        double giaTien;

        KhachHang kh = new KhachHang();
        Scanner nhap = new Scanner(System.in);
        System.out.println("Nhập số CMND khách hàng:");
        cMND = nhap.nextLine();
        KhachHang khTim = listKH.timKH(cMND);
        if(khTim == null)
        {
            kh.setcMND(cMND);
            System.out.println("Nhập tên khách hàng:");
            tenKH = nhap.nextLine();
            kh.setTenKH(tenKH);
            System.out.println("Nhập tên ga đến:");
            gaDen = nhap.nextLine();
            kh.setGaDen(gaDen);
            System.out.println("Nhập giá tiền:");
            giaTien = nhap.nextDouble();
            kh.setGiaTien(giaTien);
        }
        else
        {
            capNhat(khTim);
            throw new Exception("Cập nhật thành công!");
        }
        return kh;
    }

    public static void capNhat(KhachHang kh) throws Exception
    {
        String gaDen;
        double giaTien;

        System.out.println("Cập nhật dữ liệu khách hàng có số CMND là "+kh.getcMND());
        Scanner nhap = new Scanner(System.in);
        System.out.println("Nhập tên ga đến:");
        gaDen = nhap.nextLine();
        kh.setGaDen(gaDen);
        System.out.println("Nhập giá tiền:");
        giaTien = nhap.nextDouble();
        kh.setGiaTien(giaTien);
    }

    public static void xoaKH()
    {
        String cMND;

        Scanner nhap = new Scanner(System.in);
        System.out.println("Nhập số CMND khách hàng cần xóa:");
        cMND = nhap.nextLine();
        KhachHang khTim = listKH.timKH(cMND);
        if(khTim != null)
        {
            listKH.xoaKH(khTim);
            System.out.println("Xóa thành công!");
            xuatDanhSach(listKH.getAllKHDoiVe());
        }
        else
            System.out.println("Số CMND không tồn tại!");
    }

    public static String dinhDangTienTe(double tien)
    {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        return currencyVN.format(tien);
    }

    public static void thongKe()
    {
        System.out.printf("Tổng số khách hàng chờ nhận vé: "+listKH.tongSoKHDoiVe()+"\n");
        System.out.printf("Tổng số khách hàng đã có vé: "+listKH.tongSoKHCoVe()+"\n");
        System.out.printf("Tổng số tiền thu về là: "+dinhDangTienTe(listKH.tongTien())+"\n");
    }

    public static void banVe()
    {
        System.out.println("Thống kê trước khi bán vé:");
        System.out.printf("Tổng số khách hàng chờ nhận vé: "+listKH.tongSoKHDoiVe()+"\n");
        System.out.printf("Tổng số khách hàng đã có vé: "+listKH.tongSoKHCoVe()+"\n");
        if(listKH.banVe())
        {
            System.out.println("\nBán vé thành công!\n");
            System.out.println("Thống kê sau khi bán vé:");
            System.out.printf("Tổng số khách hàng chờ nhận vé: "+listKH.tongSoKHDoiVe()+"\n");
            System.out.printf("Tổng số khách hàng đã có vé: "+listKH.tongSoKHCoVe()+"\n");
        }
        else
            System.out.println("Không có khách hàng chờ mua vé!");
    }

    public static void xuatDanhSach(Queue<KhachHang> ds)
    {
        if(listKH.tongSoKHDoiVe()>0)
        {
            System.out.println("Danh sách khách hàng đợi mua vé:");
            xuatTieuDe();
            for (KhachHang kh : ds) {
                System.out.println(kh);
                inGachNgang();
            }
        }
        else
            System.out.println("Không có khách hàng chờ mua vé!");
    }

    public static void thongKeTungGa()
    {
        Map<String, Integer> thongKe = listKH.thongKeSoVeTungGa();
        if(thongKe.size() > 0){
            System.out.println("Bảng thống kê số vé đã bán theo từng ga");
            System.out.println("---------------------------------");
            System.out.printf("|%-15s|%15s|\n","Tên ga","Số vé đã bán");
            System.out.println("---------------------------------");
            for(String tenGa : thongKe.keySet())
            {
                System.out.printf("|%-15s|%15s|\n",tenGa,thongKe.get(tenGa));
                System.out.println("---------------------------------");
            }
        }
        else
            System.out.println("Không có vé nào được bán ra!");
    }

    public static void xuatDSCacGaDoiVe()
    {
        if(listKH.tongSoKHDoiVe() != 0)
        {
            System.out.println("Danh sách các ga đợi mua vé:");
            System.out.println("---------------------------------");
            System.out.printf("|%-15s|%15s|\n","Tên ga", "Số khách chờ");
            System.out.println("---------------------------------");
            Set<String> list = listKH.danhSachGaDoiVe();
            for (String s : list) {
                System.out.printf("|%-15s|%15s|\n", s, listKH.tongSoKHChoTungGa(s));
                System.out.println("---------------------------------");
            }
        }
        else
            System.out.println("Không còn khách đợi mua vé!");
    }
}
