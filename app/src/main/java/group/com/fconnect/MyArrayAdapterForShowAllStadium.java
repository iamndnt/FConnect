package group.com.fconnect;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapterForShowAllStadium extends ArrayAdapter<ItemStadium> {

        Activity context=null;
        ArrayList<ItemStadium> myItemStadium=null;
        int idLayout;
        public MyArrayAdapterForShowAllStadium(@NonNull Activity context, int idLayout, @NonNull ArrayList<ItemStadium> arl){
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

            if(position%2==0){
                convertView.setBackgroundColor(convertView.getResources().getColor(R.color.design_default_color_secondary_variant));
            }
            final ItemStadium myItem = this.myItemStadium.get(position);

            final ImageView img=convertView.findViewById(R.id.imageView);
            img.setImageResource(R.drawable.stadium_icon);

            final TextView tenSan = convertView.findViewById(R.id.ls_txt_TenSan);
            tenSan.setText(myItem.getTenSan());

            final TextView tenChuSan= convertView.findViewById(R.id.ls_txt_TenChuSan);

            tenChuSan.setText(myItem.getTenChuSan());

            final TextView diaChi= convertView.findViewById(R.id.ls_txt_DiaChi);
            diaChi.setText(myItem.getDiaChi());

            final TextView sdt= convertView.findViewById(R.id.ls_txt_SoDienThoai);
            sdt.setText(myItem.getSDT());
            return  convertView;
        }

}
