package com.example.parcel.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.parcel.R;
import com.example.parcel.model.TempUserInfo;

public class MerchantBillingActivity extends AppCompatActivity {
    private ListView billingReportLV;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_billing);

        billingReportLV = findViewById(R.id.billing_report_LV);

        //get the user id.....
        TempUserInfo tempUserInfo = new TempUserInfo(getApplicationContext());
        userId = tempUserInfo.getUserId();

    }
}
