package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class BusinessInformation {

    //4th Fragment
    @SerializedName("IsSuccess")
    private String message;

    @SerializedName("BusinessName")
    private String businessName;

    @SerializedName("BusinessNature")
    private String businessNature;

    @SerializedName("ProductType")
    private String productType;

    @SerializedName("Website")
    private String website;

    @SerializedName("FacebookPage")
    private String fbPage;

    @SerializedName("FacebookPageLink")
    private String fbPageLink;


    public BusinessInformation() {
    }

    public String getMessage() {
        return message;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getBusinessNature() {
        return businessNature;
    }

    public String getProductType() {
        return productType;
    }

    public String getWebsite() {
        return website;
    }

    public String getFbPage() {
        return fbPage;
    }

    public String getFbPageLink() {
        return fbPageLink;
    }
}
