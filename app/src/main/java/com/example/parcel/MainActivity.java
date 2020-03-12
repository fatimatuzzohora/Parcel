package com.example.parcel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.parcel.Activity.LogIn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            startActivity(new Intent(MainActivity.this, LogIn.class));
        }catch (Exception e) {
            Log.e("main_err", e.getMessage());
        }


        finish();


    }
}
