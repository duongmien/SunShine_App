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

import com.myfistapp.sunshine_app.R;

public class DangNhap extends AppCompatActivity {
    ImageView img_login, img_ig, img_fb, img_gg;
    TextView text_singin, text_sigup, text_forgetpass;
    EditText username, pass;
    Button bt_signin;
    Animation topAnim, bottomAnim, leftAnim;
    CheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dang_nhap);
//        Set Animation
        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_ani);
        bottomAnim= AnimationUtils.loadAnimation(this, R.anim.bottom_ani);
        leftAnim= AnimationUtils.loadAnimation(this, R.anim.left_ani);
//        Get element
        img_login=findViewById(R.id.img_login);
        img_fb=findViewById(R.id.img_fb);
        img_ig=findViewById(R.id.img_IG);
        img_gg=findViewById(R.id.img_gg);
        checkbox=findViewById(R.id.checkbox);
        text_singin=findViewById(R.id.textDN);
        text_sigup=findViewById(R.id.text_signup);
        text_forgetpass=findViewById(R.id.quenmatkhau);
        username=findViewById(R.id.username);
        pass=findViewById(R.id.pass);
        bt_signin=findViewById(R.id.bt_login);
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
        SharedPreferences preferences= getSharedPreferences("checkbox",MODE_PRIVATE);
        String state_checkbox= preferences.getString("remember","");
        if(state_checkbox.equals("true")){
            Intent intent= new Intent(DangNhap.this,DangNhap.class);
            startActivity(intent);
        }else if (state_checkbox.equals("false")){
            Toast.makeText(this,"Please Sign in",Toast.LENGTH_SHORT).show();
        }
        text_sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DangKy.class);
                startActivity(intent);
                finish();
            }
        });
        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DangNhap.class);
                startActivity(intent);
                finish();
            }
        });
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(DangNhap.this, "Checked",Toast.LENGTH_SHORT).show();
                }
                else if(!compoundButton.isChecked()){
                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(DangNhap.this, "Un Checked",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}