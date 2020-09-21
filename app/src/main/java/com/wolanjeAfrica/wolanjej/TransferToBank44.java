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
import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.List;
import java.util.Map;

public class TransferToBank44 extends AppCompatActivity {

    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_MESSAGE = "com.example.wolanjej.MESSAGE";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_PHONENAME = "com.example.wolanjej.PHONENAME";
    public static final String EXTRA_PHONENUMBER = "com.example.wolanjej.PHONENUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_HOLDERNAME = "com.example.wolanjej.HOLDERNAME";
    public static final String EXTRA_ACCOUNTNUMBER = "com.example.wolanjej.ACCOUNTNUMBER";
    public static final String EXTRA_BRANCHNAME = "com.example.wolanjej.BRANCHNAME";
    public static final String EXTRA_BANKSELECTED = "com.example.wolanjej.BANKSELECTED";
    public static final String EXTRA_PHONECOMPANY = "com.example.wolanjej.PHONECOMPANY";
    public static final String EXTRA_PARENTCLASSNAME = "com.example.wolanjej.PARENTCLASSNAME";
    private static final String TAG = "TransferToBank";
    private Button button;
    private Spinner spin, spinner;
    private EditText text, editText, editText1, editText2, editText3, editText4;
    private String phoneNumber;
    private String sessionID;
    private String branchName;
    private String accNumber;
    private String amount;
    private String phoneCompany;
    private String phoneName;
    private ArrayAdapter<CharSequence> aa;
    private ArrayAdapter<CharSequence> adapter;
    private String bankSelected;
    private String AGENTNO;
    private String MY_BALANCE;
    private static String className;
    private String sessionId;
    private SharedPreferences pref,pref1;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_bank44);
        setToolBar();
        text = findViewById(R.id.bankAmount);
        textView = (findViewById(R.id.balancetxtTbank));
        editText = findViewById(R.id.bankMessage);
        editText1 = findViewById(R.id.holdName);
        editText2 = findViewById(R.id.accNumber);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "MainTransfer36":
                TransferToBank44.className = intentExtra.getStringExtra(MainTransfer36.EXTRA_PARENTCLASSNAME);
                break;
            case "SelectUserAdapter": {
                this.phoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                this.phoneName = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_NAME);

                editText2.setText(phoneNumber);
                editText1.setText(phoneName);

                break;
            }
            case "ConfirmTransferToBank46": {

                pref = getApplication().getSharedPreferences("ConfirmTransferToBank46", MODE_PRIVATE);
                this.accNumber = pref.getString("accNumber", "");
                this.phoneNumber = "+" + pref.getString("phone", "");
                this.phoneName = pref.getString("phoneName", "");
                this.branchName = pref.getString("branchName", "");
                this.bankSelected = pref.getString("bankSelected", "");

                String userName = pref.getString("holderName", "");
                String sendAmount = pref.getString("amount", "");
                String sendMessage = pref.getString("message", "");
                String sendAccNumber = pref.getString("accNumber", "");
                String sendBank = pref.getString("bankSelected", "");
                String sendBranch = pref.getString("branchName", "");


                editText1.setText(userName);
                text.setText(sendAmount);

                editText.setText(sendMessage);
                editText2.setText(sendAccNumber);

                break;
            }
        }

        spin = (Spinner) this.findViewById(R.id.selectBank);
        aa = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.select_bank, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        spinner = (Spinner) this.findViewById(R.id.branchName);
        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.select_bank_branch, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        pref1 = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionId = pref1.getString("session_token", "");
        this.AGENTNO = pref1.getString("agentno", "");



        UserBalanceViewModel userBalanceViewModel = new ViewModelProvider(this).get(UserBalanceViewModel.class);
        userBalanceViewModel.getUserBalance(TransferToBank44.this, sessionId).observe(this, new Observer<List<BalanceModel>>() {
            @Override
            public void onChanged(List<BalanceModel> balanceModels) {
                for (BalanceModel b : balanceModels) {
                    MY_BALANCE = b.getBalance();
                    textView.setText("KES" + MY_BALANCE);
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
                        movetoLogo.putExtra("Class", "TransferToBank44");
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
        move.putExtra("Class", "TransferToBank44");
        move.putExtra(EXTRA_CLASSTYPE, "bank");
        startActivity(move);
    }

    public void bankTransfer(View view) {
        String phone = phoneNumber;
        String key = null;
        String value = null;

        Map<String, String> map = CheckPhoneNumber.getInstance().checkPhoneNo(TransferToBank44.this, phone);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
        if (value != null && !value.equals("Fasle")) {
            String amount = text.getText().toString();
            String message = editText.getText().toString();
            String holderName = editText1.getText().toString();
            String accNumber = editText2.getText().toString();
            if (amount.isEmpty() || message.isEmpty() || holderName.isEmpty() || accNumber.isEmpty()) {
                Toast.makeText(this, "please provide all the details", Toast.LENGTH_SHORT).show();
                return;
            }

            String[] bankAndBranch = selectedBank();
            String bank = bankAndBranch[0];
            String branch = bankAndBranch[1];

            Log.d(TAG, "bankTransfer: " + bank + "////" + branch);

            valuesConferm(holderName, key, amount, message, accNumber, branch, bank, value);

        } else {
            Toast.makeText(this, "invalid phone", Toast.LENGTH_SHORT).show();
        }
    }

    private String[] selectedBank() {
        String bank = spin.getSelectedItem().toString();
        String bank_branch = "False";
        String[] arr = new String[2];
        switch (bank) {
            case "Equity Bank":
            case "Co-operative Bank":
            case "CfC Stanbic Bank":
                arr[0] = bank;
                arr[1] = bank_branch = spinner.getSelectedItem().toString();
                break;
            case "Barclays Bank":
            case "Chase Bank":
            case "Citibank Bank":
                spinner.setAdapter(adapter);
                arr[0] = bank;
                arr[1] = spinner.getSelectedItem().toString();
                break;
        }
        return arr;
    }

    public void valuesConferm(String holderName, String phoneCompany, String amount, String message, String accNumber, String branchName, String bankSelect, String phone) {

        Intent move = new Intent(this, ConfirmTransferToBank46.class);
        move.putExtra("Class", "TransferToBank44");
        move.putExtra(EXTRA_AMOUNT, amount);
        move.putExtra(EXTRA_MESSAGE, message);
        move.putExtra(EXTRA_ACCOUNTNUMBER, accNumber);
        move.putExtra(EXTRA_PHONENAME, phoneName);
        move.putExtra(EXTRA_PHONECOMPANY, phoneCompany);
        move.putExtra(EXTRA_BANKSELECTED, bankSelect);
        move.putExtra(EXTRA_BRANCHNAME, branchName);
        move.putExtra(EXTRA_HOLDERNAME, holderName);
        move.putExtra(EXTRA_PHONENUMBER, phone);
        move.putExtra(EXTRA_PARENTCLASSNAME, className);
        startActivity(move);
    }

}