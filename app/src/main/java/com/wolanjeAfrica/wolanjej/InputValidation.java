package com.wolanjeAfrica.wolanjej;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

public class InputValidation extends AppCompatActivity implements TextWatcher {

    private int itemno;
    private String number;

    public InputValidation(int itemno) {
        this.itemno = itemno;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        number = String.valueOf(s);
    }

    @Override
    public void afterTextChanged(Editable s) {


    }

    public String getNumber() {
        return number;
    }
}
