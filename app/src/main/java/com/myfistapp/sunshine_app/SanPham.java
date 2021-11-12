package com.myfistapp.sunshine_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


import java.text.DecimalFormat;

public class SanPham extends AppCompatActivity {

    private TextView btn_themsanpham;
    private TextView txt_tensanpham,txt_giasanpham,txt_soluongsanpham,txt_danhgia,txt_thoigian,txt_kcal,txt_thongtinsanpham;
    private ImageView btn_cong,btn_tru,img_sanpham;
    private SanPhamDomain object;
    private ManagementCart managementCart;

    private int soluong = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_san_pham);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }
    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(Double.parseDouble(amount));
    }
    private void getBundle() {
        object = (SanPhamDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getAnhsanpham(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(img_sanpham);

        txt_tensanpham.setText(object.getTensanpham());
        txt_giasanpham.setText(currencyFormat(object.getGiasanpham())+" VNĐ");
        txt_danhgia.setText(object.getDanhgiasanpham());
        txt_thoigian.setText(object.getThoigian()+"phút");
        txt_kcal.setText(object.getKcal()+"kacl");
        txt_thongtinsanpham.setText(object.getThongtinsanpham());

        txt_soluongsanpham.setText(String.valueOf(soluong));

        btn_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong = soluong + 1;
                txt_soluongsanpham.setText(String.valueOf(soluong));
            }
        });

        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soluong > 1) {
                    soluong = soluong - 1;
                }
                txt_soluongsanpham.setText(String.valueOf(soluong));
            }
        });

        btn_themsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setSoluongdathang(soluong);
                managementCart.insertFood(object);
                startActivity(new Intent(SanPham.this, TrangChu.class));
            }
        });
    }

    private void initView() {
        btn_themsanpham = findViewById(R.id.btn_themsanpham);
        txt_tensanpham = findViewById(R.id.txt_tensanpham);
        txt_giasanpham = findViewById(R.id.txt_giasanpham);
        txt_soluongsanpham = findViewById(R.id.txt_soluongsanpham);
        txt_danhgia = findViewById(R.id.txt_danhgia);
        txt_thoigian = findViewById(R.id.txt_thoigian);
        txt_kcal = findViewById(R.id.txt_kcal);
        txt_thongtinsanpham = findViewById(R.id.txt_thongtinsanpham);
        btn_cong = findViewById(R.id.btn_cong);
        btn_tru = findViewById(R.id.btn_tru);
        img_sanpham = findViewById(R.id.img_sanpham_giohang);
    }

}