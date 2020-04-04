package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class MonthlyView {

    @SerializedName("TotalPickup")
    private String totalPickup;

    @SerializedName("TotalOfficeReceive")
    private String totalOfficeReceive;

    @SerializedName("TotalDelivery")
    private String totalDelivery;

    @SerializedName("TotalDeliveryComplete")
    private String totalDeliverDone;

    @SerializedName("TotalBillProcess")
    private String totalBillProcess;

    @SerializedName("TotalBillAmount")
    private String totalBillAmount;

    public MonthlyView() {
    }

    public MonthlyView(String totalPickup, String totalOfficeReceive, String totalDelivery, String totalDeliverDone, String totalBillProcess, String totalBillAmount) {
        this.totalPickup = totalPickup;
        this.totalOfficeReceive = totalOfficeReceive;
        this.totalDelivery = totalDelivery;
        this.totalDeliverDone = totalDeliverDone;
        this.totalBillProcess = totalBillProcess;
        this.totalBillAmount = totalBillAmount;
    }

    public String getTotalPickup() {
        return totalPickup;
    }

    public String getTotalOfficeReceive() {
        return totalOfficeReceive;
    }

    public String getTotalDelivery() {
        return totalDelivery;
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
