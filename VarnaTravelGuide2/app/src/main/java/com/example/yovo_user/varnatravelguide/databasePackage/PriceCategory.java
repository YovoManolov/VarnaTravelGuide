package com.example.yovo_user.varnatravelguide.databasePackage;

import android.arch.persistence.room.TypeConverter;

import java.io.Serializable;

public enum PriceCategory implements Serializable{
    BUDGET(0),
    MID_RANGE(1),
    PREMIUM(2),
    COMBINED(3);

    private final int code;

    PriceCategory(int code){
        this.code = code;
    }

    @TypeConverter
    public static PriceCategory getPriceCategory(int code){
        for(PriceCategory pc : values()){
            if(pc.code == code){
                return pc;
            }
        }
        return null;
    }

    @TypeConverter
    public static int getPriceCategoryInt(PriceCategory pc){
        return pc.code;
    }
}
