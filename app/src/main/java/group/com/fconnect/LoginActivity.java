package group.com.fconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Button btn_login2 ;
    EditText edt_username ;
    EditText edt_password ;
    ImageButton btn_fb ;
    ImageButton btn_gg ;
    ImageButton btn_tw ;
    TextView fg_pw;

    HashMap<String, String> list_account;

    HashMap<String, String> chusan_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getControl();
        addActivity();
        getAccount();
        getChuSanAccount();
        
    }

    @Override
    protected void onResume() {
        list_account.clear();
        getAccount();
        super.onResume();
    }

    private void addActivity() {
        btn_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        btn_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://accounts.google.com/signin";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        btn_tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/i/flow/login";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btn_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_account.containsKey(edt_username.getText().toString()) && list_account.get(edt_username.getText().toString()).compareTo(edt_password.getText().toString())==0)
                {
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);

                    Information.username=edt_username.getText().toString();
                    Information.password=edt_password.getText().toString();
                    getInfo(Information.username);

                }
                else if(chusan_account.containsKey(edt_username.getText().toString()) && chusan_account.get(edt_username.getText().toString()).compareTo(edt_password.getText().toString())==0)
                {
                    Intent intent=new Intent(LoginActivity.this,ChuSanActivity.class);
                    startActivity(intent);
                    getDataChuSan(edt_username.getText().toString());

                }
                else
                    Toast.makeText(LoginActivity.this,"Username or password is wrong!!!",Toast.LENGTH_SHORT).show();

            }
        });


        fg_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent (LoginActivity.this,ForgotPass.class);
                startActivity(i);
            }
        });
    }

    private void getDataChuSan(String username) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("listChuSan");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {

                    ChuSan chuSan=dataSnapshot.getValue(ChuSan.class);
                    if(chuSan.UserName.compareTo(username)==0)
                    {
                        InformationChuSan.Id_ChuSan=chuSan.getId_ChuSan();
                        InformationChuSan.TenChuSan=chuSan.getTenChuSan();
                        InformationChuSan.CCCD=chuSan.getCCCD();
                        InformationChuSan.MatKhau=chuSan.getMatKhau();
                        InformationChuSan.SDT=chuSan.getSDT();
                        InformationChuSan.UserName=chuSan.getUserName();
                        break;
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void getInfo(String username)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("List_account");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {
                    Account account=dataSnapshot.getValue(Account.class);

                    if(account.getUsername().compareTo(username)==0)
                    {
                        Information.email=account.getEmail();
                        Information.sdt= account.getSdt();
                        Information.name=account.getName();

                        break;
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getControl() {
        btn_login2 = findViewById(R.id.btn_login2);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_fb = findViewById(R.id.btn_fb);
        btn_gg = findViewById(R.id.btn_google);
        btn_tw = findViewById(R.id.btn_tw);
        fg_pw=findViewById(R.id.txtQuenMK);
    }

    private void getChuSanAccount()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("listChuSan");

        chusan_account= new HashMap<String, String>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {

                    ChuSan chuSan=dataSnapshot.getValue(ChuSan.class);
                    if(chusan_account.containsKey(chuSan.getUserName())==false)
                    {
                        chusan_account.put(chuSan.getUserName(),chuSan.getMatKhau());
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void getAccount()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("List_account");

        list_account = new HashMap<String, String>();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {
                    Account account=dataSnapshot.getValue(Account.class);
                    if(list_account.containsKey(account.getUsername())==false)
                    {

                        list_account.put(account.getUsername(),account.password);
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}