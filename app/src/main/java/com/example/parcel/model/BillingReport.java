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
    private String customerAddress;

    @SerializedName("SaleRate")
    private String merchantBill;

    @SerializedName("DeliveryCharge")
    private String deliveryCharge;

    @SerializedName("CODCharge")
    private String codCharge;

    @SerializedName("Bill")
    private String parcelValue;

    @SerializedName("CustomerName")
    private String customerName;

    @SerializedName("BillStatus")
    private String billStatus;

    @SerializedName("CutomerMobile")
    private String customerMobile;

    @SerializedName("ItemName")
    private String itemName;


    public BillingReport(String deliveryStatus, String customerAddress, String merchantBill, String deliveryCharge, String codCharge, String parcelValue, String customerName, String billStatus, String customerMobile) {
        this.deliveryStatus = deliveryStatus;
        this.customerAddress = customerAddress;
        this.merchantBill = merchantBill;
        this.deliveryCharge = deliveryCharge;
        this.codCharge = codCharge;
        this.parcelValue = parcelValue;
        this.customerName = customerName;
        this.billStatus = billStatus;
        this.customerMobile = customerMobile;
    }

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

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getMerchantBill() {
        return merchantBill;
    }

    public String getDeliveryCharge() {
        return deliveryCharge;
    }

    public String getCodCharge() {
        return codCharge;
    }

    public String getParcelValue() {
        return parcelValue;
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
