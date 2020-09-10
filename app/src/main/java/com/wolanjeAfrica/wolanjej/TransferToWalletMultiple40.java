package com.wolanjeAfrica.wolanjej;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferToWalletMultiple40 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] selectUser = {"Multiple Users", "Single User"};

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_PHONENUMBER1 = "com.example.wolanjej.PHONENUMBER1";
    public static final String EXTRA_PHONENUMBER2 = "com.example.wolanjej.PHONENUMBER2";
    public static final String EXTRA_PHONENAME1 = "com.example.wolanjej.PHONENAME1";
    public static final String EXTRA_PHONENAME2 = "com.example.wolanjej.PHONENAME2";
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    private Button confirmButton;
    private String sessionID;
    private String AGENTNO;
    private String phoneNumber1, phoneNumber2;
    private String phoneName1, phoneName2;
    private String phoneCompany;
    private EditText editText1, editText2;
    private Spinner spin;
    private TextView textView;
    private SharedPreferences pref;
    private Button button1, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_wallet_multiple40);
        setToolBar();
        pref = getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");

        pref = getApplicationContext().getSharedPreferences("TransferToWalletMultiple40", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        button1 = (Button) findViewById(R.id.buttonContactsMultiple1);
        button2 = (Button) findViewById(R.id.buttonContactsMultiple2);
        textView = (TextView) findViewById(R.id.balancetxtTMwallet) ;

        // Get the Intent that started this activity and extract the string
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "TransferToWalletSingle37":
                this.sessionID = intentExtra.getStringExtra(TransferToWalletSingle37.EXTRA_SESSION);
                this.AGENTNO = intentExtra.getStringExtra(TransferToWalletSingle37.EXTRA_AGENTNO);
                break;
            case "SelectUserAdapter": {
                this.phoneNumber1 = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                String userName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
                this.phoneName1 = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
                editor.putString("firstPhoneNumber", phoneNumber1);
                editor.putString("firstPhoneName", phoneName1);
                editor.apply();
                button1.setEnabled(false);
                EditText tvtext = findViewById(R.id.editTxtWalletMultiple);
                tvtext.setText(userName);
                break;
            }
            case "SelectUserAdapter2": {
                this.phoneNumber2 = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                String userName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
                this.phoneName2 = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
                EditText tvtext = findViewById(R.id.editTxtWalletMultiple);
                tvtext.setText(userName);
                button2.setEnabled(false);
                this.phoneNumber1 = pref.getString("firstPhoneNumber", "");
                System.out.println(phoneNumber1 + "/n/" + phoneNumber2);
                break;

            }
        }


        spin = (Spinner) this.findViewById(R.id.select_multiple);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectUser);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        confirmButton = (Button) findViewById(R.id.continue_multiple_transfer);


        UserBalanceViewModel userBalanceViewModel = new ViewModelProvider(this).get(UserBalanceViewModel.class);
        userBalanceViewModel.getUserBalance(TransferToWalletMultiple40.this, sessionID).observe(this, new Observer<List<BalanceModel>>() {
            @Override
            public void onChanged(List<BalanceModel> balanceModels) {
                for (BalanceModel b : balanceModels) {
                    textView.setText("KES" + b.getBalance());
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), selectUser[position], Toast.LENGTH_LONG).show();
        if ("Single User".equals(selectUser[position])) {
            Log.e("session before contact", sessionID);
            Intent move = new Intent(this, TransferToWalletSingle37.class);
            move.putExtra("Class", "TransferToWalletMultiple40");
            move.putExtra(EXTRA_SESSION, sessionID);
            move.putExtra(EXTRA_AGENTNO, AGENTNO);
            startActivity(move);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, MainTransfer36.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Log.e("set toolbar sess", sessionID);
                        movetoLogo.putExtra("Class", "TransferToWalletMultiple40");
                        movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                        movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
                        startActivity(movetoLogo);
                    }
                }
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }


    public void MoveToContacts(View view) {
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TransferToWalletMultiple");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_CLASSTYPE, "walletMultiple");
        startActivity(move);
        finish();
    }

    public void MoveToContacts2(View view) {
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TransferToWalletMultiple2");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_CLASSTYPE, "walletMultiple2");
        startActivity(move);
        finish();
    }

    public void Maketransfer(View view) {
        editText1 = findViewById(R.id.amountMultipleTransfer);
        String amount = editText1.getText().toString();

        editText2 = findViewById(R.id.messageMultipleTransfers);
        String message = editText2.getText().toString();

        if (amount.isEmpty()) {
            editText1.requestFocus();
            Toast.makeText(this, "please provide all details", Toast.LENGTH_SHORT).show();
            return;
        }


        String phone1 = phoneNumber1;
        String phone2 = phoneNumber2;
        Log.e("TAG phone number check", "button pressed to transfer money" + phone1 + "," + phone2);
        if (phone1 == null && phone2 == null) {
            Toast.makeText(this, "please provide recipient", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] phonenumber = changePhoneNo(phone1, phone2, view);
        phone1 = phonenumber[0];
        phone2 = phonenumber[1];
        Log.e("session after contact", sessionID);
        Log.e("TAG phone number last", phone1 + "," + phone2);
        if (!phone1.equals("False") && !phone2.equals("False")) {
            movetoConfirm(phone1, phone2, amount, message);
        }
    }

    private void movetoConfirm(String phone1, String phone2, String amount, String message) {
        Log.e("session before contact", sessionID);
        Intent move = new Intent(this, ConfirmMultipleTransfer42.class);
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_PHONENAME1, phoneName1);
        move.putExtra(EXTRA_PHONENAME2, phoneName2);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_PHONENUMBER1, phone1);
        move.putExtra(EXTRA_PHONENUMBER2, phone2);
        move.putExtra(EXTRA_AMOUNT, amount);
        startActivity(move);
    }

    public String[] changePhoneNo(String inputPhone, String inputPhoneTwo, View view) {
        String validPhoneNo1 = "False";
        String validPhoneNo2 = "False";
        String[] arr = new String[2];
        String safaricom = "^(?:254|\\+254|0)?(7(?:(?:[129][0-9])|(?:0[0-9])|(?:6[8-9])|(?:5[7-9])|(?:4[5-6])|(?:4[8])|(4[0-3]))[0-9]{6})$";
        String telkom = "^(?:254|\\+254|0)?(7(?:(?:[7][0-9]))[0-9]{6})$";
        String airtel = "^(?:254|\\+254|0)?(7(?:(?:[3][0-9])|(?:5[0-6])|(?:6[2])|(8[0-9]))[0-9]{6})$";
        Pattern patt;
        Matcher match1, match2;
        if (!inputPhone.isEmpty() && !inputPhoneTwo.isEmpty()) {
            String replPhone1 = inputPhone.trim();
            String replPhone2 = replPhone1.replaceAll("\\s", "");
            patt = Pattern.compile(safaricom);
            match1 = patt.matcher(replPhone2);

            //phone Number 2
            String replPhoneNumber2 = inputPhoneTwo.trim();
            String replPhoneNumber2_2 = replPhoneNumber2.replaceAll("\\s", "");
            patt = Pattern.compile(safaricom);
            match2 = patt.matcher(replPhoneNumber2_2);

            if (match1.find() || match2.find()) {
                Toast.makeText(getApplicationContext(), "Safaricom Number", Toast.LENGTH_LONG).show();
                String replPhone3 = "null";
                String replPhoneNumber3_2 = "null";
                phoneCompany = "safaricom";
                if (replPhone2.startsWith("0") || replPhoneNumber2_2.startsWith("0")) {
                    replPhone3 = replPhone2.replaceFirst("0", "\\254");
                    replPhoneNumber3_2 = replPhoneNumber2_2.replaceFirst("0", "\\254");
                    Log.e("TAG phone starts 0", replPhone3 + "," + replPhoneNumber3_2);
                    arr[0] = validPhoneNo1 = replPhone3;
                    arr[1] = validPhoneNo2 = replPhoneNumber3_2;
                } else if (replPhone2.startsWith("7") || replPhoneNumber2_2.startsWith("7")) {
                    replPhone3 = replPhone2.replaceFirst("7", "\\254");
                    replPhoneNumber3_2 = replPhoneNumber2_2.replaceFirst("7", "\\254");
                    Log.e("TAG phone starts 7", replPhone3 + "," + replPhoneNumber3_2);
                    arr[0] = validPhoneNo1 = replPhone3;
                    arr[1] = validPhoneNo2 = replPhoneNumber3_2;
                } else if (replPhone2.startsWith("+") || replPhoneNumber2_2.startsWith("+")) {
                    replPhone3 = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                    replPhoneNumber3_2 = replPhoneNumber2_2.replaceAll("[\\-\\+\\.\\^:,]", "");
                    Log.e("TAG phone number +", validPhoneNo1 + "," + validPhoneNo2);
                    arr[0] = validPhoneNo1 = replPhone3;
                    arr[1] = validPhoneNo2 = replPhoneNumber3_2;
                }
            } else {
                patt = Pattern.compile(airtel);
                match1 = patt.matcher(replPhone2);
                match2 = patt.matcher(replPhoneNumber2_2);

                if (match1.find() || match2.find()) {
                    Toast.makeText(getApplicationContext(), "Airtel Number", Toast.LENGTH_LONG).show();
                    String replPhone3 = "null";
                    String replPhoneNumber3_2 = "null";
                    phoneCompany = "airtel";
                    if (replPhone2.startsWith("0") || replPhoneNumber2_2.startsWith("0")) {
                        replPhone3 = replPhone2.replaceFirst("0", "\\254");
                        replPhoneNumber3_2 = replPhoneNumber2_2.replaceFirst("0", "\\254");
                        Log.e("TAG phone starts 0", replPhone3 + "," + replPhoneNumber3_2);
                        arr[0] = validPhoneNo1 = replPhone3;
                        arr[1] = validPhoneNo2 = replPhoneNumber3_2;
                    } else if (replPhone2.startsWith("7") || replPhoneNumber2_2.startsWith("7")) {
                        replPhone3 = replPhone2.replaceFirst("7", "\\254");
                        replPhoneNumber3_2 = replPhoneNumber2_2.replaceFirst("7", "\\254");
                        Log.e("TAG phone starts 7", replPhone3 + "," + replPhoneNumber3_2);
                        arr[0] = validPhoneNo1 = replPhone3;
                        arr[1] = validPhoneNo2 = replPhoneNumber3_2;
                    } else if (replPhone2.startsWith("+") || replPhoneNumber2_2.startsWith("+")) {
                        validPhoneNo1 = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                        validPhoneNo2 = replPhoneNumber2_2.replaceAll("[\\-\\+\\.\\^:,]", "");
                        Log.e("TAG phone number +", validPhoneNo1);
                        arr[0] = validPhoneNo1 = replPhone3;
                        arr[1] = validPhoneNo2 = replPhoneNumber3_2;
                    }
                } else {
                    patt = Pattern.compile(telkom);
                    match1 = patt.matcher(replPhone2);
                    match2 = patt.matcher(replPhoneNumber2_2);

                    if (match1.find() || match2.find()) {
                        Toast.makeText(getApplicationContext(), "Telkom Number", Toast.LENGTH_LONG).show();
                        String replPhone3 = "null";
                        String replPhoneNumber3_2 = "null";
                        phoneCompany = "telkom";
                        if (replPhone2.startsWith("0") || replPhoneNumber2_2.startsWith("0")) {
                            replPhone3 = replPhone2.replaceFirst("0", "\\254");
                            replPhoneNumber3_2 = replPhoneNumber2_2.replaceFirst("0", "\\254");
                            Log.e("TAG phone starts 0", replPhone3 + "," + replPhoneNumber3_2);
                            arr[0] = validPhoneNo1 = replPhone3;
                            arr[1] = validPhoneNo2 = replPhoneNumber3_2;
                        } else if (replPhone2.startsWith("7") || replPhoneNumber2_2.startsWith("7")) {
                            replPhone3 = replPhone2.replaceFirst("7", "\\254");
                            replPhoneNumber3_2 = replPhoneNumber2_2.replaceFirst("7", "\\254");
                            Log.e("TAG phone starts 7", replPhone3 + "," + replPhoneNumber3_2);
                            arr[0] = validPhoneNo1 = replPhone3;
                            arr[1] = validPhoneNo2 = replPhoneNumber3_2;
                        } else if (replPhone2.startsWith("+") || replPhoneNumber2_2.startsWith("+")) {
                            arr[0] = validPhoneNo1 = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                            arr[1] = validPhoneNo2 = replPhoneNumber2_2.replaceAll("[\\-\\+\\.\\^:,]", "");
                            Log.e("TAG phone number +", validPhoneNo1 + "," + validPhoneNo2);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter a valid mobile number 'Safaricom only'", Toast.LENGTH_LONG).show();
                        Log.e("TAG phone No not check", replPhone2);
                        MoveToContacts(view);
                    }
                }

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a mobile number ", Toast.LENGTH_LONG).show();
            MoveToContacts(view);
        }

        return new String[]{phoneNumber1, phoneNumber2};
    }


}