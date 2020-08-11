package com.example.wolanjej;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Education1 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    TextView tx;
    Button btnpro;
    ImageButton btnback;
    Toolbar tb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_1);
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

    public void displayPopUpfee(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_for_fee_details);
        popup.show();
    }
}
