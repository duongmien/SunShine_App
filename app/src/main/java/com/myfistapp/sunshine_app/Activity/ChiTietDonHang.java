package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Adapter.ChiTietDonHangAdapter;
import com.myfistapp.sunshine_app.Class.DonHang2;
import com.myfistapp.sunshine_app.Class.SanPhamMua;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class ChiTietDonHang extends AppCompatActivity {

    ImageView img_back_ctdh;
    TextView tv_dc_hoten, tv_dc_sdt, tv_dc_sonha, tv_dc_all, tv_pttt, tv_tongtien;
    RecyclerView rcv_listSanPhamDaMua;
    private ChiTietDonHangAdapter adapterChiTiet;
    private DonHang2 donHangH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_don_hang);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AnhXa();
        Intents();
        nhanDonHang();
    }

    public void AnhXa() {
        img_back_ctdh = findViewById(R.id.img_back_ctdh);
        tv_dc_hoten = findViewById(R.id.tv_ctdh_dc_hoten);
        tv_dc_sdt = findViewById(R.id.tv_ctdh_dc_sdt);
        tv_dc_sonha = findViewById(R.id.tv_ctdh_dc_sonha);
        tv_dc_all = findViewById(R.id.tv_ctdh_dc_all);
        tv_pttt = findViewById(R.id.tv_ctdh_pttt);
        tv_tongtien = findViewById(R.id.tv_ctdh_tongtien);
        rcv_listSanPhamDaMua = findViewById(R.id.rcv_ctdh_listsp);
    }

    public void Intents() {
        img_back_ctdh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietDonHang.this, LichSuMuaHang.class);
                startActivity(intent);
            }
        });
    }


    //nhận đơn hàng từ lịch sử mua hàng
    public void nhanDonHang() {
        //khai báo list sản phẩm để nhận dữ liệu list truyền vào
        ArrayList<SanPhamMua> listSanPhamDaMua = new ArrayList<>();
        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("donhang");

        if(bundle != null) {
            donHangH = (DonHang2) bundle.getSerializable("donhang");
            setRecyclerView();
        }
    }

    //set RecyclerView
    public void setRecyclerView() {
        adapterChiTiet = new ChiTietDonHangAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_listSanPhamDaMua.setLayoutManager(linearLayoutManager);

        adapterChiTiet.setData(donHangH.getListSanPhamMua());
        rcv_listSanPhamDaMua.setAdapter(adapterChiTiet);
    }
}