package com.example.parcel.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.parcel.R;
import com.example.parcel.model.BillingReport;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterBillingReport extends ArrayAdapter<BillingReport> {

    private Context mContext;
    private List<BillingReport>billingReports;


    public AdapterBillingReport(@NonNull Context context, int resource, @NonNull List<BillingReport> objects) {
        super(context, resource, objects);

        mContext = context;
        billingReports = objects;
    }

    static class ViewHolder{
        TextView cusName, cusAddress, cusMobile, parcelValue, codCharge, serviceCharge, merchantBill,deliveryStatus, paymentStatus ;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String cusName = getItem(position).getCustomerName();
        String cusAddress = getItem(position).getCustomerAddress();
        String cusMobile = getItem(position).getCustomerMobile();

        String parcelValue = getItem(position).getParcelValue();
        String codCharge = getItem(position).getCodCharge();
        String serviceCharge = getItem(position).getDeliveryCharge();
        String merchantBill = getItem(position).getMerchantBill();

        String deliveryStatus = getItem(position).getDeliveryStatus();
        String paymentStatus = getItem(position).getBillStatus();

        BillingReport billingReport = new BillingReport(deliveryStatus, cusAddress, merchantBill, serviceCharge, codCharge, parcelValue,cusName,paymentStatus, cusMobile);

        AdapterBillingReport.ViewHolder holder;

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.custom_merchant_billing_lv, parent, false);

        holder = new AdapterBillingReport.ViewHolder();

        holder.cusName = convertView.findViewById(R.id.br_cus_name);
        holder.cusAddress = convertView.findViewById(R.id.br_cus_address);
        holder.cusMobile = convertView.findViewById(R.id.br_cus_mobile);

        holder.parcelValue = convertView.findViewById(R.id.br_parcel_value);
        holder.codCharge = convertView.findViewById(R.id.br_cod_charge);
        holder.serviceCharge = convertView.findViewById(R.id.br_service_charge);
        holder.merchantBill = convertView.findViewById(R.id.br_merchant_bill);

        holder.deliveryStatus = convertView.findViewById(R.id.br_delivery_status);
        holder.paymentStatus = convertView.findViewById(R.id.br_payment_status);

        convertView.setTag(holder);

        holder.cusName.setText(billingReport.getCustomerName());
        holder.cusAddress.setText(billingReport.getCustomerAddress());
        holder.cusMobile.setText(billingReport.getCustomerMobile());

        holder.parcelValue.setText(billingReport.getParcelValue());
        holder.codCharge.setText(billingReport.getCodCharge());
        holder.serviceCharge.setText(billingReport.getDeliveryCharge());
        holder.merchantBill.setText(billingReport.getMerchantBill());

        holder.deliveryStatus.setText(billingReport.getDeliveryStatus());
        holder.paymentStatus.setText(billingReport.getBillStatus());


        return convertView;
    }

    // decimal format
    public String decimalFormat(double value){
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(value);
    }

}
