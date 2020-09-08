package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.wolanjeAfrica.wolanjej.Utils.CheckPhoneNumber;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.Map;

public class TopupOtherNumber extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] selectNumber = {"Other Number", "My Number"};

    private String phoneName;
    private String phoneNumber;
    private String phoneCompany;
    private EditText text;
    private Spinner spin;
    private Toolbar tb;
    public static final String EXTRA_PROVIDER = "com.example.wolanjej.PROVIDER";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_other_number);


        // Get the Intent that started this activity and extract the string
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "Qr_code22":

                break;
            case "Top_up":
                // todo here if there is any
                break;
            case "SelectUserAdapter":
                this.phoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                String userNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);


                EditText tvtext = findViewById(R.id.transContactAmount);
                tvtext.setText(userNumber);
        }

        spin = (Spinner) this.findViewById(R.id.select_other_number);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectNumber);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if ("My Number".equals(selectNumber[position])) {
            Intent move = new Intent(this, Top_up.class);
            startActivity(move);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void moveToContact(View view) {
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TopUpOtherNumber");
        move.putExtra(EXTRA_CLASSTYPE, "TopUpNumber");
        startActivity(move);
    }

    public void TopUpNumberContinue(View view) {
        String key = null;
        String value = null;
        text = findViewById(R.id.amount_layout_other_number);
        String amount = text.getText().toString();

        text = findViewById(R.id.transContactAmount);
        String phone = text.getText().toString();

        Log.e("TAG phone number check", "button pressed to transfer money" + phone);
        Map<String, String> map = CheckPhoneNumber.getInstance().checkPhoneNo(TopupOtherNumber.this, phone);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
        if (amount.isEmpty() || phone.isEmpty()) {
            text.requestFocus();
            Toast.makeText(getApplicationContext(), "please provide Amount", Toast.LENGTH_LONG).show();
            return;
        }
        int x = Integer.parseInt(amount);
        if (amount != null) {
            if (x >= 10) {
                if (x <= 70000) {
                    if (value != null && !value.equals("Fasle")) {
                        Log.e("TAG phone number last", value);
                        movetoPin(value, amount, key);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "The Amount is above 70000", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "The Amount is below 10", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Enter Amount", Toast.LENGTH_LONG).show();
        }

    }

    public void movetoPin(String phone, String amount, String phoneCompany) {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class", "TopUpOtherNumber");
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_PROVIDER, phoneCompany);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
    }

    public void addAmount1(View view) {

        TextView tvAdd = findViewById(R.id.amount_display1);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_layout_other_number);
        tvtext.setText(value);
    }

    public void addAmount2(View view) {
        TextView tvAdd = findViewById(R.id.amount_display2);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_layout_other_number);
        tvtext.setText(value);
    }

    public void addAmount3(View view) {
        TextView tvAdd = findViewById(R.id.amount_display3);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_layout_other_number);
        tvtext.setText(value);
    }

    public void addAmount4(View view) {
        TextView tvAdd = findViewById(R.id.amount_display4);
        String value = (String) tvAdd.getText();

        EditText tvtext = findViewById(R.id.amount_layout_other_number);
        tvtext.setText(value);
    }

    public void OpenQrCodeClass(View view) {
        Intent intent = new Intent(getApplicationContext(), Qr_code22.class);
        startActivity(intent);
    }
}