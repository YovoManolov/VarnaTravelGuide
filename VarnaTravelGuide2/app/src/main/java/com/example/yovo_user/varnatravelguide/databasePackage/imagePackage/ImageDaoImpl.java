package com.example.yovo_user.varnatravelguide.databasePackage.imagePackage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;

public class ImageDaoImpl implements ImageDao {
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
}
