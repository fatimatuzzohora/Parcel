package com.example.parcel.model.spinner;

import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("ServiceID")
    private String serviceId;

    @SerializedName("ServiceName")
    private String serviceName;

    @SerializedName("IsActive")
    private String isActive;

    @SerializedName("CODCharge")
    private String codCharge;

    public Service() {
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getCodCharge() {
        return codCharge;
    }

    //for viewing the value in spinner item
    @Override
    public String toString() {
        return serviceName ;
    }
}
