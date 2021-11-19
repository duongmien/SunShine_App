package com.myfistapp.sunshine_app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Model.KhachHang;
import com.myfistapp.sunshine_app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {
    TextView text_signin, text_signup;
    ImageView img_signup, eye;
    Button bt_singup;
    TextInputLayout  pass, check_pass;
    EditText edit_pass,email, username, edit_checkpass;
    Animation topAnim, bottomAnim, leftAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dang_ky);

//        Set Animation
        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_ani);
        bottomAnim= AnimationUtils.loadAnimation(this, R.anim.bottom_ani);
        leftAnim= AnimationUtils.loadAnimation(this, R.anim.left_ani);
//       Get element
        text_signin=findViewById(R.id.text_signin);
        text_signup=findViewById(R.id.textDK);
        img_signup=findViewById(R.id.img_signup);
        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        pass=findViewById(R.id.pass);
        bt_singup=findViewById(R.id.bt_singup);
        check_pass=findViewById(R.id.check_pass);
        edit_pass=findViewById(R.id.edit_pass);
        edit_checkpass=findViewById(R.id.edit_checkpass);
//        Set Animaton for Element
        check_pass.setAnimation(leftAnim);
        text_signup.setAnimation(topAnim);
        img_signup.setAnimation(topAnim);
        email.setAnimation(leftAnim);
        username.setAnimation(leftAnim);
        pass.setAnimation(leftAnim);
        bt_singup.setAnimation(leftAnim);
        text_signin.setAnimation(bottomAnim);
        text_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DangNhap.class);
                startActivity(intent);
                finish();
            }
        });
        bt_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_email, text_tendn, text_pass, text_mknl;
                text_email=email.getText().toString();
                text_tendn=(username.getText()).toString();
                text_pass=(edit_pass.getText()).toString().trim();
                text_mknl= (edit_checkpass.getText()).toString().trim();
                AlertDialog.Builder alert = new AlertDialog.Builder(DangKy.this);
                alert.setTitle("Nhập Thiếu Thông Tin");
                alert.setMessage("Bạn nhập thiếu thông tin. Vui lòng nhập lại");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alert.setCancelable(true);
                    }
                });
                if(text_email.isEmpty()||text_mknl.isEmpty()||text_tendn.isEmpty()||text_pass.isEmpty()){
                    alert.show();
                }
                else {
                    if(!text_pass.equals(text_mknl)){
                        alert.setTitle("Mật Khẩu Không Trùng Nhau");
                        alert.setMessage("Vui lòng kiểm tra lại mật khẩu của bạn. Mật khẩu nhập lại cần phải giống với mật khẩu ban đầu");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alert.setCancelable(true);
                            }
                        });
                        alert.show();
                    }
                    else {
                        createnewUser();
                    }
                }

            }
        });
    }

    private void createnewUser(){
        KhachHang khachHang = new KhachHang(username.getText().toString(),edit_pass.getText().toString(),username.getText().toString(),email.getText().toString());

        ApiService.apiService.createUser(khachHang).enqueue(new Callback<KhachHang>() {
            @Override
            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                //Toast.makeText(DangKy.this,"Đăng ký thành công", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(DangKy.this);
                alert.setTitle("Đăng Ký Thành Công");
                alert.setMessage("Bạn đăng ký tài khoản thành công! Vui lòng nhấn OK để đi đến trang đăng nhập!");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(DangKy.this, DangNhap.class));
                    }
                });
                alert.show();
            }

            @Override
            public void onFailure(Call<KhachHang> call, Throwable t) {
                //Toast.makeText(DangKy.this,"Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(DangKy.this);
                alert.setTitle("Đăng Ký Thất Bại");
                alert.setMessage("Bạn đăng ký tài khoản không thành công! Vui lòng nhấn OK để đăng ký lại!");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(DangKy.this, DangKy.class));
                    }
                });
                alert.show();
            }
        });
    }

}