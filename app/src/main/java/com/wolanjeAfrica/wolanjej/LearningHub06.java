package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class LearningHub06 extends AppCompatActivity {
    private static final String TAG = "LearningHub06";
    private ProgressBar progressBar;
    private CheckBox checkBox, checkBox1, checkBox2, checkBox3,checkBox4;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_hub06);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        progressBar.setProgress(10);
        progressBar.setMax(100);
        checkBox = (CheckBox) findViewById(R.id.Choice1);
        checkBox1 = (CheckBox) findViewById(R.id.Choice2);
        checkBox2 = (CheckBox) findViewById(R.id.Choice3);
        checkBox3 = (CheckBox) findViewById(R.id.Choice4);
        checkBox4 = (CheckBox) findViewById(R.id.Choice5);

        Log.e(TAG, "onCreate: "+"this is" );
        imageButton =(ImageButton)findViewById(R.id.buttonClose);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent movetoLogo = new Intent(LearningHub06.this, LearningHub05.class);
                startActivity(movetoLogo);
            }
        });

        checkBoxActionFunction();
    }

    private void checkBoxActionFunction() {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkBox.setBackgroundColor(getResources().getColor(R.color.warm_purple));
            checkBox.setTextColor(getResources().getColor(R.color.colorWhite));
        });

        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkBox1.setBackgroundColor(getResources().getColor(R.color.warm_purple));
            checkBox1.setTextColor(getResources().getColor(R.color.colorWhite));
        });

        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkBox2.setBackgroundColor(getResources().getColor(R.color.warm_purple));
            checkBox2.setTextColor(getResources().getColor(R.color.colorWhite));
        });

        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkBox3.setBackgroundColor(getResources().getColor(R.color.warm_purple));
            checkBox3.setTextColor(getResources().getColor(R.color.colorWhite));
        });

        checkBox4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            checkBox4.setBackgroundColor(getResources().getColor(R.color.warm_purple));
            checkBox4.setTextColor(getResources().getColor(R.color.colorWhite));
        });

    }

    public void moveToLearningHub07(View view) {
        Intent intent = new Intent(this, LearningHub07.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



}