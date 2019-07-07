package com.example.yovo_user.varnatravelguide;


import android.graphics.drawable.Drawable;

import org.bson.types.ObjectId;

public class ListLinksItem {

    private ObjectId placeId;
    private String heading;
    private String desc;
    private String imageUrl;
    private int image;
    private Drawable imageDrawable;

    public ListLinksItem(){}

    public ListLinksItem(String heading, String desc , String imageUrl,int imageDrawable) {
        this.heading = heading;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.image = imageDrawable;
    }

    public ListLinksItem(ObjectId placeId, String heading, String desc , String imageUrl) {
        this.placeId = placeId;
        this.heading = heading;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    public ObjectId getPlaceId() {
        return placeId;
    }

    public void setPlaceId(ObjectId placeId) {
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
