package com.example.parcel.Interface;

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
    Call<ServerResponse> userSignIn(@Query("UserId") String userId,
                                    @Query("Password") String password);*/


 /*   @GET("login")
    Call<ObjectAuth> userSignIn(@Query("UserId") String userId,
                                @Query("Password") String password);*/

    //Log in activity
    @GET("api/login")
    Call<JsonObject> userSignIn(@Query("UserId") String userId,
                                @Query("Password") String password);

    //Monthly view for dashboard
    @GET("Sales/Api/MerchantDashboard")
    Call<MonthlyView> monthlyView(@Query("Id") String userId);

}
