package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Top_up extends AppCompatActivity  implements AdapterView.OnItemSelectedListener,View.OnClickListener  {
    String[] selectNumber = {"My Number","Other Number"};
    private Button button;
    private String sessionID;


    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        // Get the Intent that started this activity and extract the string
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if (className.equals("Home")) {
            this.sessionID = intentExtra.getStringExtra(Home.EXTRA_SESSION);
        } else if (className.equals("TopupOtherNumber")) {
            this.sessionID = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_SESSION);
        }

        Spinner spin = (Spinner) this.findViewById(R.id.select_number);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectNumber);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

//        Button button = findViewById(R.id.btn_continue_top_up);
//        button.setOnClickListener((View.OnClickListener) this);

    }

//    @Override
//    public void onClick(View v) {
//        Intent intent;
//        switch (v.getId()){
//            case R.id.income_details101: intent = new Intent(this, IncomeDetails.class);startActivity(intent);
//            break;
//            case R.id.btn_continue_top_up:intent = new Intent(this,TopupOtherNumber.class);startActivity(intent);
//            default:break;
//    }
//}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e("session before contact", sessionID);
        if ("Other Number".equals(selectNumber[position])) {
            Toast.makeText(getApplicationContext(), selectNumber[position], Toast.LENGTH_LONG).show();
            Intent move = new Intent(this, TopupOtherNumber.class);
            move.putExtra("Class", "Top_up");
            move.putExtra(EXTRA_SESSION, sessionID);
            startActivity(move);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void moveToBuy(View view) {
        EditText text = findViewById(R.id.amount_top_up);
        String amount = text.getText().toString();
        int x = Integer.parseInt(amount);
        if (amount != null) {
            if (x >= 100) {
                if (x <= 70000) {
                    movetoPin("25415668934", amount);
                } else {
                    Toast.makeText(getApplicationContext(), "The Amount is above 70000", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "The Amount is below 100", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Enter Amount", Toast.LENGTH_LONG).show();
        }

    }

    public void movetoPin(String phone, String amount) {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class", "Top_up");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
    }

    public void addAmount1(View view) {

        TextView tvAdd = findViewById(R.id.amount_display01);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_top_up);
        tvtext.setText(value);
    }

    public void addAmount2(View view) {
        TextView tvAdd = findViewById(R.id.amount_display02);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_top_up);
        tvtext.setText(value);
    }

    public void addAmount3(View view) {
        TextView tvAdd = findViewById(R.id.amount_display03);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_top_up);
        tvtext.setText(value);
    }

    public void addAmount4(View view) {
        TextView tvAdd = findViewById(R.id.amount_display04);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_top_up);
        tvtext.setText(value);
    }

    @Override
    public void onClick(View v) {

    }
}
