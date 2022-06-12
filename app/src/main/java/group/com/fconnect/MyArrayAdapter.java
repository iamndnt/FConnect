package group.com.fconnect;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<San> {

    Activity context=null;
    ArrayList<San> myItemStadium=null;
    int idLayout;


    public MyArrayAdapter(@NonNull Activity context, int idLayout, @NonNull ArrayList<San> arl){
        super(context,idLayout,arl);
        this.context=context;
        this.idLayout=idLayout;
        this.myItemStadium=arl;


    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = this.context.getLayoutInflater();
        convertView = inflater.inflate(idLayout,null);
        Log.e("Test","o day");
        if(position%2==0){
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.design_default_color_secondary_variant));
        }
        final San myItem = this.myItemStadium.get(position);

        final ImageView img=convertView.findViewById(R.id.imageView);
        img.setImageResource(R.drawable.stadium_icon);

        final TextView idSan = convertView.findViewById(R.id.txt_IdSan);
        idSan.setText(myItem.getId_San());


        final TextView tenSan = convertView.findViewById(R.id.txt_TenSan);
        tenSan.setText(myItem.getTenSan());



        final TextView gia= convertView.findViewById(R.id.txt_Gia);
        gia.setText(myItem.getGia());

        final TextView diaChi= convertView.findViewById(R.id.txt_DiaChi);
        diaChi.setText(myItem.getDiaChi());

        return  convertView;
    }
}
