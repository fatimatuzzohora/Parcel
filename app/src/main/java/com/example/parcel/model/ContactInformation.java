package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class ContactInformation {

    //2nd Fraagment
    @SerializedName("IsSuccess")
    private String message;

    @SerializedName("ContactPersonFullName")
    private String contactPersonName;

    @SerializedName("ContactPersonEmailID")
    private String contactPersonMail;

    @SerializedName("ContactPersonPhone")
    private String contactPersonPhn;

    @SerializedName("ContactPersonPosition")
    private String contactPersonPosition;


    public ContactInformation() {
    }

    public String getMessage() {
        return message;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public String getContactPersonMail() {
        return contactPersonMail;
    }

    public String getContactPersonPhn() {
        return contactPersonPhn;
    }

    public String getContactPersonPosition() {
        return contactPersonPosition;
    }
}
