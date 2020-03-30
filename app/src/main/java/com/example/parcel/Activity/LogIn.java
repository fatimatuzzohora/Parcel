package com.example.parcel.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcel.ApiClient;
import com.example.parcel.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.ServerResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    private Button signInBtn;
    private EditText userId, password;

    private HashMap<String, String> userAuth = new HashMap<>();

    private ApiInterface apiInterface;


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

        //set on click listener
        signInBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.sign_in :
               // startActivity(new Intent(LogIn.this, HomeActivity.class));
                userAuth.put("UserId",userId.getText().toString());
                userAuth.put("Password",password.getText().toString());
                //Toast.makeText(this, userAuth.get("UserId"), Toast.LENGTH_SHORT).show();
                signIn();
                break;
        }

    }

    private void signIn(){
        userAuth.put("UserId",userId.getText().toString());
        userAuth.put("Password",password.getText().toString());

        try{
            Call<ServerResponse>call = apiInterface.userSignIn(userAuth);
            call.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.isSuccessful()){
                        ServerResponse serverResponse = response.body();
                        Toast.makeText(LogIn.this, "It success", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(LogIn.this, "Message"+ serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText(LogIn.this, "Failed with"+t, Toast.LENGTH_SHORT).show();
                }
            });

        }
        catch (Exception ex){
            Toast.makeText(this, "Catch the problem"+ex, Toast.LENGTH_SHORT).show();
            Log.e("error",ex.getMessage());
        }
    }



}
