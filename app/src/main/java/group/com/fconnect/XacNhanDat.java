package group.com.fconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class XacNhanDat extends AppCompatActivity {

    TextView tensan1,khunggio1,username1,gia;
    Button xnd;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dat);
        getID();
        tensan1=findViewById(R.id.txttensanconfirn);
        khunggio1=findViewById(R.id.txtkhunggiocf);
        username1=findViewById(R.id.txtnguoidatconfirm);
        gia=findViewById(R.id.txtgia);
          tensan1.setText("Stadium: "+InformationSanForUse.tensan);
          khunggio1.setText(InformationSanForUse.khunggiochonbook);
          username1.setText("User: "+Information.username);
          gia.setText("Price: "+InformationSanForUse.gia);
        xnd=findViewById(R.id.xacnhadat);
        xnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmationBooking confirmationBooking=new ConfirmationBooking();
                confirmationBooking.setIdsan(InformationSanForUse.idsan);
                confirmationBooking.setPrice(InformationSanForUse.gia);
                confirmationBooking.setUser(Information.username);


                if(InformationSanForUse.khunggiochonbook.contains("7h-8h")) {
                    InformationSanForUse.kg1 = 1;
                    confirmationBooking.setTime("7h-8h");
                }
                else if(InformationSanForUse.khunggiochonbook.contains("8h-9h")) {
                    InformationSanForUse.kg2 = 1;
                    confirmationBooking.setTime("8h-9h");
                }
                else if(InformationSanForUse.khunggiochonbook.contains("15h-16h")) {
                    InformationSanForUse.kg3 = 1;
                    confirmationBooking.setTime("15h-16h");
                }
                else if(InformationSanForUse.khunggiochonbook.contains("16h-17h")) {
                    InformationSanForUse.kg4 = 1;
                    confirmationBooking.setTime("16h-17h");
                }
                else if(InformationSanForUse.khunggiochonbook.contains("17h-18h")) {
                    InformationSanForUse.kg5 = 1;
                    confirmationBooking.setTime("17h-18h");
                }
                else if(InformationSanForUse.khunggiochonbook.contains("18h-19h")) {
                    InformationSanForUse.kg6 = 1;
                    confirmationBooking.setTime("18h-19h");
                }
                else if(InformationSanForUse.khunggiochonbook.contains("19h-20h")) {
                    InformationSanForUse.kg7 = 1;
                    confirmationBooking.setTime("19h-20h");
                }
                else if(InformationSanForUse.khunggiochonbook.contains("20h-21h")) {
                    InformationSanForUse.kg8 = 1;
                    confirmationBooking.setTime("20h-21h");
                }
                else {
                    confirmationBooking.setTime("21h-22h");
                    InformationSanForUse.kg9 = 1;
                }
                confirmationBooking.setId(id);

                San san=new San();
                san.setId_San(InformationSanForUse.idsan);
                san.setId_ChuSan(InformationSanForUse.idChuSan);
                san.setTenSan(InformationSanForUse.tensan);
                san.setGia(InformationSanForUse.gia);
                san.setDiaChi(InformationSanForUse.diachi);
                san.setKG1(InformationSanForUse.kg1);
                san.setKG2(InformationSanForUse.kg2);
                san.setKG3(InformationSanForUse.kg3);
                san.setKG4(InformationSanForUse.kg4);
                san.setKG5(InformationSanForUse.kg5);
                san.setKG6(InformationSanForUse.kg6);
                san.setKG7(InformationSanForUse.kg7);
                san.setKG8(InformationSanForUse.kg8);
                san.setKG9(InformationSanForUse.kg9);



                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("listSan");
                myRef.child(String.valueOf(san.getId_San())).setValue(san);


                 FirebaseDatabase database1=FirebaseDatabase.getInstance();
                 DatabaseReference myRef1=database1.getReference("listBooking");
                 myRef1.child(String.valueOf(confirmationBooking.id)).setValue(confirmationBooking);

                Toast.makeText(XacNhanDat.this,"Successfully",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

    private void getID()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("listBooking");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {
                    ConfirmationBooking confirmationBooking=dataSnapshot.getValue(ConfirmationBooking.class);
                    id++;

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}