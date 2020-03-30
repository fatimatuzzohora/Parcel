package com.example.parcel;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("IsSuccess")
    private String message;

    public ServerResponse() {
    }

    public ServerResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
