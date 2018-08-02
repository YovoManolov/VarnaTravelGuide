package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import static android.arch.persistence.room.ForeignKey.CASCADE;
import java.nio.ByteBuffer;

@Entity(tableName = "IMAGES",
        foreignKeys={
                @ForeignKey(
                        entity=Place.class,
                        parentColumns="placeId",
                        childColumns="PLACE_ID",
                        onDelete=CASCADE)
                },
                indices={
                @Index(value="PLACE_ID")
        }
      )
public class Image {
    @PrimaryKey(autoGenerate = true)
    private Long imageId;
    @ColumnInfo(name="PLACE_ID")
    private Long placeId;

    @ColumnInfo(name="IMAGE_URL")
    private String URLstring;


    public Image(long placeId, String URLstring) {
        this.placeId = placeId;
        this.URLstring = URLstring;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getURLstring() {
        return URLstring;
    }

    public void setURLstring(String URLstring) {
        this.URLstring = URLstring;
    }

    //първо да генерирам местата и след това ще генерирам изображенията за тях.
   /* public static Image [] populateImages(){
        return new Image[] {
                new Image(),
        }
    }*/

}
