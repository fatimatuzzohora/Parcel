package com.example.parcel.model.spinner;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("ItemGroupID")
    private String catId;

    @SerializedName("ItemGroupName")
    private String catName;

    public Category() {
    }

    public String getCatId() {
        return catId;
    }

    public String getCatName() {
        return catName;
    }

    //for viewing category name in the spinner...
    @Override
    public String toString() {
        return catName ;
    }
}
