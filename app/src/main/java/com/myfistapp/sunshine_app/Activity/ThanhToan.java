package com.myfistapp.sunshine_app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myfistapp.sunshine_app.Helper.TinyDB;
import com.myfistapp.sunshine_app.R;

public class ThanhToan extends AppCompatActivity {
    private TextView btn_thanhtoan;
    private CheckBox chb1, chb2, chb3, chb4;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_thanh_toan);
        initView();

        chb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chb2.setChecked(false);
                chb3.setChecked(false);
                chb4.setChecked(false);
            }
        });
        chb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chb1.setChecked(false);
                chb3.setChecked(false);
                chb4.setChecked(false);
            }
        });
        chb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chb2.setChecked(false);
                chb1.setChecked(false);
                chb4.setChecked(false);
            }
        });
        chb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chb2.setChecked(false);
                chb3.setChecked(false);
                chb1.setChecked(false);
            }
        });

        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chb1.isChecked()==false & chb2.isChecked()==false & chb3.isChecked()==false &chb4.isChecked()==false) {
                        Toast.makeText(ThanhToan.this, "Hãy chọn một phương thức thanh toán", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences preferences = getSharedPreferences("Pttt", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    if(chb1.isChecked()){
                        editor.putString("pttt", "COD");
                        editor.apply();
                        Intent intent = new Intent(ThanhToan.this, GioHang.class);
                        startActivity(intent);
                    }
                    if(chb2.isChecked()){
                        editor.putString("pttt", "Momo");
                        editor.apply();
                        Intent intent = new Intent(ThanhToan.this, GioHang.class);
                        startActivity(intent);
                    }
                    if(chb3.isChecked()){
                        editor.putString("pttt", "ZaloPay");
                        editor.apply();
                        Intent intent = new Intent(ThanhToan.this, GioHang.class);
                        startActivity(intent);
                    }
                    if(chb4.isChecked()){
                        editor.putString("pttt", "Banking");
                        editor.apply();
                        Intent intent = new Intent(ThanhToan.this, GioHang.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void initView() {
        btn_thanhtoan = findViewById(R.id.btn_dongy);
        chb1 = findViewById(R.id.checkbox_thanhtoan1);
        chb2 = findViewById(R.id.checkbox_thanhtoan2);
        chb3 = findViewById(R.id.checkbox_thanhtoan3);
        chb4 = findViewById(R.id.checkbox_thanhtoan4);
    }
}