package com.myfistapp.sunshine_app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myfistapp.sunshine_app.R;

public class Splash extends AppCompatActivity {
    //    Variables
    ImageView home_bg;
    TextView logo , slogan;
    Button login, signup;
    Animation topAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
//        Animations
        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_ani);
        bottomAnim= AnimationUtils.loadAnimation(this, R.anim.bottom_ani);
        home_bg= findViewById(R.id.img_home);
        logo=findViewById(R.id.logo);
        slogan=findViewById(R.id.slogan);
        login=findViewById(R.id.bt_login);
        signup=findViewById(R.id.bt_singup);
//        Set Animation
        slogan.setAnimation(topAnim);
        logo.setAnimation(topAnim);
        home_bg.setAnimation(topAnim);
        login.setAnimation(bottomAnim);
        signup.setAnimation(bottomAnim);
//        Ghi nhớ đăng nhập
        SharedPreferences preferences= getSharedPreferences("checkbox",MODE_PRIVATE);
        String state_checkbox= preferences.getString("remember","");
        if(state_checkbox.equals("true")){
            Intent intent= new Intent(Splash.this,Splash.class);
            startActivity(intent);
        }else if (state_checkbox.equals("false")){
            Toast.makeText(this,"Please Sign in",Toast.LENGTH_SHORT).show();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Splash.class);
                startActivity(intent);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Splash.class);
                startActivity(intent);
                finish();
            }
        });
    }
}