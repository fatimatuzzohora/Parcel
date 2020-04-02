package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class ObjectAuth {

    @SerializedName("objMerchantAuth")
    private ServerResponse serverResponse;

    public ObjectAuth() {
    }

    public ObjectAuth(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }
}
