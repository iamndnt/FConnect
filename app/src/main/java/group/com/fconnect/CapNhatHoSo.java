package group.com.fconnect;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CapNhatHoSo extends AppCompatActivity {
    ImageButton btn_back;
    Button btn_ThayCaNhan,btn_ThaySDT,btn_ThayAnhDaiDien;
    ImageView img_avt;
    TextView txt_hs_Ten_GT,txt_hs_NgaySinh,txt_hs_Email,txt_hs_SDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_ho_so);
        btn_back = findViewById(R.id.btn_back);
        btn_ThayCaNhan = findViewById(R.id.btn_ThayCaNhan);
        btn_ThaySDT = findViewById(R.id.btn_ThaySDT);
        txt_hs_Ten_GT = findViewById(R.id.btn_CongDong);
        txt_hs_Email = findViewById(R.id.btn_YeuThich);
        txt_hs_SDT = findViewById(R.id.txt_hs_SDT);
        btn_ThayAnhDaiDien = findViewById(R.id.btn_ThayAnhDaiDien);
        img_avt = findViewById(R.id.img_avt);
        displayInfor();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_ThayAnhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent  = new Intent(ACTION_IMAGE_CAPTURE);
                //khởi động intent và chờ kq
                if (ActivityCompat.checkSelfPermission(CapNhatHoSo.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CapNhatHoSo.this, new String[]{Manifest.permission.CAMERA},1);
                    return;
                }
                startActivityForResult(cameraIntent,99);
            }
        });

        btn_ThayCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(CapNhatHoSo.this,ThayDoiThongTinCaNhan.class);
                startActivity(myintent);

            }
        });
        btn_ThaySDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent1 = new Intent(CapNhatHoSo.this,ThayDoiSDT.class);
                startActivity(myintent1);
            }
        });

    }
    public void displayInfor(){

        txt_hs_Ten_GT.setText(Information.name);
        txt_hs_SDT.setText(Information.sdt);
        txt_hs_Email.setText(Information.email);
    }



    @Override
    protected void onResume() {
        super.onResume();
        displayInfor();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == Activity.RESULT_OK ){
            Bitmap myphoto = (Bitmap) data.getExtras().get("data");
            img_avt.setImageBitmap(myphoto);
        }
    }
}