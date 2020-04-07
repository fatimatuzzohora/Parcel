package com.example.parcel.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcel.Interface.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.model.ApiClient;
import com.example.parcel.model.BillingInformation;
import com.example.parcel.model.BusinessInformation;
import com.example.parcel.model.TempUserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessInfoFragment extends Fragment {
    private TextView businessName, businessNature,productType,website,fbPage,fbPageLink;

    private ApiInterface apiInterface;
    private BusinessInformation businessInformation;

    private String userId;

    public BusinessInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_business_info, container, false);


        TempUserInfo tempUserInfo = new TempUserInfo(getContext());
        userId = tempUserInfo.getUserId();

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        businessName = view.findViewById(R.id.business_name);
        businessNature =view.findViewById(R.id.business_nature);
        productType = view.findViewById(R.id.product_type);
        website = view.findViewById(R.id.website);
        fbPage = view.findViewById(R.id.fb_page);
        fbPageLink = view.findViewById(R.id.fb_page_link);

        businessInformation(userId);

        return view;
    }

    private void businessInformation(String userId){
        Call<BusinessInformation>businessInformationCall = apiInterface.businessInfo(userId);

        businessInformationCall.enqueue(new Callback<BusinessInformation>() {
            @Override
            public void onResponse(Call<BusinessInformation> call, Response<BusinessInformation> response) {
                if(response.isSuccessful() && response.body() != null){
                    if(response.body().getMessage().equals("true")){
                        businessInformation = response.body();

                        businessName.setText(businessInformation.getBusinessName());
                        businessNature.setText(businessInformation.getBusinessNature());
                        productType.setText(businessInformation.getProductType());
                        website.setText(businessInformation.getWebsite());
                        fbPage.setText(businessInformation.getFbPage());
                        fbPageLink.setText(businessInformation.getFbPageLink());

                    }
                }
            }

            @Override
            public void onFailure(Call<BusinessInformation> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getText(R.string.net_error), Toast.LENGTH_SHORT).show();
            }
        });
    }




}
