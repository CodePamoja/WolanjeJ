package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Top_up extends AppCompatActivity implements View.OnClickListener{
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        button = findViewById(R.id.btn_continue_top_up);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.income_details101: intent = new Intent(this, IncomeDetails.class);startActivity(intent);
            break;
            case R.id.btn_continue_top_up:intent = new Intent(this,TopupOtherNumber.class);startActivity(intent);
            default:break;
    }
}
}