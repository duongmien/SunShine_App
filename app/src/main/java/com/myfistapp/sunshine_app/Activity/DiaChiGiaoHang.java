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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Adapter.DiaChiAdapterH;
import com.myfistapp.sunshine_app.Class.DiaChi;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class DiaChiGiaoHang extends AppCompatActivity implements DiaChiAdapterH.ItemClick{

    LinearLayout tab_themdiachi;
    ImageView img_backdcgh;
    private RecyclerView rcv_listdcgh;
    private DiaChiAdapterH diaChiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_giao_hang);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AnhXa();
        OnTab();

        //thiết lập list địa chỉ
        ListDiaChi();
    }

    public void AnhXa() {
        tab_themdiachi = findViewById(R.id.tab_themdiachi);
        img_backdcgh = findViewById(R.id.img_backdcgh);
        rcv_listdcgh = findViewById(R.id.rcv_listdcgh);
    }

    public void OnTab() {
        //trang thêm địa chỉ
        tab_themdiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaChiGiaoHang.this, ThemDiaChi.class);
                startActivity(intent);
            }
        });

        //về trang cá nhân
        img_backdcgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaChiGiaoHang.this, TrangCaNhan.class);
                startActivity(intent);
            }
        });

        //test
        rcv_listdcgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaChiGiaoHang.this, SuaDiaChi.class);
                startActivity(intent);
            }
        });
    }


    //thiết lập list địa chỉ
    public void ListDiaChi() {
        diaChiAdapter = new DiaChiAdapterH(this, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_listdcgh.setLayoutManager(linearLayoutManager);

        diaChiAdapter.setData(getAllDiaChi());
        rcv_listdcgh.setAdapter(diaChiAdapter);
    }

    //set dữ liệu cho list địa chỉ
    public ArrayList<DiaChi> getAllDiaChi() {
        ArrayList<DiaChi> list = new ArrayList<>();

        list.add(new DiaChi("Nguyễn Thuỳ Linh", "0284624753", "02 Thanh Sơn", "Thanh Bình", "Hải Châu", "Đà Nẵng"));
        list.add(new DiaChi("Nguyễn Thuỳ Linh", "0284624753", "02 Thanh Sơn", "Thanh Bình", "Hải Châu", "Đà Nẵng"));
        list.add(new DiaChi("Nguyễn Thuỳ Linh", "0284624753", "02 Thanh Sơn", "Thanh Bình", "Hải Châu", "Đà Nẵng"));
        return list;

    }

    // chưa biết hàm ni dùng làm chi
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //hàm click vào item trong list địa chỉ
    @Override
    public void onItemClick(DiaChi diaChi) {
        Intent intent = new Intent(DiaChiGiaoHang.this, SuaDiaChi.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("diachi", diaChi);
        intent.putExtra("data", bundle);

        startActivity(intent);
    }

}