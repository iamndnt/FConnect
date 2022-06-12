package group.com.fconnect;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class San implements Serializable{
    private String Id_San;
    //private ChuSan ChuSan;
    private String Id_ChuSan;
    private String TenSan;
    private String Gia;
    private String DiaChi;
    private int KG1=0;
    private int KG2=0;
    private int KG3=0;
    private int KG4=0;
    private int KG5=0;
    private int KG6=0;
    private int KG7=0;
    private int KG8=0;
    private int KG9=0;
    private int KG10=0;

    public San(){}

    public Map<String,Object> toMap(){
        HashMap<String,Object> rs=new HashMap<>();
        rs.put("TenSan",getTenSan());
        rs.put("Id_San",getId_San());
        rs.put("Id_ChuSan",getId_ChuSan());
        rs.put("Gia",getGia());

        rs.put("KG1",getKG1());
        rs.put("KG2",getKG2());
        rs.put("KG3",getKG3());
        rs.put("KG4",getKG4());
        rs.put("KG5",getKG5());
        rs.put("KG6",getKG6());
        rs.put("KG7",getKG7());
        rs.put("KG8",getKG8());
        rs.put("KG9",getKG9());
        rs.put("KG10",getKG10());

        rs.put("DiaChi",getDiaChi());
        return rs;
    }

    public String getId_San() {
        return Id_San;
    }

    public void setId_San(String id_San) {
        Id_San = id_San;
    }

    public String getId_ChuSan() {
        return Id_ChuSan;
    }

    public void setId_ChuSan(String idChuSan) {
        Id_ChuSan = idChuSan;
    }

    public String getTenSan() {
        return TenSan;
    }

    public void setTenSan(String tenSan) {
        TenSan = tenSan;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public int getKG1() {
        return KG1;
    }

    public void setKG1(int KG1) {
        this.KG1 = KG1;
    }

    public int getKG2() {
        return KG2;
    }

    public void setKG2(int KG2) {
        this.KG2 = KG2;
    }

    public int getKG3() {
        return KG3;
    }

    public void setKG3(int KG3) {
        this.KG3 = KG3;
    }

    public int getKG4() {
        return KG4;
    }

    public void setKG4(int KG4) {
        this.KG4 = KG4;
    }

    public int getKG5() {
        return KG5;
    }

    public void setKG5(int KG5) {
        this.KG5 = KG5;
    }

    public int getKG6() {
        return KG6;
    }

    public void setKG6(int KG6) {
        this.KG6 = KG6;
    }

    public int getKG7() {
        return KG7;
    }

    public void setKG7(int KG7) {
        this.KG7 = KG7;
    }

    public int getKG8() {
        return KG8;
    }

    public void setKG8(int KG8) {
        this.KG8 = KG8;
    }

    public int getKG9() {
        return KG9;
    }

    public void setKG9(int KG9) {
        this.KG9 = KG9;
    }

    public int getKG10() {
        return KG10;
    }

    public void setKG10(int KG10) {
        this.KG10 = KG10;
    }

    @Override
    public String toString() {
        return "San{" +
                "Id_San='" + Id_San + '\'' +
                ", Id_ChuSan='" + Id_ChuSan + '\'' +
                ", TenSan='" + TenSan + '\'' +
                ", Gia='" + Gia + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", KG1=" + KG1 +
                ", KG2=" + KG2 +
                ", KG3=" + KG3 +
                ", KG4=" + KG4 +
                ", KG5=" + KG5 +
                ", KG6=" + KG6 +
                ", KG7=" + KG7 +
                ", KG8=" + KG8 +
                ", KG9=" + KG9 +
                ", KG10=" + KG10 +
                '}';
    }
    /*
    public San(int id_San, ChuSan chuSan, String tenSan, String gia, String diaChi) {
        Id_San = id_San;
        ChuSan = chuSan;
        TenSan = tenSan;
        Gia = gia;
        DiaChi = diaChi;
    }
*/
/*
    public San(int id_San, String tenSan, String gia, String diaChi) {
        Id_San = id_San;
        TenSan = tenSan;
        Gia = gia;
        DiaChi = diaChi;
    }
 */
/*
    protected San(Parcel in) {
        Id_San = in.readInt();
        TenSan = in.readString();
        Gia = in.readString();
        DiaChi = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id_San);
        dest.writeString(TenSan);
        dest.writeString(Gia);
        dest.writeString(DiaChi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<San> CREATOR = new Creator<San>() {
        @Override
        public San createFromParcel(Parcel in) {
            return new San(in);
        }

        @Override
        public San[] newArray(int size) {
            return new San[size];
        }
    };
*/

    /*
    public int getId_San() {
        return Id_San;
    }

    public void setId_San(int id_San) {
        Id_San = id_San;
    }

    public ChuSan getChuSan() {
        return ChuSan;
    }

    public void setChuSan(group7.com.demofirebase.ChuSan id_ChuSan) {
        ChuSan = id_ChuSan;
    }

    public String getTenSan() {
        return TenSan;
    }

    public void setTenSan(String tenSan) {
        TenSan = tenSan;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }


    @Override
    public String toString() {
        return "San{" +
                "Id_San=" + Id_San +
                ", ChuSan=" + ChuSan+
                ", TenSan='" + TenSan + '\'' +
                ", Gia='" + Gia + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                '}';
    }
    */
}
