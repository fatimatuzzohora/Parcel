package com.example.parcel.Interface;

import com.example.parcel.model.BillingInformation;
import com.example.parcel.model.BillingReport;
import com.example.parcel.model.BusinessInformation;
import com.example.parcel.model.ContactInformation;
import com.example.parcel.model.MerchantRequisition;
import com.example.parcel.model.ProductSave;
import com.example.parcel.model.spinner.Category;
import com.example.parcel.model.spinner.DeliveryPerson;
import com.example.parcel.model.spinner.District;
import com.example.parcel.model.MerchantInformation;
import com.example.parcel.model.MonthlyView;
import com.example.parcel.model.ServerResponse;
import com.example.parcel.model.spinner.Service;
import com.example.parcel.model.spinner.Unit;
import com.google.gson.JsonPrimitive;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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

    //Merchant Billing Report
    @GET("Sales/Api/MerchantBill")
    Call<List<BillingReport>> billingReport (@Query("Id") String userId,
                                             @Query("StartDate") String startDate,
                                             @Query("EndDate") String endDate);


    /*--------------------------Merchant Requisition.....-----------------------*/

   /* -------------Getting Dropdown information using API------------ */

    //Get the service name...
    @POST("Sales/Api/Service/GetServices")
    Call<List<Service>>service();

    //Get the District name...
    @POST("Sales/Api/District/GetAllDistrict")
    Call<List<District>>district();

    //Get the delivery person name.....
    @POST("Sales/Api/MerchantServiceRequisition/GetAllDeliveryPerson")
    Call<List<DeliveryPerson>>deliveryPerson(@Query("Id") String userId);

/*    //Save the requisition data to the server
    @POST("/Sales/Api/MerchantServiceRequisition/Post")
    Call<JsonObject>saveRequisition(@Body MerchantRequisition requisitionList);*/

    //Save the requisition data to the server
    @POST("/Sales/Api/MerchantServiceRequisition/Post")
    Call<JsonPrimitive>saveRequisition(@Body MerchantRequisition merchantRequisition);

   /* .......Product Activity API Call......*/

    //Spinner value for category
    @POST("/Sales/Api/ProductCategory/GetAllCategories")
    Call<List<Category>>categories();

    //Spinner value for units
    @POST("/Sales/Api/UOM/GetAllUOM")
    Call<List<Unit>>units();

    //Product save API
    @POST("/Sales/Api/Product/Post")
    Call<JsonPrimitive>saveProduct(@Body ProductSave productSave);

}
