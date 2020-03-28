package com.example.parcel.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.parcel.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private RelativeLayout merchantProfile, merchantRequest;
    private RelativeLayout call, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.tb_home);

        merchantProfile = findViewById(R.id.merchant_profile);
        merchantRequest = findViewById(R.id.merchant_request);
        call = findViewById(R.id.call);
        msg = findViewById(R.id.msg);

        merchantProfile.setOnClickListener(this);
        merchantRequest.setOnClickListener(this);
        call.setOnClickListener(this);
        msg.setOnClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //FloatingActionButton fab = findViewById(R.id.fab);
      /*  fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar ,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mer_profile) {
            startActivity(new Intent(HomeActivity.this, MerchantProfileActivity.class));
        }
        else if (id == R.id.nav_mer_req) {
            startActivity(new Intent(HomeActivity.this,MerchantRequestActivity.class));
        }
        else if (id == R.id.nav_pick_up) {

        } else if (id == R.id.nav_parcel) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.merchant_profile :
                startActivity(new Intent(HomeActivity.this, MerchantProfileActivity.class));
                break;

            case R.id.merchant_request:
                startActivity(new Intent(HomeActivity.this,MerchantRequestActivity.class));
                break;


            case R.id.call:
                callHotline();
                break;


            case R.id.msg:
                messenger();
                break;

        }
    }

    public void callHotline(){
        String phone = "01844000177";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    public void messenger(){
        try
        {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://messaging/" + "100024317349345"));
            startActivity(i);
        }
        catch (ActivityNotFoundException ex)
        {
            Toast.makeText(HomeActivity.this, "Oups!Can't open Facebook messenger right now. Please try again later.", Toast.LENGTH_SHORT).show();
        }



    }
}
