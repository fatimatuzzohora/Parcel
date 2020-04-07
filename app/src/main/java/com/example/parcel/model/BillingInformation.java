package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class BillingInformation {

    //3rd Fragment
    @SerializedName("IsSuccess")
    private String message;

    @SerializedName("PaymentMode")
    private String paymentMode;

    @SerializedName("BankACName")
    private String bankACName;

    @SerializedName("BankACNo")
    private String bankACNo;

    @SerializedName("BankAddress")
    private String bankAddress;

    @SerializedName("BkashNo")
    private String bkashNo;

    @SerializedName("RocketNo")
    private String rocketNo;

    public BillingInformation() {
    }

    public String getMessage() {
        return message;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getBankACName() {
        return bankACName;
    }

    public String getBankACNo() {
        return bankACNo;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public String getBkashNo() {
        return bkashNo;
    }

    public String getRocketNo() {
        return rocketNo;
    }
}
