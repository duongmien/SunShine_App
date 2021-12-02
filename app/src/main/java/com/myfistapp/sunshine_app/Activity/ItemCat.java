package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Adapter.ReycyclerViewAdapter;
import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.Model.SanPhamDomain;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemCat extends AppCompatActivity {
    private Khachhang khachhang;
    private RecyclerView recyclerViewPopularList;
    private RecyclerView.Adapter  adapter;
    private int object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_item_cat);
        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }
        object = (int) getIntent().getSerializableExtra("object");
        recyclerViewPopular();
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
    private void recyclerViewPopular() {
        int numcol=2;
        recyclerViewPopularList = findViewById(R.id.recycleview_danhsach);
        recyclerViewPopularList.setLayoutManager(new GridLayoutManager(this,2));

        ApiService.apiService.showitemcat(object).enqueue(new Callback<ArrayList<SanPhamDomain>>() {
            @Override
            public void onResponse(Call<ArrayList<SanPhamDomain>> call, Response<ArrayList<SanPhamDomain>> response) {
//                Toast.makeText(ItemCat.this,khachhang.toString(), Toast.LENGTH_SHORT).show();
                ArrayList<SanPhamDomain> danhsachsanpham = response.body();
                adapter = new ReycyclerViewAdapter(danhsachsanpham,khachhang);
                recyclerViewPopularList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<SanPhamDomain>> call, Throwable t) {
                Toast.makeText(ItemCat.this,"Show that bai", Toast.LENGTH_SHORT).show();

            }
        });

    }

}