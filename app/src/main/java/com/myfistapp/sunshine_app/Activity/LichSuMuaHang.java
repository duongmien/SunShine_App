package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Adapter.DonHang2Adapter;
import com.myfistapp.sunshine_app.Class.DiaChi;
import com.myfistapp.sunshine_app.Class.DonHang2;
import com.myfistapp.sunshine_app.Class.SanPhamMua;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

public class LichSuMuaHang extends AppCompatActivity implements DonHang2Adapter.LSMHClickItem{

    TextView ctdh;
    ImageView img_back_lsmh;
    RecyclerView rcv_listLSMua;
    DonHang2Adapter donHangHAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_hang);

        AnhXa();
        OnTab();

        //thiết lập list các đơn hàng
        setListMuaHang2();

    }


    public void AnhXa() {
        ctdh = findViewById(R.id.ctdh);
        img_back_lsmh = findViewById(R.id.img_back_lsmh);
        rcv_listLSMua = findViewById(R.id.rcv_lsmh_list);

    }

    public void OnTab() {
        //quay lại trang địa chỉ giao hàng
        img_back_lsmh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichSuMuaHang.this, TrangCaNhan.class);
                startActivity(intent);
            }
        });

    }

    //thiết lập list các đơn hàng
    public void setListMuaHang2() {

        //khởi tạo adapter
        donHangHAdapter = new DonHang2Adapter(this, this);

        //khởi tạo layoutmanager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_listLSMua.setLayoutManager(linearLayoutManager);

        //set dữ liệu adapter
        donHangHAdapter.setData(getListDonHang2());
        rcv_listLSMua.setAdapter(donHangHAdapter);
    }

    //set dữ liệu cho list đơn hàng
    public ArrayList<DonHang2> getListDonHang2() {
        ArrayList<DonHang2> list = new ArrayList<>();

        list.add(new DonHang2(getListSanPham(), "Ví momo", "Đã giao hàng thành công", getDiaChi(1)));
        list.add(new DonHang2(getListSanPham(), "Ví momo", "Đã giao hàng thành công", getDiaChi(0)));
        list.add(new DonHang2(getListSanPham(), "Ví momo", "Đã giao hàng thành công", getDiaChi(1)));
        list.add(new DonHang2(getListSanPham(), "Ví momo", "Đã giao hàng thành công", getDiaChi(0)));
        list.add(new DonHang2(getListSanPham(), "Ví momo", "Đã giao hàng thành công", getDiaChi(1)));
        list.add(new DonHang2(getListSanPham(), "Ví momo", "Đã giao hàng thành công", getDiaChi(0)));
        list.add(new DonHang2(getListSanPham(), "Ví momo", "Đã giao hàng thành công", getDiaChi(1)));

        return list;
    }

    //set list các sản phẩm
    public ArrayList<SanPhamMua> getListSanPham() {
        ArrayList<SanPhamMua> list = new ArrayList<>();

        list.add(new SanPhamMua("sp01", "Sinh tố hoa quả", "bla bla bla", "20000", "120", "30", "dm01", R.drawable.img_tu_sinhtohoaqua, 2));
        list.add(new SanPhamMua("sp01", "Salad gà", "bla bla bla", "20000", "120", "30", "dm01", R.drawable.img_ga_saladga, 2));
        list.add(new SanPhamMua("sp01", "Soda dưa hấu", "bla bla bla", "20000", "120", "30", "dm01", R.drawable.img_tu_sodaduahau, 2));
        list.add(new SanPhamMua("sp01", "Sữa chua kem dâu", "bla bla bla", "20000", "120", "30", "dm01", R.drawable.img_tu_suachuakemdau, 2));
        list.add(new SanPhamMua("sp01", "Sandwich gà", "bla bla bla", "20000", "120", "30", "dm01", R.drawable.img_ga_sandwichga, 2));

        return list;
    }

    //lấy địa chỉ từ trang địa chỉ giao hàng
    public DiaChi getDiaChi(int i) {
        DiaChiGiaoHang trang = new DiaChiGiaoHang();
        return trang.getAllDiaChi().get(i);
    }

    //truyền đơn hàng khi có sự kiện click
    @Override
    public void onItemClick(DonHang2 donHang2) {
        Intent intent = new Intent(LichSuMuaHang.this, ChiTietDonHang.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("donhang", donHang2);
        intent.putExtra("donhang", bundle);

        startActivity(intent);
    }
}