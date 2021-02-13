package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import com.wolanjeAfrica.wolanjej.Utils.CheckPhoneNumber;
import com.wolanjeAfrica.wolanjej.Utils.WolenjeUtil;
import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.List;
import java.util.Map;

public class TransferToPhone50 extends AppCompatActivity  implements View.OnClickListener{
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_PHONECOMPANY = "com.example.wolanjej.PHONECOMPANY";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_PARENTCLASSNAME = "com.example.wolanjej.PARENTCLASSNAME";
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    private static String className;
    private Button button;
    private EditText editText1, editText2, editText3;
    private TextView textView1;
    private String phoneNumber = "phone1";
    private String phoneName;
    private String phoneCompany;
    private ArrayAdapter adapter;
    private Spinner spin;
    private SharedPreferences pref;
    private String sessionId;
    private String AGENTNO;
    private String MY_BALANCE;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_phone50);
        setToolBar();
        //SharedPreferences values for login eg token, user registered number
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionId = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");
        this.userId = pref.getString("userDbId", null);

        editText1 = (EditText) findViewById(R.id.transContactAmount);
        editText2 = (EditText) findViewById(R.id.transAmount);
        editText3 = (EditText) findViewById(R.id.messagetxt);
        textView1 = (TextView) findViewById(R.id.balancetxtTphone);
        Button button = (Button) findViewById(R.id.qrcodetransphone);button.setOnClickListener(this);
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "LinkAccount11":
                break;
            case "MainTransfer36":
                TransferToPhone50.className = intentExtra.getStringExtra(MainTransfer36.EXTRA_PARENTCLASSNAME);
                String activePaymentMethod = new WolenjeUtil().ActivePaymentMethod(userId);
                if (activePaymentMethod == null){
                    Intent intent = new Intent(TransferToPhone50.this, LinkAccount11.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("Class", "TransferToPhone50");
                    startActivity(intent);
                    finish();
                }
                break;
            case "ContactsView":
                break;
            case "SelectUserAdapter": {
                String CheckphoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);
                editText1.setText(CheckphoneNumber);
                break;
            }
            case "ConfirmTransferToPhone52": {

                //SharedPreferences values for TransferToPhone52 activity class eg token
                pref = getApplication().getSharedPreferences("ConfirmTransferToPhone52", MODE_PRIVATE);
                this.phoneNumber = pref.getString("phone", "");
                this.phoneName = pref.getString("phoneName", "");
                String sendAmount = pref.getString("amount", "");
                this.phoneCompany = pref.getString("phoneCompany", "");

                String CheckphoneNumber = "+" + pref.getString("phone", "");

                editText1.setText(CheckphoneNumber);
                editText2.setText(sendAmount);

                break;
            }
        }
        spin = (Spinner) this.findViewById(R.id.select_multiple);

        //Creating the ArrayAdapter instance having the country list

        adapter = ArrayAdapter.createFromResource(this,
                R.array.select_phone, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(adapter);


        UserBalanceViewModel userBalanceViewModel = new ViewModelProvider(this).get(UserBalanceViewModel.class);
        userBalanceViewModel.getUserBalance(TransferToPhone50.this, sessionId).observe(this, new Observer<List<BalanceModel>>() {
            @Override
            public void onChanged(List<BalanceModel> balanceModels) {
                for (BalanceModel b : balanceModels) {
                    MY_BALANCE = b.getBalance();
                    textView1.setText("KES" + MY_BALANCE);
                }
            }
        });

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


    public void moveToContact(View view) {
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TransferToPhone50");
        move.putExtra(EXTRA_CLASSTYPE, "phone");
        startActivity(move);
    }

    public void transferMoney(View view) {
        String message ="";
        String phone = editText1.getText().toString();
        String amount = editText2.getText().toString();
        message = editText3.getText().toString();
        String productName = null;
        String key = null;
        String value = null;
        if (phone.isEmpty()) {
            Toast.makeText(this, "provide phoneNumber", Toast.LENGTH_SHORT).show();
            return;
        }
        if (amount.isEmpty()) {
            Toast.makeText(this, "provide Amount", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = CheckPhoneNumber.getInstance().checkPhoneNo(TransferToPhone50.this, phone);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
        if (value != null && !value.equals("Fasle")) {
            valuesConferm(value, amount, key, message);
        } else {
            Toast.makeText(this, "invalid phone", Toast.LENGTH_SHORT).show();
        }
    }

    public void valuesConferm(String phone, String amount, String phoneprovider, String message) {
        //adding values to SharedPreferences
        // make sure that in the getsharedPreferences the key value should be the same as the intent putextra class value

        Intent move = new Intent(this, ConfirmTransferToPhone52.class);
        move.putExtra("Class", "TransferPhone50");
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_PHONECOMPANY, phoneprovider);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_PARENTCLASSNAME, className);
        move.putExtra(EXTRA_MESSAGE, message);
        startActivity(move);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qrcodetransphone:
                Intent intent = new Intent(getApplicationContext(), Qr_code22.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }

    }
}