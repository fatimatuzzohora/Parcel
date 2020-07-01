package com.example.parcel;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.parcel.Activity.HomeActivity;
import com.example.parcel.Activity.LogIn;

public class MainActivity extends AppCompatActivity {

    ImageView splashImage;
    Animation bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation declaration...
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        splashImage =findViewById(R.id.splash_img);
        splashImage.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LogIn.class));
                finish();
            }
        },2000);

      /*  try {
            startActivity(new Intent(MainActivity.this, LogIn.class));
        }catch (Exception e) {
            Log.e("main_err", e.getMessage());
        }
        finish();*/
    }
}
