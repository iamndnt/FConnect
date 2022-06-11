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
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChungMinhNhanDan extends AppCompatActivity {
    ImageButton imgbtn_truoc,imgbtn_sau,btn_back;
    Button btn_Luu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chung_minh_nhan_dan);
        imgbtn_truoc = findViewById(R.id.imgbtn_truoc);
        imgbtn_sau = findViewById(R.id.imgbtn_sau);
        btn_back = findViewById(R.id.btn_back);
        btn_Luu = findViewById(R.id.btn_Luu);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imgbtn_truoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent  = new Intent(ACTION_IMAGE_CAPTURE);
                //khởi động intent và chờ kq
                if (ActivityCompat.checkSelfPermission(ChungMinhNhanDan.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ChungMinhNhanDan.this, new String[]{Manifest.permission.CAMERA},1);
                    return;
                }
                startActivityForResult(cameraIntent,98);

            }
        });

        imgbtn_sau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent  = new Intent(ACTION_IMAGE_CAPTURE);
                //khởi động intent và chờ kq
                if (ActivityCompat.checkSelfPermission(ChungMinhNhanDan.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ChungMinhNhanDan.this, new String[]{Manifest.permission.CAMERA},1);
                    return;
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // Start camera and wait for the results.
                 startActivityForResult(intent, 99);
                startActivityForResult(cameraIntent,99);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == Activity.RESULT_OK ){
            Bitmap myphoto = (Bitmap) data.getExtras().get("data");
            imgbtn_sau.setImageBitmap(myphoto);
        }
        else if(requestCode == 98 && resultCode == Activity.RESULT_OK ){
            Bitmap myphoto = (Bitmap) data.getExtras().get("data");
            imgbtn_truoc.setImageBitmap(myphoto);
        }

    }
}