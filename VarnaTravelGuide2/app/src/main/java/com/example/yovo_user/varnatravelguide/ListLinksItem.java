package com.example.yovo_user.varnatravelguide;

import android.graphics.drawable.Drawable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class ListLinksItem {

    private String placeId;
    private String heading;
    private String desc;
    private String imageUrl;
    private int image;
    private Drawable imageDrawable;

    public ListLinksItem(String heading, String desc , String imageUrl,int imageDrawable) {
        this.heading = heading;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.image = imageDrawable;
    }

    public ListLinksItem(String placeId, String heading, String desc , String imageUrl) {
        this.placeId = placeId;
        this.heading = heading;
        this.desc = desc;
        this.imageUrl = imageUrl;
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
