package com.myfistapp.sunshine_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class DiaChiAdapter extends ArrayAdapter<DiaChiChiTiet> {
    public DiaChiAdapter(@NonNull Context context, int resource, @NonNull List<DiaChiChiTiet> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_selected_diachi,parent,false);
        TextView tvdselected= convertView.findViewById(R.id.txt_selected);

        DiaChiChiTiet diaChi= (DiaChiChiTiet) this.getItem(position);

        if(diaChi!=null){
            tvdselected.setText(diaChi.getName());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_danhsach_diachi,parent,false);
        TextView tvdanhsach= convertView.findViewById(R.id.txt_quanhuyen);

        DiaChiChiTiet diaChi= (DiaChiChiTiet) this.getItem(position);

        if(diaChi!=null){
            tvdanhsach.setText(diaChi.getName());
        }

        return convertView;
    }
}
