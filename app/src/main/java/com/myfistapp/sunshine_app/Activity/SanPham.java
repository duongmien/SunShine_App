package com.myfistapp.sunshine_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Model.SanPhamDomain;
import com.myfistapp.sunshine_app.Helper.ManagementCart;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.Model.Sanphamyeuthich;
import com.myfistapp.sunshine_app.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPham extends AppCompatActivity {

    private TextView btn_themsanpham;
    private TextView txt_tensanpham,txt_giasanpham,txt_soluongsanpham,txt_danhgia,txt_thoigian,txt_kcal,txt_thongtinsanpham;
    private ImageView btn_cong,btn_tru,img_sanpham;
    private SanPhamDomain object;
    private ManagementCart managementCart;
    private Khachhang khachhang;
    private ToggleButton btn_fav;
    private int id_khachhang, id_sanpham;
    private int soluong = 1;
    private ArrayList<Sanphamyeuthich> sanphamyeuthiches;
    private boolean ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_san_pham);

        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }

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

        id_khachhang = khachhang.getIdkh();
        id_sanpham = object.getIdsp();
        showFav(id_khachhang);


        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((ToggleButton) view).isChecked();
                if (checked){
                    setFav(id_khachhang,id_sanpham);
                }else {
                    setunFav(id_khachhang,id_sanpham);
                }
            }
        });

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
                Intent intent = new Intent(getApplicationContext(), TrangChu.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void showFav(int id_khachhang) {
        sanphamyeuthiches=new ArrayList<Sanphamyeuthich>();

        ApiService.apiService.showFavorite(id_khachhang).enqueue(new Callback<ArrayList<Sanphamyeuthich>>() {
            @Override
            public void onResponse(Call<ArrayList<Sanphamyeuthich>> call, Response<ArrayList<Sanphamyeuthich>> response) {
                sanphamyeuthiches = response.body();
                if(checkFavorite(id_khachhang,id_sanpham)){
                    btn_fav.setChecked(true);
                }else {
                    btn_fav.setChecked(false);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sanphamyeuthich>> call, Throwable t) {

            }
        });
    }

    private void setunFav(int id_khachhang, int id_sanpham) {
        if (checkFavorite(id_khachhang,id_sanpham)){
            ApiService.apiService.deleteFavorite(id_khachhang,id_sanpham).enqueue(new Callback<Sanphamyeuthich>() {
                @Override
                public void onResponse(Call<Sanphamyeuthich> call, Response<Sanphamyeuthich> response) {
                    Toast.makeText(SanPham.this,"Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Sanphamyeuthich> call, Throwable t) {

                }
            });
        }else {
            return;
        }
    }

    private void setFav(int id_khachhang, int id_sanpham) {
        if (checkFavorite(id_khachhang,id_sanpham)){
            return;
        }else {
            Sanphamyeuthich sanphamyeuthich2 = new Sanphamyeuthich(id_khachhang,id_sanpham);
            ApiService.apiService.addFavorite(sanphamyeuthich2).enqueue(new Callback<Sanphamyeuthich>() {
                @Override
                public void onResponse(Call<Sanphamyeuthich> call, Response<Sanphamyeuthich> response) {
                    Toast.makeText(SanPham.this,"Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Sanphamyeuthich> call, Throwable t) {

                }
            });
        }
    }

    private boolean checkFavorite(int id_khachhang, int id_sanpham) {
        boolean isHasFav = false;
        if(sanphamyeuthiches == null || sanphamyeuthiches.isEmpty()){
            return false;
        }
        for(Sanphamyeuthich sanphamyeuthich : sanphamyeuthiches){
            if(id_khachhang== sanphamyeuthich.getIdkh() && id_sanpham == sanphamyeuthich.getIdsp()){
                isHasFav = true;
                break;
            }
        }
        if (isHasFav){
            return true;
        }else {
            return false;
        }
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
        btn_fav = findViewById(R.id.btn_fav);
    }

}