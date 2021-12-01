package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.myfistapp.sunshine_app.Adapter.ReycyclerViewAdapter;
import com.myfistapp.sunshine_app.Adapter.ReycyclerViewAdapterSearch;
import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.Model.SanPhamDomain;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiem extends AppCompatActivity {
    private ReycyclerViewAdapterSearch  adapter;
    private RecyclerView  recyclerViewPopularList;
    private Khachhang khachhang;
    private EditText txtSearch;
    ImageView img_back;
    private ArrayList<SanPhamDomain> danhsachsanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tim_kiem);

        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }
        recyclerViewPopular();
        txtSearch = findViewById(R.id.search_text);
        img_back=findViewById(R.id.btn_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrangChu.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });

    }

    private void filter(String text) {
        ArrayList<SanPhamDomain> sanPhamDomains = new ArrayList<>();
        for (SanPhamDomain item : danhsachsanpham){
            if (item.getTensanpham().toLowerCase().contains(text.toLowerCase())){
                sanPhamDomains.add(item);
            }
        }
        adapter.filterList(sanPhamDomains,khachhang);
    }

    private void recyclerViewPopular() {
        recyclerViewPopularList = findViewById(R.id.recycleview_danhsach);
        recyclerViewPopularList.setLayoutManager(new GridLayoutManager(this,1));

        ApiService.apiService.showitem().enqueue(new Callback<ArrayList<SanPhamDomain>>() {
            @Override
            public void onResponse(Call<ArrayList<SanPhamDomain>> call, Response<ArrayList<SanPhamDomain>> response) {
                Toast.makeText(TimKiem.this,khachhang.toString(), Toast.LENGTH_SHORT).show();
                danhsachsanpham = response.body();
                adapter = new ReycyclerViewAdapterSearch(danhsachsanpham,khachhang);
                recyclerViewPopularList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<SanPhamDomain>> call, Throwable t) {
                Toast.makeText(TimKiem.this,"Show that bai", Toast.LENGTH_SHORT).show();

            }
        });

    }
}