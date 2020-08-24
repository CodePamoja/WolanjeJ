package com.wolanjeAfrica.wolanjej;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import maes.tech.intentanim.CustomIntent;

public class Education1 extends AppCompatActivity {
    private TextView tx;
    private Button btnpro;
    private ImageButton btnback;
    private Toolbar tb;
    private ArrayAdapter adapter;
    private Spinner spinner1, spinner2, spinner3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_1);
        btnpro = findViewById(R.id.buttonproceed);
        setActionBarColor();
        setToolBar(tb);
        selectCounty();
        selectSchool();
        selectFeeDetails();

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "EnterPin":
                showDialogPayTutionFee();
                break;
            case "Education50":
            case "Education_2":
                break;
        }

    }


    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        //clear FLAG_TRANSLUCENT_STATUS flag:

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }

    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbarEducation);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this, Education_50.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                        finish();
                    }
                }
        );

    }


    private void showDialogPayTutionFee() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.transfer_success_popup, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button button = view.findViewById(R.id.dismiss_success);
        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });
        alertDialog.show();
    }

    private void selectCounty() {
        spinner1 = (Spinner) findViewById(R.id.spinnerSelectCounty);
        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.select_county, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
    }
    private void selectSchool(){
        spinner2 = (Spinner) findViewById(R.id.spinnerSelectSchool);
        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.select_School, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

    }
    private void selectFeeDetails() {
        spinner3 = (Spinner) findViewById(R.id.spinnerFeeDetails);
        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.select_fee_details, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
    }


    public void confirmfeepayment(View view) {
        Intent intent = new Intent(Education1.this, Education_2.class);
        startActivity(intent);
        CustomIntent.customType(Education1.this, "left-to-right");
    }

}
