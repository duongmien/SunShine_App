package com.myfistapp.sunshine_app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.myfistapp.sunshine_app.Adapter.GioHangAdapter;
import com.myfistapp.sunshine_app.Helper.ManagementCart;
import com.myfistapp.sunshine_app.Helper.TinyDB;
import com.myfistapp.sunshine_app.Interface.ChangeNumberItemsListener;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.R;
import com.myfistapp.sunshine_app.Activity.TrangCaNhan;

import java.text.DecimalFormat;

public class GioHang extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView tongtien, tongsanpham,emptyTxt,btndathang,pttt, diachi;
    private RelativeLayout rediachi,redanhsach,rethanhtoan;
    private ImageView btnpttt,btndiachi;
    private Khachhang khachhang;
    private TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }
        Toast.makeText(GioHang.this,khachhang.toString(), Toast.LENGTH_SHORT).show();

        managementCart = new ManagementCart(this);

        initView();
        SharedPreferences preferences = getSharedPreferences("Diachi", MODE_PRIVATE);
        SharedPreferences preferences2 = getSharedPreferences("Pttt", MODE_PRIVATE);
        String lhe = preferences.getString("diachi", "");
        String Pttt = preferences2.getString("pttt", "");
        diachi.setText(lhe);
        pttt.setText(Pttt);

        initList();
        calculateCard();
        bottomNavigation();

        btndiachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(GioHang.this, LienHe.class));
            }
        });

        btnpttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GioHang.this, ThanhToan.class));
            }
        });
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pttt.getText()==""){
                    Toast.makeText(GioHang.this, "Hãy chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(GioHang.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static String currencyFormat(Double amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((amount));
    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new GioHangAdapter(managementCart.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCard().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            rediachi.setVisibility(View.GONE);
            redanhsach.setVisibility(View.GONE);
            rethanhtoan.setVisibility(View.GONE);
            btndathang.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            rediachi.setVisibility(View.VISIBLE);
            redanhsach.setVisibility(View.VISIBLE);
            rethanhtoan.setVisibility(View.VISIBLE);
            btndathang.setVisibility(View.VISIBLE);
        }
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

    private void calculateCard() {
        tongsanpham.setText("Tổng "+managementCart.getTotalItems()+" sản phẩm" );
        tongtien.setText(currencyFormat(managementCart.getTotalFee())+ " VNĐ" );
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recycleview_danhsach);
        tongtien = findViewById(R.id.txt_tongtien);
        tongsanpham = findViewById(R.id.txt_tongsanpham);
        emptyTxt = findViewById(R.id.emptyTxt);
        rediachi = findViewById(R.id.re_diachi);
        redanhsach= findViewById(R.id.re_danhsach);
        rethanhtoan= findViewById(R.id.re_thanhtoan);
        btndathang= findViewById(R.id.btn_dathang);
        pttt= findViewById(R.id.txt_pttt);
        btnpttt= findViewById(R.id.img_arrow);
        btndiachi= findViewById(R.id.btn_diachi);
        diachi= findViewById(R.id.txt_diachi);
    }
}