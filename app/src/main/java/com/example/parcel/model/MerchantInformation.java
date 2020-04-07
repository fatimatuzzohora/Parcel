package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class MerchantInformation {

    //1st Fragment
    @SerializedName("IsSuccess")
    private String message;

    @SerializedName("EmailID")
    private String emailId;

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("PostalCode")
    private String postalCode;

    @SerializedName("PostOffice")
    private String postOffice;

    @SerializedName("FullName")
    private String fullName;

    @SerializedName("PresentAddress")
    private String presentAddress;

    @SerializedName("ParmanentAddress")
    private String permanentAddress;

    @SerializedName("TradeLicenseNo")
    private String tradeLicenseNo;


    public MerchantInformation() {
    }

    public String getMessage() {
        return message;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getTradeLicenseNo() {
        return tradeLicenseNo;
    }

}
