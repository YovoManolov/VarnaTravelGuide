package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import java.nio.ByteBuffer;

@Entity(tableName = "IMAGES")
public class Image {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name="PLACE_ID")
    private long placeId;
    @ColumnInfo(name="IMAGE")
    private String URLstring;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }
}
