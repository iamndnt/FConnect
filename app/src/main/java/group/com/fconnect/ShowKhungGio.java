package group.com.fconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowKhungGio extends AppCompatActivity {

    TextView txtshowgio;
    ListView lv;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_khung_gio);

        txtshowgio=findViewById(R.id.txt_showtensan);
        txtshowgio.setText(InformationSanForUse.tensan);

        lv=findViewById(R.id.lvkhunggio);
        setAdap();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(arrayList.get(position).contains("Not available"))
                {
                    Toast.makeText(ShowKhungGio.this,"Please choose another time slot !!\n"+arrayList.get(position),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    InformationSanForUse.khunggiochonbook=arrayList.get(position);
                    Intent i=new Intent(ShowKhungGio.this,Option_Choice.class);
                    startActivity(i);
                }
            }
        });


    }

    @Override
    protected void onResume() {
        arrayList.clear();
        setAdap();
        super.onResume();
    }

    String trangthai(int i)
    {
        if(i==0)
            return "Status: Available";
        return "Status: Not available";
    }


    void setAdap()
    {
        String kg1="Time slot: 7h-8h \n"+trangthai(InformationSanForUse.kg1);
        String kg2="Time slot: 8h-9h \n"+trangthai(InformationSanForUse.kg2);
        String kg3="Time slot: 15h-16h \n"+trangthai(InformationSanForUse.kg3);
        String kg4="Time slot: 16h-17h \n"+trangthai(InformationSanForUse.kg4);
        String kg5="Time slot: 17h-18h \n"+trangthai(InformationSanForUse.kg5);
        String kg6="Time slot: 18h-19h \n"+trangthai(InformationSanForUse.kg6);
        String kg7="Time slot: 19h-20h \n"+trangthai(InformationSanForUse.kg7);
        String kg8="Time slot: 20h-21h \n"+trangthai(InformationSanForUse.kg8);
        String kg9="Time slot: 21h-22h \n"+trangthai(InformationSanForUse.kg9);
        arrayList=new ArrayList<>();
        arrayList.add(kg1);
        arrayList.add(kg2);
        arrayList.add(kg3);
        arrayList.add(kg4);
        arrayList.add(kg5);
        arrayList.add(kg6);
        arrayList.add(kg7);
        arrayList.add(kg8);
        arrayList.add(kg9);
        arrayAdapter=new ArrayAdapter<>(ShowKhungGio.this, android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(arrayAdapter);
    }
}