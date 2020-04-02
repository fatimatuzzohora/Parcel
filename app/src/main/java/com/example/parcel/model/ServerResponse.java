package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("IsSuccess")
    private String message;

    @SerializedName("FullName")
    private String name;

    @SerializedName("EmailID")
    private String email;


    public ServerResponse() {
    }

    public ServerResponse(String message, String name, String email) {
        this.message = message;
        this.name = name;
        this.email = email;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
