package group.com.fconnect;public class InformationSanForUse {
    public static String diachi="";
    public static String gia="";
    public static String idChuSan="";
    public static String tensan="";
    public static int kg1=0;
    public static int kg2=0;
    public static int kg3=0;
    public static int kg4=0;
    public static int kg5=0;
    public static int kg6=0;
    public static int kg7=0;
    public static int kg8=0;
    public static int kg9=0;

    public static String khunggiochonbook="";
    public static String sdt="";
    public static String idsan="";
    public  static  void forUse(String diachi1, String gia1, String idChuSan1, String tensan1, int kg11, int kg21, int kg31, int kg41, int kg51, int kg61, int kg71, int kg81, int kg91) {
        diachi = diachi1;
        gia = gia1;
        idChuSan = idChuSan1;
        tensan = tensan1;
        kg1 = kg11;
        kg2 = kg21;
        kg3 = kg31;
        kg4 = kg41;
        kg5 = kg51;
        kg6 = kg61;
        kg7 = kg71;
        kg8 = kg81;
        kg9 = kg91;
    }

    public static void forDestroy()
    {
        diachi = "";
        gia = "";
        idChuSan = "";
        tensan = "";
        kg1 = 0;
        kg2 = 0;
        kg3 = 0;
        kg4 = 0;
        kg5 = 0;
        kg6 = 0;
        kg7 = 0;
        kg8 = 0;
        kg9 = 0;
    }

}
