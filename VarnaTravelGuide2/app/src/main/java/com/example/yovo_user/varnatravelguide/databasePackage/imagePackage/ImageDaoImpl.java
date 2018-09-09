package com.example.yovo_user.varnatravelguide.databasePackage.imagePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;

import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl implements ImageDao {

    @Override
    public void createImageTable(SQLiteDatabase dbWritableConnection) throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_IMAGES);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_IMAGES_TABLE);
    }

    @Override
    public void addImage(SQLiteDatabase dbWritableConnection, Image[] images) {
        dbWritableConnection.beginTransaction();
        int i;
        ContentValues values = new ContentValues();
        for(i = 0 ;i < images.length ;i++){
            values.put(DbStringConstants.IM_PLACE_ID, images[i].getPlaceId());
            values.put(DbStringConstants.IM_IMAGE_URL, images[i].getImageURL());
            values.put(DbStringConstants.IM_MAIN_IMAGE, images[i].getIsMainImage());

            dbWritableConnection.insert(DbStringConstants.TABLE_IMAGES,
                    null, values);
            values.clear();
        }
        dbWritableConnection.endTransaction();
    }

    public List<Image> getImagesForPlace(SQLiteDatabase dbReadableConnection,int placeId){
        List<Image> allImagesForPlace = new ArrayList<>();
        dbReadableConnection.beginTransaction();

        Cursor cursor = dbReadableConnection.rawQuery(DbStringConstants.GET_IMAGES_FOR_PLACE,
                                    new String[]{
                String.valueOf(placeId)
        });

        if (cursor.moveToFirst()) {
            do {
                Image image = new Image(cursor.getInt(1),
                        cursor.getString(2),cursor.getInt(3));
                allImagesForPlace.add(image);
            } while (cursor.moveToNext());
        }

        dbReadableConnection.endTransaction();
        return allImagesForPlace;
    }

    public Image getMainImageForPlace(SQLiteDatabase dbReadableConnection,int placeId){
        Image mainImage  = null;
        dbReadableConnection.beginTransaction();

        Cursor cursor = dbReadableConnection.rawQuery(DbStringConstants.GET_MAIN_IMAGE_FOR_PLACE,
                new String[]{
                        String.valueOf(placeId),
                        String.valueOf(1)
                });

        if (cursor.moveToFirst()) {
                mainImage = new Image(cursor.getInt(1),
                        cursor.getString(2),cursor.getInt(3));
        }

        dbReadableConnection.endTransaction();
        return mainImage;
    }
}
