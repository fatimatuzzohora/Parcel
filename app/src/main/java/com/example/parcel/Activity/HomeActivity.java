package com.example.parcel.Activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcel.Interface.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.model.ApiClient;
import com.example.parcel.model.MonthlyView;
import com.example.parcel.model.TempUserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private TextView totalPickup,totalDelivery,totalOnProcess,totalCancel;
    private RelativeLayout merchantProfile, merchantRequest;
    private RelativeLayout call, msg;
    TempUserInfo tempUserInfo;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.tb_home);

        //monthlyViewInformation
        totalPickup = findViewById(R.id.totalPickup);
        totalDelivery = findViewById(R.id.totalDelivery);
        totalOnProcess = findViewById(R.id.totalOnProcess);
        totalCancel = findViewById(R.id.totalCancel);

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

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        tempUserInfo = new TempUserInfo(this);

        String id = tempUserInfo.getUserId();

        monthlyView(id);

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
      /*  DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }*/

        diaExitFromApp();


    }
    private void diaExitFromApp(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.exit_msg);
        builder.setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finishAffinity();
                HomeActivity.super.onBackPressed();
            }
        });
        builder.show();
    }

    private void monthlyView(String userId){
        Call<MonthlyView>call = apiInterface.monthlyView(userId);

        call.enqueue(new Callback<MonthlyView>() {
            @Override
            public void onResponse(Call<MonthlyView> call, Response<MonthlyView> response) {
                if(response.isSuccessful() && response.body() != null){
                    MonthlyView monthlyView = response.body();
                    totalPickup.setText(monthlyView.getTotalPickup());
                    totalOnProcess.setText(monthlyView.getOnProcess());
                    totalDelivery.setText(monthlyView.getTotalDeliverDone());
                }
            }

            @Override
            public void onFailure(Call<MonthlyView> call, Throwable t) {
                Toast.makeText(HomeActivity.this, getResources().getText(R.string.net_error), Toast.LENGTH_SHORT).show();
            }
        });
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
        else if(id == R.id.nav_mer_product){
            startActivity(new Intent(HomeActivity.this, ProductActivity.class));
        }
        else if (id == R.id.nav_mer_bill) {
            startActivity(new Intent(HomeActivity.this,MerchantBillingActivity.class));
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
        String phone = "01979-919107";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    public void messenger(){
        try
        {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://messaging/" + "101882344564923"));
            startActivity(i);
        }
        catch (ActivityNotFoundException ex)
        {
            Toast.makeText(HomeActivity.this, "Ops!Can't open Facebook messenger right now. Please try again later.", Toast.LENGTH_SHORT).show();
        }



    }
}
