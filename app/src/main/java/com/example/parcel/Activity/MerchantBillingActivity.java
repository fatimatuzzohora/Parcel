package com.example.parcel.Activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcel.Adapter.AdapterBillingReport;
import com.example.parcel.Interface.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.model.ApiClient;
import com.example.parcel.model.BillingReport;
import com.example.parcel.model.TempUserInfo;
import com.example.parcel.model.Validation;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MerchantBillingActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView billingReportLV;
    private EditText startDate, endDate;
    private ImageView searchbtn;

    private TextView parcelValueTotal,codChargeTotal, serviceChargeTotal, billTotal;

    private double parcelTotal=0.0;
    private double codTotal=0.0;
    private double serviceTotal= 0.0;
    private double billChargeTotal =0.0;

    private ProgressBar billingReportPB;
    private TextView billingReportTV;

    private String userId;
    private ApiInterface apiInterface;

    private Calendar calendar;
    private int year, month, day;


    private AdapterBillingReport adapterBillingReport;
    private List<BillingReport> billingReports ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_billing);

        Toolbar toolbar = findViewById(R.id.tb_merchant_billing);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get the user id from shared preference....
        TempUserInfo tempUserInfo = new TempUserInfo(getApplicationContext());
        userId = tempUserInfo.getUserId();

        // Network call...
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        //find view by id....
        billingReportLV = findViewById(R.id.billing_report_lv);
        startDate = findViewById(R.id.start_date_ET);
        endDate = findViewById(R.id.end_date_ET);
        searchbtn = findViewById(R.id.search_btn);

        billingReportPB = findViewById(R.id.billing_report_pb);
        billingReportTV = findViewById(R.id.billing_report_tv);

        parcelValueTotal=findViewById(R.id.sub_parcel_value);
        codChargeTotal=findViewById(R.id.sub_cod_charge);
        serviceChargeTotal= findViewById(R.id.sub_service_charge);
        billTotal = findViewById(R.id.sub_bill);

        //click events....
        startDate.setOnClickListener(this);
        endDate.setOnClickListener(this);
        searchbtn.setOnClickListener(this);


    }


    //Network call...
    private void billingReport(){
        Call<List<BillingReport>>listCall = apiInterface.billingReport(userId,startDate.getText().toString(),endDate.getText().toString());

        listCall.enqueue(new Callback<List<BillingReport>>() {
            @Override
            public void onResponse(Call<List<BillingReport>> call, Response<List<BillingReport>> response) {
                if(response.isSuccessful() && response.body() != null){

                    billingReports = response.body();
                    showReportIntoList();
                }
                else {
                    billingReportPB.setVisibility(View.INVISIBLE);
                    Toast.makeText(MerchantBillingActivity.this, getResources().getString(R.string.net_error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BillingReport>> call, Throwable t) {
                billingReportPB.setVisibility(View.INVISIBLE);
                Toast.makeText(MerchantBillingActivity.this, getResources().getString(R.string.net_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //set the list of the report in the adapter
    private void showReportIntoList() {
        if (billingReports.size()==0){

            //for no value message...
            billingReportLV.setVisibility(View.INVISIBLE);
            billingReportTV.setVisibility(View.VISIBLE);
            billingReportPB.setVisibility(View.INVISIBLE);

            //Subtotal TextView...
            parcelValueTotal.setText("0.0");
            codChargeTotal.setText("0.0");
            serviceChargeTotal.setText("0.0");
            billTotal.setText("0.0");

        }else
        {
            //Textview & progressbar will be invisible if get the data
            billingReportTV.setVisibility(View.INVISIBLE);
            billingReportPB.setVisibility(View.INVISIBLE);
            billingReportLV.setVisibility(View.VISIBLE);

            //setting listview in to the adapter
            adapterBillingReport =  new AdapterBillingReport(getApplicationContext(),R.layout.custom_merchant_billing_lv,billingReports);
            billingReportLV.setAdapter(adapterBillingReport);

            //showing the sub total value ....
            for(int i=0; i<billingReports.size();i++){

                parcelTotal += Double.valueOf(billingReports.get(i).getParcelValue());
                parcelValueTotal.setText(String.valueOf(decimalFormat(parcelTotal)));

                codTotal += Double.valueOf(billingReports.get(i).getCodCharge());
                codChargeTotal.setText(String.valueOf(decimalFormat(codTotal)));

                serviceTotal += Double.valueOf(billingReports.get(i).getDeliveryCharge());
                serviceChargeTotal.setText(String.valueOf(decimalFormat(serviceTotal)));

                billChargeTotal += Double.valueOf(billingReports.get(i).getMerchantBill());
                billTotal.setText(String.valueOf(decimalFormat(billChargeTotal)));
            }

        }

    }

    //Datepicker Method ....
    public void datePicker(final EditText editText){
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                calendar.set(year,month,day);
                String date = sdf.format(calendar.getTime());

                //set the value to the edit text...
                editText.setText(date);

            }
        },year,month,day);

        // disable future date
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_date_ET :
                datePicker(startDate);
                break;

            case R.id.end_date_ET:
                datePicker(endDate);
                break;

            case R.id.search_btn:
                if(Validation.editTextValidation(startDate,getResources().getString(R.string.error_msg))
                        && Validation.editTextValidation(endDate, getResources().getString(R.string.error_msg))){

                    //It will hide the virtual keyboard...........
                    //New Learning........
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                    //showing progressbar after clicking search button...
                    billingReportPB.setVisibility(View.VISIBLE);
                    billingReportTV.setVisibility(View.INVISIBLE);

                    //fetch the billing report data by calling API
                    billingReport();

                }
                break;
        }
    }

    // decimal format
    public String decimalFormat(double value){
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(value);
    }

}
