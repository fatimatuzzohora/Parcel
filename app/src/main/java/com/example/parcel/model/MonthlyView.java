package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class MonthlyView {

    @SerializedName("TotalPickup")
    private String totalPickup;

    @SerializedName("TotalOfficeReceive")
    private String totalOfficeReceive;

    @SerializedName("TotalDelivery")
    private String onProcess;

    @SerializedName("TotalDeliveryComplete")
    private String totalDeliverDone;

    @SerializedName("TotalBillProcess")
    private String totalBillProcess;

    @SerializedName("TotalBillAmount")
    private String totalBillAmount;

    public MonthlyView() {
    }


    public String getTotalPickup() {
        return totalPickup;
    }

    public String getTotalOfficeReceive() {
        return totalOfficeReceive;
    }

    public String getOnProcess() {
        return onProcess;
    }

    public String getTotalDeliverDone() {
        return totalDeliverDone;
    }

    public String getTotalBillProcess() {
        return totalBillProcess;
    }

    public String getTotalBillAmount() {
        return totalBillAmount;
    }
}
