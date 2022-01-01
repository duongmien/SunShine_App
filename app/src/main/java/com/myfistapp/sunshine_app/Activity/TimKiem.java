package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.myfistapp.sunshine_app.Adapter.ReycyclerViewAdapter;
import com.myfistapp.sunshine_app.Adapter.ReycyclerViewAdapterSearch;
import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.Model.SanPhamDomain;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiem extends AppCompatActivity {
    private static final int REQ_CODE_SPEECH_INPUT =100 ;
    private ReycyclerViewAdapterSearch  adapter;
    private RecyclerView  recyclerViewPopularList;
    private Khachhang khachhang;
    private ImageView mic;
    private EditText txtSearch;
    ImageView img_back;
    private ArrayList<SanPhamDomain> danhsachsanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tim_kiem);

        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }
        recyclerViewPopular();
        txtSearch = findViewById(R.id.search_text);
        img_back=findViewById(R.id.btn_back);
        mic=findViewById(R.id.iconmic);
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestAudioPermissions();
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrangChu.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });

    }
    public void speech_TO_TEXT(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault() );

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Đang nghe  --__-- ");

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn\\'t support speech input" ,
                    Toast.LENGTH_SHORT).show();
        }
    }
    //Requesting run-time permissions

    //Create placeholder for user's consent to record_audio permission.
//This will be used in handling callback
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private void requestAudioPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {


            //When permission is not granted by user, show them message why this permission is needed.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                Toast.makeText(this, "Please grant permissions to record audio", Toast.LENGTH_LONG).show();

                //Give user option to still opt-in the permissions
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);

            } else {
                // Show user dialog to grant permission to record audio
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);
            }
        }
        //If permission is granted, then go ahead recording audio
        else if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {

            //Go ahead with recording audio now
            speech_TO_TEXT();
        }
    }

    //Handling callback
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    speech_TO_TEXT();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permissions Denied to record audio", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && data != null) {

                    List<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String text = result.get(0);
                    txtSearch.setText(text);

                }
                break;
            }

        }
    }
    private void filter(String text) {
        ArrayList<SanPhamDomain> sanPhamDomains = new ArrayList<>();
        for (SanPhamDomain item : danhsachsanpham){
            if (item.getTensanpham().toLowerCase().contains(text.toLowerCase())){
                sanPhamDomains.add(item);
            }
        }
        adapter.filterList(sanPhamDomains,khachhang);
    }

    private void recyclerViewPopular() {
        recyclerViewPopularList = findViewById(R.id.recycleview_danhsach);
        recyclerViewPopularList.setLayoutManager(new GridLayoutManager(this,1));

        ApiService.apiService.showitem().enqueue(new Callback<ArrayList<SanPhamDomain>>() {
            @Override
            public void onResponse(Call<ArrayList<SanPhamDomain>> call, Response<ArrayList<SanPhamDomain>> response) {
                danhsachsanpham = response.body();
                adapter = new ReycyclerViewAdapterSearch(danhsachsanpham,khachhang);
                recyclerViewPopularList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<SanPhamDomain>> call, Throwable t) {
                Toast.makeText(TimKiem.this,"Show that bai", Toast.LENGTH_SHORT).show();

            }
        });

    }
}