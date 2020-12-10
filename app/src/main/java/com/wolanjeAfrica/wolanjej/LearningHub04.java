package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class LearningHub04 extends AppCompatActivity {
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_hub04);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout12);
        relativeLayout.setBackgroundResource(R.mipmap.bitmapic);
    }

    public void moveToLearningHub05(View view) {
        Intent intent = new Intent(this,LearningHub05.class);
        startActivity(intent);
    }
}