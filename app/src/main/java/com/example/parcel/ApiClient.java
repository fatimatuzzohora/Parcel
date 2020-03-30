package com.example.parcel;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://papyrusflits.com/api/";
    //public static final String BASE_URL = "http://192.168.1.9/Web/halkhata/api/v1/main/";
/*
    static Gson gson = new GsonBuilder().setLenient().create();

    static OkHttpClient client = new OkHttpClient();*/
    public static Retrofit retrofit = null;

    // create a method that return a instance of retrofit
    public static Retrofit getApiClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
