package com.myfistapp.sunshine_app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.myfistapp.sunshine_app.R;

public class ViewPageAdapter extends PagerAdapter {
    Context context;
    int images[]={
            R.raw.anim_personchoose,
            R.raw.anim_mobile_food_choose,
            R.raw.anim_pan_food,
            R.raw.anim_deliverfood,
    };
    int heading[]={
            R.string.heading1,
            R.string.heading2,
            R.string.heading3,
            R.string.heading4,
    };


    public ViewPageAdapter(Context context){
        this.context=context;

    }
    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container, false);
        LottieAnimationView slidetitlelottie = (LottieAnimationView) view.findViewById(R.id.titleImage);
        TextView slideheading = (TextView) view.findViewById(R.id.texttitle);
        slidetitlelottie.setAnimation(images[position]);
        slideheading.setText(heading[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((LinearLayout)object);
    }
}
