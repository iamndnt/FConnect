package group.com.fconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CaiDatTaiKhoan extends AppCompatActivity {
    ImageButton btn_back;
    Button btn_DoiMatKhau,btn_CMND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat_tai_khoan);
        btn_back = findViewById(R.id.btn_back);
        btn_DoiMatKhau = findViewById(R.id.btn_DoiMatKhau);
        btn_CMND = findViewById(R.id.btn_CMND);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_DoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new  Intent(CaiDatTaiKhoan.this,DoiMatKhau.class);
                startActivity(myintent);
            }
        });

        btn_CMND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new  Intent(CaiDatTaiKhoan.this,ChungMinhNhanDan.class);
                startActivity(myintent);
            }
        });
    }
}