package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class MerchantRequisition {

    @SerializedName("ServiceID")
    private String serviceId;

    @SerializedName("DistrictID")
    private String districtId;

    //delivery person ID
    @SerializedName("UserID")
    private String deliveryPersonId;

    //Selected Date
    @SerializedName("TransactionDate")
    private String transactionDate;

    //Merchant ID
    @SerializedName("CreateBy")
    private String userId;


    public MerchantRequisition() {
    }

    public MerchantRequisition(String serviceId, String districtId, String deliveryPersonId, String transactionDate, String userId) {
        this.serviceId = serviceId;
        this.districtId = districtId;
        this.deliveryPersonId = deliveryPersonId;
        this.transactionDate = transactionDate;
        this.userId = userId;
    }
}
