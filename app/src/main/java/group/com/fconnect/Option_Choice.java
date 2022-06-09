package group.com.fconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Option_Choice extends AppCompatActivity {

    TextView khunggio;
    TextView tensan;
    ImageButton imgBtnCall,imgBtnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_choice);
        khunggio=findViewById(R.id.txtKhungGio);
        tensan=findViewById(R.id.txtTenSanOP);
        tensan.setText(InformationSanForUse.tensan);
        khunggio.setText(InformationSanForUse.khunggiochonbook);

        imgBtnAdd=findViewById(R.id.imgBtnAdd);
        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Option_Choice.this,XacNhanDat.class);
                startActivity(intent);
            }
        });
        imgBtnCall=findViewById(R.id.imgBtnCall);
        imgBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + InformationSanForUse.sdt));
                startActivity(intent);
            }
        });
    }
}