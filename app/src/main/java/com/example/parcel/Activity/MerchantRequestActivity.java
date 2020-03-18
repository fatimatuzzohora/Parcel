package com.example.parcel.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

import com.example.parcel.R;

public class MerchantRequestActivity extends AppCompatActivity {
    private Spinner service_spinnner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_request);


        Toolbar toolbarMerReq = findViewById(R.id.toolbar_mer_req);


        setSupportActionBar(toolbarMerReq);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
