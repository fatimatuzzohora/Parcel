package com.example.parcel.Activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.parcel.Interface.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.model.ApiClient;
import com.example.parcel.model.MerchantRequisition;
import com.example.parcel.model.spinner.DeliveryPerson;
import com.example.parcel.model.spinner.District;
import com.example.parcel.model.spinner.Service;
import com.example.parcel.model.TempUserInfo;
import com.google.gson.JsonPrimitive;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MerchantRequestActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner service_spinnner, district_spinner, delivery_person_spinner;
    private EditText dateET;
    private Button merchantReqSave;

    private Service serviceId;

    private ApiInterface apiInterface;

    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_request);

        Toolbar toolbarMerReq = findViewById(R.id.tb_mer_req);

        setSupportActionBar(toolbarMerReq);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find view by id........
        service_spinnner = findViewById(R.id.sp_service);
        district_spinner = findViewById(R.id.sp_district);
        delivery_person_spinner = findViewById(R.id.sp_delivery_person);

        dateET = findViewById(R.id.date_ET);
        merchantReqSave = findViewById(R.id.mer_req_btn);

        setSupportActionBar(toolbarMerReq);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TempUserInfo tempUserInfo = new TempUserInfo(this);
        userId = tempUserInfo.getUserId();

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        //set onclick listener...
        dateET.setOnClickListener(this);
        merchantReqSave.setOnClickListener(this);

        //call the method for service....
        getService();
        getDistrict();
        Toast.makeText(this, ""+userId, Toast.LENGTH_SHORT).show();
        getDeliverPerson(userId);
    }


    //class for getting the service
    private void getService(){
        Call<List<Service>>call = apiInterface.service();
        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if(response.isSuccessful() && response != null){

                    List<Service>serviceList = response.body();

                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),
                            R.layout.spinner_layout_merchant_request,
                            serviceList);

                    service_spinnner.setAdapter(arrayAdapter);
                    service_spinnner.setSelection(0);

                }

            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {

            }
        });
    }

    private void getDistrict(){
        Call<List<District>>call = apiInterface.district();
        call.enqueue(new Callback<List<District>>() {
            @Override
            public void onResponse(Call<List<District>> call, Response<List<District>> response) {
                if(response.isSuccessful() && response.body()!= null){
                    List<District>districtList = response.body();

                    Toast.makeText(MerchantRequestActivity.this, ""+districtList.get(0).getDistrictName(), Toast.LENGTH_SHORT).show();

                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),
                            R.layout.spinner_layout_merchant_request,
                            districtList);

                    district_spinner.setAdapter(arrayAdapter);
                    district_spinner.setSelection(0);
                }
            }

            @Override
            public void onFailure(Call<List<District>> call, Throwable t) {

            }
        });
    }

    private void getDeliverPerson(String userId){
        Call<List<DeliveryPerson>>call = apiInterface.deliveryPerson(userId);
        call.enqueue(new Callback<List<DeliveryPerson>>() {
            @Override
            public void onResponse(Call<List<DeliveryPerson>> call, Response<List<DeliveryPerson>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<DeliveryPerson>deliveryPeople = response.body();

                    ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),
                            R.layout.spinner_layout_merchant_request,
                            deliveryPeople);

                    delivery_person_spinner.setAdapter(arrayAdapter);
                    delivery_person_spinner.setSelection(0);
                }
            }

            @Override
            public void onFailure(Call<List<DeliveryPerson>> call, Throwable t) {

            }
        });
    }

    //Datepicker Method ....
    private void datePicker(final EditText editText){
       final Calendar calendar;
       int year, month, day;

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
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        datePickerDialog.show();
    }

    //API calls for saving merchant request.....
    public void saveMerchantRequisition(MerchantRequisition merchantRequisition){
        Call<JsonPrimitive>call = apiInterface.saveRequisition(merchantRequisition);
        call.enqueue(new Callback<JsonPrimitive>() {
            @Override
            public void onResponse(Call<JsonPrimitive> call, Response<JsonPrimitive> response) {
                if(response.code()==200 && response.isSuccessful()){
                    JsonPrimitive jsonPrimitive = response.body();
                    Log.d("result1",jsonPrimitive.getAsString());
                    Toast.makeText(MerchantRequestActivity.this, jsonPrimitive.getAsString(), Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MerchantRequestActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonPrimitive> call, Throwable t) {
                Toast.makeText(MerchantRequestActivity.this, "Network Problem "+t, Toast.LENGTH_SHORT).show();
                Log.d("result3", t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.date_ET:
                datePicker(dateET);
                break;

            case R.id.mer_req_btn:
                if(service_spinnner.getSelectedItem() != null
                        && district_spinner.getSelectedItem()!=null
                            && delivery_person_spinner.getSelectedItem()!=null
                                && dateET.getText()!=null){

                    Service service = (Service) service_spinnner.getSelectedItem();
                    District district = (District) district_spinner.getSelectedItem();
                    DeliveryPerson deliveryPerson = (DeliveryPerson) delivery_person_spinner.getSelectedItem();


                    //Binding the data......
                    MerchantRequisition merchantRequisition = new MerchantRequisition(service.getServiceId(),
                                                                        district.getDistrictId(),
                                                                        deliveryPerson.getDeliverPersonId(),
                                                                        dateET.getText().toString(),
                                                                        userId);

                    //network call
                    saveMerchantRequisition(merchantRequisition);

                }


                break;
        }
    }
}
