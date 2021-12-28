package com.myfistapp.sunshine_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Adapter.CategoryAdapter;
import com.myfistapp.sunshine_app.Adapter.PhotoViewPagerAdapter;
import com.myfistapp.sunshine_app.Adapter.ReycyclerViewAdapter;
import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Class.CategoryDomain;
import com.myfistapp.sunshine_app.Class.Photo;
import com.myfistapp.sunshine_app.Model.SanPhamDomain;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu extends AppCompatActivity {
    private RecyclerView.Adapter  adapter;
    private RecyclerView  recyclerViewPopularList, recyclerViewCategoryList;
    private Khachhang khachhang;

    private EditText search_box;
    //Khai báo ImageSlide
    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private List<Photo> mListPhoto;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(mViewPager.getCurrentItem()==mListPhoto.size()-1){
                mViewPager.setCurrentItem(0);
            }else {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        setContentView(R.layout.activity_trang_chu);

        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }
        search_box = findViewById(R.id.search_box);

        bottomNavigation();
        //Ánh xạ imageslide
        mViewPager =findViewById(R.id.viewpage);
        mCircleIndicator=findViewById(R.id.circle_indicator);

          //Code ImageSlider
        mListPhoto = getmListPhoto();
        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(mListPhoto);
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(true, new DepthPageTransformer ());
        mViewPager.setTranslationX(-1 * mViewPager.getWidth() * mViewPager.getCurrentItem());
        mCircleIndicator.setViewPager(mViewPager);

        handler.postDelayed(runnable,3000);

        search_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TimKiem.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        recyclerViewPopular();
        recyclerViewCategory();
    }


    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout favBtn = findViewById(R.id.favBtn);
        LinearLayout notiBtn = findViewById(R.id.notBtn);
        LinearLayout proBtn = findViewById(R.id.proBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GioHang.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrangChu.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YeuThich.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        notiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThongBao.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        proBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrangCaNhan.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",khachhang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }



    //Thêm ảnh Slider
    private List<Photo> getmListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo((R.drawable.img_banner1)));
        list.add(new Photo((R.drawable.img_banner2)));
        list.add(new Photo((R.drawable.img_banner1)));

        return list;
    }
    //slide tranform
    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    private void recyclerViewPopular() {
        int numcol=2;
        recyclerViewPopularList = findViewById(R.id.recycleview_danhsach);
        recyclerViewPopularList.setLayoutManager(new GridLayoutManager(this,2));

        ApiService.apiService.showitem().enqueue(new Callback<ArrayList<SanPhamDomain>>() {
            @Override
            public void onResponse(Call<ArrayList<SanPhamDomain>> call, Response<ArrayList<SanPhamDomain>> response) {
                Toast.makeText(TrangChu.this,khachhang.toString(), Toast.LENGTH_SHORT).show();
                ArrayList<SanPhamDomain> danhsachsanpham = response.body();
                adapter = new ReycyclerViewAdapter(danhsachsanpham,khachhang);
                recyclerViewPopularList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<SanPhamDomain>> call, Throwable t) {
                Toast.makeText(TrangChu.this,"Show that bai", Toast.LENGTH_SHORT).show();

            }
        });
        
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView_danhmuc);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

//        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
//        categoryList.add(new CategoryDomain("Pizza", "cat_1"));
//        categoryList.add(new CategoryDomain("Burger", "cat_2"));
//        categoryList.add(new CategoryDomain("Hotdog", "cat_3"));
//        categoryList.add(new CategoryDomain("Drink", "cat_4"));
//        categoryList.add(new CategoryDomain("Dount", "cat_5"));

        ApiService.apiService.showcat().enqueue(new Callback<ArrayList<CategoryDomain>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryDomain>> call, Response<ArrayList<CategoryDomain>> response) {
                Toast.makeText(TrangChu.this,khachhang.toString(), Toast.LENGTH_SHORT).show();
                ArrayList<CategoryDomain> danhsachdanhmuc = response.body();
                adapter = new CategoryAdapter(danhsachdanhmuc,khachhang);
                recyclerViewCategoryList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryDomain>> call, Throwable t) {
                Toast.makeText(TrangChu.this,"Show that bai", Toast.LENGTH_SHORT).show();

            }
        });
    }


}