package com.example.yovo_user.varnatravelguide;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.text.method.ScrollingMovementMethod;
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
    private int arrayListPictureIterator = 1;

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
    private String getStringResourceByName(String aString) {
        String packageName = context.getPackageName();
        Resources res = context.getResources();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        return res.getString(resId);
    }

    @Override
    public Object instantiateItem(ViewGroup containter , int position){

        layoutInflater  = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_slider,null);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.imageCurrPosition);
        textView.setMovementMethod(new ScrollingMovementMethod());

        dbManager = new DBManager();


        if(getAreImagesFromResourceFolder()){
            imageView.setImageResource(images[position]);
            textView.setText(position +"/"+images.length);
        }else{
            Picasso picasso = Picasso.get();
            picasso.load(
                    (
                            getStringResourceByName("URL_prefix")
                            + imagesArrayList.get(position).getImageURL()
                    )).into(imageView);
            textView.setText(position +"/"+imagesArrayList.size());
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
