package com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage;

import org.bson.Document;
import org.bson.types.ObjectId;

public class WorkHours {
    private ObjectId _id;
   // private Object placeId;
    private boolean is24h;
    private String monFri;
    private String sat;
    private String sun;

    public WorkHours(ObjectId _id,
                     boolean is24h, String monFri,
                     String sat, String sun) {
        this._id = _id;
        this.is24h = is24h;
        this.monFri = monFri;
        this.sat = sat;
        this.sun = sun;
    }

    public WorkHours(final Document document) {
        _id = document.getObjectId("_id");
        is24h = document.getBoolean("is_24H");
        monFri = document.getString("mond_fird");
        sat = document.getString("sat");
        sun = document.getString("sun");
    }

    public String getMonFri() {
        return monFri;
    }

    public void setMonFri(String monFri) {
        this.monFri = monFri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public boolean isIs24h() {
        return is24h;
    }

    public void setIs24h(boolean is24h) {
        this.is24h = is24h;
    }

    @Override
    public String toString() {
        if(is24h == true){
            return " 24 hours open";
        }else{
            return " Monday- Friday: " + monFri
                    +"\n Saturday: " + sat
                    +"\n Sun: " + sun;
        }
    }

    //INFO: WHEN is24h is -1 ake info from monFri field
/*    public static WorkHours[] populateWorkHours() {
        return new WorkHours[]{
                //RESTAURANTS

                //Complex Valsheben Izvor Devnia
                new WorkHours(1,0, "12:00 h - 24:00 h", "12:00 h - 24:00 h", "12:00 h - 24:00 h"),
                //Pizza Pizzarela
                new WorkHours(2,0, "8:00 h - 23:00 h", "8:00 h - 23:00 h", "8:00 h - 23:00 h"),
                //Musala restaurant
                new WorkHours(3,-1, "24h: only café shop ,Restaurant: 10:00 h – 22:00 h", null,null),
                //La Mer Restaurant&Bar
                new WorkHours( 4,0, "08:00h - 23:00h", "08:00h - 23:00h", "08:00h - 23:00h"),
                //The Black Sheep Brewhouse
                new WorkHours(5,0, "09:00h - 02:00h", " 09:00h - 02:00h"," 09:00h - 02:00h"),


                //SHOPPING PLACES


                //Grand Mall Varna
                new WorkHours(6,0, "10:00h - 22:00h", "10:00h - 22:00h","10:00h - 22:00h"),
                //LOLLIPOP
                new WorkHours(7,0, "11:00h - 19:00h","11:00h - 19:00h","11:00h - 19:00h"),
                //Picadilly Park
                new WorkHours(8,0, "08:00h - 22:00h", "08:00h - 22:00h", "08:00h - 22:00h"),
                //Retail Park Varna
                new WorkHours(9, 0, "10:00h - 21:00h", "10:00h - 21:00h", "10:00h - 21:00h"),
                //Tina Art Gallery
                new WorkHours( 10,0  ,"10:00h - 19:00h","10:00h - 19:00h",null),

                //HOTELS

                //Akopolis
                new WorkHours(11,1, null, null, null),
                //Guesthouse Jana
                new WorkHours(12,1, null, null, null),
                //Villa Duchessa Varna
                new WorkHours(13,1, null, null, null),
                //Hotel Boutique Splendid
                new WorkHours(14,1, null, null, null),
                //Rosslyn Dimyat Hotel
                new WorkHours(15,1, null, null, null),


                //--LANDMARKS

                // Stone Forest
                new WorkHours(16,-1,"10 a.m. - 5 p.m", null, null),

                //Patleina monastery
                new WorkHours(17,-1,"Hours: Objects can be visited by tourists, " +
                        "after a preliminary inquiry ", null, null),
                //Palace of Omurtag
                new WorkHours(18,1, null,null, null),

                //Madara Rider
                new WorkHours(19,-1,"summer: 8:00h – 19:00h "+
                        "\n winter: 8:00h – 17:00h", null, null),

                //Regional History Museum of Varn
                new WorkHours(20,-1,"10.00 h - 17.00 h Почивни дни:"+
                        " неделя и понеделник",null, null)
        };
    }*/
}
