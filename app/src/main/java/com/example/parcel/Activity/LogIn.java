package com.example.parcel.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcel.model.ApiClient;
import com.example.parcel.Interface.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.model.ObjectAuth;
import com.example.parcel.model.ServerResponse;
import com.example.parcel.model.TempUserInfo;
import com.example.parcel.model.Validation;
import com.google.gson.JsonObject;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    private Button signInBtn;
    private EditText userId, password;

    private HashMap<String, String> userAuth = new HashMap<>();

    private ApiInterface apiInterface;
    TempUserInfo tempUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

       /* findViewById*/
        signInBtn = findViewById(R.id.sign_in);
        userId = findViewById(R.id.user_id);
        password= findViewById(R.id.password);

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        //Shared preferences....
        tempUserInfo = new TempUserInfo(this);

        //set on click listener
        signInBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in :
                if (Validation.editTextValidation(userId, String.valueOf(R.string.error_msg))
                        && Validation.editTextValidation(password, getResources().getString(R.string.error_msg))) {
                            //Login method calls.....
                            signIn();
                        }
                break;
        }

    }

    //API Network Call
    private void signIn(){
        Call<ServerResponse>call =apiInterface.userSignIn(userId.getText().toString(), password.getText().toString());

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                try {
                    if (response.isSuccessful() && response.body() != null) {
                        ServerResponse serverResponse = response.body();

                        String msg = serverResponse.getMessage();
                        Toast.makeText(LogIn.this, "Success" + msg, Toast.LENGTH_SHORT).show();

                        String userId = serverResponse.getId();
                        tempUserInfo.saveTempUserValue(userId);
                        startActivity(new Intent(LogIn.this, HomeActivity.class));
                        }
                }
                catch (Exception ex){
                    Toast.makeText(LogIn.this, "not success "+ex, Toast.LENGTH_SHORT).show();
                    Log.d("error", String.valueOf(ex));
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(LogIn.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }





/*    //API Network call
   private void signIn() {

       Call<JsonObject> call = apiInterface.userSignIn(userId.getText().toString(), password.getText().toString());
       call.enqueue(new Callback<JsonObject>() {
           @Override
           public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
               if (response.isSuccessful() && response.body() != null) {
                   JsonObject jsonObject = response.body();
                   JsonObject jsonObject1 = jsonObject.getAsJsonObject("objMerchantAuth");
                   //Condition for either user is valid or not...
                   if(jsonObject1.get("IsSuccess").getAsString().equals("true")){
                       String userId = jsonObject1.get("Id").getAsString();

                       //Save userid in shared preference....
                       tempUserInfo.saveTempUserValue(userId);
                       startActivity(new Intent(LogIn.this,HomeActivity.class));
                   }
                   else Toast.makeText(LogIn.this, "The user is not valid!", Toast.LENGTH_SHORT).show();
               }
               else Toast.makeText(LogIn.this, "Unsucessful", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<JsonObject> call, Throwable t) {
               Log.d("response", t.getMessage());
               Toast.makeText(LogIn.this, getResources().getText(R.string.net_error), Toast.LENGTH_SHORT).show();
           }
       });

   }*/

/* private void signIn(){
     Call<ObjectAuth>call = apiInterface.userSignIn(userId.getText().toString(),password.getText().toString());
     call.enqueue(new Callback<ObjectAuth>() {
         @Override
         public void onResponse(Call<ObjectAuth> call, Response<ObjectAuth> response) {
             if(response.isSuccessful()){
                 ObjectAuth objectAuth = response.body();
                 Log.d("response",objectAuth.getServerResponse().getName());
             }
             else Toast.makeText(LogIn.this, "Failure", Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onFailure(Call<ObjectAuth> call, Throwable t) {
             Log.d("response",t.getMessage());
         }
     });

 }*/

}
