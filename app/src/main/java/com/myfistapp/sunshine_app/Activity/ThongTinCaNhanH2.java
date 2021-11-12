package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.R;

public class ThongTinCaNhanH2 extends AppCompatActivity {

    Button btn_sua_ttcn;
    ImageView img_back_ttcn;
    TextView tv_hoten, tv_sdt, tv_gioitinh, tv_ngaysinh, tv_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan_h2);

        AnhXa();
        OnTab();
    }

    public void AnhXa() {
        btn_sua_ttcn = findViewById(R.id.btn_sua_ttcn);
        img_back_ttcn = findViewById(R.id.img_back_ttcn);

        tv_hoten = findViewById(R.id.tv_ttcn_hoten);
        tv_email = findViewById(R.id.tv_ttcn_email);
        tv_ngaysinh = findViewById(R.id.tv_ttcn_ngaysinh);
        tv_gioitinh = findViewById(R.id.tv_ttcn_gioitinh);
        tv_sdt = findViewById(R.id.tv_ttcn_sdt);
    }

    public void OnTab() {
        //quay lại trang cá nhân
        img_back_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhanH2.this, TrangCaNhan.class);
                startActivity(intent);
            }
        });

        //đến trang sửa thông tin
        btn_sua_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhanH2.this, ChinhSuaThongTin.class);

                //truyền dữ liệu
                Bundle bundle = new Bundle();
                bundle.putString("hoten", tv_hoten.getText().toString());
                bundle.putString("gioitinh", tv_gioitinh.getText().toString());
                bundle.putString("ngaysinh", tv_ngaysinh.getText().toString());
                bundle.putString("sdt", tv_sdt.getText().toString());
                bundle.putString("email", tv_email.getText().toString());

                intent.putExtra("thongtinnguoidung", bundle);

                startActivity(intent);
            }
        });
    }

}