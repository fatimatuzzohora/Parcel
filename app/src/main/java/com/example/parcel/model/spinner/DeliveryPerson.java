package com.example.parcel.model.spinner;

import com.google.gson.annotations.SerializedName;

public class DeliveryPerson {

    @SerializedName("UserID")
    private String deliverPersonId;

    @SerializedName("UserFullName")
    private String deliverPersonName;

    @SerializedName("IsActive")
    private String isActive;

    public DeliveryPerson() {
    }

    public String getDeliverPersonId() {
        return deliverPersonId;
    }

    public String getDeliverPersonName() {
        return deliverPersonName;
    }

    public String getIsActive() {
        return isActive;
    }

    //for viewing the name in dropdown box
    @Override
    public String toString() {
        return deliverPersonName;
    }
}
