package com.example.wolanjej;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
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

import com.example.wolanjej.RetrofitUtils.ApiJsonObjects;
import com.example.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.example.wolanjej.RetrofitUtils.RetrofitClient;
import com.example.wolanjej.models.BalanceModel;
import com.example.wolanjej.pagerAdapters.LoansAdapter;
import com.example.wolanjej.recyclerAdapters.RecyclerViewHomeAdapter;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Home extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    Toolbar tb;
    DrawerLayout drawer;
    private String sessionID;
    private String AGENTNO;
    private TextView tvtext;
    private SharedPreferences pref;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private List<BalanceModel> balanceModel = new ArrayList<>();
    View bottomSheetView;
    BottomSheetDialog bottomSheetDialog;
    public static final String EXTRA_SESSION = "com.example.wolanjej.SESSION";
    public static final String EXTRA_AGENTNO = "com.example.wolanjej.AGENTNO";


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button button4 = findViewById(R.id.btnaddnew);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserProfileDetails.class);
                startActivity(intent);
            }
        });


        tb = findViewById(R.id.toolbarhome);
        drawer = findViewById(R.id.drawer_layout);
        // Get the Intent that started this activity and extract the string
        transferListDetails();
        // Get the Intent that started this activity and extract the string


        //SharedPreferences values for login eg token, user registered number
        pref = getApplication().getSharedPreferences("LogIn", MODE_PRIVATE);
        this.sessionID = pref.getString("session_token", "");
        this.AGENTNO = pref.getString("agentno", "");


        NavigationView navigationView = (NavigationView) findViewById(R.id.mynav);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.name_holder);
        navUsername.setText(pref.getString("user_name", ""));



        RetrieveWalletBalance();
        new UserServices().execute();
        new UserBills().execute();


        tvtext = findViewById(R.id.MYBalance);

        MaterialCardView materialCardView = findViewById(R.id.cardBuyAirtime);
        materialCardView.setOnClickListener(this);

        Button viewall = (Button) findViewById(R.id.btnviewall);
        viewall.setOnClickListener(this);

        Button transferMoney = (Button) findViewById(R.id.transfer_money_button);
        transferMoney.setOnClickListener(this);


        MaterialCardView TransferHome = findViewById(R.id.CTransferMain);
        TransferHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainTransfer36.class);
                startActivity(intent);
            }
        });

        MaterialCardView materialCardView1 = findViewById(R.id.cardTrans);
        materialCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), TransactionView.class);
                startActivity(intent1);
            }
        });

        findViewById(R.id.imagebtn).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.show();
                        findViewById(R.id.Ewallet2).setVisibility(View.INVISIBLE);

                    }
                }
        );


        findViewById(R.id.loanscard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Loans.class));

            }
        });

        findViewById(R.id.btnviewall).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OpenScreen16();
            }
        });

        findViewById(R.id.wallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenWallet();
            }
        });


        setToolBar();

//        MenuItem item = findViewById(R.id.bell_icon);

        //      BadgeDrawable badgeDrawable = BadgeDrawable.create(getApplicationContext());
        //    BadgeUtils.attachBadgeDrawable(badgeDrawable, (View) item, null);


    }

    public void moveToProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), profile.class);
        startActivity(intent);
    }


//    https://wolenjeafrica.com/wolenje/api/services

    public class UserServices extends AsyncTask<Void, Void, Response> {

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
            if (result.code() == 200) {
                try {
                    String test = result.body().string();
                    Log.d("TAG test", test);
                    JSONObject JBalance = new JSONObject(test);
                    System.out.println("Response body json values  services are : " + JBalance);
                    JSONObject JService = new JSONObject(test);
                    System.out.println("Response body json values  services are : " + JService);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

            } else if (result.code() != 201) {
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
            if (result.code() == 200) {
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

            } else if (result.code() != 201) {
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

    private void RetrieveWalletBalance() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionID + "");

        Retrofit retrofit = RetrofitClient.getInstance();  //        Getting Retrofit Instance
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);

        Call<ApiJsonObjects> call = jsonPlaceHolders.getBalance(headers);
        call.enqueue(new Callback<ApiJsonObjects>() {
            @Override
            public void onResponse(Call<ApiJsonObjects> call, retrofit2.Response<ApiJsonObjects> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(Home.this, "code" + response.code(), Toast.LENGTH_SHORT).show();

                    SharedPreferences pref = getApplicationContext().getSharedPreferences("LogIn", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("session_token", null);
                    editor.apply();
                    Intent move = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(move);
                    finish();
                    return;
                }

                balanceModel = response.body().getBalances();
                for (BalanceModel balanceModel : balanceModel){
                    String MY_BALANCE = balanceModel.getBalance();

                    tvtext.setText("KES " + MY_BALANCE);
                }
                tvtext = findViewById(R.id.MYBalance);

            }

            @Override
            public void onFailure(Call<ApiJsonObjects> call, Throwable t) {
                Toast.makeText(Home.this, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    close drawer on register new number
    private void closeMyDrawer1() {
        drawer.closeDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void movetoSettings48(MenuItem item) {
        Intent intent = new Intent(this, Settings48.class);
        startActivity(intent);

    }

    public void moveBillManagerPayment(MenuItem item) {
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);

    }

    public void moveBillManagerBiller(MenuItem item) {
        Intent intent = new Intent(this, BillManager.class);
        startActivity(intent);
    }

    public void moveReferrals(MenuItem item) {
        Intent intent = new Intent(this, Referrals.class);
        startActivity(intent);
    }

    public void moveMyWallet(MenuItem item) {
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


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.cardBuyAirtime:
                i = new Intent(this, Top_up.class);
                i.putExtra("Class", "Home");
                startActivity(i);
                break;
            case R.id.services:
                i = new Intent(this, Home.class);
                startActivity(i);
                break;
            case R.id.TransferMain:
                i = new Intent(this, TransactionView.class);
                i.putExtra("Class", "transaction");
                startActivity(i);
                break;
            case R.id.transfer_money_button:
                i = new Intent(this, MainTransfer36.class);
                i.putExtra("Class", "Home");
                startActivity(i);
                break;
            default:
                break;
        }

    }

    public void close_wallet3(View view) {
        bottomSheetDialog.show();
        findViewById(R.id.Ewallet3).setVisibility(View.INVISIBLE);

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

    public void openHistory(MenuItem item) {
        Intent intent1 = new Intent(getApplicationContext(), TransactionView.class);
        startActivity(intent1);
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

    public void displayPopUp(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_up_menu_for_loan_reason);
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.others) {
            findViewById(R.id.specific).setVisibility(View.VISIBLE);
            return true;
        } else {
            return true;
        }
    }


    public void movotopin(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.loans2).setVisibility(View.VISIBLE);


    }

    public void close_poup_loans(View view) {
        findViewById(R.id.Loansbox).setVisibility(View.INVISIBLE);
        findViewById(R.id.bottom_navigation_home_two).setVisibility(View.VISIBLE);

    }

    private void OpenScreen16() {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.activity_screen18, (LinearLayout) findViewById(R.id.screen_16)
                );
        bottomSheetView.findViewById(R.id.img_close16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetView.findViewById(R.id.income_details101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), IncomeDetails.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.wallet101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), Home.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.services101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), services.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.exchange101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), Home.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.crypto101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), CryptoBalance.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.transfer101).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), MainTransfer36.class);
                        startActivity(intent);
                    }
                }
        );

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    private void OpenWallet() {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.pop_up_my_wallet, (LinearLayout) findViewById(R.id.show_ple)
                );
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setDismissWithAnimation(true);
        bottomSheetView.findViewById(R.id.imgCloseWallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetView.findViewById(R.id.mytopupcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        // Intent intent = new Intent(v.getContext(), Top_up.class);
                        //startActivity(intent);
                        bottomSheetDialog.hide();
                        findViewById(R.id.Ewallet2).setVisibility(View.VISIBLE);

                    }
                }
        );

        bottomSheetView.findViewById(R.id.mywithdrawcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        findViewById(R.id.Ewallet3).setVisibility(View.VISIBLE);


                    }

                });

        bottomSheetView.findViewById(R.id.openFundTrasfer).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), MainTransfer36.class);
                        intent.putExtra("Class", "Home");
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.transactionHistCard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(v.getContext(), TransactionView.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.pendingCommCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                TODO: intent

            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    public void OpenWalletM(MenuItem item) {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.pop_up_my_wallet, (LinearLayout) findViewById(R.id.show_ple)
                );
        bottomSheetView.findViewById(R.id.imgCloseWallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetView.findViewById(R.id.mytopupcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        drawer.closeDrawer(GravityCompat.START);

                        bottomSheetDialog.hide();
                        findViewById(R.id.Ewallet2).setVisibility(View.VISIBLE);

                    }
                }
        );
        bottomSheetView.findViewById(R.id.mywithdrawcard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        bottomSheetDialog.hide();
                        findViewById(R.id.Ewallet3).setVisibility(View.VISIBLE);
                    }
                }
        );

        bottomSheetView.findViewById(R.id.openFundTrasfer).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), MainTransfer36.class);
//                        intent.putExtra(EXTRA_SESSION, sessionID);
//                        intent.putExtra(EXTRA_AGENTNO, AGENTNO);
                        intent.putExtra("Class", "Home");
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.transactionHistCard).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), TransactionView.class);
                        startActivity(intent);
                    }
                }
        );
        bottomSheetView.findViewById(R.id.pendingCommCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                TODO: intent

            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }

    public void registerNewNumber(MenuItem item) {
        bottomSheetDialog = new BottomSheetDialog(
                Home.this, R.style.BottomSheetDialogTheme
        );
        bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.activity_register_new_number, (LinearLayout) findViewById(R.id.register_new_number)
                );
        bottomSheetView.findViewById(R.id.closeRegNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        TextView textView = bottomSheetView.findViewById(R.id.newphoneNumber);

        bottomSheetView.findViewById(R.id.btn_sendinvite).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        TODO:
                    }
                }
        );
        bottomSheetView.findViewById(R.id.cancel_register_new).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                }
        );
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }


    private void transferListDetails() {

//        mImage and mNames ArrayList go here
        mImageUrls.add("https://images.pexels.com/photos/4328961/pexels-photo-4328961.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("John");

        mImageUrls.add("https://images.pexels.com/photos/2951142/pexels-photo-2951142.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("Lucy");

        mImageUrls.add("https://images.pexels.com/photos/4015088/pexels-photo-4015088.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("ivy");

        mImageUrls.add("https://images.pexels.com/photos/2954199/pexels-photo-2954199.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("Mark");

        mImageUrls.add("https://images.pexels.com/photos/4015088/pexels-photo-4015088.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("ivy");


        mImageUrls.add("https://images.pexels.com/photos/2954199/pexels-photo-2954199.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("Mark");


        initTransferRecyclerList();
    }

    private void initTransferRecyclerList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewHomeAdapter adapter = new RecyclerViewHomeAdapter(mNames, mImageUrls, Home.this);
        recyclerView.setAdapter(adapter);

    }
    public void LogoutAccount(MenuItem item) {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("LogIn", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("session_token", null);
        editor.apply();
        Intent move = new Intent(getApplicationContext(), LogIn.class);
        startActivity(move);
        finish();

    }
}

