package com.example.yovo_user.varnatravelguide.databasePackage.hotelPackage;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Hotel {

    private ObjectId _id ;
    private ObjectId place_id;
    private int numbOfStars;

    public Hotel(ObjectId id, ObjectId place_id, int numbOfStars,
                 int priceCategoryId) {
        this._id = _id;
        this.place_id = place_id;
        this.numbOfStars = numbOfStars;
    }

    public Hotel(ObjectId place_id, int numbOfStars) {
        this.place_id = place_id;
        this.numbOfStars = numbOfStars;
    }

    public Hotel(final Document document) {
        _id = document.getObjectId("_id");
        place_id = document.getObjectId("place_id");
        numbOfStars = document.getInteger("numbOfStars");
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "_id=" + _id +
                ", place_id=" + place_id +
                ", numbOfStars=" + numbOfStars +
                '}';
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
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
}
