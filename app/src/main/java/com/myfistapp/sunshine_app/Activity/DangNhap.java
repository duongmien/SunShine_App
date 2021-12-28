package com.myfistapp.sunshine_app.Activity;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
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
    private TextInputLayout layout_pass;
    private Button bt_signin;
    private Animation topAnim, bottomAnim, leftAnim;
    private CheckBox checkbox;
    private ArrayList<Khachhang> khachhangs;
    private Khachhang mkhachhang;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN=123;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            Intent intent=new Intent(getApplicationContext(), TrangChu.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dang_nhap);
        mAuth=FirebaseAuth.getInstance();
        onStart();

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
        layout_pass=findViewById(R.id.layout_pass);
////        Set Animation
        img_login.setAnimation(topAnim);
        text_singin.setAnimation(topAnim);

        bt_signin.setAnimation(leftAnim);
        username.setAnimation(leftAnim);
        layout_pass.setAnimation(leftAnim);
        checkbox.setAnimation(leftAnim);

        text_sigup.setAnimation(bottomAnim);
        text_forgetpass.setAnimation(bottomAnim);
        img_gg.setAnimation(bottomAnim);
        img_fb.setAnimation(bottomAnim);
        img_ig.setAnimation(bottomAnim);

        createRequest();
        img_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
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
//FireBase Login start
    private void createRequest(){
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.heading1))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(getApplicationContext(), TrangChu.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }

//FireBase Login end
    private void clicklogin(){
        String strUsername=username.getText().toString().trim();
        String strPassword=pass.getText().toString().trim();
        AlertDialog.Builder alert = new AlertDialog.Builder(DangNhap.this);
        alert.setTitle("Nhập Thiếu Thông Tin");
        alert.setMessage("Bạn nhập thiếu thông tin. Vui lòng nhập lại");
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alert.setCancelable(true);
            }
        });
        if(strUsername.isEmpty()||strPassword.isEmpty()){
            alert.show();
        }
        else {
        if(khachhangs == null || khachhangs.isEmpty()){
            return;
        }
        boolean isHasUser = false;
        for(Khachhang khachHang: khachhangs){
            if(strUsername.equals(khachHang.getTendangnhap()) && strPassword.equals(khachHang.getMatkhau())){
                isHasUser = true;
                mkhachhang = khachHang;
                break;
            }
        }
        if (isHasUser){
            Intent intent = new Intent(getApplicationContext(), TrangChu.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object_user",mkhachhang);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }else {
            alert.setTitle("Đăng nhập thất bại");
            alert.setMessage("Bạn nhập sai tên đăng nhập hoặc mật khẩu! Vui lòng kiểm tra lại!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alert.setCancelable(true);
                }
            });
            alert.show();
        }
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
