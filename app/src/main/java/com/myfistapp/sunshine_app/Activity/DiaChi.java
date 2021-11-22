package com.myfistapp.sunshine_app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.myfistapp.sunshine_app.Adapter.DiaChiAdapter;
import com.myfistapp.sunshine_app.Class.DiaChiChiTiet;
import com.myfistapp.sunshine_app.Helper.TinyDB;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;
import java.util.List;

public class DiaChi extends AppCompatActivity {

    private Spinner spnQuanHuyen, spnPhuongXa;
    private DiaChiAdapter quanhuyenAdapter, phuongxaAdapter;
    private TextView btn_xacnhan;
    private String qh,px;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dia_chi);

        btn_xacnhan = findViewById(R.id.btn_xacnhan);
        spnQuanHuyen = findViewById(R.id.spn_quanhuyen);
        spnPhuongXa = findViewById(R.id.spn_phuongxa);


        quanhuyenAdapter = new DiaChiAdapter(this,R.layout.holder_selected_diachi,getListQuanhuyen());
        phuongxaAdapter = new DiaChiAdapter(this,R.layout.holder_selected_diachi,getListPhuongxa());
        spnQuanHuyen.setAdapter(quanhuyenAdapter);
        spnPhuongXa.setAdapter(phuongxaAdapter);

        spnQuanHuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                qh = quanhuyenAdapter.getItem(i).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnPhuongXa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                px = phuongxaAdapter.getItem(i).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qh==""){
                    Toast.makeText(DiaChi.this, "Vui long nhập Quận/Huyện", Toast.LENGTH_SHORT).show();
                }else if(px==""){
                    Toast.makeText(DiaChi.this, "Vui long nhập Phường/Xã", Toast.LENGTH_SHORT).show();
                }else{
                    String diachi = qh+", "+px+", Thành phố Đà Nẵng";

                    Intent intent = new Intent(DiaChi.this, LienHe.class);
                    startActivity(intent);
                }
            }
        });


    }
    private List<DiaChiChiTiet> getListQuanhuyen(){
        List<DiaChiChiTiet> list = new ArrayList<>();
        list.add(new DiaChiChiTiet("place1"));
        list.add(new DiaChiChiTiet("place2"));
        list.add(new DiaChiChiTiet("place3"));
        list.add(new DiaChiChiTiet("place4"));
        list.add(new DiaChiChiTiet("place5"));
        return list;
    }
    private List<DiaChiChiTiet> getListPhuongxa(){
        List<DiaChiChiTiet> list = new ArrayList<>();
        list.add(new DiaChiChiTiet("place1.1"));
        list.add(new DiaChiChiTiet("place1.2"));
        list.add(new DiaChiChiTiet("place1.3"));
        list.add(new DiaChiChiTiet("place1.4"));
        list.add(new DiaChiChiTiet("place1.5"));
        return list;
    }
}