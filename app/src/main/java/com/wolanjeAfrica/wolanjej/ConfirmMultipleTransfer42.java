package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.models.SelectUser;
import com.wolanjeAfrica.wolanjej.models.Transactions;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.ConfirmMultipleTransaction;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.RecyclerViewHomeAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class ConfirmMultipleTransfer42 extends AppCompatActivity {

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNAME = "com.example.wolanjej.AGENTNAME";
    private static final String TAG = "ConfirmMultipleTransfer";
    public static final String EXTRA_PARENTCLASSNAME ="com.example.wolanjej.PARENTCLASSNAME";
    public Button button;
    public TextView textView1, textView2, textView3, textView4;
    private String agentName;
    private String sessionId;
    private Transactions transactions;
    public static List<SelectUser> listOfMultiUserConfirmMultipleTransfer42= new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_multiple_transfer42);
        setToolBar();
        setActionBarColor();
        Intent intent = getIntent();
        this.sessionId = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_SESSION);
        this.agentName = intent.getStringExtra(TransferToWalletMultiple40.EXTRA_AGENTNO);
        Bundle bundle = getIntent().getExtras();
        transactions = bundle.getParcelable("transactions");
        listOfMultiUserConfirmMultipleTransfer42 = TransferToWalletMultiple40.listOfMultiUserTransferToWalletMultiple40;
        List<SelectUser> listWithoutDuplicates = listOfMultiUserConfirmMultipleTransfer42.stream()
                .distinct()
                .collect(Collectors.toList());
        initTransferRecyclerList(listWithoutDuplicates);

    }

    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, TransferToWalletMultiple40.class);
        tb.setNavigationOnClickListener(
                v -> startActivity(movetoLogo)
        );
    }



    private void initTransferRecyclerList( List<SelectUser> transactionsList) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(ConfirmMultipleTransfer42.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.multipletransactions);
        recyclerView.setLayoutManager(layoutManager);
        ConfirmMultipleTransaction adapter = new ConfirmMultipleTransaction(ConfirmMultipleTransfer42.this, transactionsList);
        recyclerView.setAdapter(adapter);

    }

    public void movetoEnterpin(View view) {
        Intent move = new Intent(this, EnterPin.class);
        Bundle bundle = new Bundle();
        move.putExtra("Class", "ConfirmMultipleTransfer");
        move.putExtra(EXTRA_SESSION, sessionId);
        move.putExtra(EXTRA_AGENTNAME, agentName);
        move.putExtra(EXTRA_PARENTCLASSNAME, "Home");
        bundle.putParcelable("transactions", transactions);
        move.putExtras(bundle);
        startActivity(move);
        startActivity(move);
    }
}