package com.wolanjeAfrica.wolanjej;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.ApiJsonObjects;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;
import com.wolanjeAfrica.wolanjej.models.Model;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.MyAdapter;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.RecyclerViewHomeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Home extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";
    public static final String EXTRA_ACCOUNTNUMBER = "com.example.wolanjej.ACCOUNTNUMBER";
    public static final String EXTRA_AMOUNT = "com.example.wolanjej.AMOUNT";
    public static final String EXTRA_PRODUCT_NAME = "com.example.wolanjej.PRODUCT";
    public static final String EXTRA_MYBALANCE = "com.example.wolanjej.MYBALANCE";
    private static final String TAG = "HOME";
    private Toolbar tb;
    private DrawerLayout drawer;
    private String sessionID;
    private String sendFee;
    private String refrenceNumber;
    private String amount;
    private String AGENTNO;
    private AlertDialog alertDialogTrans;
    private Spinner spinner;
    private String MY_BALANCE;
    private String phoneCompany;
    private ArrayAdapter adapter;
    private TextView tvtext, txtAccounNoPayNetSheet, txtAmountWithdraw, txtamountPyNetSheet, txtMobileNumberWithdraw, txtAmountTopUpWallet, txtMobilenumber, txtTokenAccountNumber, txtAmountPayTvSheet, txtAmountToken, txtPaytvAccountNoSheet;
    private SharedPreferences pref;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private List<BalanceModel> balanceModel = new ArrayList<>();
    private ConnectivityManager connectivityManager;
    private View bottomSheetViewPayInternet, bottomSheetViewOpenWalletM, bottomSheetViewPayElectricity, bottomSheetViewopenEwalletPopUp, bottomSheetViewOpenWithdraw, bottomSheetViewOpenWalle, bottomSheetViewOpenScreen16, bottomSheetViewPayTvSubsription, bottomSheetViewregisterNewNumber;
    private Button buttonWallet, transferMoney;
    private BottomSheetDialog bottomSheetDialog;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button4 = findViewById(R.id.btnaddnew);
        tb = findViewById(R.id.toolbarhome);
        drawer = findViewById(R.id.drawer_layout);

        /*
         SharedPreferences values for login eg token, user registered number
         */
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");


        NavigationView navigationView = (NavigationView) findViewById(R.id.mynav);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsernumber = (TextView) headerView.findViewById(R.id.phone_number_nav_header);
        TextView UserName = headerView.findViewById(R.id.name_holder);
        UserName.setText(pref.getString("agentno", ""));
        navUsernumber.setText("+" + pref.getString("user_name", ""));


        tvtext = findViewById(R.id.MYBalance);

        MaterialCardView materialCardView = findViewById(R.id.cardBuyAirtime);
        materialCardView.setOnClickListener(this);

        Button viewall = (Button) findViewById(R.id.btnviewall);
        viewall.setOnClickListener(this);

        transferMoney = (Button) findViewById(R.id.transfer_money_button);
        transferMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonWallet.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                buttonWallet.setTextColor(getResources().getColor(R.color.colorAccent));
                transferMoney.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                transferMoney.setTextColor(getResources().getColor(R.color.colorWhite));
                Intent intent = new Intent(getApplicationContext(), MainTransfer36.class);
                intent.putExtra("Class", "Home");
                startActivity(intent);

            }
        });

        buttonWallet = findViewById(R.id.wallet);
        buttonWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonWallet.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                buttonWallet.setTextColor(getResources().getColor(R.color.colorWhite));
                transferMoney.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                transferMoney.setTextColor(getResources().getColor(R.color.colorAccent));
                OpenWallet();
            }
        });


        MaterialCardView TransferHome = findViewById(R.id.CTransferMain);
        TransferHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainTransfer36.class);
                intent.putExtra("Class", "Home");
                startActivity(intent);
            }
        });

        MaterialCardView materialCardView1 = findViewById(R.id.cardTrans);
        materialCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), TransactionView.class);
                startActivity(intent1);
            }
        });


        findViewById(R.id.loanscard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Loans.class));

            }
        });

        findViewById(R.id.btnviewall).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OpenScreen16();
            }
        });

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); //check Connectivity to internet services

        setToolBar();
        getClassIntent();
        transferListDetails();


    }

    private void getClassIntent() {

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        if (className != null) {
            Log.e("class Type className", className);
            switch (className) {
                case "Ewallet2_1":
                    ShowWalletTopUpConfDialog();
                    break;
                case "Ewallet3_1":
                    withdrawConfirmationDialog();
                    break;

                case "MyAdapterCard3":
                    PayInternetBottomSheet();
                    break;
                case "PayInternet2PinSuccess":
                    this.sendFee = intentExtra.getStringExtra(EnterPin3.EXTRA_SEND_FEE);
                    this.amount = intentExtra.getStringExtra(EnterPin3.EXTRA_AMOUNT);
                    this.refrenceNumber = intentExtra.getStringExtra(EnterPin3.EXTRA_REFERENCE_NUMBER);
                    PayInternetorWaterDialog();
                    break;
                case "payInternet2pinUnsuccessful":
                    this.sendFee = intentExtra.getStringExtra(EnterPin3.EXTRA_SEND_FEE);
                    this.amount = intentExtra.getStringExtra(EnterPin3.EXTRA_AMOUNT);
                    this.refrenceNumber = intentExtra.getStringExtra(EnterPin3.EXTRA_REFERENCE_NUMBER);
                    PayTransactions();
                    break;
                case "MyAdapterCard2":
                    PayElectricityToken();
                    break;
                case "PayElectricity2":
                    PayElectricityTokenDialog();
                    break;
                case "MyAdapterCard1":
                    PayTvSubsriptionBottomSheet();
                    break;
                case "TvSubscriptions":
                    payTvSubscriptionDialog();
                    break;
                case "EnterPin":
                    break;
            }
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (isNetworkAvailable()) {
            RetrieveWalletBalance();

        } else {
            Snackbar.make(findViewById(R.id.drawer_layout), "No Internet Connection", Snackbar.LENGTH_LONG).show();
        }
    }


    public void moveToProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), profile.class);
        startActivity(intent);
    }

    public void OpenNotifications(MenuItem item) {
        Intent intent = new Intent(this, Notifications52.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(this, "Log out to exit", Toast.LENGTH_SHORT).show();
        }
    }

    public void movetoSettings48(MenuItem item) {
        Intent intent = new Intent(this, Settings48.class);
        startActivity(intent);

    }

    public void moveBillManagerPayment(MenuItem item) {
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);

    }

    public void moveBillManagerBiller(MenuItem item) {
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);
    }

    public void moveReferrals(MenuItem item) {
        Intent intent = new Intent(this, Referrals.class);
        startActivity(intent);
    }

    public void moveMyWallet(MenuItem item) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


    private void setToolBar() {
        setSupportActionBar(tb);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.openDrawer(GravityCompat.START);

                    }
                }
        );

    }

    public void closeMyDrawer(View view) {
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,
                menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void close_wallet3(View view) {
        bottomSheetDialog.show();
        findViewById(R.id.Ewallet3).setVisibility(View.INVISIBLE);

    }

    public void openBillManager(MenuItem item) {
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);
    }

    public void opensevrvices(MenuItem item) {
        Intent intent = new Intent(this, services.class);
        startActivity(intent);
    }

    public void openreferalls(MenuItem item) {
        Intent intent = new Intent(this, Referrals.class);
        startActivity(intent);

    }

    public void openHistory(MenuItem item) {
        Intent intent1 = new Intent(getApplicationContext(), TransactionView.class);
        startActivity(intent1);
    }

    public void openLearningHub(MenuItem item) {
        Intent intent = new Intent(this, Hub.class);
        startActivity(intent);
    }

    public void openIncomingDetails(MenuItem item) {
        Intent intent = new Intent(this, IncomeDetails.class);
        startActivity(intent);
    }

    public void openProfile(MenuItem item) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    public void openNotifications52(MenuItem item) {
        Intent intent = new Intent(this, Notifications52.class);
        startActivity(intent);
    }

    public void chooseAnotherAccount(MenuItem item) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void displayPopUp(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_up_menu_for_loan_reason);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.others) {
            findViewById(R.id.specific).setVisibility(View.VISIBLE);
        }
        return true;
    }


    public void movotopin(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.loans2).setVisibility(View.VISIBLE);


    }

    public void close_poup_loans(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.bottom_navigation_home_two).setVisibility(View.VISIBLE);

    }

    private void OpenScreen16() {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewOpenScreen16 = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.activity_screen18, (LinearLayout) findViewById(R.id.screen_16)
                );

        //bottomSheet16 recyclerview
        RecyclerView mRecyclerView = bottomSheetViewOpenScreen16.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MyAdapter myAdapter = new MyAdapter(this, getMylist());
        mRecyclerView.setAdapter(myAdapter);

        bottomSheetViewOpenScreen16.findViewById(R.id.img_close16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetViewOpenScreen16.findViewById(R.id.income_details101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), IncomeDetails.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenScreen16.findViewById(R.id.wallet101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), Home.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenScreen16.findViewById(R.id.services101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), services.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenScreen16.findViewById(R.id.exchange101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), Home.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenScreen16.findViewById(R.id.crypto101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), CryptoBalance.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenScreen16.findViewById(R.id.transfer101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), MainTransfer36.class);
                        intent.putExtra("Class", "Home");
                        startActivity(intent);
                    }
                }
        );

        bottomSheetDialog.setContentView(bottomSheetViewOpenScreen16);
        bottomSheetDialog.show();

    }

    private void OpenWallet() {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewOpenWalle = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.pop_up_my_wallet, (ConstraintLayout) findViewById(R.id.show_ple)
                );
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setDismissWithAnimation(true);
        bottomSheetViewOpenWalle.findViewById(R.id.imgCloseWallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetViewOpenWalle.findViewById(R.id.mytopupcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        openEwalletPopUp();

                    }
                }
        );

        bottomSheetViewOpenWalle.findViewById(R.id.mywithdrawcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        OpenWithdrawBottomSheet();
                    }

                });

        bottomSheetViewOpenWalle.findViewById(R.id.openFundTrasfer).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), MainTransfer36.class);
                        intent.putExtra("Class", "Home");
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenWalle.findViewById(R.id.transactionHistCard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), TransactionView.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenWalle.findViewById(R.id.pendingCommCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                TODO: intent

            }
        });

        bottomSheetDialog.setContentView(bottomSheetViewOpenWalle);
        bottomSheetDialog.show();

    }

    private void OpenWithdrawBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewOpenWithdraw = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.withdraw, (LinearLayout) findViewById(R.id.Ewallet3)
                );
        spinner = bottomSheetViewOpenWithdraw.findViewById(R.id.selectypeWithdraw);
        txtMobileNumberWithdraw = bottomSheetViewOpenWithdraw.findViewById(R.id.mobileNumberWithdraw);
        txtAmountWithdraw = bottomSheetViewOpenWithdraw.findViewById(R.id.amountWithdraw);
        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.wallet_top_up_services, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        bottomSheetViewOpenWithdraw.findViewById(R.id.btn_sendWithdrawRequest).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        MoveToConfirmWithdraw();
                    }
                }
        );
        bottomSheetViewOpenWithdraw.findViewById(R.id.cancelWithdraw).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                }
        );


        bottomSheetDialog.setContentView(bottomSheetViewOpenWithdraw);
        bottomSheetDialog.show();
    }

    private void MoveToConfirmWithdraw() {
        String productName = spinner.getSelectedItem().toString();
        if (productName.equals("Safaricom")) {
            productName = "SAF_ATP";

        }
        if (productName.equals("Airtel")) {
            productName = "AIRTEL_ATP";

        }
        if (productName.equals("Telkom")) {
            productName = "TELKOM_ATP";

        }
        String amount = txtAmountWithdraw.getText().toString().trim();
        String phoneNumber = txtMobileNumberWithdraw.getText().toString().trim();
        String key = "";
        String value = "";
        if (phoneNumber.isEmpty() || amount.isEmpty()) {
            Toast.makeText(getApplicationContext(), "please provide a the inputs", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.parseInt(amount) > 100000) {
            Toast.makeText(this, "Amount should be less than 100000", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> map = checkPhoneNo(phoneNumber);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
        }
        if (key.equals("safaricom") && !productName.equals("SAF_ATP")) {
            Toast.makeText(this, "select Safaricom", Toast.LENGTH_SHORT).show();
            return;
        }
        if (key.equals("airtel") && !productName.equals("AIRTEL_ATP")) {
            Toast.makeText(this, "select Airtel", Toast.LENGTH_SHORT).show();
            return;
        }
        if (key.equals("telkom") && productName.equals("TELKOM_ATP")) {
            Toast.makeText(this, "select Telkom", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(getApplicationContext(), EnterPin2.class);
        intent.putExtra("Class", "HomeWithdraw");
        intent.putExtra(EXTRA_PRODUCT_NAME, productName);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_ACCOUNTNUMBER, value);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void openEwalletPopUp() {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewopenEwalletPopUp = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.wallet_pop_up, (LinearLayout) findViewById(R.id.Ewallet2)
                );
        spinner = bottomSheetViewopenEwalletPopUp.findViewById(R.id.selecttype);
        txtMobilenumber = bottomSheetViewopenEwalletPopUp.findViewById(R.id.mobilenumber);
        txtAmountTopUpWallet = bottomSheetViewopenEwalletPopUp.findViewById(R.id.amountTopUpWallet);
        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.wallet_top_up_services, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        bottomSheetViewopenEwalletPopUp.findViewById(R.id.contWalletPop).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MoveToTopUpWallet();
                    }
                }
        );
        bottomSheetViewopenEwalletPopUp.findViewById(R.id.cancelWalletTopUp).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                }
        );
        bottomSheetDialog.setContentView(bottomSheetViewopenEwalletPopUp);
        bottomSheetDialog.show();
    }

    private void MoveToTopUpWallet() {
        String productName = spinner.getSelectedItem().toString();
        if (productName.equals("Safaricom")) {
            productName = "SAF_ATP";

        }
        if (productName.equals("Airtel")) {
            productName = "AIRTEL_ATP";

        }
        if (productName.equals("Telkom")) {
            productName = "TELKOM_ATP";

        }
        String amount = txtAmountTopUpWallet.getText().toString().trim();
        String phoneNumber = txtMobilenumber.getText().toString().trim();
        if (phoneNumber.isEmpty() || amount.isEmpty()) {
            Toast.makeText(getApplicationContext(), "please provide a the inputs", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.parseInt(amount) > 100000) {
            Toast.makeText(this, "Amount should be less than 100000", Toast.LENGTH_SHORT).show();
            return;
        }

//        String typePhoneNumber = checkPhoneNo(phoneNumber);
//        Toast.makeText(getApplicationContext(), "number" + typePhoneNumber, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), EnterPin2.class);
        intent.putExtra("Class", "HomePayWalletTopUp");
        intent.putExtra(EXTRA_PRODUCT_NAME, productName);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_ACCOUNTNUMBER, phoneNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }

    public void OpenWalletM(MenuItem item) {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewOpenWalletM = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.pop_up_my_wallet, (LinearLayout) findViewById(R.id.show_ple)
                );
        bottomSheetViewOpenWalletM.findViewById(R.id.imgCloseWallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetViewOpenWalletM.findViewById(R.id.mytopupcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        drawer.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(getApplicationContext(), Ewallet2_1.class);
                        startActivity(intent);
                        bottomSheetDialog.dismiss();

                    }
                }
        );
        bottomSheetViewOpenWalletM.findViewById(R.id.mywithdrawcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        bottomSheetDialog.hide();
                    }
                }
        );

        bottomSheetViewOpenWalletM.findViewById(R.id.openFundTrasfer).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), MainTransfer36.class);
//                        intent.putExtra(EXTRA_SESSION, sessionID);
//                        intent.putExtra(EXTRA_AGENTNO, AGENTNO);
                        intent.putExtra("Class", "Home");
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenWalletM.findViewById(R.id.transactionHistCard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), TransactionView.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenWalletM.findViewById(R.id.pendingCommCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                TODO: intent

            }
        });

        bottomSheetDialog.setContentView(bottomSheetViewOpenWalletM);
        bottomSheetDialog.show();

    }


    public void registerNewNumber(MenuItem item) {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewregisterNewNumber = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.activity_register_new_number, (LinearLayout) findViewById(R.id.register_new_number)
                );
        bottomSheetViewregisterNewNumber.findViewById(R.id.closeRegNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        //TextView textView = bottomSheetView.findViewById(R.id.newphoneNumber);

        bottomSheetViewregisterNewNumber.findViewById(R.id.btn_sendinvite).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        TODO:
                    }
                }
        );
        bottomSheetViewregisterNewNumber.findViewById(R.id.cancel_register_new).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                }
        );
        bottomSheetDialog.setContentView(bottomSheetViewregisterNewNumber);
        bottomSheetDialog.show();

    }

    public void PayInternetBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewPayInternet = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.pay_internet_bottom_sheet, (LinearLayout) findViewById(R.id.register_new_number)
                );
        bottomSheetViewPayInternet.findViewById(R.id.imagebtn_pay_netSheet).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                }
        );
        txtAccounNoPayNetSheet = bottomSheetViewPayInternet.findViewById(R.id.AccounNoPayNetSheet);
        txtamountPyNetSheet = bottomSheetViewPayInternet.findViewById(R.id.amountPyNetSheet);
        bottomSheetViewPayInternet.findViewById(R.id.buttonPayNetSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPayNet();
            }
        });
        bottomSheetViewPayInternet.findViewById(R.id.cancelPayNetSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetViewPayInternet);
        bottomSheetDialog.show();
    }

    private void moveToPayNet() {
        String produt_name = "ZUKU_BILLPAY";
        String AccountNumber = txtAccounNoPayNetSheet.getText().toString();
        String AmountPayable = txtamountPyNetSheet.getText().toString();
        Intent move = new Intent(getApplicationContext(), EnterPin3.class);
        move.putExtra("Class", "HomePayNet");
        move.putExtra(EXTRA_PRODUCT_NAME, produt_name);
        move.putExtra(EXTRA_ACCOUNTNUMBER, AccountNumber);
        move.putExtra(EXTRA_AMOUNT, AmountPayable);
        startActivity(move);
        bottomSheetDialog.dismiss();
        Toast.makeText(Home.this, "" + AmountPayable + AccountNumber, Toast.LENGTH_SHORT).show();
    }

    private void PayElectricityToken() {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewPayElectricity = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.buy_electricity_token_sheet, (ConstraintLayout) findViewById(R.id.buyElectricityToken)
                );
        txtTokenAccountNumber = bottomSheetViewPayElectricity.findViewById(R.id.TokenNumberSheet);
        txtAmountToken = bottomSheetViewPayElectricity.findViewById(R.id.amountBuyTokenSheet);

        bottomSheetViewPayElectricity.findViewById(R.id.buttonBuyTokenSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToPayElectricityToken();
            }
        });
        bottomSheetViewPayElectricity.findViewById(R.id.cancelBuyTokenSheetSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetViewPayElectricity);
        bottomSheetDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
            bottomSheetDialog = null;
        }

    }

    private void MoveToPayElectricityToken() {
        String productName = "KPLC_BILLPAY ";
        String AccountNumber = txtTokenAccountNumber.getText().toString();
        String amount = txtAmountToken.getText().toString();
        Intent intent = new Intent(getApplicationContext(), EnterPin3.class);
        intent.putExtra("Class", "HomePayElectricity");
        intent.putExtra(EXTRA_PRODUCT_NAME, productName);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_ACCOUNTNUMBER, AccountNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void PayTvSubsriptionBottomSheet() {
        if (bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
        }

        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetViewPayTvSubsription = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.pay_tv_subscription_sheet, (LinearLayout) findViewById(R.id.register_new_number)
                );
        spinner = bottomSheetViewPayTvSubsription.findViewById(R.id.selectServiceProviderTvSheet);

        adapter = ArrayAdapter.createFromResource(this,    // setting array-adapter belonging to spinner
                R.array.tv_service_products, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        txtPaytvAccountNoSheet = bottomSheetViewPayTvSubsription.findViewById(R.id.PaytvAccountNoSheet);
        txtAmountPayTvSheet = bottomSheetViewPayTvSubsription.findViewById(R.id.amountPayTvSheet);
        bottomSheetViewPayTvSubsription.findViewById(R.id.imagebtn_pay_tvSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        MaterialButton button = bottomSheetViewPayTvSubsription.findViewById(R.id.buttonPayTvSheet2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToPayTvSubcription();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetViewPayTvSubsription.findViewById(R.id.cancelPayTvSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetViewPayTvSubsription);
        bottomSheetDialog.show();
    }

    private void MoveToPayTvSubcription() {
        String productName = spinner.getSelectedItem().toString();
        if (productName.equals("ZUKU PayBill")) {
            productName = "ZUKU_BILLPAY";

        }
        if (productName.equals("STARTIMES PayBill")) {
            productName = "STARTIMES_BILLPAY";

        }
        if (productName.equals("DSTV PayBill")) {
            productName = "DSTV_BILLPAY";

        }
        if (productName.equals("GOTV PayBill")) {
            productName = "GOTV_BILLPAY";

        }
        String amount = txtAmountPayTvSheet.getText().toString().trim();

        if (Integer.parseInt(amount) > 100000) {
            Toast.makeText(this, "Amount should be less than 100000", Toast.LENGTH_SHORT).show();
            return;
        }
        String AccountNumber = txtPaytvAccountNoSheet.getText().toString();
        Intent intent = new Intent(getApplicationContext(), EnterPin2.class);
        intent.putExtra("Class", "HomePayTvSubscription");
        intent.putExtra(EXTRA_PRODUCT_NAME, productName);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_ACCOUNTNUMBER, AccountNumber);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


    private void transferListDetails() {

//        mImage and mNames ArrayList go here
        new Thread(new Runnable() {
            @Override
            public void run() {
                mImageUrls.add("https://images.pexels.com/photos/4328961/pexels-photo-4328961.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                mNames.add("John");

                mImageUrls.add("https://images.pexels.com/photos/2951142/pexels-photo-2951142.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                mNames.add("Lucy");

                mImageUrls.add("https://images.pexels.com/photos/4015088/pexels-photo-4015088.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                mNames.add("ivy");

                mImageUrls.add("https://images.pexels.com/photos/2954199/pexels-photo-2954199.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                mNames.add("Mark");

                mImageUrls.add("https://images.pexels.com/photos/4015088/pexels-photo-4015088.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                mNames.add("ivy");


                mImageUrls.add("https://images.pexels.com/photos/2954199/pexels-photo-2954199.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                mNames.add("Mark");

            }
        }).start();

        initTransferRecyclerList();
    }

    private void initTransferRecyclerList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewHomeAdapter adapter = new RecyclerViewHomeAdapter(mNames, mImageUrls, Home.this);
        recyclerView.setAdapter(adapter);

    }

    public void LogoutAccount(MenuItem item) {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("LogIn", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("session_token", null);
        editor.apply();
        Intent move = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(move);
        finish();

    }

    private void ShowWalletTopUpConfDialog() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.wallet_top_up_confirmation_dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        MaterialButton button = view.findViewById(R.id.confDialogUnsuccessful);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void withdrawConfirmationDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.success_withdraw_confirmation, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn = view.findViewById(R.id.confDialogSuccessful);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void PayInternetorWaterDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.payment_successfull, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView txtRefNumberSuccess = view.findViewById(R.id.refNumberSuccessD);
        TextView txtAmountSuccessPay = view.findViewById(R.id.amountSuccessDPay);
        TextView txtBalanceSuccess = view.findViewById(R.id.balanceSuccessD);

        txtAmountSuccessPay.setText(amount);
        txtBalanceSuccess.setText(sendFee);
        txtRefNumberSuccess.setText(refrenceNumber);
        Button btn = view.findViewById(R.id.btn_continue_pay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void PayTransactions() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.transfer_unsuccessful_popup, null);
        alertDialogTrans = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        Objects.requireNonNull(alertDialogTrans.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView txtReference_number = view.findViewById(R.id.reference_numberTra);
        TextView txtAmount_sent_note = view.findViewById(R.id.amount_sent_note);
        TextView txtBalance_note2 = view.findViewById(R.id.balance_note2);

        txtReference_number.setText(refrenceNumber);
        txtAmount_sent_note.setText(amount);
        txtBalance_note2.setText(sendFee);
        Button btn = view.findViewById(R.id.try_again_unsuccessful);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogTrans.dismiss();
            }
        });
        alertDialogTrans.show();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (alertDialogTrans != null) {
            alertDialogTrans.dismiss();
            alertDialogTrans = null;
        }
    }

    private void PayElectricityTokenDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.success_withdraw_confirmation, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn = view.findViewById(R.id.confDialogSuccessful);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void payTvSubscriptionDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.success_withdraw_confirmation, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn = view.findViewById(R.id.confDialogSuccessful);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }


    public ArrayList<Model> getMylist() {
        ArrayList<Model> models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("PayTv");
        m.setImage(R.drawable.ic_exchange);
        models.add(m);

        Model m3 = new Model();
        m3.setTitle("Saved Billers");
        m3.setImage(R.mipmap.group_18);
        models.add(m3);

        Model m1 = new Model();
        m1.setTitle("Electricity");
        m1.setImage(R.drawable.ic_services);
        models.add(m1);

        Model m2 = new Model();
        m2.setTitle("Pay Internet");
        m2.setImage(R.drawable.ic_wallet);
        models.add(m2);

        Model m4 = new Model();
        m4.setTitle("Buy Airtime");
        m4.setImage(R.drawable.ic_wallet);
        models.add(m4);

        return models;
    }

    public Map<String, String> checkPhoneNo(String inputPhone) {
        String validPhoneNo = "Fasle";
        Map<String, String> map = new HashMap<>();
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
                if (replPhone2.startsWith("0")) {
                    replPhone3 = replPhone2.replaceFirst("0", "\\254");
                    Log.e("TAG phone starts 0", replPhone3);
                    validPhoneNo = replPhone3;
                } else if (replPhone2.startsWith("7")) {
                    replPhone3 = replPhone2.replaceFirst("7", "\\254");
                    Log.e("TAG phone starts 7", replPhone3);
                    validPhoneNo = replPhone3;
                } else if (replPhone2.startsWith("+")) {
                    validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                    Log.e("TAG phone number +", validPhoneNo);
                }
            } else {
                patt = Pattern.compile(airtel);
                match = patt.matcher(replPhone2);
                if (match.find()) {
                    Toast.makeText(getApplicationContext(), "Airtel Number", Toast.LENGTH_LONG).show();
                    String replPhone3 = "null";
                    phoneCompany = "airtel";
                    if (replPhone2.startsWith("0")) {
                        replPhone3 = replPhone2.replaceFirst("0", "\\254");
                        Log.e("TAG phone starts 0", replPhone3);
                        validPhoneNo = replPhone3;
                    } else if (replPhone2.startsWith("7")) {
                        replPhone3 = replPhone2.replaceFirst("7", "\\254");
                        Log.e("TAG phone starts 7", replPhone3);
                        validPhoneNo = replPhone3;
                    } else if (replPhone2.startsWith("+")) {
                        validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                        Log.e("TAG phone number +", validPhoneNo);
                    }
                } else {
                    patt = Pattern.compile(telkom);
                    match = patt.matcher(replPhone2);
                    if (match.find()) {
                        Toast.makeText(getApplicationContext(), "Telkom Number", Toast.LENGTH_LONG).show();
                        String replPhone3 = "null";
                        phoneCompany = "telkom";
                        if (replPhone2.startsWith("0")) {
                            replPhone3 = replPhone2.replaceFirst("0", "\\254");
                            Log.e("TAG phone starts 0", replPhone3);
                            validPhoneNo = replPhone3;
                        } else if (replPhone2.startsWith("7")) {
                            replPhone3 = replPhone2.replaceFirst("7", "\\254");
                            Log.e("TAG phone starts 7", replPhone3);
                            validPhoneNo = replPhone3;
                        } else if (replPhone2.startsWith("+")) {
                            validPhoneNo = replPhone2.replaceAll("[\\-\\+\\.\\^:,]", "");
                            Log.e("TAG phone number +", validPhoneNo);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter a valid mobile number 'Safaricom only'", Toast.LENGTH_LONG).show();
                        Log.e("TAG phone No not check", replPhone2);
                    }
                }

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a mobile number ", Toast.LENGTH_LONG).show();
        }
        map.put(phoneCompany, validPhoneNo);
        return map;
    }

    private void RetrieveWalletBalance() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");

        Retrofit retrofit = RetrofitClient.getInstance();  //        Getting Retrofit Instance
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);

        Call<ApiJsonObjects> call = jsonPlaceHolders.getBalance(headers);
        call.enqueue(new Callback<ApiJsonObjects>() {
            @Override
            public void onResponse(Call<ApiJsonObjects> call, retrofit2.Response<ApiJsonObjects> response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (!response.isSuccessful()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Home.this, "code" + response.code() + response.message(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            SharedPreferences pref = getApplicationContext().getSharedPreferences("LogIn", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("session_token", null);
                            editor.apply();
                            Intent move = new Intent(getApplicationContext(), LogIn.class);
                            startActivity(move);
                            finish();
                            return;
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                balanceModel = response.body().getBalances();
                                for (BalanceModel balanceModel : balanceModel) {
                                    MY_BALANCE = balanceModel.getBalance();

                                    tvtext.setText("KES " + MY_BALANCE);
                                }

                            }
                        });

                    }
                }).start();
            }

            @Override
            public void onFailure(Call<ApiJsonObjects> call, Throwable t) {
                Toast.makeText(Home.this, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cardBuyAirtime:
                i = new Intent(this, Top_up.class);
                i.putExtra("Class", "Home");
                startActivity(i);
                break;
            case R.id.services:
                i = new Intent(this, Home.class);
                startActivity(i);
                break;
            case R.id.TransferMain:
                i = new Intent(this, TransactionView.class);
                i.putExtra("Class", "transaction");
                startActivity(i);
                break;
            case R.id.transfer_money_button:
                i = new Intent(this, MainTransfer36.class);
                i.putExtra("Class", "Home");
                startActivity(i);
                break;
            default:
                break;
        }

    }


    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

}

