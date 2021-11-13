package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChinhSuaThongTin extends AppCompatActivity {

    ImageView img_back_cstt;
    Button btn_luu_cstt;
    EditText edt_hoten, edt_gioitinh, edt_ngaysinh, edt_sdt, edt_email;
    RadioButton rbt_nam, rbt_nu, rbt_khac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AnhXa();
        OnTab();

        //nhận dữ liệu là 1 địa chỉ từ trang địa chỉ
        nhanData();
    }

    public void AnhXa() {
        img_back_cstt = findViewById(R.id.img_back_cstt);
        btn_luu_cstt = findViewById(R.id.btn_luu_cstt);

        edt_hoten = findViewById(R.id.edt_cstt_hoten);
//        edt_gioitinh = findViewById(R.id.edt_cstt_gioitinh);
        edt_ngaysinh = findViewById(R.id.edt_cstt_ngaysinh);
        edt_sdt = findViewById(R.id.edt_cstt_sdt);
        edt_email = findViewById(R.id.edt_cstt_email);

        rbt_nam = findViewById(R.id.rbt_nam_cstt);
        rbt_nu = findViewById(R.id.rbt_nu_cstt);
        rbt_khac = findViewById(R.id.rbt_khac_cstt);
    }

    public void OnTab() {
        //quay lại trang thông tin
        img_back_cstt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChinhSuaThongTin.this, ThongTinCaNhanH2.class);
                startActivity(intent);
            }
        });

        //chọn ngày
        edt_ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });

    }

    public void nhanData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("thongtinduoidung");

        if (bundle != null) {
            edt_hoten.setText(bundle.getString("hoten", ""));
//            edt_gioitinh.setText(bundle.getString("gioitinh", ""));
            edt_ngaysinh.setText(bundle.getString("ngaysinh", ""));
            edt_sdt.setText(bundle.getString("sdt", ""));
            edt_email.setText(bundle.getString("email", ""));

            String gioitinh = bundle.getString("gioitinh", "");
            if (gioitinh.equals("Nam")) {
                rbt_nam.isSelected();
            } else {
                if (gioitinh.equals("Nữ")) {
                    rbt_nu.isSelected();
                } else
                    rbt_khac.isSelected();
            }
        }
    }

    public void ChonNgay() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_ngaysinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}