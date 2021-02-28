package com.wolanjeAfrica.wolanjej;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.wolanjeAfrica.wolanjej.models.SelectUser;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactsView extends AppCompatActivity {

    private static final String TAG = "ContactsView";
    private static final String EXTRA_PHONE = "com.example.wolanjej.SelectUserList";
    private DatabaseAdapter mydb;
    private SelectUserAdapter suAdapter;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public static List<SelectUser> listOfMultiUserContactView = new ArrayList<>();
    private RecyclerView recyclerView;
    private SearchView search;
    private String classType;
    private String className;
    private LinearLayout linearLayout1;
    private RelativeLayout relativeLayout;
    public MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_view);
        linearLayout1 =findViewById(R.id.holdandp);
        relativeLayout = findViewById(R.id.Rlayoutbottom);
        button = findViewById(R.id.continuePaymentMultipleUsers);

        button.setOnClickListener(v -> {
            listOfMultiUserContactView = SelectUserAdapter.getListOfSelectedUsers();
            if (listOfMultiUserContactView.size() > 0){
                Intent move = new Intent(ContactsView.this, TransferToWalletMultiple40.class);
                move.putExtra("Class","SelectUserAdapter");
                move.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(move);
            }
        });


        search = (SearchView) findViewById(R.id.searchView);
        setToolBar();

        Intent intentExtra = getIntent();
        className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        switch (className) {
            case "Home":
                classType = "home";
                break;
            case "TransferToPhone50":
                this.classType = intentExtra.getStringExtra(TransferToPhone50.EXTRA_CLASSTYPE);
                break;
            case "TransferToWalletSingle37":
                this.classType = intentExtra.getStringExtra(TransferToWalletSingle37.EXTRA_CLASSTYPE);
                break;
            case "TransferToBank44":
                this.classType = intentExtra.getStringExtra(TransferToBank44.EXTRA_CLASSTYPE);
                break;
            case "TopUpOtherNumber":
                this.classType = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_CLASSTYPE);
                break;
            case "TransferToWalletMultiple":
                linearLayout1.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.VISIBLE);
                this.classType = intentExtra.getStringExtra(TransferToWalletMultiple40.EXTRA_CLASSTYPE);
                break;
            case "TransferToWalletMultiple2":
                this.classType = intentExtra.getStringExtra(TransferToWalletMultiple40.EXTRA_CLASSTYPE);
                break;
        }


        mydb = new DatabaseAdapter(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.contacts_list);
        requestContactPermission();

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        tb.setNavigationOnClickListener(v -> {
                    Intent movetoLogo = null;
                    switch (className) {
                        case "Home":
                            movetoLogo = new Intent(ContactsView.this, Home.class);
                            break;
                        case "TransferToPhone50":
                            movetoLogo = new Intent(ContactsView.this, TransferToPhone50.class);
                            break;
                        case "TransferToWalletSingle37":
                            movetoLogo = new Intent(ContactsView.this, TransferToWalletSingle37.class);
                            break;
                        case "TransferToBank44":
                            movetoLogo = new Intent(ContactsView.this, TransferToBank44.class);
                            break;
                        case "TopUpOtherNumber":
                            movetoLogo = new Intent(ContactsView.this, TopupOtherNumber.class);
                            break;
                        case "TransferToWalletMultiple":
                        case "TransferToWalletMultiple2":
                            movetoLogo = new Intent(ContactsView.this, TransferToWalletMultiple40.class);
                            break;
                    }
                    final Intent finalMovetoLogo = movetoLogo;
                    finalMovetoLogo.putExtra("Class", "ContactsView");
                    startActivity(finalMovetoLogo);
                }
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.bShadeGray));
    }

    private void setRecyclerview() {
        new ConttactLoader().execute();
    }

    public void QueryContacts(View view) {
        search.setIconified(false);
        search = (SearchView) findViewById(R.id.searchView);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String stext) {
                if (stext == null) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                if (stext == null) {
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                suAdapter.filter(stext);
                return false;
            }
        });

    }

    public class ConttactLoader extends AsyncTask<Void, Void, List<SelectUser>> {

        @Override
        protected List<SelectUser> doInBackground(Void... voids) {
            List<SelectUser> MHList = mydb.getData();
            return MHList;
        }

        @Override
        protected void onPostExecute(List<SelectUser> selectUsers) {
            if (!selectUsers.isEmpty()) {
                suAdapter = new SelectUserAdapter(ContactsView.this,R.layout.activity_contacts_view, selectUsers, classType);

                recyclerView.setLayoutManager(new LinearLayoutManager(ContactsView.this));
                recyclerView.setAdapter(suAdapter);
            }
        }
    }

    private void getContacts() {
        setRecyclerview();
    }

    public void requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Read Contacts permission");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("Please enable access to contacts.");
                    builder.setOnDismissListener(dialog -> requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS},PERMISSIONS_REQUEST_READ_CONTACTS));
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
                }
            } else {
                getContacts();
            }
        } else {
            getContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts();
                } else {
                    Toast.makeText(this, "You have disabled a contacts permission", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        listOfMultiUserContactView.clear();
    }
}
