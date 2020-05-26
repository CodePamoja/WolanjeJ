package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Sponsor01 extends AppCompatActivity {
    private Spinner spinner;
    private EditText editText,editText1,editText2;
    private Toolbar tb;

    String [] Sponsors = new String[]{"National Government","County/Local Government","Religious Groups","Foundation","Donor - OrganiZation that runs Funding","Companies","Individuals","Learning Institutions", "Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor01);
        editText1 = findViewById(R.id.scholarship_name);

        spinner = this.findViewById(R.id.select_sponsor);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Sponsors);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


    }

    public void moveSponsor02(View view) {
        startActivity(new Intent(this,Sponsor02.class));
    }


}