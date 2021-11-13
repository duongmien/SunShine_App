package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.R;

public class ThietLapMatKhau extends AppCompatActivity {

    Button btn_xacnhan_tlmk;
    ImageView img_back_tlmk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiet_lap_mat_khau);

        AnhXa();
        OnTab();
    }

    public void AnhXa() {
        btn_xacnhan_tlmk = findViewById(R.id.btn_xacnhan_tlmk);
        img_back_tlmk = findViewById(R.id.img_back_tlmk);
    }

    public void OnTab() {
        //quay lại trang địa chỉ giao hàng sau khi lưu
        btn_xacnhan_tlmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThietLapMatKhau.this, TrangCaNhan.class);
                startActivity(intent);
            }
        });

        //quay lại trang địa chỉ giao hàng
        img_back_tlmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThietLapMatKhau.this, TrangCaNhan.class);
                startActivity(intent);
            }
        });

    }

}