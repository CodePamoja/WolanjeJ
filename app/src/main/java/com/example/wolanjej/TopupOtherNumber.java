package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopupOtherNumber extends AppCompatActivity  implements AdapterView.OnItemSelectedListener  {
    String[] selectNumber = {"Other Number", "My Number"};

    private String phoneName;
    private String phoneNumber;
    private String phoneCompany;
    private EditText text;
    private Spinner spin;

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
        if(className.equals("Top_up")) {
            // todo here if there is any
        }else if (className.equals("SelectUserAdapter")){
            this.phoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
            String userNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
            this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);

            EditText tvtext =  findViewById(R.id.transContactAmount);
            tvtext.setText(userNumber);
        }

        spin = (Spinner) this.findViewById(R.id.select_other_number);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,selectNumber);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if ("My Number".equals(selectNumber[position])){
            Intent move = new Intent(this, Top_up.class);
            startActivity(move);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void moveToContact(View view) {
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class","TopUpOtherNumber");
        move.putExtra(EXTRA_CLASSTYPE, "TopUpNumber");
        startActivity(move);
    }

    public void TopUpNumberContinue(View view) {
        text = findViewById(R.id.amount_layout_other_number);
        String amount = text.getText().toString();

        text = findViewById(R.id.transContactAmount);
        String phone = text.getText().toString();

        Log.e("TAG phone number check", "button pressed to transfer money" + phone);
        String phonenumber = changePhoneNo(phone, view);
        Log.e("TAG phone number last", phonenumber);
        int x =Integer.parseInt(amount);
        if (amount!=null){
            if (x>=10){
                if (x<=70000){
                    if(phonenumber!="Fasle"){
                        movetoPin(phonenumber, amount);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"The Amount is above 70000" , Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "The Amount is below 10", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Enter Amount", Toast.LENGTH_LONG).show();
        }

    }

    public String changePhoneNo(String inputPhone, View view){
        String validPhoneNo = "Fasle";
        String safaricom = "^(?:254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-9])|(?:6[8-9])|(?:5[7-9])|(?:4[5-6])|(?:4[8])|(4[0-3]))[0-9]{6})$";
        String telkom = "^(?:254|\\+254|0)?(7(?:(?:[7][0-9]))[0-9]{6})$";
        String airtel = "^(?:254|\\+254|0)?(7(?:(?:[3][0-9])|(?:5[0-6])|(?:6[2])|(8[0-9]))[0-9]{6})$";
        Pattern patt;
        Matcher match;
        if (!inputPhone.isEmpty()) {
            String replPhone1 = inputPhone.trim();
            String replPhone2 = replPhone1.replaceAll("\\s", "");
            patt = Pattern.compile(safaricom);
            match = patt.matcher(replPhone2);
            if (match.find()) {
                Toast.makeText(getApplicationContext(), "Safaricom Number", Toast.LENGTH_LONG).show();
                String replPhone3 = "null";
                phoneCompany = "safaricom";
                if(replPhone2.startsWith("0")){
                    replPhone3 = replPhone2.replaceFirst("0", "\\254");
                    Log.e("TAG phone starts 0", replPhone3);
                    validPhoneNo = replPhone3;
                }else if(replPhone2.startsWith("7")){
                    replPhone3 = replPhone2.replaceFirst("7", "\\254");
                    Log.e("TAG phone starts 7", replPhone3);
                    validPhoneNo = replPhone3;
                }else if(replPhone2.startsWith("+")){
                    validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]","");
                    Log.e("TAG phone number +", validPhoneNo);
                }
            } else {
                patt = Pattern.compile(airtel);
                match = patt.matcher(replPhone2);
                if (match.find()) {
                    Toast.makeText(getApplicationContext(), "Airtel Number", Toast.LENGTH_LONG).show();
                    String replPhone3 = "null";
                    phoneCompany = "airtel";
                    if(replPhone2.startsWith("0")){
                        replPhone3 = replPhone2.replaceFirst("0", "\\254");
                        Log.e("TAG phone starts 0", replPhone3);
                        validPhoneNo = replPhone3;
                    }else if(replPhone2.startsWith("7")){
                        replPhone3 = replPhone2.replaceFirst("7", "\\254");
                        Log.e("TAG phone starts 7", replPhone3);
                        validPhoneNo = replPhone3;
                    }else if(replPhone2.startsWith("+")){
                        validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]","");
                        Log.e("TAG phone number +", validPhoneNo);
                    }
                }else {
                    patt = Pattern.compile(telkom);
                    match = patt.matcher(replPhone2);
                    if (match.find()) {
                        Toast.makeText(getApplicationContext(), "Telkom Number", Toast.LENGTH_LONG).show();
                        String replPhone3 = "null";
                        phoneCompany = "telkom";
                        if(replPhone2.startsWith("0")){
                            replPhone3 = replPhone2.replaceFirst("0", "\\254");
                            Log.e("TAG phone starts 0", replPhone3);
                            validPhoneNo = replPhone3;
                        }else if(replPhone2.startsWith("7")){
                            replPhone3 = replPhone2.replaceFirst("7", "\\254");
                            Log.e("TAG phone starts 7", replPhone3);
                            validPhoneNo = replPhone3;
                        }else if(replPhone2.startsWith("+")){
                            validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]","");
                            Log.e("TAG phone number +", validPhoneNo);
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Please enter a valid mobile number 'Safaricom only'", Toast.LENGTH_LONG).show();
                        Log.e("TAG phone No not check", replPhone2);
                        moveToContact(view);
                    }
                }

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a mobile number ", Toast.LENGTH_LONG).show();
            moveToContact(view);
        }

        return validPhoneNo;
    }

    public void movetoPin(String phone,String amount) {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class","TopUpOtherNumber");
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_PROVIDER, phoneCompany);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
    }

    public void addAmount1(View view) {

        TextView tvAdd =  findViewById(R.id.amount_display1);
        String value = (String) tvAdd.getText();

        EditText tvtext =  findViewById(R.id.amount_layout_other_number);
        tvtext.setText(value);
    }

    public void addAmount2(View view) {
        TextView tvAdd =  findViewById(R.id.amount_display2);
        String value = (String) tvAdd.getText();

        EditText tvtext =  findViewById(R.id.amount_layout_other_number);
        tvtext.setText(value);
    }

    public void addAmount3(View view) {
        TextView tvAdd =  findViewById(R.id.amount_display3);
        String value = (String) tvAdd.getText();

        EditText tvtext =  findViewById(R.id.amount_layout_other_number);
        tvtext.setText(value);
    }

    public void addAmount4(View view) {
        TextView tvAdd =  findViewById(R.id.amount_display4);
        String value = (String) tvAdd.getText();

        EditText tvtext =  findViewById(R.id.amount_layout_other_number);
        tvtext.setText(value);
    }
}