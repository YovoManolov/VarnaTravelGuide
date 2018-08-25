package com.example.yovo_user.varnatravelguide.databasePackage.imagePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;

import java.util.ArrayList;
import java.util.List;

public abstract class ImageDaoImpl extends Context implements ImageDao {
    @Override
    public void createImageTable(SQLiteDatabase dbWritableConnection) {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_IMAGES);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_IMAGES_TABLE);
    }

    @Override
    public void addImage(SQLiteDatabase dbWritableConnection, Image[] images) {
        dbWritableConnection.beginTransaction();

        ContentValues values = new ContentValues();
        for(int i = 0 ;i < images.length ;i++){
            values.put(DbStringConstants.IM_PLACE_ID, images[i].getPlaceId());
            values.put(DbStringConstants.IMAGE_URL, images[i].getImageURL());

            dbWritableConnection.insert(DbStringConstants.TABLE_IMAGES,
                    null, values);
        }
        dbWritableConnection.endTransaction();
    }

    public List<Image> getImagesForPlace(int placeId){
        List<Image> allImagesForPlace = new ArrayList<>();
        SQLiteDatabase dbReadableConnection = VTGDatabase.getInstance(this.getApplicationContext())
                .getReadableDatabase();

        Cursor cursor = dbReadableConnection.rawQuery(DbStringConstants.GET_IMAGES_FOR_PLACE,new String[]{
                String.valueOf(placeId)
        });

        if (cursor.moveToFirst()) {
            do {
                Image image = new Image(cursor.getInt(1),
                        cursor.getString(2));
                allImagesForPlace.add(image);
            } while (cursor.moveToNext());
        }

        return allImagesForPlace;
    }
}
