package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.myfistapp.sunshine_app.R;

public class DangKy extends AppCompatActivity {
    TextView text_signin, text_signup;
    ImageView img_signup, eye;
    Button bt_singup;
    EditText email, username, pass, check_pass;
    Animation topAnim, bottomAnim, leftAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        eye=findViewById(R.id.eye);
//        Set Animaton for Element
        check_pass.setAnimation(leftAnim);
        text_signup.setAnimation(topAnim);
        img_signup.setAnimation(topAnim);
        email.setAnimation(leftAnim);
        eye.setAnimation(leftAnim);
        username.setAnimation(leftAnim);
        pass.setAnimation(leftAnim);
        bt_singup.setAnimation(leftAnim);
        text_signin.setAnimation(bottomAnim);

    }
}