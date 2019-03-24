package com.example.yovo_user.varnatravelguide;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yovo_user.varnatravelguide.databasePackage.DBManager;
import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private boolean areImagesFromResourceFolder;
    private DBManager dbManager;

    static {  AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); }

    private Integer[] images;
    private ArrayList<Image> imagesArrayList;

    public ViewPagerAdapter(Context context,Integer[] images){
        this.context = context;
        this.images = images;
        setAreImagesFromResourceFolder(true);
    }

    public ViewPagerAdapter(Context context,ArrayList<Image> imagesArrayList){
        this.context = context;
        this.imagesArrayList = imagesArrayList;
        setAreImagesFromResourceFolder(false);
    }

    @Override
    public int getCount() {
        if(getAreImagesFromResourceFolder() == false){
            return imagesArrayList.size();
        }else{
            return images.length;
        }
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
        dbManager = new DBManager();
        boolean isInitialized = false;


        if(getAreImagesFromResourceFolder()){
            imageView.setImageResource(images[position]);
            textView.setText(position +"/"+images.length);
        }else{
            ImageView helperView = null;
            if(!isInitialized){
                for(int i = 0 ;i < imagesArrayList.size(); i++){
                    Picasso picasso = Picasso.get();
                    picasso.load(imagesArrayList.get(i).getImageURL()).into(imageView);
                    textView.setText(position +"/"+i);
                }
            }
            isInitialized = true;
        }

        ViewPager vp = (ViewPager) containter;
        vp.addView(view, 0);
        return view;
    }

    public void destroyItem(ViewGroup container,int  postition,Object object){
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public boolean getAreImagesFromResourceFolder() {
        return areImagesFromResourceFolder;
    }

    public void setAreImagesFromResourceFolder(boolean areImagesFromResourceFolder) {
        this.areImagesFromResourceFolder = areImagesFromResourceFolder;
    }
}
