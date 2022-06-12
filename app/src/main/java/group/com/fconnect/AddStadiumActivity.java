package group.com.fconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStadiumActivity extends AppCompatActivity {
    EditText tvIdSan,tvTenSan,tvDiaChi,tvGia;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stadium);

        tvTenSan=findViewById(R.id.add_TenSan);
        tvDiaChi=findViewById(R.id.add_DiaChiSan);
        tvGia=findViewById(R.id.add_GiaSan);
        tvIdSan=findViewById(R.id.add_IdSan);
        btnAdd=findViewById(R.id.add_btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                San s=new San();
                s.setId_ChuSan(InformationChuSan.Id_ChuSan);
                s.setId_San(tvIdSan.getText().toString());
                s.setTenSan(tvTenSan.getText().toString());
                s.setGia(tvGia.getText().toString());
                s.setDiaChi(tvDiaChi.getText().toString());

                FirebaseDatabase database= FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("listSan");
                myRef.child(String.valueOf(s.getId_San())).setValue(s);
            }
        });
    }
}