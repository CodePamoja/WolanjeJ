package com.example.wolanjej;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Reg_02 extends AppCompatActivity {


    private Intent myintent;
    private TextView filetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_02);
        filetxt = findViewById(R.id.myidupload);
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

    public void movetoreg3(View view) {

        startActivity(new Intent(this,Reg03Activity.class));
    }

    public void loadCopyOfId(View view) {


        Toast.makeText(this, "pressed", Toast.LENGTH_SHORT).show();
        myintent = new Intent(Intent.ACTION_GET_CONTENT);
        myintent.setType("*/*");
        startActivityForResult(myintent,10);
    }
}