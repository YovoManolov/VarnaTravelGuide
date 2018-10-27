package com.example.yovo_user.varnatravelguide.databasePackage.workHoursPackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yovo_user.varnatravelguide.databasePackage.DbBaseOperations;
import com.example.yovo_user.varnatravelguide.databasePackage.DbStringConstants;
import com.example.yovo_user.varnatravelguide.databasePackage.VTGDatabase;

import java.util.ArrayList;
import java.util.List;

public class WorkHoursDaoImpl implements WorkHoursDao {

    @Override
    public void createWorkHoursTable(SQLiteDatabase dbWritableConnection) throws SQLException {
        DbBaseOperations.dropTableX(dbWritableConnection, DbStringConstants.TABLE_WORK_HOURS);
        dbWritableConnection.execSQL(DbStringConstants.CREATE_WORK_HOURS_TABLE);
        Log.d("Create table message: ","Table "
                + DbStringConstants.TABLE_WORK_HOURS + " is being created !");

    }

    @Override
    public void addWorkHours(SQLiteDatabase dbWritableConnection, WorkHours[] workHours) {
        dbWritableConnection.beginTransaction();
        try{

            ContentValues values = new ContentValues();
            for (int i = 0; i < workHours.length; i++) {

                values.put(DbStringConstants.WH_PLACE_ID, workHours[i].getPlaceId());
                if (workHours[i].getIs24h() == -1) {
                    values.put(DbStringConstants.WH_IS_24H, workHours[i].getIs24h());
                    values.put(DbStringConstants.WH_MON_FRI, workHours[i].getMonFri());
                } else if (workHours[i].getIs24h() == 0) {
                    values.put(DbStringConstants.WH_MON_FRI, workHours[i].getMonFri());
                    values.put(DbStringConstants.WH_SAT, workHours[i].getSat());
                    values.put(DbStringConstants.WH_SUN, workHours[i].getSun());
                } else if (workHours[i].getIs24h() == 1) {
                    values.put(DbStringConstants.WH_IS_24H, workHours[i].getIs24h());
                } else {
                    values.put(DbStringConstants.WH_IS_24H, workHours[i].getPlaceId());
                    values.put(DbStringConstants.WH_SAT, workHours[i].getPlaceId());
                    values.put(DbStringConstants.WH_SUN, workHours[i].getPlaceId());
                }

                long rowId = dbWritableConnection.insert(DbStringConstants.TABLE_WORK_HOURS,
                        null, values);

                if(rowId  == -1){
                    Log.d("Insert failed:", "For table "
                            + DbStringConstants.TABLE_WORK_HOURS + "for: i = " + i );
                }

                Log.d("Work hours  ", " newly inserted row ID: " + rowId);

                values = new ContentValues();
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbWritableConnection.endTransaction();
        }
    }

    @Override
    public List<WorkHours> getWorkHoursByPlaceId(int placeId,SQLiteDatabase dbReadableConnection){
        List<WorkHours> workHoursList = new ArrayList<>();

        Cursor cursor = dbReadableConnection.rawQuery(DbStringConstants.GET_WORK_HOURS_BY_ID,new String[]{
                String.valueOf(placeId)
        });

        if (cursor.moveToFirst()) {
            do {
                WorkHours workHours = new WorkHours(cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                workHoursList.add(workHours);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return workHoursList;
    }
}
