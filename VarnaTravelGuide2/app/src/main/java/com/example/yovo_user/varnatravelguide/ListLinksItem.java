package com.example.yovo_user.varnatravelguide;


import android.graphics.drawable.Drawable;

import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListLinksItem {

    private int placeId;
    private String heading;
    private String desc;
    private String url;
    private int image;
    private Drawable imageDrawable;

    public ListLinksItem(){}

    public ListLinksItem(String heading, String desc, String url, int image) {
        this.heading = heading;
        this.desc = desc;
        this.url = url;
        this.image = image;
    }

    public ListLinksItem(int placeId, String heading, String desc , Drawable imageDrawable) {
        this.placeId = placeId;
        this.heading = heading;
        this.desc = desc;
        this.imageDrawable = imageDrawable;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public static ListLinksItem[] populatelistUrlLinksITems() {
        return new ListLinksItem[]{
            new ListLinksItem("Public transport",
                    "Information is brought to you by: www.varnatraffic.com",
                    "https://www.varnatraffic.com/en",
                    R.drawable.varna_traffic
            ),
            new ListLinksItem("Taxi information",
                   "Information is brought to you by: www.visit.varna.bg",
                   "http://www.visit.varna.bg/en/transport/preview/92.html",
                    R.drawable.taxi
            )
        };
    }

}
