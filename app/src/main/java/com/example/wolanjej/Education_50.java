package com.example.wolanjej;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class Education_50 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    TextView tx;
    Button btnpro;
    ImageButton btnback;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu);


        findViewById(R.id.cardpayfee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.edu1).setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.scholarshipOpener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Education_50.this, "scholarship", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getBaseContext(), Scholarship.class));
                //startActivity(new Intent(getParent(),Scholarship.class));
            }
        });
        findViewById(R.id.SponsorOpener).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Education_50.this, "scholarship", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getBaseContext(), Sponsors00.class));
            }
        });

        btnpro = findViewById(R.id.buttonproceed);
        btnback = findViewById(R.id.imageback);
        tx = findViewById(R.id.headertext);
        findViewById(R.id.con).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnback.setEnabled(false);
                btnback.setAlpha(0.3f);
                findViewById(R.id.loans2).setVisibility(View.GONE);
                findViewById(R.id.scroll).setVisibility(View.VISIBLE);
                findViewById(R.id.imageback).setVisibility(View.VISIBLE);
                findViewById(R.id.cardtutionfee).setVisibility(View.GONE);
                findViewById(R.id.cardCornfirm).setVisibility(View.GONE);
                findViewById(R.id.cardDone).setVisibility(View.VISIBLE);

                tx.setText("Tution Fee");
                tx.setAlpha(0.3f);
                btnpro.setText("Proceed");
                btnpro.setAlpha(0.3f);
                btnpro.setEnabled(false);

            }
        });
        setToolBar(tb);
        setActionBarColor();
    }

    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
    }

    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbarEducation);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,services.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    public void displayPopUp1(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_up_for_counties);
        popup.show();

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }


    public void Backtoeducation(View view) {
        findViewById(R.id.edu1).setVisibility(View.GONE);
    }

    public void confirmfeepayment(View view) {
        btnpro = findViewById(R.id.buttonproceed);
        tx = findViewById(R.id.headertext);
        if (!btnpro.getText().equals("Continue")) {

            findViewById(R.id.cardtutionfee).setVisibility(View.GONE);
            findViewById(R.id.cardCornfirm).setVisibility(View.VISIBLE);

            tx.setText("Confirm");
            btnpro.setText("Continue");


        } else if (btnpro.getText().equals("Continue")) {
            findViewById(R.id.loans2).setVisibility(View.VISIBLE);
            findViewById(R.id.scroll).setVisibility(View.GONE);
            findViewById(R.id.imageback).setVisibility(View.GONE);

        }
    }

    public void displayPopUpfee(View view) {

        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_for_fee_details);
        popup.show();

    }
}