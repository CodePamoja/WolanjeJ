package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class LearningHub04 extends AppCompatActivity {
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_hub04);
        setToolBar();
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout12);
        relativeLayout.setBackgroundResource(R.mipmap.bitmapic);
    }
    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.l4_toolbar);
        setSupportActionBar(tb);
        final Intent movetoLogo = new Intent(this, LearningHub3.class);
        tb.setNavigationOnClickListener(
                v -> startActivity(movetoLogo)
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }
    public void moveToLearningHub05(View view) {
        Intent intent = new Intent(this,LearningHub05.class);
        startActivity(intent);
    }
}