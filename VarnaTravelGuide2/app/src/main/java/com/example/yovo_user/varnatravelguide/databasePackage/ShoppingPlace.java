package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

@Entity(tableName = "SHOPING_PLACES")
public class ShoppingPlace {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @Embedded
    private Place place;

    @TypeConverters(PriceCategory.class)
    private PriceCategory priceCategory;

    public ShoppingPlace(String name,
                         String address,
                         double latitude,
                         double longitude,
                         String contacts,
                         String description,
                         Integer is24h,
                         String monFri,
                         String sat,
                         String sun,
                         PriceCategory priceCategory) {

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
        this.priceCategory = priceCategory;
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

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public static ShoppingPlace[] populateShoppingPlaces() {
        return new ShoppingPlace[]{
                //43.199070, 27.919569
                //LL - landline phone abbriviation
                new ShoppingPlace("Grand Mall Varna",
                        "\"Akademik Andrey Sakharov\" 2, 9000 Varna",
                        43.217475, 27.898588,
                        "Cell./Mob. :  0700 33 939\n" +
                                "e-mail: information@grandmall-varna.com\n" +
                                "Facebook: www.facebook.com/GrandMallVarna\n" +
                                "http://www.grandmall-varna.com/en/\n",
                        "\n" +
                                "Grand Mall is the largest and most visited shopping " +
                                "center in Varna. Its extensive bright spaces stretch " +
                                "over 150,000 square meters of total built-up area," +
                                " retail space is over 50,000 square meters, and " +
                                "stores - more than 220. The convenience of visitors" +
                                "provides over 1,700 parking spaces.",
                        0, "10:00h - 22:00h",
                        "10:00h - 22:00h",
                        "10:00h - 22:00h",
                        PriceCategory.COMBINED
                ),
                new ShoppingPlace("LOLLIPOP",
                        "V36, Knyaz Boris I bul., Varna, Bulgaria, 9000",
                        43.204173, 27.917980,
                        "Cell./Mob. : +359 877 133 757\n" +
                                "e-mail: info@lolandpop.bg\n" +
                                "Facebook: https://www.facebook.com/lolandpopbg\n" +
                                "https://lolandpop.bg/\n",
                        "\n" +
                                "\n" +
                                "   Confectionery LOLLIPOP Bulgaria - is the first workshop " +
                                "for handmade candies from natural ingredients in Bulgaria!\n" +
                                "\n" +
                                "   Our candies are made from white sugar with added natural" +
                                " colors and flavors also do not contain gluten.",
                        0, "11:00h - 19:00h",
                        "11:00h - 19:00h",
                        "11:00h - 19:00h",
                        PriceCategory.MID_RANGE
                ),
                new ShoppingPlace("Picadilly Park",
                        "Varna, bul. Primorski park II 482",
                        43.215300, 27.954959,
                        "LL :  052 385 466\n" +
                                "e-mail: information@grandmall-varna.com\n" +
                                "Facebook: https://www.facebook.com/PikadiliPark\n",
                        "\n" +
                                "   The building is a retail complex on separate " +
                                "levels - includes: Piccadilly shop, restaurant, " +
                                "bank office, alcohol store, M-tel shop, souvenir" +
                                "shops, travel agency, optics, Technomarket" +
                                "store, fitness center, solarium , a beauty " +
                                "center, a panorama bar and more.",
                        0, "08:00h - 22:00h",
                        "08:00h - 22:00h",
                        "08:00h - 22:00h",
                        PriceCategory.COMBINED
                ),
                new ShoppingPlace("Retail Park Varna",
                        "Blvd. \"Vladislav Varnenchik\" 310, 9009 West Industrial Zone, Varna",
                        43.223435, 27.872933,
                        "Cell./Mob. :  0700 33 939\n" +
                                "e-mail: information@grandmall-varna.com\n" +
                                "Facebook: www.facebook.com/GrandMallVarna\n" +
                                "http://www.grandmall-varna.com/en/\n",
                        "\n" +
                                "Retail Park Varna is the first of its kind in Varna and " +
                                "the Northeastern part of Bulgaria. It has been established" +
                                " as the ultimate shopping destination, featuring some of " +
                                "the most prestigious local and international brands in the " +
                                "furniture, sports, and toys segments. The project is anchored " +
                                "by Decathlon sports shop with its single location in Varna," +
                                " the only IKEA in Eastern Bulgaria, introducing a unique " +
                                "store concept (IKEA Order and Collection Point) and " +
                                "Comsed toy store. Retail Park Varna also features " +
                                "a Gallery with representatives of the houseware industry," +
                                "which complement the retail concept. ",
                        0, "10:00h - 21:00h",
                        "10:00h - 21:00h",
                        "10:00h - 21:00h",
                        PriceCategory.COMBINED
                ),
                new ShoppingPlace("Tina Art Gallery",
                        "25 Vasil Drumev str 9010 Varna, Bulgaria",
                        43.210998, 27.926977,
                        "Cell./Mob. : 088 856 8023\n" +
                                "e-mail: tinaartbg@gmail.com\n" +
                                "Facebook: https://www.facebook.com/pg/TinaArtShop\n" +
                                "http://www.grandmall-varna.com/en/\n",
                        "\n" +
                                "  Art Gallery Tina Art offers paintings, small sculpture," +
                                "original silver jewelry, ceramics, artistic ceramics, framing." +
                                "  Oil on canvas paintigs from leading Varna's artists , " +
                                "unique silver jewelry , apply arts .We will deliver your" +
                                " order in Europe .\n ",
                        0, "10:00h - 19:00h",
                        "10:00h - 19:00h",
                        "closed",
                        PriceCategory.COMBINED
                )

        };
    }
}
