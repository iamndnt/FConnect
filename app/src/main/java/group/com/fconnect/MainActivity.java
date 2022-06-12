package group.com.fconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    ImageView comment;
    TextView txtcom;
    EditText box_comment;
    int id_cmt=0;


    ListView lv;
    MyArrayAdapterForShowAllStadium myArrayAdapterForShowAllStadium;
    ArrayList<ItemStadium> itemStadiums;
    ArrayList<San> arrayListSan;

    Button btn_CapNhat,btn_DangXuat,btn_CaiDat,btn_LienHe,btn_QuanLyThe,btn_YeuThich,btn_CauHoi,btn_CongDong;
    TextView txt_Ten,txt_Sdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComment();
        getChuSan();
        setControl();
        forLike();
        tab1();
        setActivity();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                San san=arrayListSan.get(position);
                InformationSanForUse.forUse(san.getDiaChi(),san.getGia(),san.getId_ChuSan(),san.getTenSan(),san.getKG1(),san.getKG2(),san.getKG3(),san.getKG4(),san.getKG5(),san.getKG6(),san.getKG7(),san.getKG8(),san.getKG9());
                InformationSanForUse.sdt=list_chusansdt.get(san.getId_ChuSan());
                InformationSanForUse.idChuSan=san.getId_ChuSan();
                InformationSanForUse.idsan=san.getId_San();
                Intent intent=new Intent(MainActivity.this,ShowKhungGio.class);
                startActivity(intent);
            }
        });


    }

    private void setActivity() {


        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c_cmt=box_comment.getText().toString();
                box_comment.setText("");

                Comment comment=new Comment();
                comment.setId(id_cmt);
                comment.setComment(c_cmt);
                comment.setName(Information.username);

                id_cmt++;
                pushComment(comment);

            }
        });

        btn_CapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, CapNhatHoSo.class);
                startActivity(myintent);
            }
        });
        btn_DangXuat = findViewById(R.id.btn_DangXuat);
        btn_DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder quitDia = new AlertDialog.Builder(MainActivity.this);
                quitDia.setTitle("Question");
                quitDia.setIcon(R.drawable.question);
                quitDia.setMessage("Are you sure you want to logout ?");
                quitDia.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                quitDia.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                quitDia.create().show();
            }
        });

        btn_CaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, CaiDatTaiKhoan.class);
                startActivity(myintent);
            }
        });
        btn_CauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, CauHoiThuongGap.class);
                startActivity(myintent);
            }
        });
        btn_QuanLyThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, QuanLyThe.class);
                startActivity(myintent);
            }
        });
        btn_LienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, LienHe.class);
                startActivity(myintent);
            }
        });

        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(like==0)
                {
                    like++;
                    btnlike.setImageResource(R.drawable.ic_unlike);

                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference myRef=database.getReference("ListLike");

                    Like like=new Like();

                    like.check=1;
                    like.username=Information.username;
                    myRef.child(String.valueOf(like.getUsername())).setValue(like);
                }
                else
                {
                    like--;
                    btnlike.setImageResource(R.drawable.ic_like);

                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference myRef=database.getReference("ListLike");

                    Like like=new Like();
                    like.check=0;
                    like.username=Information.username;
                    myRef.child(String.valueOf(like.getUsername())).setValue(like);
                    hmlike.remove(Information.username);
                    forLike();
                }
            }
        });

    }

    String likestatus="Be the first one like this post!!";
    int idlike=0;
    TextView txtlikestatus;

    HashMap<String, String> hmlike;
    private void forLike()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("ListLike");

       hmlike = new HashMap<String, String>();
       hmlike.clear();
        final String[] nguoidung = {""};
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {
                    Like flike=dataSnapshot.getValue(Like.class);

                    if(flike.check==1)
                    {
                        if(flike.getUsername().compareTo(Information.username)==0)
                        {
                            btnlike.setImageResource(R.drawable.ic_unlike);
                            like=1;
                        }
                        if(hmlike.containsKey(flike.getUsername())==false)
                        {
                            hmlike.put(flike.getUsername(), flike.getUsername());

                        }
                        nguoidung[0] =flike.getUsername();
                        if(hmlike.size()==0)
                            likestatus="Be the first one like this post!!";
                        else if (hmlike.size()==1)
                        {
                            likestatus=nguoidung[0]+" liked this post";
                        }
                        else
                        {
                            likestatus=nguoidung[0]+" and "+(hmlike.size()-1)+" liked this post";
                        }

                        txtlikestatus.setText(likestatus);
                    }


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(hmlike.size()==0)
            likestatus="Be the first one like this post!!";
        else if (hmlike.size()==1)
        {
            likestatus=nguoidung[0]+" liked this post";
        }
        else
        {
            likestatus=nguoidung[0]+" and "+(hmlike.size()-1)+" liked this post";
        }
        txtlikestatus.setText(likestatus);
    }


    ImageView btnlike;
    int like=0;
    private void setControl() {

        txtlikestatus=findViewById(R.id.txtlikestatus);
        tabHost=findViewById(R.id.tabmain);
        tabHost.setup();
        TabHost.TabSpec tab1, tab2,tab3;

        tab1=tabHost.newTabSpec("tab1");
        tab1.setContent(R.id.tabtrangchu);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.homepage));
        tabHost.addTab((tab1));

        tab2=tabHost.newTabSpec("tab2");
        tab2.setContent(R.id.tabhoatdong);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.activity));
        tabHost.addTab((tab2));

        tab3=tabHost.newTabSpec("tab3");
        tab3.setContent(R.id.tabtrangcanhan);
        tab3.setIndicator("",getResources().getDrawable(R.drawable.personalpage));
        tabHost.addTab((tab3));

        comment=findViewById(R.id.comment);
        box_comment=findViewById(R.id.boxcomment);
        txtcom=findViewById(R.id.txtcom);
        btn_CapNhat = findViewById(R.id.btn_CapNhat);
        btn_CaiDat= findViewById(R.id.btn_CaiDat);
        btn_LienHe = findViewById(R.id.btn_LienHe);
        txt_Ten = findViewById(R.id.txt_Ten);
        txt_Sdt= findViewById(R.id.txt_Sdt);
        btn_CauHoi = findViewById(R.id.btn_CauHoi);
        btn_QuanLyThe = findViewById(R.id.btn_QuanLyThe);
        btn_YeuThich = findViewById(R.id.btn_YeuThich);

        txt_Ten.setText("Name: "+Information.name);
        txt_Sdt.setText("Phonenumber: "+Information.sdt);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.compareTo("tab1")==0)
                {
                    getChuSan();
                    tab1();

                }

                if(tabId.compareTo("tab2")==0)
                {
                    forLike();

                }
            }
        });

        btnlike=findViewById(R.id.btnlike);


    }

    int check=0;
    @Override
    protected void onResume() {
        if(check!=0)
         tab1();
        check++;
        forLike();
        super.onResume();
    }

    private void tab1()
    {

        lv=findViewById(R.id.ls_listview);
        itemStadiums=new ArrayList<>();
        itemStadiums.clear();

        myArrayAdapterForShowAllStadium=new MyArrayAdapterForShowAllStadium(MainActivity.this,R.layout.my_custom_listview_show_all_stadium,itemStadiums);
        lv.setAdapter(myArrayAdapterForShowAllStadium);
        arrayListSan=new ArrayList<>();

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("listSan");
        HashMap<String, String> listSan = new HashMap<String, String>();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {
                    San san=dataSnapshot.getValue(San.class);
                    if(listSan.containsKey(san.getId_San())==false)
                    {
                        listSan.put(san.getId_San(),san.getTenSan());
                        ItemStadium vd1=new ItemStadium();


                        vd1.setDiaChi(san.getDiaChi());
                        vd1.setSDT(list_chusansdt.get(san.getId_ChuSan()));
                        vd1.setTenSan(san.getTenSan());
                        vd1.setTenChuSan(list_chusanname.get(san.getId_ChuSan()));

                        arrayListSan.add(san);
                        itemStadiums.add(vd1);
                        myArrayAdapterForShowAllStadium.notifyDataSetChanged();

                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myArrayAdapterForShowAllStadium.notifyDataSetChanged();
    }

    private void pushComment(Comment comment)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("List_comment");
        myRef.child(String.valueOf(comment.getId())).setValue(comment);
    }
    private void getComment()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("List_comment");

        HashMap<String, String> cmt = new HashMap<String, String>();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {
                    Comment comment=dataSnapshot.getValue(Comment.class);

                    if(cmt.containsKey(comment.getId()+comment.getComment()+comment.getName())==false)
                    {

                        cmt.put(comment.getId()+comment.getComment()+comment.getName(),comment.getName()+": "+comment.getComment());

                        id_cmt=cmt.size();
                        String tam=txtcom.getText().toString();
                        txtcom.setText(comment.getName()+": "+comment.getComment()+"\n"+tam);
                        tam="";
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    HashMap<String, String> list_chusanname;
    HashMap<String, String> list_chusansdt;
    private void getChuSan()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("listChuSan");

        list_chusanname = new HashMap<String, String>();
        list_chusansdt=new HashMap<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {

                    ChuSan chuSan=dataSnapshot.getValue(ChuSan.class);
                    if(list_chusanname.containsKey(chuSan.getId_ChuSan())==false)
                    {
                        list_chusanname.put(chuSan.getId_ChuSan(),chuSan.getTenChuSan());
                        list_chusansdt.put(chuSan.getId_ChuSan(),chuSan.getSDT());
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }





}