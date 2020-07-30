package com.example.wolanjej;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class Reg03Activity extends AppCompatActivity {

    private Intent myintent;
    private TextView filetxt;
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_03);

        filetxt = findViewById(R.id.filetxt);


        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbarhome);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,Reg_02.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10){
            if (requestCode == RESULT_OK){
                String datafromfile  = data.getData().getPath();
                filetxt.setText(datafromfile);
            }
        }


    }


    public void movetoreg04(View view) {
        Intent movetoreg4 = new Intent(this,Reg_04.class);
        startActivity(movetoreg4);
    }

    public void uploadid(View view) {

        Toast.makeText(this, "pressed", Toast.LENGTH_SHORT).show();
        myintent = new Intent(Intent.ACTION_GET_CONTENT);
        myintent.setType("*/*");
        startActivityForResult(myintent,10);
    }
}