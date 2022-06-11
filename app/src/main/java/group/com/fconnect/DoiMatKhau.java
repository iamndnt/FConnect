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

public class DoiMatKhau extends AppCompatActivity {
    ImageButton btn_back;
    Button btn_Luu;
    EditText edt_MatKhauCu,edt_MatKhauMoi,edt_NhapLaiMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        btn_back = findViewById(R.id.btn_back);
        btn_Luu = findViewById(R.id.btn_Luu);
        edt_MatKhauCu = findViewById(R.id.edt_MatKhauCu);
        edt_MatKhauMoi = findViewById(R.id.edt_MatKhauMoi);
        edt_NhapLaiMK = findViewById(R.id.edt_NhapLaiMK);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfor();
            }
        });
    }
    public  void saveInfor() {

        if (edt_MatKhauMoi.getText().toString().equals(edt_NhapLaiMK.getText().toString())) {
            if (edt_MatKhauCu.getText().toString().equals(Information.password)) {

                Information.password = edt_MatKhauMoi.getText().toString();
                Account acc = new Account();

                acc.setUsername(Information.username);
                acc.setName(Information.name);
                acc.setEmail(Information.email);
                acc.setPassword(Information.password);
                acc.setSdt(Information.sdt);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("List_account");
                myRef.child(String.valueOf(acc.getUsername())).setValue(acc);

                Toast.makeText(DoiMatKhau.this, "Update successful!", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(DoiMatKhau.this, "Enter the wrong password !!!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(DoiMatKhau.this,"Re-enter password does not match !!!",Toast.LENGTH_LONG).show();

    }

}