package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class ProductSave {
    //must field
    @SerializedName("ItemName")
    private String productName;

    @SerializedName("ItemGroupID")
    private String productCatId;

    @SerializedName("UOMID")
    private String productUnitId;

    @SerializedName("Rate")
    private String productRate;

    @SerializedName("ItemWeight")
    private String productWeight;

    @SerializedName("ItemSize")
    private String productSize;

    //must field
    @SerializedName("CreateBy")
    private String merchantId;
    //must field
    @SerializedName("IsActive")
    private String isActive;

    public ProductSave() {
    }

    public ProductSave(String productName, String productCatId, String productUnitId, String productRate, String productWeight, String productSize, String merchantId, String isActive) {
        this.productName = productName;
        this.productCatId = productCatId;
        this.productUnitId = productUnitId;
        this.productRate = productRate;
        this.productWeight = productWeight;
        this.productSize = productSize;
        this.merchantId = merchantId;
        this.isActive = isActive;
    }
}
