package com.wolanjeAfrica.wolanjej;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.wolanjeAfrica.wolanjej.models.Model;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.RecyclerViewHomeAdapter;

import java.util.ArrayList;

public class HomeTwo extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {

    private static final String EXTRA_CLASS_TYPE =  "com.example.wolanjej.CLASS_TYPE";
    private static String ClassType;
    private ArrayAdapter adapter;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Button transfer_money_button_hm2;
    private MaterialCardView materialCardView, materialCardView1, materialCardView2;
    private BottomSheetDialog bottomSheetDialog;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Spinner spinner;
    private TextView txtMobilenumber, txtAmountTopUpWallet, txtMobileNumberWithdraw, txtAmountWithdraw;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);
        toolbar = findViewById(R.id.toolbarhome2);
        drawer = findViewById(R.id.drawer_layout_home_two);
        transfer_money_button_hm2 = findViewById(R.id.transfer_money_button_hm2);
        transfer_money_button_hm2.setOnClickListener(this);
        materialCardView = (MaterialCardView) findViewById(R.id.loanscardHomeTwo);
        materialCardView.setOnClickListener(this);
        materialCardView1 = (MaterialCardView) findViewById(R.id.card1_home_two);
        materialCardView1.setOnClickListener(this);
        materialCardView2 = (MaterialCardView) findViewById(R.id.loanactivator);
        materialCardView2.setOnClickListener(this);
        button = (Button) findViewById(R.id.wallet_home_two);
        button.setOnClickListener(this);

        Intent intentExtra = getIntent();
        String className = getIntent().getStringExtra("Class");
        if (className != null) {
            Log.e("class Type className", className);
            switch (className) {
                case "EnterPinloan":
                    ShowLoanSuccess();
                    break;
                default:
                    break;
            }
        }
        transferListDetails();
        setToolBar();
    }


    private void setToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.openDrawer(GravityCompat.START);

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

    public void closeMyDrawer(View view) {
        drawer.closeDrawer(GravityCompat.START);

    }

    public void opensevrvices(MenuItem item) {
        Intent intent = new Intent(this, services.class);
        startActivity(intent);
    }

    public void openIncomingDetails(MenuItem item) {
        Intent intent = new Intent(this, IncomeDetails.class);
        startActivity(intent);
    }

    public void openLearningHub(MenuItem item) {
        Log.e("Found", "Troal");
        Intent intent = new Intent(this, Learning_Hub2.class);
        startActivity(intent);
    }

    public void openProfile(MenuItem item) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    public void movetoSettings48(MenuItem item) {
        Intent intent = new Intent(this, Settings48.class);
        startActivity(intent);

    }

    public void moveMyWallet(MenuItem item) {
        Intent intent = new Intent(this, HomeTwo.class);
        startActivity(intent);
    }

    public void moveBillManagerBiller(MenuItem item) {
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);
    }

    public void OpenWalletMHome2(MenuItem item) {
        bottomSheetDialog = new BottomSheetDialog(
                HomeTwo.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetViewOpenWalletM = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.pop_up_my_wallet, (ConstraintLayout) findViewById(R.id.show_ple)
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
                        bottomSheetDialog.dismiss();
//                        openEwalletPopUp();

                    }
                }
        );
        bottomSheetViewOpenWalletM.findViewById(R.id.mywithdrawcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
//                        OpenWithdrawBottomSheet();
                    }
                }
        );

        bottomSheetViewOpenWalletM.findViewById(R.id.openFundTrasfer).setOnClickListener(
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
        bottomSheetViewOpenWalletM.findViewById(R.id.transactionHistCard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), TransactionView.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetViewOpenWalletM.findViewById(R.id.pendingCommCard).setVisibility(View.INVISIBLE);


        if (bottomSheetViewOpenWalletM.getParent() != null) {
            ((ViewGroup) bottomSheetViewOpenWalletM.getParent()).removeView(bottomSheetViewOpenWalletM); // <- fix
        }
        bottomSheetDialog.setContentView(bottomSheetViewOpenWalletM);
        bottomSheetDialog.show();

    }

    private void OpenWallet() {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                HomeTwo.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetViewOpenWalle = LayoutInflater.from(getApplicationContext())
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
                        intent.putExtra("Class", "HomeTwo");
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
        if (bottomSheetViewOpenWalle.getParent() != null) {
            ((ViewGroup) bottomSheetViewOpenWalle.getParent()).removeView(bottomSheetViewOpenWalle); // <- fix
            OpenWallet();
            return;
        }
        bottomSheetDialog.setContentView(bottomSheetViewOpenWalle);
        bottomSheetDialog.show();

    }

    private void openEwalletPopUp() {
        bottomSheetDialog = new BottomSheetDialog(
                HomeTwo.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetViewopenEwalletPopUp = LayoutInflater.from(getApplicationContext())
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
//                        MoveToTopUpWallet();
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

    private void OpenWithdrawBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(
                HomeTwo.this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetViewOpenWithdraw = LayoutInflater.from(getApplicationContext())
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
//                        MoveToConfirmWithdraw();
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
    private void ShowLoanSuccess() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.loan_req_success, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageButton button = view.findViewById(R.id.btn_closeSuccess);
        button.setOnClickListener(new View.OnClickListener() {
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
        m.setTitle("Pay Tv");
        m.setImage(R.drawable.ic_exchange);
        models.add(m);

        Model m3 = new Model();
        m3.setTitle("Saved Billers");
        m3.setImage(R.mipmap.group_18);
        models.add(m3);

        Model m1 = new Model();
        m1.setTitle("Electricity");
        m1.setImage(R.drawable.ic_services);
        models.add(m);

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


    public void backtohomevisibility(View view) {
        Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
        //findViewById(R.id.loansholder).setVisibility(View.INVISIBLE);
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
            return true;
        } else {
            return true;
        }
    }

    public void openloanspop(View view) {
//        findViewById(R.id.loansholder).setVisibility(View.INVISIBLE);
//        TODO DELETE
        findViewById(R.id.Loansbox).setVisibility(View.VISIBLE);
    }

    public void movotopin(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.loans2).setVisibility(View.VISIBLE);


    }

    public void close_poup_loans(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.bottom_navigation_home_two).setVisibility(View.VISIBLE);

    }

    private void transferListDetails() {

        //        mImage and mNames ArrayList go here
//        mImageUrls.add("https://pixabay.com/photos/tree-sunset-amazing-beautiful-736885/");
//        mNames.add("John");

        initTransferRecyclerList();
    }

    private void initTransferRecyclerList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeTwo.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewHomeTwo);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewHomeAdapter adapter = new RecyclerViewHomeAdapter(mNames, mImageUrls, HomeTwo.this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.transfer_money_button_hm2:
            case R.id.loanscardHomeTwo:
                intent = new Intent(HomeTwo.this, MainTransfer36.class);
                intent.putExtra("Class", "HomeTwo");
                startActivity(intent);
                break;
            case R.id.card1_home_two:
                intent = new Intent(HomeTwo.this, BulkPaymentsMain.class);
                intent.putExtra("Class","HomeTwo");
                startActivity(intent);
                break;
            case R.id.wallet_home_two:
                OpenWallet();
                break;
            case R.id.loanactivator:
                intent = new Intent(HomeTwo.this, Loans.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Class","HomeTwo");
                intent.putExtra(EXTRA_CLASS_TYPE,"HomeTwoloans");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}