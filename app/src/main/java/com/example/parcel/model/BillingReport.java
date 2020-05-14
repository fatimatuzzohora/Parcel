package com.example.parcel.model;

import com.google.gson.annotations.SerializedName;

public class BillingReport {

    @SerializedName("FullName")
    private String fullName;

    @SerializedName("OfficeAddress")
    private String officeAddress;

    @SerializedName("MerchantAddress")
    private String merchantAddress;

    @SerializedName("FromDate")
    private String fromDate;

    @SerializedName("ToDate")
    private String toDate;

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("Area")
    private String area;

    @SerializedName("DeliveryStatus")
    private String deliveryStatus;

    @SerializedName("RequisitionDate")
    private String requisitionDate;

    @SerializedName("TransactionDate")
    private String transactionDate;

    @SerializedName("DeliveryAddress")
    private String deliveryAddress;

    @SerializedName("SaleRate")
    private String saleRate;

    @SerializedName("DeliveryCharge")
    private String deliveryCharge;

    @SerializedName("CODCharge")
    private String codCharge;

    @SerializedName("Bill")
    private String bill;

    @SerializedName("CustomerName")
    private String customerName;

    @SerializedName("BillStatus")
    private String billStatus;

    @SerializedName("CustomerMobile")
    private String customerMobile;

    @SerializedName("ItemName")
    private String itemName;

    public String getFullName() {
        return fullName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getArea() {
        return area;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getRequisitionDate() {
        return requisitionDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getSaleRate() {
        return saleRate;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public String getCodCharge() {
        return codCharge;
    }

    public String getBill() {
        return bill;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public String getItemName() {
        return itemName;
    }
}
