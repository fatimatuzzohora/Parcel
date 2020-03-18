package com.example.parcel.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parcel.R;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    private Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        signInBtn = findViewById(R.id.sign_in);
        signInBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.sign_in :
                startActivity(new Intent(LogIn.this, HomeActivity.class));
                break;
        }

    }
}
