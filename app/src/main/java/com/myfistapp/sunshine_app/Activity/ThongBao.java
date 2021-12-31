package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.R;

public class ThongBao extends AppCompatActivity {

    private Khachhang khachhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_thong_bao);
        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }

        bottomNavigation();
    }



    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout favBtn = findViewById(R.id.favBtn);
        LinearLayout notiBtn = findViewById(R.id.notBtn);
        LinearLayout proBtn = findViewById(R.id.proBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHang.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrangChu.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YeuThich.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        notiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThongBao.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        proBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrangCaNhan.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}