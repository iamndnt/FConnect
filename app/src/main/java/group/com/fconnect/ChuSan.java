package group.com.fconnect;

import java.io.Serializable;

public class ChuSan implements Serializable {
    private String Id_ChuSan;
    private String TenChuSan;
    private String CCCD;
    private String SDT;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    String UserName;
    String MatKhau;

    public ChuSan(){}

    public ChuSan(String id_ChuSan, String tenChuSan, String CCCD, String SDT) {
        Id_ChuSan = id_ChuSan;
        TenChuSan = tenChuSan;
        this.CCCD = CCCD;
        this.SDT = SDT;
    }
/*
    protected ChuSan(Parcel in) {
        Id_ChuSan = in.readInt();
        TenChuSan = in.readString();
        CCCD = in.readString();
        SDT = in.readString();
    }

 */
/*
    public static final Creator<ChuSan> CREATOR = new Creator<ChuSan>() {
        @Override
        public ChuSan createFromParcel(Parcel in) {
            return new ChuSan(in);
        }
        @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id_ChuSan);
        parcel.writeString(TenChuSan);
        parcel.writeString(CCCD);
        parcel.writeString(SDT);
    }

        @Override
        public ChuSan[] newArray(int size) {
            return new ChuSan[size];
        }
    };
*/
    public String getId_ChuSan() {
        return Id_ChuSan;
    }

    public void setId_ChuSan(String id_ChuSan) {
        Id_ChuSan = id_ChuSan;
    }

    public String getTenChuSan() {
        return TenChuSan;
    }

    public void setTenChuSan(String tenChuSan) {
        TenChuSan = tenChuSan;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String display(){
        String rs="";
        rs+="Id_ChuSan="+getId_ChuSan()+"--TenChuSan="+getTenChuSan()+"--CCCD="+getCCCD()+"---SDT="+getSDT();
             return rs;
    }

    @Override
    public String toString() {
        return "ChuSan{" +
                "Id_ChuSan='" + Id_ChuSan + '\'' +
                ", TenChuSan='" + TenChuSan + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", SDT='" + SDT + '\'' +
                '}';
    }
}
