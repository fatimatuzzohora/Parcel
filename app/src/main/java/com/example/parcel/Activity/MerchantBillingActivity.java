package com.example.parcel.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcel.Adapter.AdapterBillingReport;
import com.example.parcel.Interface.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.model.ApiClient;
import com.example.parcel.model.BillingReport;
import com.example.parcel.model.TempUserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MerchantBillingActivity extends AppCompatActivity {
    private ListView billingReportLV;

    private String userId;
    private ApiInterface apiInterface;

    private AdapterBillingReport adapterBillingReport;
    private List<BillingReport> billingReports ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_billing);

        //get the user id.....
        TempUserInfo tempUserInfo = new TempUserInfo(getApplicationContext());
        userId = tempUserInfo.getUserId();

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        billingReportLV = findViewById(R.id.billing_report_LV);

        billingReport();


    }


    private void billingReport(){
        Call<List<BillingReport>>listCall = apiInterface.billingReport(userId,"2020-03-01","2020-04-30");

        listCall.enqueue(new Callback<List<BillingReport>>() {
            @Override
            public void onResponse(Call<List<BillingReport>> call, Response<List<BillingReport>> response) {
                if(response.isSuccessful() && response.body() != null){
                    Toast.makeText(MerchantBillingActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    billingReports = response.body();

                    showReportIntoList();
                }
                else Toast.makeText(MerchantBillingActivity.this, "Unsuccessfull", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<BillingReport>> call, Throwable t) {
                Toast.makeText(MerchantBillingActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showReportIntoList() {
        adapterBillingReport =  new AdapterBillingReport(getApplicationContext(),R.layout.custom_merchant_billing_lv,billingReports);
        billingReportLV.setAdapter(adapterBillingReport);
    }
}
