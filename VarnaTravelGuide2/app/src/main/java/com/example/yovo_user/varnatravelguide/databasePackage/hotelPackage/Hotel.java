package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;
import com.example.yovo_user.varnatravelguide.databasePackage.placePackage.Place;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Hotel {

    private ObjectId id ;
    private ObjectId place_id;
    private int numbOfStars;

    public Hotel(ObjectId id, ObjectId place_id, int numbOfStars,
                 int priceCategoryId) {
        this.id = id;
        this.place_id = place_id;
        this.numbOfStars = numbOfStars;
    }

    public Hotel(ObjectId place_id, int numbOfStars) {
        this.place_id = place_id;
        this.numbOfStars = numbOfStars;
    }

    public Hotel(final Document document) {
        id = document.getObjectId("_id");
        place_id = document.getObjectId("place_id");
        numbOfStars = document.getInteger("numbOfStars");
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", place_id=" + place_id +
                ", numbOfStars=" + numbOfStars +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getplace_id() {
        return place_id;
    }

    public void setplace_id(ObjectId place_id) {
        this.place_id = place_id;
    }

    public int getNumbOfStars() {
        return numbOfStars;
    }

    public void setNumbOfStars(int numbOfStars) {
        this.numbOfStars = numbOfStars;
    }

    /*public static Hotel[] populateHotels() {
        return new Hotel[] {
                //43.199070, 27.919569
                //LL - landline phone abbriviation

                new Hotel(11,2,1),
                new Hotel(12,3, 2),
                new Hotel(13,3, 2),
                new Hotel(14,4, 2),
                new Hotel(15,5, 3)
        };
    }*/
}
