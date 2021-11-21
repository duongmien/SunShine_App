package com.myfistapp.sunshine_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myfistapp.sunshine_app.Adapter.PhotoViewPagerAdapter;
import com.myfistapp.sunshine_app.Adapter.ReycyclerViewAdapter;
import com.myfistapp.sunshine_app.Api.ApiService;
import com.myfistapp.sunshine_app.Class.Photo;
import com.myfistapp.sunshine_app.Class.SanPhamDomain;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.Model.Sanpham;
import com.myfistapp.sunshine_app.R;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu extends AppCompatActivity {
    private RecyclerView.Adapter  adapter;
    private RecyclerView  recyclerViewPopularList;
    private Khachhang khachhang;
    //Khai báo botomNavigation
    private BottomNavigationView bottomNavigationView;

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
        setContentView(R.layout.activity_trang_chu);

        Bundle bundleRecevie = getIntent().getExtras();
        if(bundleRecevie!=null){
            khachhang = (Khachhang) bundleRecevie.get("object_user");
        }
        //Ánh xạ bottomNavigation

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


//        danhsachsanpham.add(new SanPhamDomain("Salad Trứng","img_favorite_food_1","Salad trứng thơm ngon với các nguyên liệu organic phù hợp với chế độ ăn kiêng ít dầu mỡ. Thành phần: xà lách, dầu ô liu, trứng, vừng...","30","328","79000","4.9"));
//        danhsachsanpham.add(new SanPhamDomain("Mì Ý Rigatoni","img_favorite_food_2","Salad trứng thơm ngon với các nguyên liệu organic phù hợp với chế độ ăn kiêng ít dầu mỡ. Thành phần: xà lách, dầu ô liu, trứng, vừng...","20","111","98000","4.0"));
//        danhsachsanpham.add(new SanPhamDomain("Chicken Hamburger","img_favorite_food_3","Salad trứng thơm ngon với các nguyên liệu organic phù hợp với chế độ ăn kiêng ít dầu mỡ. Thành phần: xà lách, dầu ô liu, trứng, vừng...","15","33","45000","4.9"));
//        danhsachsanpham.add(new SanPhamDomain("Salad Cá Hồi","img_favorite_food_4","Salad trứng thơm ngon với các nguyên liệu organic phù hợp với chế độ ăn kiêng ít dầu mỡ. Thành phần: xà lách, dầu ô liu, trứng, vừng...","35","123","23000","5"));
//        danhsachsanpham.add(new SanPhamDomain("Bánh Muffin","img_favorite_food_5","Salad trứng thơm ngon với các nguyên liệu organic phù hợp với chế độ ăn kiêng ít dầu mỡ. Thành phần: xà lách, dầu ô liu, trứng, vừng...","25","532","41000","3.9"));
//        danhsachsanpham.add(new SanPhamDomain("Nước ép dưa hấu","img_favorite_food_6","Salad trứng thơm ngon với các nguyên liệu organic phù hợp với chế độ ăn kiêng ít dầu mỡ. Thành phần: xà lách, dầu ô liu, trứng, vừng...","10","123","12000","4.9"));
//        danhsachsanpham.add(new SanPhamDomain("Trái cây mix","img_favorite_food_7","Salad trứng thơm ngon với các nguyên liệu organic phù hợp với chế độ ăn kiêng ít dầu mỡ. Thành phần: xà lách, dầu ô liu, trứng, vừng...","30","112","44000","4.9"));
//        danhsachsanpham.add(new SanPhamDomain("Nghêu sốt bơ tỏi","img_favorite_food_8","Salad trứng thơm ngon với các nguyên liệu organic phù hợp với chế độ ăn kiêng ít dầu mỡ. Thành phần: xà lách, dầu ô liu, trứng, vừng...","20","423","22000","5"));

        ApiService.apiService.showitem().enqueue(new Callback<ArrayList<SanPhamDomain>>() {
            @Override
            public void onResponse(Call<ArrayList<SanPhamDomain>> call, Response<ArrayList<SanPhamDomain>> response) {
                Toast.makeText(TrangChu.this,"Show thanh cong", Toast.LENGTH_SHORT).show();
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


}