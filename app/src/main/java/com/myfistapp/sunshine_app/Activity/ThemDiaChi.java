package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.R;

public class ThemDiaChi extends AppCompatActivity {

    Button btn_them_tdc;
    ImageView img_back_tdc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dia_chi);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AnhXa();
        OnTab();
    }

    public void AnhXa() {
        btn_them_tdc = findViewById(R.id.btn_them_tdc);
        img_back_tdc = findViewById(R.id.img_back_tdc);
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

}