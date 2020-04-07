package com.example.parcel.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.parcel.Adapter.AdapterPager;
import com.example.parcel.Fragment.BillingInfoFragment;
import com.example.parcel.Fragment.BusinessInfoFragment;
import com.example.parcel.Fragment.ContactInfoFragment;
import com.example.parcel.Fragment.MerchantInfoFragment;
import com.example.parcel.Interface.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.model.ApiClient;
import com.example.parcel.model.MerchantInformation;
import com.example.parcel.model.TempUserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MerchantProfileActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_profile);

        Toolbar toolbar = findViewById(R.id.tb_mer_pro);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //id initialize......
        tabLayout = findViewById(R.id.tabLayout_mer_pro);
        viewPager = findViewById(R.id.viewPager_mer_pro);
        setViewPager();

        //set viewpager to tablayout
        tabLayout.setupWithViewPager(viewPager);

        //set title
        tabLayout.getTabAt(0).setText(R.string.gen_info);
        tabLayout.getTabAt(1).setText(R.string.contact_info);
        tabLayout.getTabAt(2).setText(R.string.business_info);
        tabLayout.getTabAt(3).setText(R.string.billing_info);

    }

    private void setViewPager() {
        AdapterPager adapterPager = new AdapterPager(getSupportFragmentManager());

        adapterPager.addFragment(new MerchantInfoFragment());
        adapterPager.addFragment(new ContactInfoFragment());
        adapterPager.addFragment(new BusinessInfoFragment());
        adapterPager.addFragment(new BillingInfoFragment());

        viewPager.setAdapter(adapterPager);

    }

}
