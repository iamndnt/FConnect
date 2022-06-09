package group.com.fconnect;

import java.io.Serializable;

public class ItemStadium implements Serializable {
    Integer IconStadium=R.drawable.stadium_icon;
    String TenSan;
    String TenChuSan;
    String DiaChi;
    String SDT;

    ItemStadium(){}

    public ItemStadium(Integer iconStadium, String tenSan, String tenChuSan, String diaChi, String SDT) {
        IconStadium = iconStadium;
        TenSan = tenSan;
        TenChuSan = tenChuSan;
        DiaChi = diaChi;
        this.SDT = SDT;
    }

    public Integer getIconStadium() {
        return IconStadium;
    }

    public void setIconStadium(Integer iconStadium) {
        IconStadium = iconStadium;
    }

    public ItemStadium(String tenSan, String tenChuSan, String diaChi, String SDT) {
        TenSan = tenSan;
        TenChuSan = tenChuSan;
        DiaChi = diaChi;
        this.SDT = SDT;
    }

    public String getTenSan() {
        return TenSan;
    }

    public void setTenSan(String tenSan) {
        TenSan = tenSan;
    }

    public String getTenChuSan() {
        return TenChuSan;
    }

    public void setTenChuSan(String tenChuSan) {
        TenChuSan = tenChuSan;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return "ItemStadium{" +
                "TenSan='" + TenSan + '\'' +
                ", TenChuSan='" + TenChuSan + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                '}';
    }
}
