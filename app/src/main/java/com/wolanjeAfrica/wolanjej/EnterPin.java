package com.wolanjeAfrica.wolanjej;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.wolanjeAfrica.wolanjej.RealmDataBase.DbMigrations;
import com.wolanjeAfrica.wolanjej.RealmDataBase.User;
import com.wolanjeAfrica.wolanjej.ViewModels.TransactionApi;
import com.wolanjeAfrica.wolanjej.ViewModels.UserBalanceViewModel;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;

import com.wolanjeAfrica.wolanjej.models.Transactions;


import java.util.List;

import io.realm.Realm;


public class EnterPin extends AppCompatActivity {
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    private static String parentClassName;
    private static String userBalance;
    private Button button;
    private String sessionID;
    private String phoneNumber, PhoneNumber2;
    private String phoneName, phoneName1;
    private String amount;
    private static String accNumber = "68-2727272727";
    private String sendIDReference;
    private String phoneProvider;
    private String message;
    private String sendAmount;
    private String myBalance;
    private String resultBalance;
    private String bankDetails;
    private Context context;
    private AlertDialog alertDialog;
    private EditText text1, text2, text3, text4;
    private SharedPreferences pref;
    private final String TAG = "EnterPin";
    private ProgressBar progressBar;
    private Transactions transactions;
    private String userId;
    private Realm realm;
    private String bank_ProductName;
    private List<Transactions> transactionsList;

    public EnterPin() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);

        Realm.init(this);


        //SharedPreferences values for login eg token
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.userId = pref.getString("userDbId", null);
        progressBar = (ProgressBar) findViewById(R.id.progressBarEnterPin);


        text1 = findViewById(R.id.pinValue1);
        text2 = findViewById(R.id.pinValue2);
        text3 = findViewById(R.id.pinValue3);
        text4 = findViewById(R.id.pinValue4);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "BillManager":
                parentClassName = "Home";
                break;
            case "HomeTwoloans":
            case "Homeloans":
                break;
            case "ScholarShip06":
                break;
            case "BookBus06":

                break;
            case "Education_2":
                //TODO:

                break;
            case "TransferToWalletSingle37":

                this.phoneNumber = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENUMBER);
                this.amount = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_AMOUNT);
                this.phoneName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PHONENAME);
                parentClassName = intentExtra.getStringExtra(ConfirmSingleTransfer40.EXTRA_PARENTCLASSNAME);

                break;
            case "TransferToPhone50":
                pref = getApplication().getSharedPreferences("ConfirmTransferToPhone52", MODE_PRIVATE);
                this.phoneNumber = pref.getString("phone", "");
                this.phoneName = pref.getString("phoneName", "");
                this.amount = pref.getString("amount", "");
                this.phoneProvider = pref.getString("phoneCompany", "");

                break;
            case "TransferToBank44":

                this.phoneNumber = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_PHONENUMBER);
                this.phoneName = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_PHONENAME);
                this.amount = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_AMOUNT);
                this.message = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_MESSAGE);
                this.phoneProvider = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_PHONECOMPANY);
                this.bank_ProductName = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_PRODUCTNAME);
                String sendBranch = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_BRANCHNAME);
                String sendBank = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_BANKSELECTED);
                parentClassName = intentExtra.getStringExtra(ConfirmTransferToBank46.EXTRA_PARENTCLASSNAME);
                this.bankDetails = sendBank + "-" + sendBranch;

                break;
            case "TopUpOtherNumber":

                this.phoneNumber = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PHONENUMBER);
                this.amount = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_AMOUNT);
                this.phoneName = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PHONENAME);
                this.phoneProvider = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_PROVIDER);

                break;
            case "Top_up":

                this.phoneNumber = intentExtra.getStringExtra(Top_up.EXTRA_PHONENUMBER);
                this.amount = intentExtra.getStringExtra(Top_up.EXTRA_AMOUNT);
                this.phoneProvider = intentExtra.getStringExtra(Top_up.EXTRA_PROVIDER);
                parentClassName = intentExtra.getStringExtra(Top_up.EXTRA_PARENTCLASSNAME);
                break;
            case "ConfirmMultipleTransfer":
                Bundle bundle = getIntent().getExtras();
                transactions = bundle.getParcelable("transactions");
                transactionsList = transactions.getTransactionsList();
                parentClassName = intentExtra.getStringExtra(ConfirmMultipleTransfer42.EXTRA_PARENTCLASSNAME);
                break;
            case "ConfirmTransferToPhone52":
                this.amount = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_AMOUNT);
                this.phoneProvider = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONEPROVIDER);
                this.phoneName = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONENAME);
                this.phoneNumber = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PHONENUMBER);
                parentClassName = intentExtra.getStringExtra(ConfirmTransferToPhone52.EXTRA_PARENTCLASSNAME);
                break;

        }


        final String[] numbers = new String[4];

        text1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[0] = s.toString();
                // text1.setFocusable(false);
                text1.setClickable(false);
                text2.requestFocus();

            }
        });
        text2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {


                numbers[1] = s.toString();
                //text2.setFocusable(false);
                text2.setClickable(false);
                text3.requestFocus();
            }
        });
        text3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[2] = s.toString();
                //text3.setFocusable(false);
                text3.setClickable(false);
                text4.requestFocus();
            }
        });
        text4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                numbers[3] = s.toString();
                //text4.setFocusable(false);
                text4.setClickable(false);

            }
        });

        setActionBarColor();
        ImplementService();
    }

    private void ImplementService() {
        text4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String pin1 = text1.getText().toString();

                String pin2 = text2.getText().toString();

                String pin3 = text3.getText().toString();

                String pin4 = text4.getText().toString();

                String fullPin = pin1 + pin2 + pin3 + pin4;
                Intent intentExtra = getIntent();
                String className = getIntent().getStringExtra("Class");
                Intent intent;
                switch (className) {
                    case "BillManager":
                        showPopup();
                        break;
                    case "Homeloans":
                        intent = new Intent(EnterPin.this, Home.class);
                        intent.putExtra("Class", "EnterPinloan");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                    case "HomeTwoloans":
                        intent = new Intent(EnterPin.this, HomeTwo.class);
                        intent.putExtra("Class", "EnterPinloan");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                    case "ScholarShip06":
                        if (ValidateUserPin(fullPin)) {
                            intent = new Intent(EnterPin.this, Scholarship06.class);
                            intent.putExtra("Class", "EnterPin");
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(EnterPin.this, "Invalid pin", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "Education_2":
                        if (ValidateUserPin(fullPin)) {
                            intent = new Intent(getApplicationContext(), Education1.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Class", "EnterPin");
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(EnterPin.this, "Invalid pin", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "ConfirmMultipleTransfer":
                        if (ValidateUserPin(fullPin)) {
                            for (Transactions transactions :transactionsList){
                                PerformTransaction("WALLET_XFER",transactions.getPhone(),transactions.getPhone(),transactions.getAmount());
                            }
                        } else {
                            Toast.makeText(EnterPin.this, "Invalid pin", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "BookBus06":
                        if (ValidateUserPin(fullPin)) {
                            intent = new Intent(getApplicationContext(), BookBus09.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EnterPin.this, "Invalid pin", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "TransferToWalletSingle37":
                        if (ValidateUserPin(fullPin)) {
                            PerformTransaction("WALLET_XFER",phoneNumber,phoneNumber,amount);
                        } else {
                            Toast.makeText(EnterPin.this, "Invalid pin", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "TransferToPhone50":
                    case "ConfirmTransferToPhone52":
                        if (ValidateUserPin(fullPin)) {
                            switch (phoneProvider) {
                                //Case statements
                                case "safaricom":
                                    PerformTransaction("MPESA_B2C",phoneNumber,phoneNumber,amount);
                                    break;
                                case "airtel":
                                    PerformTransaction("AIRTEL_B2C",phoneNumber,phoneNumber,amount);
                                    break;
                                case "telkom":

                                    PerformTransaction("TKASH_B2C",phoneNumber,phoneNumber,amount);
                                    break;
                                default:
                                    System.out.println("Not an airtel, safaricom or telkom");
                            }
                        } else {
                            Toast.makeText(EnterPin.this, "Invalid pin", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "TransferToBank44":
                        if (ValidateUserPin(fullPin)) {

                            PerformTransaction(bank_ProductName,accNumber,phoneNumber,amount);
                        } else {
                            Toast.makeText(EnterPin.this, "Invalid pin", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "TopUpOtherNumber":
                    case "Top_up":
                        if (ValidateUserPin(fullPin)) {
                            switch (phoneProvider) {
                                //Case statements
                                case "safaricom":
                                    PerformTransaction("SAF_ATP",phoneNumber,phoneNumber,amount);
                                    break;
                                case "airtel":

                                    PerformTransaction("AIRTEL_ATP",phoneNumber, phoneNumber, amount );
                                    break;
                                case "telkom":

                                    PerformTransaction("TKASH_ATP",phoneNumber, phoneNumber, amount );
                                    break;
                                default:
                                    System.out.println("Not an airtel, safaricom or telkom");
                            }
                            break;
                        } else {
                            Toast.makeText(EnterPin.this, "Invalid pin", Toast.LENGTH_SHORT).show();
                        }
                }
                return false;
            }
        });
    }

    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }


    public Boolean ValidateUserPin(String pin) {
        LogIn logIn = new LogIn();
        String hasshedPassword = logIn.generateHashedPassword(pin);  //public method in Login class
        realm = Realm.getInstance(DbMigrations.getDefaultInstance());
        User user = realm.where(User.class)
                .equalTo("id", Integer.valueOf(userId))
                .findFirst();

        if (user != null) {
            String password = user.getPassword();
            return password.equals(hasshedPassword);
        }
        return false;
    }


    private void RetrieveBalance() {
        UserBalanceViewModel userBalanceViewModel = new ViewModelProvider(this).get(UserBalanceViewModel.class);
        userBalanceViewModel.getUserBalance(EnterPin.this, sessionID).observe(this, new Observer<List<BalanceModel>>() {
            @Override
            public void onChanged(List<BalanceModel> balanceModels) {
                for (BalanceModel b : balanceModels) {
                    userBalance = b.getBalance();
                    Log.e(TAG, "onChanged: balanceq"+userBalance );
                }
            }
        });
    }

    private void PerformTransaction(String product, String refNo, String phoneNumber, String amount){
        progressBar.setVisibility(View.VISIBLE);
        TransactionApi transactionApi = new ViewModelProvider(this).get(TransactionApi.class);
        RetrieveBalance();
        transactionApi.userTransactions(EnterPin.this, sessionID, product, refNo,phoneNumber,amount).observe(this, servicesModel -> {

            progressBar.setVisibility(View.GONE);



            sendAmount =servicesModel.getAmountTransaction();
            String sendfee = servicesModel.getTransactionFee();
            String sendNumber =servicesModel.getRef();
            sendIDReference = servicesModel.getId();
            String statusResulsts = servicesModel.getLast_status();
            switch (statusResulsts) {
                case "TRX_ASYNC":
                    showPopup();
                    break;
                case "TRX_OK":
                    showPopup();
                    break;
                case "TRX_INSUFFICIENT_BALANCE":
                    showPopupFail();
                    break;
                case "TRX_VERIFY":
                    ShowDialogWalletFail();
                    break;
                default:
                    showPopupFail();
                    break;
            }
        });
    }



    public void showPopup() {
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");

        switch (className) {
            case "BillManager":
            case "ConfirmTransferToPhone52":
            case "TransferToWalletSingle37":
            case "ConfirmMultipleTransfer":
            case "Top_up":
            case "TopUpOtherNumber":
            case "TransferToBank44":
            case "TransferToPhone50": {
                ShowDialogSuccess();
                break;
            }
        }
    }

    public void showPopupFail() {
        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");

        switch (className) {
            case "BillManager":
            case "ConfirmTransferToPhone52":
            case "TransferToWalletSingle37":
            case "ConfirmMultipleTransfer":
            case "TransferToPhone50":
            case "TransferToBank44":
            case "TopupOtherNumber":
            case "Top_up": {
                ShowDialogWalletFail();
                break;
            }
        }

    }

    private void ShowDialogSuccess() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.transfer_success_popup, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textView = (TextView) view.findViewById(R.id.balanceTransferDone);
        textView.setText(userBalance);
        Button btn = view.findViewById(R.id.dismiss_success);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (parentClassName) {
                    case "HomeTwo":
                        intent = new Intent(getApplicationContext(), HomeTwo.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Class", "EnterPin");
                        startActivity(intent);
                        finish();
                        alertDialog.dismiss();
                        break;
                    case "Home":
                        intent = new Intent(getApplicationContext(), Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Class", "EnterPin");
                        startActivity(intent);
                        finish();
                        alertDialog.dismiss();
                        break;
                    case "BulkPaymentFrag":
                        intent = new Intent(getApplicationContext(), BulkPaymentsMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Class", "EnterPinSuccess");
                        startActivity(intent);
                        finish();
                        alertDialog.dismiss();
                        break;
                    default:
                        intent = new Intent(getApplicationContext(), Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Class", "EnterPin");
                        startActivity(intent);
                        finish();
                        alertDialog.dismiss();
                        finish();
                        break;
                }
            }
        });
        TextView textView1 = view.findViewById(R.id.refNumberSuccess);
        textView1.setText(sendIDReference);
        TextView textView2 = view.findViewById(R.id.amountSentSuccess);
        textView2.setText(sendAmount);

        alertDialog.show();


    }

    private void ShowDialogWalletFail() {
        if (!((Activity) EnterPin.this).isFinishing()) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.transfer_unsuccessful_popup, null);
            alertDialog = new AlertDialog.Builder(this)
                    .setView(view)
                    .create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView text = (TextView) view.findViewById(R.id.balance_note2);
            text.setText(userBalance);
            Button btn = view.findViewById(R.id.try_again_unsuccessful);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;

                    switch (parentClassName) {
                        case "HomeTwo":
                            intent = new Intent(getApplicationContext(), HomeTwo.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Class", "EnterPin");
                            startActivity(intent);
                            finish();
                            alertDialog.dismiss();
                            break;
                        case "Home":
                            intent = new Intent(getApplicationContext(), Home.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Class", "EnterPin");
                            startActivity(intent);
                            finish();
                            alertDialog.dismiss();
                            break;
                        case "BulkPaymentFrag":
                            intent = new Intent(getApplicationContext(), BulkPaymentsMain.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Class", "EnterPinFail");
                            startActivity(intent);
                            finish();
                            alertDialog.dismiss();
                            break;
                        default:
                            intent = new Intent(getApplicationContext(), LogIn.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Class", "EnterPin");
                            startActivity(intent);
                            finish();
                            alertDialog.dismiss();
                            finish();
                            break;
                    }
                }
            });
            TextView textView = view.findViewById(R.id.amount_sent_note);
            TextView textView2 = view.findViewById(R.id.reference_numberTra);
            textView2.setText(sendIDReference);
            textView.setText(sendAmount);
            alertDialog.show();
        }
    }



    @Override

    protected void onDestroy() {
        super.onDestroy();
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }

}