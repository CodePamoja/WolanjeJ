package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class Education_50 extends AppCompatActivity {

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
                Intent intent = new Intent(getApplicationContext(), Education1.class);
                intent.putExtra("Class", "Education50");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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

}