package com.example.yovo_user.varnatravelguide;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {
                        R.drawable.VARNA_TUR_LOGO,R.drawable.varna_skyView,
                        R.drawable.varna_center,R.drawable.varna_coastline};

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem() {
        return instantiateItem(, );
    }

    @Override
    public Object instantiateItem(ViewGroup containter , int position){
        layoutInflater  = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate()
        return null;
    }

}
