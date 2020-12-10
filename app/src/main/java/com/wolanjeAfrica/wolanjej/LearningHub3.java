package com.wolanjeAfrica.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.wolanjeAfrica.wolanjej.models.AvailableBuses;
import com.wolanjeAfrica.wolanjej.models.LearningHubFeatured;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LearningHub3 extends AppCompatActivity {

    private static final String TAG = "LearningHub3";
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_hub3);
        setToolBar();
        relativeLayout = findViewById(R.id.featuredimage);

        Intent intent = getIntent();
        LearningHubFeatured learningHubFeatured = intent.getParcelableExtra("featured");
        Log.e(TAG, "onCreate: "+learningHubFeatured.getFeatured_item_image()+","+learningHubFeatured.getFeatured_item_description()+","+
                learningHubFeatured.getFeatured_item_title());
        Bitmap myImage = getBitmapFromURL(learningHubFeatured.getFeatured_item_image());
        Drawable dr = new BitmapDrawable(myImage);
        relativeLayout.setBackground(dr);
    }
    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this, Learning_Hub2.class);
        tb.setNavigationOnClickListener(
                v -> startActivity(movetoLogo)
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void moveToChapter01(View view) {
        Intent intent = new Intent(this, LearningHub04.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}