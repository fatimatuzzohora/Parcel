package com.example.parcel.model;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.parcel.R;

public class Validation {

    public static CharSequence hintName;

    //Edittext validation
    public static boolean editTextValidation(final EditText editText, String errorMssage){
        if(!editText.getText().toString().isEmpty()){
            editText.clearFocus();

            return true;
        }
        else {
            //editText.setError(errorMssage);

            editText.requestFocus();
            hintName = editText.getHint();
            return false;
        }

    }

}
