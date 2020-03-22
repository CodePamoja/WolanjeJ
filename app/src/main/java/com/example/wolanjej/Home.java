package com.example.wolanjej;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ResponseCache;

import static com.example.wolanjej.R.color.warm_purple;

public class Home extends AppCompatActivity implements View.OnClickListener{
    Toolbar tb;
    DrawerLayout drawer;
    Button transferMoney, viewall;
    private EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        tb = findViewById(R.id.toolbarhome);
        drawer = findViewById(R.id.drawer_layout);

        viewall = (Button)findViewById(R.id.btnviewall);
        viewall.setOnClickListener(this);

        transferMoney = (Button)findViewById(R.id.transfer_money_button);
        transferMoney.setOnClickListener(this);

     //start of  end of registernew number for activity_new_number


        Button btn_sendinvite = findViewById(R.id.btn_sendinvite);
        btn_sendinvite.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("Button Clicked");
//                sendToVerification();
            }
        });


        TextView cancel = findViewById(R.id.cancel_register_new);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cancelled");
                closeMyDrawer1();
            }
        });
      
      //end of registernew number for activity_new_number

//        findViewById(R.id.test7w1).setOnClickListener(

//        findViewById(R.id.mytopupcard).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
//                        findViewById(R.id.show_ple).setVisibility(View.INVISIBLE);
//                        findViewById(R.id.Ewallet2).setVisibility(View.VISIBLE);
//                    }
//                }
//        );
        findViewById(R.id.mywithdrawcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
                        findViewById(R.id.show_ple).setVisibility(View.INVISIBLE);
                        findViewById(R.id.Ewallet3).setVisibility(View.VISIBLE);

                    }
                }
        );

        findViewById(R.id.opentrans).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getBaseContext(), MainTransfer36.class);
                        startActivity(i);
                  }
                }
        );

//            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,tb,R.string.Open_drawer,R.string.Close_drawer);
        //      drawer.addDrawerListener(toggle);
        //    toggle.syncState();


        setToolBar();
    }


    //    close drawer on register new number
    private void closeMyDrawer1() {
        drawer.closeDrawer(GravityCompat.START);
    }

//    private void sendToVerification() {
//        text = findViewById(R.id.newphoneNumber);
//        String value = text.getText().toString();
//        System.out.println(value);
//
//        JSONObject jValue = new JSONObject();
//        try {
//            jValue.put("phone", value);
//            Log.e("JValues",jValue.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String url = "/register";
//        OkhttpConnection okConn = new OkhttpConnection();
//        ResponseCache result = okConn.postRequest(url,jValue.toString());
//        System.out.println(result);
//
//        Intent move = new Intent(this, Home.class);
//        startActivity(move);
//    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();}
    }

    public void movetoSettings48(MenuItem item){
        Intent intent = new Intent(this, Settings48.class);
        startActivity(intent);

    }

    public void moveBillManagerPayment(MenuItem item){
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);

    }

    public void moveBillManagerBiller(MenuItem item){
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);
    }

    public void moveReferrals(MenuItem item){
        Intent intent = new Intent(this, Referrals.class);
        startActivity(intent);
    }

    public void moveMyWallet(MenuItem item){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void setToolBar() {
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.openDrawer(GravityCompat.START);

                    }
                }
        );

    }

    public void closeMyDrawer(View view) {
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,
                menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void maketoast(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);

        findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
        findViewById(R.id.show_ple).setVisibility(View.VISIBLE);
 findViewById(R.id.show_ple).setVisibility(View.VISIBLE);

    }
//    public void movetoTransfer(){
//        Intent move = new Intent(this, MainTransfer36.class);
//        startActivity(move);
//    }

    public void close_show_ple(View view) {
        findViewById(R.id.show_ple).setVisibility(View.INVISIBLE);

        findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

    }

    public void open_screen16(View view) {
        findViewById(R.id.screen_16).setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btnviewall: i = new Intent(this,screen18.class);startActivity(i); break;
            case R.id.transfer_money_button: i = new Intent(this,MainTransfer36.class);startActivity(i); break;
            default:break;
        }



        switch (v.getId()){
            case R.id.services: i = new Intent(this,screen18.class);startActivity(i); break;
            default:break;

    }
}


    public void close_wallet2(View view) {
        findViewById(R.id.Ewallet2).setVisibility(View.INVISIBLE);

        findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        findViewById(R.id.drawer_layout).setAlpha(0f);

    }

    public void close_wallet3(View view) {
        findViewById(R.id.Ewallet3).setVisibility(View.INVISIBLE);

        findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

    }

    public void openBillManager(MenuItem item) {
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);
    }

    public void opensevrvices(MenuItem item) {
        Intent intent = new Intent(this, services.class);
        startActivity(intent);
    }

    public void openreferalls(MenuItem item) {
        Intent intent = new Intent(this, Referrals.class);
        startActivity(intent);

    }
}