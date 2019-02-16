package com.example.yovo_user.varnatravelguide.databasePackage.imagePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.DatabaseHelper;
import com.example.yovo_user.varnatravelguide.databasePackage.landmarkPackage.Landmark;

import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl implements ImageDao {

    private SQLiteDatabase dbWritableConnection;

    public ImageDaoImpl(SQLiteDatabase dbWritableConnection) {
        this.dbWritableConnection = dbWritableConnection;
    }

    @Override
    public void createImageTable() throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection,DbStringConstants.TABLE_IMAGES);

        try{
            dbWritableConnection.execSQL(DbStringConstants.CREATE_IMAGES_TABLE);
        }catch(SQLException e){
            e.printStackTrace();
        }

        Log.d("Create table message: ","Table "
                + DbStringConstants.TABLE_IMAGES + " is being created !");
    }

    @Override
    public void addImage(Image[] images) {

        dbWritableConnection.beginTransaction();
        try {
            int i;
            for (i = 0; i < images.length; i++) {
                ContentValues values = new ContentValues();
                values.put(DbStringConstants.IM_PLACE_ID, images[i].getPlaceId());
                values.put(DbStringConstants.IM_IMAGE_URL, images[i].getImageURL());
                values.put(DbStringConstants.IM_MAIN_IMAGE, images[i].getIsMainImage());

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_IMAGES,
                        null, values);
                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_IMAGES + "for: i = " + i );
                }

                Log.d("Images ", " newly inserted row ID: " + rowId);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    public List<Image> getImagesForPlace(int placeId){
        List<Image> allImagesForPlace = new ArrayList<>();
        dbWritableConnection.beginTransaction();

        try{
            Cursor cursor = dbWritableConnection.rawQuery(DbStringConstants.GET_IMAGES_FOR_PLACE,
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

            cursor.close();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return allImagesForPlace;
    }

    public Image getMainImageForPlace(int placeId){
        Image mainImage  = null;
        dbWritableConnection.beginTransaction();

        try{

            Cursor cursor = dbWritableConnection.rawQuery(
                    DbStringConstants.GET_MAIN_IMAGE_FOR_PLACE,
                    new String[]{
                            String.valueOf(placeId),
                            String.valueOf(1)
                    });

            if (cursor.moveToFirst()) {
                    mainImage = new Image(cursor.getInt(1),
                            cursor.getString(2),cursor.getInt(3));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }

        return mainImage;
    }
}
