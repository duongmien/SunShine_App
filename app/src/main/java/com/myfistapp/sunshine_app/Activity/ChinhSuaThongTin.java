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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSuaThongTin extends AppCompatActivity {

    ImageView img_back_cstt;
    Button btn_luu_cstt;
    TextView tv_tennguoidung;
    EditText edt_hoten, edt_gioitinh, edt_ngaysinh, edt_sdt, edt_email;
    RadioButton rbt_nam, rbt_nu, rbt_khac;
    private Khachhang khachhang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_thong_tin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AnhXa();
        nhanData();
        OnTab();

        //nhận dữ liệu là 1 địa chỉ từ trang địa chỉ

    }

    public void AnhXa() {
        img_back_cstt = findViewById(R.id.img_back_cstt);
        btn_luu_cstt = findViewById(R.id.btn_luu_cstt);

        edt_hoten = findViewById(R.id.edt_cstt_hoten);
        edt_ngaysinh = findViewById(R.id.edt_cstt_ngaysinh);
        edt_sdt = findViewById(R.id.edt_cstt_sdt);
        edt_email = findViewById(R.id.edt_cstt_email);

        rbt_nam = findViewById(R.id.rbt_nam_cstt);
        rbt_nu = findViewById(R.id.rbt_nu_cstt);
        rbt_khac = findViewById(R.id.rbt_khac_cstt);
        tv_tennguoidung=findViewById(R.id.tenguoidung);
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

        btn_luu_cstt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfor(khachhang);
                ApiService.apiService.updateKhachhang(khachhang,khachhang.getIdkh()).enqueue(new Callback<Khachhang>() {
                    @Override
                    public void onResponse(Call<Khachhang> call, Response<Khachhang> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ChinhSuaThongTin.this,"Cập nhật thành công!!!",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Khachhang> call, Throwable t) {
                        Toast.makeText(ChinhSuaThongTin.this,"Cập nhật thành công!!!",Toast.LENGTH_LONG).show();
                    }
                });
                Intent intent = new Intent(ChinhSuaThongTin.this, ThongTinCaNhanH2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void saveInfor(Khachhang khachhang) {
        khachhang.setHovaten(edt_hoten.getText().toString());
        khachhang.setNgaysinh(edt_ngaysinh.getText().toString());
        khachhang.setSdt(edt_sdt.getText().toString());
        khachhang.setEmail(edt_email.getText().toString());

        if (rbt_nam.isChecked()){
            khachhang.setGioitinh("Nam");
        }else {
            if (rbt_nu.isChecked()){
                khachhang.setGioitinh("Nữ");
            }else {
                khachhang.setGioitinh("Khác");
            }
        }
    }

    public void nhanData() {
        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");

            edt_hoten.setText(khachhang.getHovaten());
            edt_ngaysinh.setText(khachhang.getNgaysinh());
            edt_sdt.setText(khachhang.getSdt());
            edt_email.setText(khachhang.getEmail());
            tv_tennguoidung.setText(khachhang.getTendangnhap());

            String gioitinh = khachhang.getGioitinh();
            if (gioitinh.equals("Nam")) {
                rbt_nam.isSelected();
            } else {
                if (gioitinh.equals("Nữ")) {
                    rbt_nu.isSelected();
                } else{
                    if (gioitinh.equals("Nữ")){
                        rbt_khac.isSelected();
                    }
                }
            }
        }
//        Toast.makeText(ChinhSuaThongTin.this,khachhang.toString(), Toast.LENGTH_SHORT).show();
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