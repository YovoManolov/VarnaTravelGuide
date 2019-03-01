package com.example.yovo_user.varnatravelguide.databasePackage.placePackage;



import com.example.yovo_user.varnatravelguide.databasePackage.imagePackage.Image;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Place {

    private ObjectId _id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String contacts;
    private String description;
    private ArrayList<Image> images;
    public Place(){};
    public Place(String name, String address,
                      double latitude, double longitude, String contacts,
                      String description) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contacts = contacts;
        this.description = description;
    }

    public Place(final Document document) {

        _id = document.getObjectId("_id");
        name = document.getString("name");
        address = document.getString("address");
        latitude = document.getDouble("latitude");
        longitude = document.getDouble("longitude");
        contacts = document.getString("contacts");
        description = document.getString("description");
        images = Image.convertDocsToImages((ArrayList<Document>) document.get("images"));
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

   /* public static Place[] populatePlaces() {
        return new Place[]{
                //restaurants
                new Place("Complex Valsheben Izvor Devnia",
                        "Quarter Devnya River, Devnya River, 9160 Devnya",
                        43.230801, 27.588384,
                        "Cell./Mob. : +359 88 808 8120 \n" +
                                "Website: http://valshebenizvor.com\n",
                        "\nTavern \"Vulsheben Izvor\" is located at Vulsheben" +
                                " Izvor 22 in Devnya. Master chefs take care of the" +
                                " preparation of dishes from the Bulgarian, European and" +
                                " Mediterranean cuisine. The restaurant has a garden with" +
                                " century-old trees and a children's playground. Payments" ),
                new Place("Pizza Pizzarela",
                        "18 Vasil Petleshkov Street, 9010 Levski, Varna",
                        43.222125, 27.934123,
                        "Cell./Mob. : +359 87 877 0898 \n" +
                                "Website: http://pizzarelavarna.com/en\n",
                        "\n Affordable and delicious! \n" +
                                "SUITABLE FOR:\n" +
                                "   family meeting, first date, friends meeting," +
                                " birthday, non-smoker, name days, banquet," +
                                " private parties, business meeting, romance," +
                                " company parties, smokers, anniversaries, celebrations"
                        ),
                new Place("Musala restaurant",
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
                                " unforgettable meeting with our exclusive cuisine."),
                new Place("La Mer Restaurant&Bar",
                        "zh.k.Chaika ,Street Boyan Bachvarov 152",
                        43.264810, 28.035492,
                        "Cell./Mob. :+35988 810 1909 \n" +
                                "Website: http://restaurant.la-mer.bg/ \n",
                        "\\n" +
                                " Refined starters, fine sausages and special cheeses," +
                                " high-quality meat specialties, delicious desserts " +
                                " and a variety of sweet temptations. Rich variety of" +
                                " incredible suggestions made with lots of love for the " +
                                " customer and attention to detail."),
                new Place("The Black Sheep Brewhouse",
                        "27 Tsar Simeon I Str., 9000 Varna Center, Varna",
                        43.206339, 27.920642,
                        "Cell./Mob. : +359 87 862 3426 \n" +
                                "Website: http://theblacksheep.bg/\n",
                        " Even the Czechs would come to The Black Sheep Brewhouse" +
                                " to drink really good Czech beer! Golden because it " +
                                " is golden in taste and Varna as the name of your city."
                        ),

                //shopping places
                new Place("Grand Mall Varna",
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
                                "provides over 1,700 parking spaces."),
                new Place("LOLLIPOP",
                        "V36, Knyaz Boris I bul., Varna, Bulgaria, 9000",
                        43.204173, 27.917980,
                        "Cell./Mob. : +359 877 133 757\n" +
                                "e-mail: info@lolandpop.bg\n" +
                                "Facebook: https://www.facebook.com/lolandpopbg\n" +
                                "https://lolandpop.bg/\n",
                        "\n" +
                                "   Confectionery LOLLIPOP Bulgaria - is the first workshop " +
                                "for handmade candies from natural ingredients in Bulgaria!\n" +
                                "\n" +
                                "   Our candies are made from white sugar with added natural" +
                                " colors and flavors also do not contain gluten."),
                new Place("Picadilly Park",
                        "Varna, bul. Primorski park II 482",
                        43.215300, 27.954959,
                        "LL :  052 385 466\n" +
                                "e-mail: office.park@piccadilly.bg\n" +
                                "Facebook: https://www.facebook.com/PikadiliPark\n",
                        "\n" +
                                "   The building is a retail complex on separate " +
                                "levels - includes: Piccadilly shop, restaurant, " +
                                "bank office, alcohol store, M-tel shop, souvenir" +
                                "shops, travel agency, optics, Technomarket" +
                                "store, fitness center, solarium , a beauty " +
                                "center, a panorama bar and more."),
                new Place("Retail Park Varna",
                        "Blvd. \"Vladislav Varnenchik\" 310, 9009 West Industrial Zone, Varna",
                        43.223435, 27.872933,
                        "\n",
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
                                "which complement the retail concept. "),
                new Place("Tina Art Gallery",
                        "25 Vasil Drumev str 9010 Varna, Bulgaria",
                        43.210998, 27.926977,
                        "Cell./Mob. : 088 856 8023\n" +
                                "e-mail: tinaartbg@gmail.com\n" +
                                "Facebook: https://www.facebook.com/pg/TinaArtShop\n",
                        "\n" +
                                "  Art Gallery Tina Art offers paintings, small sculpture," +
                                "original silver jewelry, ceramics, artistic ceramics, framing." +
                                "  Oil on canvas paintigs from leading Varna's artists , " +
                                "unique silver jewelry , apply arts .We will deliver your" +
                                " order in Europe .\n "
                        ),

                //Hotels
                new Place("Akopolis",
                        "13, Tsar Ivan Shishman Street, 9000 Varna Center",
                        43.217500, 27.958885,
                        "LL: 052 603 108  \n" +
                                "Cell./Mob. : +359 898 506 505 \n" +
                                "e-mail: hotel_acropolis@yahoo.com\n" +
                                "www.hotelacropolis.net\n",
                        "The family hotel \"Acropolis\" is situated in" +
                                " a quiet and peaceful place in the trade center of Varna," +
                                " in the most prestigious district of Varna - the Greek quarter." +
                                "It is located next to the the train station, " +
                                "Varna Custom house, 50 meters from the Varna" +
                                " beach and the beautiful Sea Garden."),
                new Place("Guesthouse Jana",
                        "137, Knyaz Boris I Blvd., 9010 Varna, Bulgaria",
                        43.199029, 27.919575,
                        "LL: 052 335 237 \n" +
                                "Cell./Mob. : +359 888 445 989 \n" +
                                "e-mail: impuls_ood@abv.bg\n"
                                "http://www.villa-jana.com/\n",
                        "This hotel is located on the way to the Black Sea resort" +
                                " of Golden Sands and is situated in a quiet place in a" +
                                " residential area of Varna. It offers a large garden with " +
                                "barbecue area and panoramic views. Guests can make use of" +
                                " Villa Jana's shared kitchen, which is equipped with all" +
                                " necessary utensils and amenities. Free Wi-Fi is available " +
                                "in the entire building." ),
                new Place("Villa Duchessa Varna",
                        "Varna 9010, Primorski Park, Saltanat 64",
                        43.214039, 27.943327,
                        "LL: 052 71 66 44 / 052 71 66 55 \n" +
                                " Cell./Mob. : 0887 42 44 83 \n" +
                                "e-mail: duc_varna@abv.bg\n" +
                                "http://hotelduchess.com/\n",
                        "Family hotel Villa Duchessa Varna enjoys a wonderful location" +
                                " in the Saltanat area - a continuation of the Sea Garden, sunken" +
                                " in greenery and tranquility and at the same time just a " +
                                "few minutes walk from the central commercial and administrative " +
                                "part of our biggest seaside town where you will find a wide" +
                                " variety of restaurants cafes and nightclubs. The sea is only" +
                                " a few meters away and is visible from the second and third" +
                                " floors of the villa."),

                new Place("Hotel Boutique Splendid",
                        "Str. Bratia Shkorpil 30,\n" +
                                "Varna 9000, Bulgaria",
                43.205848, 27.910952,
                        "LL: +359 52 681 414 \n" +
                                " Cell./Mob. : +359 88888 71 64\n" +
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
                                " of Varna City."),
                new Place("Rosslyn Dimyat Hotel",
                        "bul.\"Knyaz Boris I\" 111,9002 Varna, Bulgaria\n",
                        43.209699, 27.928747,
                        "LL: +359 52 910 800 \n" +
                                "e-mail: reception.dimyat@rosslyn-hotels.com \n" +
                                "http://dimyat.rosslyn-hotels.com\n",
                        "With a central location and next to the sea garden," +
                                " Rosslyn Dimyat Hotel is ideal to make the most of Your" +
                                " stay in Varna, whether on a business or a city break visit." +
                                " The hotel provides 95 stylish rooms and suites, a restaurant," +
                                " lobby bar, three conference rooms and a ballroom. Guests can" +
                                " also indulge in Maya Vita center featuring a range of" +
                                " treatments, sauna park and an indoor swimming pool."
                        ),

                //LANDMARKS
                new Place(
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
                                " cliffs."),
                new Place("Patleina monastery",
                        " Patleina reserve, " +
                                "about 8 km from Veliki Preslav",
                        43.134077, 26.818260,
                        "Cell./Mob. : 00359888986935 - Archpriest N. Nikolov \n",
                        "It is the successor of St. Tsar Boris`s medieval monastery " +
                                "in the historic area Patleina. Housed in an impressive" +
                                " 70 years of building, not far from the ruins of the old " +
                                "monastery , where was discovered the world-famous ceramic" +
                                " icon \"St. Theodore\" "),
                new Place(
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
                                "in 815–831, as mentioned in the Chatalar Inscription of 822."),
                new Place(
                        "Madara Rider",
                        "Location:\t east of Shumen in northeastern Bulgaria," +
                                " near the village of Madara.\n" +
                                "Nearest city:\t Aksakovo",
                        43.225932, 27.706441,
                        " +359 5313 20 95",
                        "    The Madara Rider or Madara Horseman " +
                                "(Bulgarian: Мадарски конник, Madarski konnik) is" +
                                " an early medieval large rock relief carved on the" +
                                " Madara Plateau east of Shumen in northeastern Bulgaria, " +
                                "near the village of Madara. The monument is dated in the" +
                                " very late 7th, or more often very early 8th century," +
                                " during the reign of Bulgar Khan Tervel. In 1979 became" +
                                " enlisted on the UNESCO World Heritage List."),
                //TODO : description to be changes
                new Place(
                        "Regional History Museum of Varna",
                        "9000 Varna, 41 Maria Luisa Blvd" +
                                "Nearest city:\tAksakovo",
                        43.207054, 27.914544,
                        "LL: + 359 52 618011",
                        "\n" +
                                "The Varna Archaeological Museum is the historical " +
                                "and archaeological museum in Varna. It was founded in " +
                                "1887 by the brothers Karel and Hermann Shkorpil.\n" +
                                "\n" +
                                "The museum is preserved in 1972 near Varna, the oldest" +
                                " gold in the world: the Ethnolite treasure of Varna," +
                                " dated to 4200-4600 BC. It consists of jewels and bracelets," +
                                " mostly forged or cast. The alloy gold is a purity of 13 to" +
                                "23 carats, with a total weight of about 6 kg. It is protected " +
                                "by special mode.")
        };
    }*/

}
