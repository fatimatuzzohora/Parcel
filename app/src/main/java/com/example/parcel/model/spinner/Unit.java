package com.example.parcel.model.spinner;

import com.google.gson.annotations.SerializedName;

public class Unit {
    @SerializedName("UOMID")
    private String unitId;

    @SerializedName("UOMName")
    private String unitName;

    public Unit() {
    }

    public String getUnitId() {
        return unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    @Override
    public String toString() {
        return  unitName ;
    }
}
