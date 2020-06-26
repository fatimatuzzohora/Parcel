package com.example.parcel.model.spinner;

import com.google.gson.annotations.SerializedName;

public class District {

    @SerializedName("DistrictID")
    private String districtId;

    @SerializedName("DistrictName")
    private String districtName;

    @SerializedName("IsActive")
    private String isActive;


    public District() {
    }

    public String getDistrictId() {
        return districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getIsActive() {
        return isActive;
    }

    //for viewing the district name in dropdown box....
    @Override
    public String toString() {
        return districtName;
    }
}
