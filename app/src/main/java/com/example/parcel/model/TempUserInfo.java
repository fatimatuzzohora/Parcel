package com.example.parcel.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class TempUserInfo {

    private Activity activity;
    private Context context;


    private SharedPreferences pref ;
    private SharedPreferences.Editor editor;

    private static final String USER_ID = "temp_user_id";
    private static final String USER_NAME = "temp_user_name";
    private static final String USER_MOBILE = "temp_user_mobile";
    private static final String DEFAULT_MESSAGE = "UserId not found";

    public TempUserInfo(Activity activity) {
        this.activity = activity;
        this.pref = activity.getApplicationContext().getSharedPreferences("TEMP_USER_INFO", Context.MODE_PRIVATE);
        this.editor = pref.edit();
    }


    public TempUserInfo(Context context) {
        this.context = context;
        this.pref = context.getApplicationContext().getSharedPreferences("TEMP_USER_INFO",Context.MODE_PRIVATE);
        this.editor = pref.edit();
    }

    //save for first time
    public void saveTempUserValue(String userId,String userName,String userMobile){
        editor.putString(USER_ID,userId);
        editor.putString(USER_NAME,userName);
        editor.putString(USER_MOBILE,userMobile);

        editor.commit();
        //return  "Saved Successfully";
    }

    //save the value
    public void dsTempUserInfo(String userId,String userName,String userMobile){
            if(pref.contains(USER_ID)){

                //deleteTemUserInfo();
                saveTempUserValue(userId,userName,userMobile);
            }
    }

    //delete the value from preferences
    public void deleteTemUserInfo(){
            editor.clear();
            editor.commit();
    }

    //getting saved value
    public String getUserId(){
            return pref.getString(USER_ID,null);
    }

    public String getUserName(){
        return pref.getString(USER_NAME,null);
    }

    public String getUserMobile() {
        return pref.getString(USER_MOBILE,null);
    }
}
