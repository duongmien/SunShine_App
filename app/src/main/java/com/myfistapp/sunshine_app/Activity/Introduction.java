package com.myfistapp.sunshine_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myfistapp.sunshine_app.R;
import com.myfistapp.sunshine_app.Adapter.ViewPageAdapter;

public class Introduction extends AppCompatActivity {
    ViewPager mSliderViewPager;
    LinearLayout mDotLayout;
    Button backbtn, nextbtn, skipbtn;
    TextView[] dots;

    ViewPageAdapter viewPageAdapter= new ViewPageAdapter(this);

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(mSliderViewPager.getCurrentItem()==3){
            Intent i = new Intent(Introduction.this, Splash.class);
                startActivity(i);
                finish();
            }else {
                mSliderViewPager.setCurrentItem(mSliderViewPager.getCurrentItem() + 1, true);
            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        skipbtn=findViewById(R.id.skipbtn);
        //        Ghi nhớ đăng nhập
        SharedPreferences preferences= getSharedPreferences("checkbox",MODE_PRIVATE);
        String state_checkbox= preferences.getString("remember","");
        if(state_checkbox.equals("true")){
            Intent intent= new Intent(Introduction.this,TrangChu.class);
            startActivity(intent);
        }else if (state_checkbox.equals("false")){
            Toast.makeText(this,"Please Sign in",Toast.LENGTH_SHORT).show();
        }

        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Introduction.this, Splash.class);
                startActivity(i);
                finish();
            }
        });

        mSliderViewPager=(ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout=(LinearLayout) findViewById(R.id.indicator_layout);

        mSliderViewPager.setAdapter(viewPageAdapter);
        setUpindicator(0);
//        Tự động di chuyển slide
        mSliderViewPager.addOnPageChangeListener(viewlistener);
        handler.postDelayed(runnable,3000);

    }
    public void setUpindicator(int position){
        dots = new TextView[4];
        mDotLayout.removeAllViews();
        for(int i=0; i<dots.length; i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.xam));
            mDotLayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.black));
    }
    ViewPager.OnPageChangeListener viewlistener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable,3000);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getItem(int i){
        return mSliderViewPager.getCurrentItem()+i;
    }
    }
