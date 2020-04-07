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
import com.example.parcel.model.MerchantInformation;
import com.example.parcel.model.TempUserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MerchantInfoFragment extends Fragment {
    private TextView merchantName,merchantPhone,merchantMail,merchantTradeNum,merchantPostCode,merchantAddress,
                        merchantMailingAddress, merchantPermanantAddress;

    private ApiInterface apiInterface;
    private MerchantInformation merchantInformation;

    private String userId;


    public MerchantInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_merchant_general_information, container, false);

        TempUserInfo tempUserInfo = new TempUserInfo(getContext());
        userId = tempUserInfo.getUserId();

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        merchantInformation = new MerchantInformation();

        merchantName = view.findViewById(R.id.merchant_name);
        merchantPhone = view.findViewById(R.id.merchant_phn);
        merchantMail = view.findViewById(R.id.merchant_mail);
        merchantTradeNum= view.findViewById(R.id.merchant_trade_num);
        merchantPostCode = view.findViewById(R.id.merchant_post_code);
        merchantAddress = view.findViewById(R.id.merchant_address);
        merchantMailingAddress = view.findViewById(R.id.merchant_mailing_address);
        merchantPermanantAddress = view.findViewById(R.id.merchant_permanent_address);

        merchantInformation(userId);

        // Inflate the layout for this fragment
        return  view;
    }

    private void merchantInformation(String userId){
        Call<MerchantInformation>call = apiInterface.merchantProfileInfo(userId);

        call.enqueue(new Callback<MerchantInformation>() {
            @Override
            public void onResponse(Call<MerchantInformation> call, Response<MerchantInformation> response) {
                if(response.isSuccessful() && response.body()!=null ){
                    if (response.body().getMessage().equals("true")){
                        merchantInformation = response.body();

                        merchantName.setText(merchantInformation.getFullName());
                        merchantPhone.setText(merchantInformation.getMobileNo());
                        merchantMail.setText(merchantInformation.getEmailId());
                        merchantTradeNum.setText(merchantInformation.getTradeLicenseNo());
                        merchantPostCode.setText(merchantInformation.getPostalCode());
                        merchantAddress.setText(merchantInformation.getPresentAddress());
                        merchantMailingAddress.setText(merchantInformation.getPresentAddress());
                        merchantPermanantAddress.setText(merchantInformation.getPermanentAddress());

                    }
                }
            }

            @Override
            public void onFailure(Call<MerchantInformation> call, Throwable t) {
                Toast.makeText(getActivity(), getResources().getText(R.string.net_error), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
