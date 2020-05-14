package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("IsSuccess")
    private String message;

    @SerializedName("Id")
    private String id;

    @SerializedName("FullName")
    private String name;

    @SerializedName("EmailID")
    private String email;

    @SerializedName("MobileNo")
    private String mobileNo;

    public ServerResponse() {
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNo() {
        return mobileNo;
    }
}
