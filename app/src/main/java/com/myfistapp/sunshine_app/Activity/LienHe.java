package com.myfistapp.sunshine_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myfistapp.sunshine_app.R;

public class LienHe extends AppCompatActivity {

    TextView btn_diachi,btn_hoanthanh,ten,sdt,sonha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lien_he);
        initView();
        Intent intent = getIntent();
        String diachi = intent.getStringExtra("DiaChi");
        if(diachi != null)
        {
            btn_diachi.setText(diachi);
        }


        btn_hoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LienHe.this, GioHang.class);
                String lienhe = ten.getText()+"\n"+sdt.getText()+"\n"+sonha.getText()+" "+btn_diachi.getText();
                intent.putExtra("LienHe", lienhe);
                startActivity(intent);
            }
        });
        btn_diachi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(LienHe.this, DiaChi.class);
                startActivity(intent3);
            }
        });

    }
    private void initView() {
        btn_diachi = findViewById(R.id.txt_diachi);
        btn_hoanthanh = findViewById(R.id.btn_hoanthanh);
        ten = findViewById(R.id.txt_ten);
        sdt = findViewById(R.id.txt_sdt);
        sonha = findViewById(R.id.txt_sonha);
    }
    
}