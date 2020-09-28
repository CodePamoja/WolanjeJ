package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import maes.tech.intentanim.CustomIntent;

public class LinkCardSuccessfully extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_card_successfully);
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
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.bShadeGray));


    }

    public void MoveToHome(View view) {
        Intent movetohome = new Intent(this,Home.class);
        movetohome.putExtra("Class", "LinkAccount11");
        movetohome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(movetohome);
        CustomIntent.customType(LinkCardSuccessfully.this, "left-to-right");
        finish();
    }
}