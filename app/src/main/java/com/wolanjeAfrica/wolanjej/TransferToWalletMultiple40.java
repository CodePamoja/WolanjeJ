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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.models.SelectUser;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.MultipleUsersTransactionsAdapter;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransferToWalletMultiple40 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] selectUser = {"Select User", "Single User", "Multiple Users",};

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_CLASSTYPE = "com.example.wolanjej.CLASSTYPE";
    private static final String TAG = "TransferToWallet";
    private Button confirmButton;
    private String sessionID;
    private String AGENTNO;
    private String phoneNumber;
    private String phoneName;
    private String phoneCompany;
    private EditText editText1, editText2;
    private Spinner spin;
    private TextView textView;
    private ArrayAdapter adapter;
    private SharedPreferences pref;
    private Button button1, button2;
    public static List<SelectUser> listOfMultiUserTransferToWalletMultiple40= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_wallet_multiple40);
        setToolBar();

        pref = getApplication().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");

        textView = (TextView) findViewById(R.id.balancetxtTMwallet);
        editText1 = findViewById(R.id.amount_to_send_multiple_users);

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
                this.phoneNumber = intentExtra.getStringExtra(SelectUserAdapter.EXTRA_PHONE);
                listOfMultiUserTransferToWalletMultiple40 = ContactsView.listOfMultiUserContactView;
                break;
            }


        }


        spin = (Spinner) this.findViewById(R.id.select_multiple);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list

        adapter = ArrayAdapter.createFromResource(this,
                R.array.user_select, R.layout.custom_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(adapter);

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
        initTransferRecyclerList();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if ("Single User".equals(selectUser[position])) {
            Log.e("session before contact", sessionID);
            Intent move = new Intent(this, TransferToWalletSingle37.class);
            move.putExtra("Class", "TransferToWalletMultiple40");
            move.putExtra(EXTRA_SESSION, sessionID);
            move.putExtra(EXTRA_AGENTNO, AGENTNO);
            startActivity(move);
        } else if ("Multiple Users".equals(selectUser[position])) {
            //do nothing
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.include7);
        setSupportActionBar(tb);
        final Intent movetoLogo = new Intent(this, MainTransfer36.class);
        tb.setNavigationOnClickListener(
                v -> {
                    movetoLogo.putExtra("Class", "TransferToWalletMultiple40");
                    movetoLogo.putExtra(EXTRA_SESSION, sessionID);
                    movetoLogo.putExtra(EXTRA_AGENTNO, AGENTNO);
                    startActivity(movetoLogo);
                }
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }


    private void initTransferRecyclerList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(TransferToWalletMultiple40.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.selected_multiple_users);
        recyclerView.setLayoutManager(layoutManager);
        MultipleUsersTransactionsAdapter adapter = new MultipleUsersTransactionsAdapter(TransferToWalletMultiple40.this,listOfMultiUserTransferToWalletMultiple40);
        recyclerView.setAdapter(adapter);

    }
    public void AddTransaction(View view) {
        Intent move = new Intent(this, ContactsView.class);
        move.putExtra("Class", "TransferToWalletMultiple");
        move.putExtra(EXTRA_SESSION, sessionID);
        move.putExtra(EXTRA_AGENTNO, AGENTNO);
        move.putExtra(EXTRA_CLASSTYPE, "walletMultiple");
        startActivity(move);
        finish();
    }

    public String dateTime() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a | dd/MMM/yyyy ");

        return simpleDateFormat.format(calendar.getTime());
    }

    public void Maketransfer(View view) {
        String amount = editText1.getText().toString();
        if (amount.isEmpty()){
            Toast.makeText(this, "Provide Amount", Toast.LENGTH_SHORT).show();
        }else {
            listOfMultiUserTransferToWalletMultiple40.forEach(selectUser1 -> {
                selectUser1.setDate(dateTime());
                selectUser1.setAmount(amount);
            });

            Intent move = new Intent(this, ConfirmMultipleTransfer42.class);
            Bundle bundle = new Bundle();
            move.putExtra(EXTRA_SESSION, sessionID);
            move.putExtra(EXTRA_AGENTNO, AGENTNO);
            move.putExtras(bundle);
            startActivity(move);
            finish();
        }
    }

}