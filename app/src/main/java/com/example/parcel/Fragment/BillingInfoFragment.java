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
import com.example.parcel.model.ContactInformation;
import com.example.parcel.model.TempUserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillingInfoFragment extends Fragment {
        private TextView paymentMode,bankName, bankACNo, bankAddress,bkashNo, rocketNo;

    private ApiInterface apiInterface;
    private BillingInformation billingInformation;

    private String userId;


    public BillingInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_billing_info, container, false);

        TempUserInfo tempUserInfo = new TempUserInfo(getContext());
        userId = tempUserInfo.getUserId();

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        billingInformation = new BillingInformation();

        paymentMode = view.findViewById(R.id.payment_mode);
        bankName = view.findViewById(R.id.bank_ac_name);
        bankACNo = view.findViewById(R.id.bank_ac_no);
        bankAddress = view.findViewById(R.id.bank_address);
        bkashNo = view.findViewById(R.id.bkash_no);
        rocketNo = view.findViewById(R.id.rocket_no);

        billingInformation(userId);

        return view;
    }

    private void billingInformation(String userId){
        Call<BillingInformation>billingInformationCall = apiInterface.billingInfo(userId);

        billingInformationCall.enqueue(new Callback<BillingInformation>() {
            @Override
            public void onResponse(Call<BillingInformation> call, Response<BillingInformation> response) {
                if(response.isSuccessful() && response.body() != null){
                    if(response.body().getMessage().equals("true")){
                        billingInformation = response.body();

                        paymentMode.setText(billingInformation.getPaymentMode());
                        bankName.setText(billingInformation.getBankACName());
                        bankACNo.setText(billingInformation.getBankACNo());
                        bankAddress.setText(billingInformation.getBankAddress());
                        bkashNo.setText(billingInformation.getBkashNo());
                        rocketNo.setText(billingInformation.getRocketNo());

                    }
                }
            }

            @Override
            public void onFailure(Call<BillingInformation> call, Throwable t) {
                Toast.makeText(getContext(), getResources().getText(R.string.net_error), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
