package com.example.yovo_user.varnatravelguide;


public class ListUrlLinksItem {

    private String heading;
    private String desc;
    private String url;
    private int imageURL;

    public ListUrlLinksItem(String heading, String desc, String url, int imageURL) {
        this.heading = heading;
        this.desc = desc;
        this.url = url;
        this.imageURL = imageURL;
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

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public static ListUrlLinksItem[] populatelistUrlLinksITems() {
        return new ListUrlLinksItem[]{
            new ListUrlLinksItem("Public transport",
                    "Information is brought to you by: www.varnatraffic.com",
                    "https://www.varnatraffic.com/en",
                    R.drawable.taxi
            ),
            new ListUrlLinksItem("Taxi information",
                   "Information is brought to you by: www.visit.varna.bg",
                   "http://visit.varna.bg/en",
                    R.drawable.varna_traffic
            )
        };
    }
}
