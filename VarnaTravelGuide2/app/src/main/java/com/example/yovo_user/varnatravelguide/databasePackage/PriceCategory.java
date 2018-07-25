package com.example.yovo_user.varnatravelguide.databasePackage;

public enum PriceCategory {
    BUDGET(0),
    MID_RANGE(1),
    PREMIUM(2),
    COMBINED(3);

    private int code;

    PriceCategory(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
