package com.example.yovo_user.varnatravelguide;

public class ListLinksItem {

    private String heading;
    private String desc;
    private String url;
    private String imageURL;

    public ListLinksItem(String heading, String desc, String url, String imageURL) {
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
