package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class ThemDiaChi extends AppCompatActivity {

    Button btn_them_tdc;
    ImageView img_back_tdc;
    Spinner spn_tinh, spn_huyen, spn_xa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dia_chi);

        AnhXa();
        OnTab();
        setSelectTinh();
    }

    public void AnhXa() {
        btn_them_tdc = findViewById(R.id.btn_them_tdc);
        img_back_tdc = findViewById(R.id.img_back_tdc);
        spn_tinh = findViewById(R.id.spn_tdc_tinh);
        spn_huyen = findViewById(R.id.spn_tdc_huyen);
        spn_xa = findViewById(R.id.spn_tdc_xa);
    }

    public void OnTab() {
        //quay lại trang địa chỉ giao hàng sau khi lưu
        btn_them_tdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemDiaChi.this, DiaChiGiaoHang.class);
                startActivity(intent);
            }
        });

        //quay lại trang địa chỉ giao hàng
        img_back_tdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemDiaChi.this, DiaChiGiaoHang.class);
                startActivity(intent);
            }
        });

    }

    //set dữ liệu cho 3 spinner tỉnh, huyện, xã
    public void setSelectTinh() {
        //set dữ liệu spinner tỉnh
        ArrayList<String> listTinh = new ArrayList<>();
        listTinh.add("Đà Nẵng");
        listTinh.add("Quảng Nam");
        listTinh.add("Quảng Ngãi");
        listTinh.add("Quảng Trị");

        ArrayAdapter<String> adapterTinh = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listTinh);
        spn_tinh.setAdapter(adapterTinh);


        //set dữ liệu spinner huyện
        ArrayList<String> listHuyen = new ArrayList<>();
        listHuyen.add("Hải Châu");
        listHuyen.add("Thanh Khê");
        listHuyen.add("Sơn Trà");
        listHuyen.add("Ngũ Hành Sơn");

        ArrayAdapter<String> adapterHuyen = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listHuyen);
        spn_huyen.setAdapter(adapterHuyen);


        //set dữ liệu spinner xã
        ArrayList<String> listXa = new ArrayList<>();
        listXa.add("Thanh Bình");
        listXa.add("Thanh Bình 2");
        listXa.add("Thanh Bình 3");
        listXa.add("Thanh Bình 4");

        ArrayAdapter<String> adapterXa = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listXa);
        spn_xa.setAdapter(adapterXa);

    }

}