package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setToolBar();
        button = (Button)findViewById(R.id.btn_getstarted);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movetoRegistration();
            }
        });
    }


    public void movetoRegistration() {
        System.out.println("Button pressed");
        Intent move = new Intent(this,LinkAccount11.class);
        startActivity(move);
    }

    /*private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    //code for image
    //ImageView mImageView = (ImageView)findViewById(R.id.mImageView);

//set resource for imageview

    //mImageView.setImageResource(R.drawable.your_image_name);*/
}