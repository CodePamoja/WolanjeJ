package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
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
import com.google.android.material.card.MaterialCardView;
import com.wolanjeAfrica.wolanjej.models.Model;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.RecyclerViewHomeAdapter;

import java.util.ArrayList;

public class HomeTwo extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {


    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Button transfer_money_button_hm2;
    private MaterialCardView materialCardView, materialCardView1;
    private BottomSheetDialog bottomSheetDialog;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        toolbar = findViewById(R.id.toolbarhome2);
        drawer = findViewById(R.id.drawer_layout_home_two);
        transfer_money_button_hm2 = findViewById(R.id.transfer_money_button_hm2);transfer_money_button_hm2.setOnClickListener(this);
        materialCardView = (MaterialCardView)findViewById(R.id.loanscardHomeTwo);materialCardView.setOnClickListener(this);
        materialCardView1 = (MaterialCardView)findViewById(R.id.card1_home_two);materialCardView1.setOnClickListener(this);
        setToolBar();

        transferListDetails();
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
        Intent intent = new Intent(this, Hub.class);
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
                        openEwalletPopUp();

                    }
                }
        );
        bottomSheetViewOpenWalletM.findViewById(R.id.mywithdrawcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        OpenWithdrawBottomSheet();
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

    private void OpenWithdrawBottomSheet() {
    }

    private void openEwalletPopUp() {
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
        switch (v.getId()){
            case R.id.transfer_money_button_hm2:
            case R.id.loanscardHomeTwo:
                intent = new Intent(HomeTwo.this, MainTransfer36.class);
                startActivity(intent);
                break;
            case R.id.card1_home_two:
                intent = new Intent(HomeTwo.this, BulkPaymentsMain.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}