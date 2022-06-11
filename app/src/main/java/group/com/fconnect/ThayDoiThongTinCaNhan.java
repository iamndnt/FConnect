package group.com.fconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ThayDoiThongTinCaNhan extends AppCompatActivity {
    Spinner spinner;
    ImageButton btn_back;
    Button btn_Luu;
    EditText edt_Ten,edt_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin_ca_nhan);
        edt_Ten = findViewById(R.id.edt_Ten);
        edt_Email = findViewById(R.id.edt_Email);
        btn_back = findViewById(R.id.btn_back);
        btn_Luu = findViewById(R.id.btn_Luu);
        btn_back = findViewById(R.id.btn_back);

        displayInfor();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateInfor();
                displayInfor();
            }
        });


    }


    public void displayInfor(){

        edt_Ten.setText(Information.name);
        edt_Email.setText(Information.email);
    }

    public void UpdateInfor(){
        Information.name=edt_Ten.getText().toString();
        Information.email=edt_Email.getText().toString();
        Account acc=new Account();

        acc.setUsername(Information.username);
        acc.setName(Information.name);
        acc.setEmail(Information.email);
        acc.setPassword(Information.password);
        acc.setSdt(Information.sdt);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("List_account");
        myRef.child(String.valueOf(acc.getUsername())).setValue(acc);

        Toast.makeText(ThayDoiThongTinCaNhan.this, "Update successful!", Toast.LENGTH_SHORT).show();
    }
}