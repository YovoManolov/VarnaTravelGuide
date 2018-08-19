package com.example.yovo_user.varnatravelguide;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    static {  AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); }

    private Integer[] images = {
                        R.drawable.varna_tour_logo,
                        R.drawable.varna_center,
                        R.drawable.varna_sky_view,
                        R.drawable.varna_coastline
    };

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup containter , int position){
        layoutInflater  = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_slider,null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.imageCurrPosition);

        imageView.setImageResource(images[position]);
        textView.setText(position +"/"+images.length);

        ViewPager vp = (ViewPager) containter;
        vp.addView(view, 0);
        return view;
    }

    public void destroyItem(ViewGroup container,int  postition,Object object){
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

}
