package group.com.fconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChinhSuaTTCNChuSan extends AppCompatActivity {

    EditText edtName,edtCCCD,edtSDT;
    Button btnAplly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_ttcnchu_san);

        edtName=findViewById(R.id.sua_edtTenChuSan);
        edtCCCD=findViewById(R.id.sua_edtCCCDChuSan);
        edtSDT=findViewById(R.id.sua_edtSDTChuSan);

        btnAplly=findViewById(R.id.sua_btnAdd);

        edtName.setText(InformationChuSan.TenChuSan);
        edtSDT.setText(InformationChuSan.SDT);
        edtCCCD.setText(InformationChuSan.CCCD);

        btnAplly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database= FirebaseDatabase.getInstance();
                DatabaseReference myRef= database.getReference("listChuSan");
                ChuSan cs=new ChuSan();
                cs.setId_ChuSan(InformationChuSan.Id_ChuSan);
                cs.setUserName(InformationChuSan.UserName);
                cs.setMatKhau(InformationChuSan.MatKhau);
                cs.setSDT(edtSDT.getText().toString());
                cs.setTenChuSan(edtName.getText().toString());
                cs.setCCCD(edtCCCD.getText().toString());
                myRef.child(String.valueOf(cs.getId_ChuSan())).setValue(cs);
                Toast.makeText(ChinhSuaTTCNChuSan.this, "Update Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}