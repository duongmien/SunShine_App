package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Class.DiaChi;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class SuaDiaChi extends AppCompatActivity {

    Button btn_luu_sdc;
    ImageView img_back_sdc;
    EditText edt_hote, edt_sdt;
    Spinner spn_tinh, spn_huyen, spn_xa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_dia_chi);

        AnhXa();
        OnTab();

        //nhận dữ liệu là địa chỉ từ trang địa chỉ giao hàng
        nhanDuLieu();
        //set dữ liệu cho 3 spinner tỉnh, huyện, xã
        setSelectTinh();
    }

    public void AnhXa() {
        btn_luu_sdc = findViewById(R.id.btn_luu_sdc);
        img_back_sdc = findViewById(R.id.img_back_sdc);
        edt_hote = findViewById(R.id.edt_sdc_hoten);
        edt_sdt = findViewById(R.id.edt_sdc_sdt);
        spn_tinh = findViewById(R.id.spn_sdc_tinh);
        spn_huyen = findViewById(R.id.spn_sdc_huyen);
        spn_xa = findViewById(R.id.spn_sdc_xa);
    }

    public void OnTab() {
        //quay lại trang địa chỉ giao hàng sau khi lưu
        btn_luu_sdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaDiaChi.this, DiaChiGiaoHang.class);
                startActivity(intent);
            }
        });

        //quay lại trang địa chỉ giao hàng
        img_back_sdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuaDiaChi.this, DiaChiGiaoHang.class);
                startActivity(intent);
            }
        });

    }

    //nhận dữ liệu là địa chỉ từ trang địa chỉ giao hàng
    public void nhanDuLieu() {
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("data");

        if(bundle != null) {
            DiaChi diaChi = (DiaChi) bundle.getSerializable("diachi");
            edt_hote.setText(diaChi.getTen());
            edt_sdt.setText(diaChi.getSdt());
        }
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
        listHuyen.add("Đà Nẵng");
        listHuyen.add("Quảng Nam");
        listHuyen.add("Quảng Ngãi");
        listHuyen.add("Quảng Trị");

        ArrayAdapter<String> adapterHuyen = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listHuyen);
        spn_huyen.setAdapter(adapterHuyen);


        //set dữ liệu spinner xã
        ArrayList<String> listXa = new ArrayList<>();
        listXa.add("Đà Nẵng");
        listXa.add("Quảng Nam");
        listXa.add("Quảng Ngãi");
        listXa.add("Quảng Trị");

        ArrayAdapter<String> adapterXa = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listXa);
        spn_xa.setAdapter(adapterXa);

    }
}