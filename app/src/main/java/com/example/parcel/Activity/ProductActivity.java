package com.example.parcel.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.parcel.Interface.ApiInterface;
import com.example.parcel.R;
import com.example.parcel.model.ApiClient;
import com.example.parcel.model.ProductSave;
import com.example.parcel.model.TempUserInfo;
import com.example.parcel.model.Validation;
import com.example.parcel.model.spinner.Category;
import com.example.parcel.model.spinner.Unit;
import com.google.gson.JsonPrimitive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    private ApiInterface apiInterface;

    private String userId;

    private Spinner spinnerCategory, spinnerUnit;
    private CheckBox isActiveCB;
    private EditText proNameET,proMeasurementET,proWeightET,proRateET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Toolbar toolbar = findViewById(R.id.tb_merchant_product);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TempUserInfo tempUserInfo = new TempUserInfo(this);
        userId = tempUserInfo.getUserId();

        // Network call
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        //find view by id
        //spinner..
        spinnerCategory = findViewById(R.id.sp_pro_cat);
        spinnerUnit = findViewById(R.id.sp_pro_unit);
        //checkbox..
        isActiveCB =findViewById(R.id.pro_cb);
        //edittext..
        proNameET = findViewById(R.id.product_name_ET);
        proMeasurementET = findViewById(R.id.product_measurement_ET);
        proWeightET = findViewById(R.id.product_weight_ET);
        proRateET = findViewById(R.id.product_rate_ET);


        getCategories();
        getUnits();

    }

    private void getCategories(){
        Call<List<Category>>call = apiInterface.categories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<Category>categories = response.body();

                    ArrayAdapter categoryAdapter = new ArrayAdapter(getApplicationContext(),
                            R.layout.spinner_layout_merchant_request,categories);

                    spinnerCategory.setAdapter(categoryAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    private void getUnits(){
        Call<List<Unit>>call = apiInterface.units();
        call.enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Unit> units = response.body();

                    ArrayAdapter unitAdapter =  new ArrayAdapter(getApplicationContext(),
                            R.layout.spinner_layout_merchant_request,units);

                    spinnerUnit.setAdapter(unitAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Failed to get unit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveProductAPI(ProductSave productSave){
        Call<JsonPrimitive>call = apiInterface.saveProduct(productSave);

        call.enqueue(new Callback<JsonPrimitive>() {
            @Override
            public void onResponse(Call<JsonPrimitive> call, Response<JsonPrimitive> response) {
                if(response.isSuccessful() &&response.code()==200 && response.body()!=null){
                    JsonPrimitive jsonPrimitive = response.body();

                    Toast.makeText(ProductActivity.this, ""+jsonPrimitive.getAsString(), Toast.LENGTH_SHORT).show();

                }
                else Toast.makeText(ProductActivity.this, "The attempt did not successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonPrimitive> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveProduct(View view) {

        Category category = (Category) spinnerCategory.getSelectedItem();
        Unit unit = (Unit) spinnerUnit.getSelectedItem();

        if(isActiveCB.isChecked()==true){
            if(Validation.editTextValidation(proNameET,String.valueOf(R.string.error_msg))){

                ProductSave productSave = new ProductSave(proNameET.getText().toString(),
                                                                category.getCatId(),
                                                                unit.getUnitId(),
                                                                proRateET.getText().toString(),
                                                                proWeightET.getText().toString(),
                                                                proMeasurementET.getText().toString(),
                                                                userId,
                                                                "true");

                saveProductAPI(productSave);

                }

        }
        else Toast.makeText(this, "Please active the product", Toast.LENGTH_SHORT).show();


    }
}
