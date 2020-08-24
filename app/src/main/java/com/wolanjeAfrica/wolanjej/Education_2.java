package com.example.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class Education_2 extends AppCompatActivity {
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_2);
        setActionBarColor();
        setToolBar(tb);
    }

    public void MoveToPin(View view) {
        Intent move = new Intent(this, EnterPin.class);
        move.putExtra("Class", "Education_2");
        startActivity(move);
    }

    private void setActionBarColor() {
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }

    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this, Education1.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveToLogo.putExtra("Class", "Education_2");
                        startActivity(moveToLogo);
                        finish();
                    }
                }
        );

    }
}