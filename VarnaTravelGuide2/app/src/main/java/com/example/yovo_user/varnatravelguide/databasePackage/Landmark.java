package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "LANDMARK")
public class Landmark {

    @PrimaryKey(autoGenerate = true)
    private long id ;
    @Embedded
    private Place place ;
    @ColumnInfo(name = "entranceTicket")
    private String entranceTicket;

    public Landmark(   String name,
                       String address,
                       double latitude,
                       double longitude,
                       String contacts,
                       String description,
                       Integer is24h,
                       String monFri,
                       String sat,
                       String sun,
                       String entranceTicket) {

        this.place = new Place(
                name,
                address,
                latitude,
                longitude,
                contacts,
                description,
                is24h,
                monFri,
                sat,
                sun
        );
        this.entranceTicket = entranceTicket;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEntranceTicket() {
        return entranceTicket;
    }

    public void setEntranceTicket(String entranceTicket) {
        this.entranceTicket = entranceTicket;
    }


                     /* String name,
                       String address,
                       double latitude,
                       double longitude,
                       String contacts,
                       String description,
                       Integer is24h,
                       String monFri,
                       String sat,
                       String sun,
                       double entranceTicket*/
    public static Landmark[] populateLandmarks() {
        return new Landmark[] {
               new Landmark(
                    "Stone Forest",
                    "Location:\tVarna Province, Bulgaria\n" +
                            "Nearest city:\tAksakovo",
                    43.225932, 27.706441,
                    "LL: + 359 52 618011",
                    "Natural phenomenon “Stone Forest” is better known with " +
                            "its Bulgarian name “Pobiti kamani” which could be " +
                            "translated as “stones beaten into the ground”. Seven" +
                            " large and several separate small groups are located on " +
                            "the north and south from the Beloslav Lake. These are" +
                            " numerous limestone pillars as high as 10 m, hollow or" +
                            " solid cylinders, truncated cones and single rocks and" +
                            " cliffs.",0,
                    "10 a.m. - 5 p.m",null,null,
                    "Adults: 3.00 leva\n" +
                            " Children/Students: 2.00 leva" ),
                new Landmark(
                        "Patleina monastery",
                        " Patleina reserve, " +
                                "about 8 km from Veliki Preslav",
                        43.134077, 26.818260,
                        "Cell./Mob. : 00359888986935 - Archpriest N. Nikolov \n",
                        "It is the successor of St. Tsar Boris`s medieval monastery " +
                                "in the historic area Patleina. Housed in an impressive" +
                                " 70 years of building, not far from the ruins of the old " +
                                "monastery , where was discovered the world-famous ceramic" +
                                " icon \"St. Theodore\" ",
                        null,
                        "Hours: Objects can be visited by tourists, " +
                                "after a preliminary inquiry ",
                        null,null,
                        "Information not available. " ),
                new Landmark(
                        "Palace of Omurtag",
                        "Location: near the village of Han Krum in Shumen Province. ",
                        43.184499262, 26.892329764,
                        "LL: + 359 52 618011",
                        "The Palace of Omurtag or Aul (Aulē) of Omurtag " +
                                "(Bulgarian: Аул на Омуртаг, Aul na Omurtag) is an " +
                                "archaeological site in northeastern Bulgaria dating to" +
                                " Late Antiquity and the Early Middle Ages located near" +
                                " the village of Han Krum in Shumen Province. The site" +
                                " has been pinpointed as the location of a fort and palace " +
                                "of Omurtag, ruler (kanasybigi) of the First Bulgarian Empire " +
                                "in 815–831, as mentioned in the Chatalar Inscription of 822."
                        ,1,
                        null,null,null,
                        "No ticket required "),
                new Landmark(
                        "Madara Rider",
                        "Location:\t east of Shumen in northeastern Bulgaria," +
                                " near the village of Madara.\n" +
                                "Nearest city:\t Aksakovo",
                        43.225932, 27.706441,
                        " +359 5313 20 95",
                        "Natural phenomenon “Stone Forest” is better known with " +
                                "its Bulgarian name “Pobiti kamani” which could be " +
                                "translated as “stones beaten into the ground”. Seven" +
                                " large and several separate small groups are located on " +
                                "the north and south from the Beloslav Lake. These are" +
                                " numerous limestone pillars as high as 10 m, hollow or" +
                                " solid cylinders, truncated cones and single rocks and" +
                                " cliffs.",0,
                        "summer: 8:00h – 19:00h \n winter: 8:00h – 17:00h",
                        null, null,
                        "аdults: 4.00 leva\n" +
                                "students: 1.00 leva\n"+
                                "organized groups: 0.50 leva \n"+
                                "lecture: 4.00 leva"),
                new Landmark(
                        "Regional History Museum of Varna",
                        "9000 Varna, 41 Maria Luisa Blvd" +
                                "Nearest city:\tAksakovo",
                        43.207054, 27.914544,
                        "LL: + 359 52 618011",
                        "Natural phenomenon “Stone Forest” is better known with " +
                                "its Bulgarian name “Pobiti kamani” which could be " +
                                "translated as “stones beaten into the ground”. Seven" +
                                " large and several separate small groups are located on " +
                                "the north and south from the Beloslav Lake. These are" +
                                " numerous limestone pillars as high as 10 m, hollow or" +
                                " solid cylinders, truncated cones and single rocks and" +
                                " cliffs.",0,
                        "10.00 h - 17.00 h\n" +
                                "\n Почивни дни: неделя и понеделник",null,null,
                        "Adults:  10.00  leva\n" +
                                " Children/Students: 2.00 leva")
        };
    }
}
