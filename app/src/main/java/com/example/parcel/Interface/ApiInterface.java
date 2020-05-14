package com.example.parcel.Interface;

import com.example.parcel.model.BillingInformation;
import com.example.parcel.model.BusinessInformation;
import com.example.parcel.model.ContactInformation;
import com.example.parcel.model.MerchantInformation;
import com.example.parcel.model.MonthlyView;
import com.example.parcel.model.ObjectAuth;
import com.example.parcel.model.ServerResponse;
import com.google.gson.JsonObject;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

   /* @GET("login")
    Call<ServerResponse> userSignIn(@FieldMap Map<String, String> userAuth);*/


 /*   @GET("login")
    Call<ObjectAuth> userSignIn(@Query("UserId") String userId,
                                @Query("Password") String password);*/

 /*   @GET("api/login")
    Call<JsonObject> userSignIn(@Query("UserId") String userId,
                                @Query("Password") String password);*/



    //Log in activity
    @POST("api/login")
    Call<ServerResponse> userSignIn(@Query("UserId") String userId,
                                    @Query("Password") String password);


    //Dashboard Activity...........

    //Monthly view for dashboard
    @GET("Sales/Api/MerchantDashboard")
    Call<MonthlyView> monthlyView(@Query("Id") String userId);

    //Merchant Profile Activity...........

    //Merchant Profile Information
    @GET("Sales/Api/MerchantProfile")
    Call<MerchantInformation> merchantProfileInfo(@Query("Id") String userId);

    //Contact Information Information
    @GET("Sales/Api/MerchantProfile")
    Call<ContactInformation> contactInfo(@Query("Id") String userId);

    //Billing Information Information
    @GET("Sales/Api/MerchantProfile")
    Call<BillingInformation> billingInfo(@Query("Id") String userId);


    //Business Information Information
    @GET("Sales/Api/MerchantProfile")
    Call<BusinessInformation> businessInfo(@Query("Id") String userId);

}
