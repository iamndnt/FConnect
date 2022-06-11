package group.com.fconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThayDoiSDT extends AppCompatActivity {
    ImageButton btn_back;
    EditText edt_SDT,edt_MatKhau;
    Button btn_Luu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_sdt);
        btn_back = findViewById(R.id.btn_back);
        edt_SDT = findViewById(R.id.edt_MatKhauCu);
        edt_MatKhau = findViewById(R.id.edt_MatKhauMoi);
        btn_Luu = findViewById(R.id.btn_Luu);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newSDT = edt_SDT.getText().toString();
                String matKhau = edt_MatKhau.getText().toString();

                if(matKhau.equals(Information.password) ){
                    Information.sdt=newSDT;
                    Account acc=new Account();
                    acc.setUsername(Information.username);
                    acc.setName(Information.name);
                    acc.setEmail(Information.email);
                    acc.setPassword(Information.password);
                    acc.setSdt(Information.sdt);
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference myRef=database.getReference("List_account");
                    myRef.child(String.valueOf(acc.getUsername())).setValue(acc);

                    Toast.makeText(ThayDoiSDT.this, "Update successful!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ThayDoiSDT.this,"You have entered the wrong password !!! ",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}