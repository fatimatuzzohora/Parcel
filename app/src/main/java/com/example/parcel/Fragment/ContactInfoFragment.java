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
import com.example.parcel.model.ContactInformation;
import com.example.parcel.model.MerchantInformation;
import com.example.parcel.model.TempUserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactInfoFragment extends Fragment {
    private TextView contactPersonName, contactPersonPosition, contactNumber, contactMail;

    private ApiInterface apiInterface;
    private ContactInformation contactInformation;

    private String userId;

    public ContactInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_information, container, false);

        TempUserInfo tempUserInfo = new TempUserInfo(getContext());
        userId = tempUserInfo.getUserId();

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        contactInformation = new ContactInformation();

        contactPersonName = view.findViewById(R.id.contactName);
        contactPersonPosition = view.findViewById(R.id.contactPersonPosition);
        contactNumber = view.findViewById(R.id.contactNumber);
        contactMail = view.findViewById(R.id.contactEmail);

        contactInformation(userId);

        return view;
    }

    private void contactInformation(String userId){
        Call<ContactInformation>call = apiInterface.contactInfo(userId);

        call.enqueue(new Callback<ContactInformation>() {
            @Override
            public void onResponse(Call<ContactInformation> call, Response<ContactInformation> response) {
                if(response.isSuccessful() && response.body() != null){
                    if(response.body().getMessage().equals("true")){
                        contactInformation = response.body();

                        contactPersonName.setText(contactInformation.getContactPersonName());
                        contactPersonPosition.setText(contactInformation.getContactPersonPosition());
                        contactNumber.setText(contactInformation.getContactPersonPhn());
                        contactMail.setText(contactInformation.getContactPersonMail());
                    }
                }
            }

            @Override
            public void onFailure(Call<ContactInformation> call, Throwable t) {
                Toast.makeText(getActivity(), getResources().getText(R.string.net_error), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
