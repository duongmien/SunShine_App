package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.R;

public class TrangCaNhan extends AppCompatActivity {

    LinearLayout tab_ttcn, tab_lsmh, tab_dcgh, tab_xtk, tab_thoat, tab_tlmk;
    private Khachhang khachhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }
        Toast.makeText(TrangCaNhan.this,khachhang.toString(), Toast.LENGTH_SHORT).show();

        AnhXa();
        OnTab();
        bottomNavigation();

    }

    public void AnhXa() {
        tab_ttcn = findViewById(R.id.tab_ttcn);
        tab_lsmh = findViewById(R.id.tab_lsmh);
        tab_dcgh = findViewById(R.id.tab_dcgh);
        tab_xtk = findViewById(R.id.tab_xtk);
        tab_thoat = findViewById(R.id.tab_thoat);
        tab_tlmk = findViewById(R.id.tab_tlmk);
    }

    public void OnTab() {
        //trang thông tin cá nhân
        tab_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangCaNhan.this, ThongTinCaNhanH2.class);
                startActivity(intent);
            }
        });

        //trang địa chỉ giao hàng
        tab_dcgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangCaNhan.this, DiaChiGiaoHang.class);
                startActivity(intent);
            }
        });

        //trang lịch sử mua hàng
        tab_lsmh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangCaNhan.this, LichSuMuaHang.class);
                startActivity(intent);
            }
        });

        //trang thiết lập mật khẩu
        tab_tlmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangCaNhan.this, ThietLapMatKhau.class);
                startActivity(intent);
            }
        });

        //yêu cầu xoá tài khoản
        tab_xtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaTK();
            }
        });

        //đăng xuất
        tab_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thoat();

            }
        });
    }

    //set thông báo khi người dùng muốn xoá tài khoản
    private void XoaTK() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Xóa tài khoản");
        alert.setMessage("Chúng tôi rất lấy làm tiếc khi bạn muốn rời Shunshine, nhưng xin lưu ý các tài khoản đã bị xóa sẽ không được mở trở lại.");

        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(TrangCaNhan.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.show();
    }

    //set thông báo khi người dùng muốn đăng xuất
    private void Thoat() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Bạn có muốn thoát không?");
//        alert.setMessage("Chúng tôi rất lấy làm tiếc khi bạn muốn rời Shunshine, nhưng xin lưu ý các tài khoản đã bị xóa sẽ không được mở trở lại.");

        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                startActivity(new Intent(TrangCaNhan.this, DangNhap.class));
            }
        });

        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
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
}