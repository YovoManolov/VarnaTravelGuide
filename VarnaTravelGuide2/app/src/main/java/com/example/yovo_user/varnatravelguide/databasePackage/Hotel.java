package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

@Entity(tableName = "HOTELS")
public class Hotel {
    @PrimaryKey(autoGenerate = true)
    private long id ;
    @Embedded
    private Place place ;
    @ColumnInfo(name = "NUMB_OF_STARS")
    private Integer numbOfStars;

    /** Status of the given task.
     * Enumerated Values: 0 (Active), 1 (Inactive), 2 (Completed)
     */
    @TypeConverters(PriceCategory.class)
    private PriceCategory priceCategory;

    public Hotel(
                         String name,
                         String address,
                         double latitude,
                         double longitude,
                         String contacts,
                         String description,
                         Integer is24h,
                         String monFri,
                         String sat,
                         String sun,
                         Integer numbOfStars,
                         PriceCategory priceCategory
                         ) {

        this.place = new Place(name,
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
        this.numbOfStars = numbOfStars;
        this.priceCategory = priceCategory;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Integer getNumbOfStars() {
        return numbOfStars;
    }

    //TODO make a validation for min and maks numb of stars
    public void setNumbOfStars(Integer numbOfStars) {
        this.numbOfStars = numbOfStars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public static Hotel[] populateHotels() {
        return new Hotel[] {
                //43.199070, 27.919569
                //LL - landline phone abbriviation
                new Hotel("Akopolis",
                         "13, Tsar Ivan Shishman Street, 9000 Varna Center",
                        43.217500, 27.958885,
                                "LL: 052 603 108  \n"+
                        "Cell./Mob. : +359 898 506 505 \n" +
                        "e-mail: hotel_acropolis@yahoo.com\n" +
                        "impuls_ood@abv.bg\n" +
                        "www.hotelacropolis.net\n",
                        "The family hotel \"Acropolis\" is situated in" +
                        " a quiet and peaceful place in the trade center of Varna," +
                        " in the most prestigious district of Varna - the Greek quarter." +
                        "It is located next to the the train station, " +
                        "Varna Custom house, 50 meters from the Varna" +
                        " beach and the beautiful Sea Garden.",
                        1,null,null,null,2,
                        PriceCategory.BUDGET
                ),

                new Hotel("Guesthouse Jana" ,
                        "137, Knyaz Boris I Blvd., 9010 Varna, Bulgaria",
                        43.199029,27.919575,
                        "LL: 052 335 237 \n"+
                                "Cell./Mob. : +359 888 445 989 \n"+
                                "e-mail: impuls_ood@abv.bg\n" +
                                "info@hotelacropolis.net\n" +
                                "http://www.villa-jana.com/\n",
                        "This hotel is located on the way to the Black Sea resort" +
                                " of Golden Sands and is situated in a quiet place in a" +
                                " residential area of Varna. It offers a large garden with " +
                                "barbecue area and panoramic views. Guests can make use of" +
                                " Villa Jana's shared kitchen, which is equipped with all" +
                                " necessary utensils and amenities. Free Wi-Fi is available " +
                                "in the entire building." ,
                        1,null,null,null,2, PriceCategory.BUDGET
                ),
                new Hotel("Villa Duchessa Varna",
                        "Varna 9010, Primorski Park, Saltanat 64",
                        43.2136639,43.213663,
                        "LL: 052 71 66 44 / 052 71 66 55 \n" +
                                " Cell./Mob. : 0887 42 44 83 \n"+
                                "e-mail: duc_varna@abv.bg\n" +
                                "http://hotelduchess.com/\n",
                        "Family hotel Villa Duchessa Varna enjoys a wonderful location" +
                                " in the Saltanat area - a continuation of the Sea Garden, sunken" +
                                " in greenery and tranquility and at the same time just a " +
                                "few minutes walk from the central commercial and administrative " +
                                "part of our biggest seaside town where you will find a wide" +
                                " variety of restaurants cafes and nightclubs. The sea is only" +
                                " a few meters away and is visible from the second and third" +
                                " floors of the villa.",
                        1,null,null,null,3, PriceCategory.MID_RANGE
                ),
                new Hotel("Villa Duchessa Varna",
                        "Varna 9010, Primorski Park, Saltanat 64",
                        43.2136639,43.213663,
                        "LL: 052 71 66 44 / 052 71 66 55 \n" +
                                " Cell./Mob. : 0887 42 44 83 \n"+
                                "e-mail: duc_varna@abv.bg\n" +
                                "http://hotelduchess.com/\n",
                        "Family hotel Villa Duchessa Varna enjoys a wonderful location" +
                                " in the Saltanat area - a continuation of the Sea Garden, sunken" +
                                " in greenery and tranquility and at the same time just a " +
                                "few minutes walk from the central commercial and administrative " +
                                "part of our biggest seaside town where you will find a wide" +
                                " variety of restaurants cafes and nightclubs. The sea is only" +
                                " a few meters away and is visible from the second and third" +
                                " floors of the villa.",
                        1,null,null,null,3, PriceCategory.MID_RANGE
                ),

                new Hotel("Hotel Boutique Splendid",
                        "Str. Bratia Shkorpil 30,\n" +
                                "Varna 9000, Bulgaria",
                        43.2136639,43.213663,
                        "LL: +359 52 681 414 \n" +
                                " Cell./Mob. : +359 88888 71 64\n"+
                                "e-mail: info@boutiquesplendid.net\n" +
                                "http://boutiquesplendid.net/en/\n",
                        "The Boutique Splendid hotel offers an excellent" +
                                " combination of comfort and retro luxury for your city " +
                                "break or business trip. It is conveniently located in " +
                                "the very center of Varna and it’s close to the financial " +
                                "district, transportation, the pedestrian shopping street, " +
                                "the Opera House and the Theatre of Varna. The first-class " +
                                "service, friendly and professional attitude of the staff" +
                                " will contribute to your pleasant stay in the heart" +
                                " of Varna City.",
                        1,null,null,null,4,
                        PriceCategory.MID_RANGE
                ),

                new Hotel("Rosslyn Dimyat Hotel",
                        "Varna 9010, Primorski Park, Saltanat 64",
                        43.2136639,43.213663,
                        "LL: +359 52 910 800 \n" +
                                "e-mail: bul.\"Knyaz Boris I\" 111,9002 Varna, Bulgaria\n" +
                                "http://dimyat.rosslyn-hotels.com\n",
                        "With a central location and next to the sea garden," +
                                " Rosslyn Dimyat Hotel is ideal to make the most of Your" +
                                " stay in Varna, whether on a business or a city break visit." +
                                " The hotel provides 95 stylish rooms and suites, a restaurant," +
                                " lobby bar, three conference rooms and a ballroom. Guests can" +
                                " also indulge in Maya Vita center featuring a range of" +
                                " treatments, sauna park and an indoor swimming pool.",
                        1,null,null,null,5, PriceCategory.PREMIUM
                )
        };
    }
}
