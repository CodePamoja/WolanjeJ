package com.example.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Home extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    Toolbar tb;
    DrawerLayout drawer;
    private String sessionID;
    private String AGENTNO;
    private TextView tvtext;
    private SharedPreferences pref;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tb = findViewById(R.id.toolbarhome);
        drawer = findViewById(R.id.drawer_layout);
        // Get the Intent that started this activity and extract the string
        transferListDetails();
        // Get the Intent that started this activity and extract the string



        //SharedPreferences values for login eg token, user registered number
        pref=getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO =  pref.getString("agentno", "");

        NavigationView navigationView = (NavigationView) findViewById(R.id.mynav);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.name_holder);
        navUsername.setText(pref.getString("user_name", ""));

        new UserBalance().execute();
        new UserServices().execute();
        new UserBills().execute();

//     this  belongs to  screen 18
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        findViewById(R.id.CTransferMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(v.getContext(),MainTransfer36.class));
            }
        });

        MyAdapter myAdapter = new MyAdapter(this, getMylist());
        mRecyclerView.setAdapter(myAdapter);

        MaterialCardView materialCardView = findViewById(R.id.cardBuyAirtime);
        materialCardView.setOnClickListener(this);

        Button viewall = (Button) findViewById(R.id.btnviewall);
        viewall.setOnClickListener(this);

        Button transferMoney = (Button) findViewById(R.id.transfer_money_button);
        transferMoney.setOnClickListener(this);

        MaterialCardView transfer101 = (MaterialCardView) findViewById(R.id.transfer101);
        transfer101.setOnClickListener(this);

        MaterialCardView income_details101 = (MaterialCardView) findViewById(R.id.income_details101);
        income_details101.setOnClickListener(this);

        MaterialCardView wallet101 = (MaterialCardView) findViewById(R.id.wallet101);
        wallet101.setOnClickListener(this);

        MaterialCardView services101 = (MaterialCardView) findViewById(R.id.services101);
        services101.setOnClickListener(this);

        MaterialCardView exchange101 = (MaterialCardView) findViewById(R.id.exchange101);
        exchange101.setOnClickListener(this);

        MaterialCardView crypto101 = (MaterialCardView) findViewById(R.id.crypto101);
        crypto101.setOnClickListener(this);

        MaterialCardView TransferHome = findViewById(R.id.CTransferMain);
        TransferHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TransactionView.class);startActivity(intent);
            }
        });


     //start of  registernew number for activity_new_number
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
        findViewById(R.id.mytopupcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), activity_15_Ewallet2.class);
                        intent.putExtra("Class","Home");
                        startActivity(intent);
                    }
                }
        );

        findViewById(R.id.openFundTrasfer).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), MainTransfer36.class);
//                        intent.putExtra(EXTRA_SESSION, sessionID);
//                        intent.putExtra(EXTRA_AGENTNO, AGENTNO);
                        intent.putExtra("Class","Home");
                        startActivity(intent);
                  }
                }
        );

        findViewById(R.id.mytopupcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);

                findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
                findViewById(R.id.Ewallet2).setVisibility(View.VISIBLE);
                findViewById(R.id.show_ple).setVisibility(View.INVISIBLE);
            }
        });
        ViewPager2 vp2 = findViewById(R.id.viewpager2);
        vp2.setAdapter(new LoansAdapter(this));
        TabLayout tb = findViewById(R.id.tabs);
        TabLayoutMediator tbmed = new TabLayoutMediator(tb, vp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("History");
                        break;

                    case 1:
                        tab.setText("Paid");
                        break;
                    case 2:
                        tab.setText("Failed");
                        break;
                }
            }
        });
        tbmed.attach();


        findViewById(R.id.loanscard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.loansholder).setVisibility(View.VISIBLE);
                findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.btnviewall).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                findViewById(R.id.screen_16).setVisibility(View.VISIBLE);
            }
        });

        setToolBar();
    }

    public void close_screen18(View view) {
        Log.e("yes","pressed");
        findViewById(R.id.screen_16).setVisibility(View.INVISIBLE);

    }








//    https://wolenjeafrica.com/wolenje/api/services

    public  class UserServices extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... voids) {

            String url = "/api/services";
            OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
            Response result = okConn.getBalance(url, sessionID);// sending the url string and base 64 results to the okhttp connection and it's method is getLogin
            Log.d("TAG", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            String verifyResult = null;
            if ( result.code() == 200) {
                try {
                    String test = result.body().string();
                    Log.d("TAG test", test);
                    JSONObject JBalance = new JSONObject(test);
                    System.out.println("Response body json values  services are : " + JBalance);
                    JSONObject JService= new JSONObject(test);
                    System.out.println("Response body json values  services are : " + JService);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }else if( result.code() != 201) {
                try {
                    verifyResult = result.body().string();
                    JSONObject jBody = new JSONObject(verifyResult); // adding
                    System.out.println("Response body json values are : " + verifyResult);
                    Log.e("TAG", String.valueOf(verifyResult));
//                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
//                    Log.e("TAG", String.valueOf(sendResutls));
//                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                    Log.e("TAG result value", String.valueOf(result));
                    Log.e("TAG result body", verifyResult);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class UserBills extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... voids) {

            String url = "/api/bills";
            OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
            Response result = okConn.getBalance(url, sessionID); // sending the url string and base 64 results to the okhttp connection and it's method is getLogin
            Log.d("TAG", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            String verifyResult = null;
            if ( result.code() == 200) {
                try {
                    String test = result.body().string();
                    Log.d("TAG test", test);
                    JSONObject JBills = new JSONObject(test);
                    System.out.println("Response body json values  for bills are : " + JBills);

           //         String resultBalance = JBills.getJSONArray("balance").getJSONObject(0).getString("balance");
           //         tvtext = findViewById(R.id.MYBalance);
            //        tvtext.setText("KSH "+resultBalance);

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }else if( result.code() != 201) {
                try {
                    verifyResult = result.body().string();
                    JSONObject jBody = new JSONObject(verifyResult); // adding
                    System.out.println("Response body json values are : " + verifyResult);
                    Log.e("TAG", String.valueOf(verifyResult));
//                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
//                    Log.e("TAG", String.valueOf(sendResutls));
//                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                    Log.e("TAG result value", String.valueOf(result));
                    Log.e("TAG result body", verifyResult);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class UserBalance extends AsyncTask<Void, Void, Response> {

        @Override
        protected Response doInBackground(Void... voids) {

            String url = "/api/balance";
            OkhttpConnection okConn = new OkhttpConnection(); // calling the okhttp connection class here
            Response result = okConn.getBalance(url, sessionID); // sending the url string and base 64 results to the okhttp connection and it's method is getLogin
            Log.d("TAG", String.valueOf(result));
            return result;
        }

        @Override
        protected void onPostExecute(Response result) {
            String verifyResult = null;
            if ( result.code() == 200) {
                try {
                    String test = result.body().string();
                    Log.d("TAG test", test);
                    JSONObject JBalance = new JSONObject(test);
                    System.out.println("Response body json values are : " + JBalance);

                    String resultBalance = JBalance.getJSONArray("balance").getJSONObject(0).getString("balance");

                    tvtext = findViewById(R.id.MYBalance);
                    tvtext.setText("KSH "+resultBalance);

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            }else if( result.code() != 201) {
                try {
                    verifyResult = result.body().string();
                    JSONObject jBody = new JSONObject(verifyResult); // adding
                    System.out.println("Response body json values are : " + verifyResult);
                    Log.e("TAG", String.valueOf(verifyResult));
//                    String sendResutls = jBody.getJSONObject("errors").getJSONObject("otp").getJSONArray("otp").getJSONArray(0).getString(2);
//                    Log.e("TAG", String.valueOf(sendResutls));
//                    Toast.makeText(getApplicationContext(), "Phone Number, "+sendResutls, Toast.LENGTH_LONG).show();
                    Log.e("TAG result value", String.valueOf(result));
                    Log.e("TAG result body", verifyResult);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    //    close drawer on register new number
    private void closeMyDrawer1() {
        drawer.closeDrawer(GravityCompat.START);
    }



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

    public void close_show_ple(View view) {
        findViewById(R.id.show_ple).setVisibility(View.INVISIBLE);

        findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

    }

    public void open_screen16(View view) {
        Log.e("yes","pressed");
        findViewById(R.id.screen_16).setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.income_details101: i = new Intent(this, IncomeDetails.class);startActivity(i);
            break;
            case R.id.wallet101: i = new Intent(this, Home.class);startActivity(i);
            break;
            case R.id.services101: i = new Intent(this, services.class);startActivity(i);
            break;
            case R.id.exchange101: i = new Intent(this, Home.class);startActivity(i);
            break;
            case R.id.crypto101: i = new Intent(this, CryptoBalance.class);startActivity(i);
            break;
            case R.id.transfer101:i = new Intent(this, MainTransfer36.class);startActivity(i);
            break;
            case R.id.cardBuyAirtime: i = new Intent(this,Top_up.class);
                i.putExtra("Class","Home");startActivity(i);
            break;
            case R.id.services: i = new Intent(this,Home.class);startActivity(i);
            break;
            case R.id.TransferMain: i = new Intent(this,TransactionView.class);
                i.putExtra("Class","transaction");startActivity(i);
                break;
            case R.id.transfer_money_button: i = new Intent(this,MainTransfer36.class);
                i.putExtra("Class","Home");startActivity(i);
                break;
            default:break;
        }

}


    public void close_wallet2(View view) {
        findViewById(R.id.Ewallet2).setVisibility(View.INVISIBLE);

        findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);

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

    public void openLearningHub(MenuItem item) {
        Intent intent = new Intent(this, Hub.class);
        startActivity(intent);
    }

    public void openIncomingDetails(MenuItem item) {
        Intent intent = new Intent(this, IncomeDetails.class);
        startActivity(intent);
    }

    public void openProfile(MenuItem item) {
        Intent intent = new Intent(this, profile.class);
        startActivity(intent);
    }

    public void openNotifications52(MenuItem item) {
        Intent intent = new Intent(this, Notifications52.class);
        startActivity(intent);
    }

    public void chooseAnotherAccount(MenuItem item) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
    public void registerNewNumber(MenuItem item){
        drawer.closeDrawer(GravityCompat.START);
        findViewById(R.id.show_newnumber).setVisibility(View.VISIBLE);
    }
    public void closeRegisterNewNumber(View view){
        findViewById(R.id.show_newnumber).setVisibility(View.INVISIBLE);
    }
    public ArrayList<Model> getMylist() {
        ArrayList<Model>models = new ArrayList<>();
        Model m = new Model();
        m.setTitle("Pay Tv");
        m.setImage(R.mipmap.group_18a);
        models.add(m);

        Model m3 = new Model();
        m3.setTitle("Saved Billers");
        m3.setImage(R.mipmap.group_18);
        models.add(m3);

        Model m1 = new Model();
        m1.setTitle("Electricity");
        m1.setImage(R.mipmap.group_18c);
        models.add(m);

        Model m2 = new Model();
        m2.setTitle("Pay Internet");
        m2.setImage(R.mipmap.group_18d);
        models.add(m2);

        Model m4 = new Model();
        m4.setTitle("Buy Airtime");
        m4.setImage(R.mipmap.group_18d);
        models.add(m4);

        return models;
    }

    public void maketoast(View view) {
        drawer.closeDrawer(GravityCompat.START);

        findViewById(R.id.bottom_navigation).setVisibility(View.INVISIBLE);
        findViewById(R.id.show_ple).setVisibility(View.VISIBLE);
        findViewById(R.id.show_ple).setVisibility(View.VISIBLE);

    }
    public void displayPopUp(View view) {
        PopupMenu popup = new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_up_menu_for_loan_reason);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.others){
            findViewById(R.id.specific).setVisibility(View.VISIBLE);
            return true;
        }else{
            return true;
        }
    }

    public void openloanspop(View view) {
        findViewById(R.id.loansholder).setVisibility(View.INVISIBLE);
        findViewById(R.id.Loansbox).setVisibility(View.VISIBLE);
    }

    public void movotopin(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.loans2).setVisibility(View.VISIBLE);


    }

    public void close_poup_loans(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.bottom_navigation_home_two).setVisibility(View.VISIBLE);

    }
    private void transferListDetails(){

//        mImage and mNames ArrayList go here
//        mImageUrls.add("https://pixabay.com/photos/tree-sunset-amazing-beautiful-736885/");
//        mNames.add("John");

        initTransferRecyclerList();
    }
    private void initTransferRecyclerList(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(Home.this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewHomeAdapter adapter = new RecyclerViewHomeAdapter( mNames, mImageUrls, Home.this);
        recyclerView.setAdapter(adapter);

    }
}

