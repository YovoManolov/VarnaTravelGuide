package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

@Entity(tableName = "RESTAURANT",
        foreignKeys = @ForeignKey(entity = Place.class,
                parentColumns = "placeId",
                childColumns = "restaurantId"))
public class Restaurant {
    @PrimaryKey(autoGenerate = true)
    private long restaurantId ;

 /*   @Embedded
    private Place place ;
*/
    @TypeConverters(PriceCategory.class)
    @ColumnInfo(name = "PRICE_CATEGORY")
    private PriceCategory priceCategory;

    @ColumnInfo(name = "cousine")
    private String cousine;

    public Restaurant(  long restaurantId,
                        PriceCategory priceCategory,
                        String cousine) {

        this.restaurantId = restaurantId;
        this.priceCategory = priceCategory;
        this.cousine = cousine;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

  /*  public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
*/
    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCousine() {
        return cousine;
    }

    public void setCousine(String cousine) {
        this.cousine = cousine;
    }


    /*public static Restaurant[] populateRestaurants() {
        return new Restaurant[]{
                new Restaurant(
                         PriceCategory.MID_RANGE,
                        "Bulgarian, European and Mediterranean cuisine"),
                new Restaurant(new Place("Pizza Pizzarela",
                        "18 Vasil Petleshkov Street, 9010 Levski, Varna",
                        43.222125, 27.934123,
                        "Cell./Mob. : +359 87 877 0898 \n" +
                                "Website: http://pizzarelavarna.com/en\n",
                        "\n Affordable and delicious! \n" +
                                "SUITABLE FOR:\n" +
                                "   family meeting, first date, friends meeting," +
                                " birthday, non-smoker, name days, banquet," +
                                " private parties, business meeting, romance," +
                                " company parties, smokers, anniversaries, celebrations",
                        new WorkHours(null, "8:00 h - 23:00 h", "8:00 h - 23:00 h",
                        "8:00 h - 23:00 h")), PriceCategory.BUDGET,
                        "Cuisine: PIZZA, PASTA & WINE "),
                new Restaurant(new Place("Musala restaurant",
                        "Musala Street, 3, Varna",
                        43.202993, 27.914683,
                        "LL: +359 664 100  \n" +
                                "Cell./Mob. : +359 89 666 8992 \n" +
                                "e-mail: info@musalarestaurant.com\n" +
                                "Website: http://musalarestaurant.com/?lang=en\n",
                        "Musala Restaurant has a capacity of 40 seats." +
                                " The restaurant is suitable for business dinners, romantic" +
                                " dinners as well as for company or personal occasions. " +
                                " We create a cozy atmosphere that predisposes you to an" +
                                " unforgettable meeting with our exclusive cuisine.",new WorkHours(
                        -1, "24h: only café shop ,Restaurant: 10:00 h – 22:00 h"
                        , null, null))
                        , PriceCategory.PREMIUM,
                        "\n" +
                                " Cuisine: The dishes are a combination of modern cuisine" +
                                " with elements of the highest French cuisine and traditional" +
                                "Bulgarian dishes."),
                new Restaurant(new Place("La Mer Restaurant&Bar",
                        "zh.k.Chaika ,Street Boyan Bachvarov 152",
                        43.264810, 28.035492,
                        "Cell./Mob. :+35988 810 1909 \n" +
                                "Website: http://restaurant.la-mer.bg/ \n",
                        "\\n" +
                                " Refined starters, fine sausages and special cheeses," +
                                " high-quality meat specialties, delicious desserts " +
                                " and a variety of sweet temptations. Rich variety of" +
                                " incredible suggestions made with lots of love for the " +
                                " customer and attention to detail.",new WorkHours(
                        0, "08:00h - 23:00h", "08:00h - 23:00h",
                        "08:00h - 23:00h"))
                        , PriceCategory.PREMIUM,
                        "European cuisine"),
                new Restaurant(new Place("The Black Sheep Brewhouse",
                        "27 Tsar Simeon I Str., 9000 Varna Center, Varna",
                        43.206339, 27.920642,
                        "Cell./Mob. : +359 87 862 3426 \n" +
                                "Website: http://theblacksheep.bg/\n",
                        " Even the Czechs would come to The Black Sheep Brewhouse" +
                                " to drink really good Czech beer! Golden because it " +
                                " is golden in taste and Varna as the name of your city.",
                        new WorkHours(
                        0, "09:00h - 02:00h", " 09:00h - 02:00h",
                        " 09:00h - 02:00h"))
                        , PriceCategory.BUDGET,
                        "\n Cousine: european, american cuisine, sandwiches, cold, pancakes,"
                                + " cocktails, alcohol and beverages, draft beer," +
                                "fast food, desserts, burgers")
        ;}*/

}
