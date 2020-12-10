package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;

public class LearningHub06 extends AppCompatActivity {
    private ProgressBar progressBar;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_hub06);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setProgress(10);
        progressBar.setMax(100);
    }

    public void moveToLearningHub07(View view) {
        Intent intent = new Intent(this, LearningHub07.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}