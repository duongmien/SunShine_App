package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.R;

public class ThongTinCaNhanH2 extends AppCompatActivity {

    Button btn_sua_ttcn;
    ImageView img_back_ttcn;
    TextView tv_hoten, tv_sdt, tv_gioitinh, tv_ngaysinh, tv_email,tv_tennguoidung;
    private Khachhang khachhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan_h2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }
//        Toast.makeText(ThongTinCaNhanH2.this,khachhang.toString(), Toast.LENGTH_SHORT).show();

        AnhXa();
        setInfor(khachhang);
        OnTab();
    }

    private void setInfor(Khachhang khachhang) {
        tv_hoten.setText(khachhang.getHovaten());
        tv_email.setText(khachhang.getEmail());
        tv_ngaysinh.setText(khachhang.getNgaysinh());
        tv_gioitinh.setText(khachhang.getGioitinh());
        tv_sdt.setText(khachhang.getSdt());
        tv_tennguoidung.setText(khachhang.getTendangnhap());
    }

    public void AnhXa() {
        btn_sua_ttcn = findViewById(R.id.btn_sua_ttcn);
        img_back_ttcn = findViewById(R.id.img_back_ttcn);
        tv_hoten = findViewById(R.id.tv_ttcn_hoten);
        tv_email = findViewById(R.id.tv_ttcn_email);
        tv_ngaysinh = findViewById(R.id.tv_ttcn_ngaysinh);
        tv_gioitinh = findViewById(R.id.tv_ttcn_gioitinh);
        tv_sdt = findViewById(R.id.tv_ttcn_sdt);
        tv_tennguoidung=findViewById(R.id.tenguoidung);
    }

    public void OnTab() {
        //quay lại trang cá nhân
        img_back_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhanH2.this, TrangCaNhan.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //đến trang sửa thông tin
        btn_sua_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhanH2.this, ChinhSuaThongTin.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}