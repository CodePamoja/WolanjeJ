package com.example.wolanjej;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Reg03Activity extends AppCompatActivity {

    private Intent myintent;
    private TextView filetxt;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_03);

        filetxt = findViewById(R.id.filetxt);

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