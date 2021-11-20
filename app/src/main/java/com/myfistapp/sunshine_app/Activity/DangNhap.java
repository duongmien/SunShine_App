package com.myfistapp.sunshine_app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhap extends AppCompatActivity {
    private ImageView img_login, img_ig, img_fb, img_gg;
    private TextView text_singin, text_sigup, text_forgetpass;
    private EditText username, pass;
    private Button bt_signin;
    private Animation topAnim, bottomAnim, leftAnim;
    private CheckBox checkbox;
    private ArrayList<Khachhang> khachhangs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dang_nhap);
//        Set Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_ani);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_ani);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.left_ani);
//        Get element
        img_login = findViewById(R.id.img_login);
        img_fb = findViewById(R.id.img_fb);
        img_ig = findViewById(R.id.img_IG);
        img_gg = findViewById(R.id.img_gg);
        checkbox = findViewById(R.id.checkbox);
        text_singin = findViewById(R.id.textDN);
        text_sigup = findViewById(R.id.text_signup);
        text_forgetpass = findViewById(R.id.quenmatkhau);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        bt_signin = findViewById(R.id.bt_login);
//        Set Animation
        img_login.setAnimation(topAnim);
        text_singin.setAnimation(topAnim);
//
        bt_signin.setAnimation(leftAnim);
        username.setAnimation(leftAnim);
        pass.setAnimation(leftAnim);
        checkbox.setAnimation(leftAnim);
//
        text_sigup.setAnimation(bottomAnim);
        text_forgetpass.setAnimation(bottomAnim);
        img_gg.setAnimation(bottomAnim);
        img_fb.setAnimation(bottomAnim);
        img_ig.setAnimation(bottomAnim);
        //
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String state_checkbox = preferences.getString("remember", "");
        if (state_checkbox.equals("true")) {
            Intent intent = new Intent(DangNhap.this, TrangChu.class);
            startActivity(intent);
        } else if (state_checkbox.equals("false")) {
            Toast.makeText(this, "Please Sign in", Toast.LENGTH_SHORT).show();
        }
        text_sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangKy.class);
                startActivity(intent);
                finish();
            }
        });
        khachhangs = new ArrayList<>();
        getlistuser();


        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicklogin();
            }
        });
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(DangNhap.this, "Checked", Toast.LENGTH_SHORT).show();
                } else if (!compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(DangNhap.this, "Un Checked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clicklogin(){
        String strUsername=username.getText().toString().trim();
        String strPassword=pass.getText().toString().trim();

        if(khachhangs == null || khachhangs.isEmpty()){
            return;
        }
        boolean isHasUser = false;
        for(Khachhang khachHang: khachhangs){
            if(strUsername.equals(khachHang.getTendangnhap()) && strPassword.equals(khachHang.getMatkhau())){
                isHasUser = true;
                break;
            }
        }
        if (isHasUser){
            Intent intent = new Intent(getApplicationContext(), TrangChu.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(DangNhap.this,"Đăng nhập thất bại!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getlistuser() {

        ApiService.apiService.showuser().enqueue(new Callback<ArrayList<Khachhang>>() {
            @Override
            public void onResponse(Call<ArrayList<Khachhang>> call, Response<ArrayList<Khachhang>> response) {
                khachhangs = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Khachhang>> call, Throwable t) {

            }
        });

    }
}
