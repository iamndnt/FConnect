package group.com.fconnect;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChuSanActivity extends AppCompatActivity {
    static TextView txtChuSan;



    Button btnGoToBarChart,btnGoToAddStadium,btnGoToModify;



    public static HashMap<String,String> arlMonth=new HashMap<>();
    public static HashMap<String,San>  hashMapSan=new HashMap<>();

    public static MyArrayAdapter arrayAdapter;
    ListView lv;
    ArrayList<San> arlSan=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_san);

        btnGoToBarChart=findViewById(R.id.btnGoToDrawBarChart);
        btnGoToAddStadium=findViewById(R.id.btnGoToAddStadium);
        btnGoToModify=findViewById(R.id.btnGoToModify);
        txtChuSan=findViewById(R.id.txtChuSan);
        lv=findViewById(R.id.lv1);
        arrayAdapter=new MyArrayAdapter(ChuSanActivity.this,R.layout.custom_show_all_stadium,arlSan);
        lv.setAdapter(arrayAdapter);

        ShowAllStadium();


        FirebaseDatabase database1= FirebaseDatabase.getInstance();
        DatabaseReference myRef1=database1.getReference("listTongTienSan2/1/Month");
        myRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                if(!arlMonth.containsKey(snapshot.getKey())){
                    arlMonth.put(snapshot.getKey(),snapshot.getValue().toString());
                    arrayAdapter.notifyDataSetChanged();
                }



                Log.e("arl",arlMonth.toString());
                btnGoToBarChart.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(ChuSanActivity.this,DrawBarChartSumMoney.class);
                        startActivity(intent);


                    }
                });

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(arlMonth.containsKey(snapshot.getKey())){
                    arlMonth.remove(snapshot.getKey());
                    arlMonth.put(snapshot.getKey(),snapshot.getValue().toString());
                    //arrayAdapter.notifyDataSetChanged();
                }

                Log.e("arlChange",arlMonth.toString());

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                if(arlMonth.containsKey(snapshot.getKey())){
                    arlMonth.remove(snapshot.getKey());
                    //  arrayAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                San s=arlSan.get(i);
                OpenDialogUpdateItem(s);
                arrayAdapter.notifyDataSetChanged();
            }
        });

            btnGoToAddStadium.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ChuSanActivity.this,AddStadiumActivity.class);
                    startActivity(intent);
                }
            });

        btnGoToModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChuSanActivity.this,ChinhSuaTTCNChuSan.class);
                startActivity(intent);
            }
        });
    }

    private void OpenDialogUpdateItem(San s) {
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);
        Window window= dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        EditText edtName= dialog.findViewById(R.id.edt_UpdateTenSan);
        EditText edtGia= dialog.findViewById(R.id.edt_UpdateGia);
        EditText edtDiaChi=dialog.findViewById(R.id.edt_UpdateDiaChi);
        Button btnCancel=dialog.findViewById(R.id.btnCancle);
        Button btnUpdate=dialog.findViewById(R.id.btnUpdate);

        edtName.setText(s.getTenSan());
        edtDiaChi.setText(s.getDiaChi());
        edtGia.setText(s.getGia());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database1= FirebaseDatabase.getInstance();
                DatabaseReference myRef1=database1.getReference("listSan");

                String tenSan=edtName.getText().toString().trim();
                s.setTenSan(tenSan);
                String diaChi=edtDiaChi.getText().toString().trim();
                s.setDiaChi(diaChi);

                String gia=edtGia.getText().toString().trim();
                s.setGia(gia);
                myRef1.child(String.valueOf(s.getId_San())).updateChildren(s.toMap(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(ChuSanActivity.this,"Update Success",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        dialog.show();
    }

    private void ShowAllStadium() {
        FirebaseDatabase database1= FirebaseDatabase.getInstance();
        DatabaseReference myRef1=database1.getReference("listSan");
        myRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                San s= snapshot.getValue(San.class);

                if(s!=null ){
                    boolean check=false;
                    arlSan.clear();
                    //    if(s.getId_ChuSan().equals("1") && !hashMapSan.containsKey(s.getId_San())){
                    if(s.getId_ChuSan().equals("1") ){
                        hashMapSan.put(s.getId_San(),s);
                        check=true;
                    }
                    Log.e("hmoday",hashMapSan.toString());

                    if(check==true){
                        for(Map.Entry<String, San> entry : hashMapSan.entrySet()) {

                            arlSan.add(entry.getValue());

                            Log.e("stadium",entry.toString());

                        }
                        arrayAdapter.notifyDataSetChanged();
                        Log.e("arlCuaSan",arlSan.toString());

                    }
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                San s= snapshot.getValue(San.class);
                if(s!=null ){
                    arlSan.clear();
                    boolean check=false;
                    if( s.getId_ChuSan().equals("1")  )
                    //if( hashMapSan.containsKey(s.getId_San()) && s.getId_ChuSan().equals("1")  )
                    {
                        hashMapSan.remove(snapshot.getKey());
                        hashMapSan.put(snapshot.getKey(),s);
                        check=true;
                        // hashMapSan.put(s.getId_San(),s);
                    }
                    else if(!s.getId_ChuSan().equals("1")){
                        hashMapSan.remove(snapshot.getKey());

                        Log.e("Sau khi doi",s.toString());
                        check=true;
                    }
                    else{

                    }

                    if(check==true){
                        for(Map.Entry<String, San> entry : hashMapSan.entrySet()) {

                            arlSan.add(entry.getValue());

                            Log.e("stadium",entry.toString());

                        }
                        arrayAdapter.notifyDataSetChanged();
                    }


                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                San s= snapshot.getValue(San.class);
                if(s!=null ){
                    boolean check=false;

                    if(s.getId_ChuSan().equals("1")){
                        arlSan.clear();
                        hashMapSan.remove(s.getId_San());
                        check=true;
                    }
                    if(check==true) {
                        for (Map.Entry<String, San> entry : hashMapSan.entrySet()) {

                            arlSan.add(entry.getValue());

                            Log.e("stadium", entry.toString());

                        }
                        arrayAdapter.notifyDataSetChanged();
                        Log.e("arlCuaSan", arlSan.toString());

                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChuSanActivity.this,"lỗi",Toast.LENGTH_SHORT).show();
            }
        });
        arrayAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {

        super.onResume();

    }
}