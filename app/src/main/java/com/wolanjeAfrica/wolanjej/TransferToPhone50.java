package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.wolanjeAfrica.wolanjej.Utils.CheckPhoneNumber;
import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.List;
import java.util.Map;

public class TransferToPhone50 extends AppCompatActivity {
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_PHONECOMPANY = "com.example.wolanjej.PHONECOMPANY";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    private Button button;
    private EditText editText1, editText2, editText3;
    private TextView textView1;
    private String phoneNumber = "phone1";
    private String phoneName;
    private String phoneCompany;
    private SharedPreferences pref;
    private String sessionId;
    private String AGENTNO;
    private String MY_BALANCE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_phone50);
        setToolBar();
        //SharedPreferences values for login eg token, user registered number
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionId = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");

        editText1 = (EditText) findViewById(R.id.transContactAmount);
        editText2 = (EditText) findViewById(R.id.transAmountPhone);
        textView1 = (TextView) findViewById(R.id.balancetxtTphone);
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "MainTransfer36":
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
        String phone = editText1.getText().toString();
        String amount = editText2.getText().toString();
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
            valuesConferm(value, amount, key);
        }else {
            Toast.makeText(this, "invalid phone", Toast.LENGTH_SHORT).show();
        }
    }

    public void valuesConferm(String phone, String amount, String phoneprovider) {
        //adding values to SharedPreferences
        // make sure that in the getsharedPreferences the key value should be the same as the intent putextra class value

        Intent move = new Intent(this, ConfirmTransferToPhone52.class);
        move.putExtra("Class", "TransferPhone50");
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_PHONECOMPANY, phoneprovider);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        startActivity(move);
    }

}