package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.Menu;
=======
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
>>>>>>> 65bcc79153e5c71c835de7ae7a148617e5821457
import android.widget.ImageView;

public class Registration07 extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_registration07);

    mImageView.setImageResource(R.mipmap.group_7);
}
=======
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration07);

        setToolBar();
        imageView = (ImageView)findViewById(R.id.image_holder);
        imageView.setImageResource(R.mipmap.group_6);
    }
>>>>>>> 65bcc79153e5c71c835de7ae7a148617e5821457

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
<<<<<<< HEAD
=======
        final Intent movetoLogo = new Intent(this, Registration06.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );
>>>>>>> 65bcc79153e5c71c835de7ae7a148617e5821457

    }


<<<<<<< HEAD
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
=======
    public void sendtoFingerPrint(View view) {
        Intent move = new Intent(this, Registration08.class);
        startActivity(move);

>>>>>>> 65bcc79153e5c71c835de7ae7a148617e5821457
    }

    //code for image
    ImageView mImageView = (ImageView)findViewById(R.id.image_holder);



//set resource for imageview
}