package com.example.wolanjej;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.wolanjej.models.Contacts;
import com.example.wolanjej.recyclerAdapters.ReferralsAdapter;

import java.util.List;

public class Referrals extends AppCompatActivity {
    ReferralsDatabaseAdapter mydb;
    ReferralsAdapter suAdapter;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    RecyclerView recyclerView;
    SearchView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referrals);

        setToolBar();

        mydb  = new ReferralsDatabaseAdapter(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.contacts_list);

        requestContactPermission();

        search = (SearchView)findViewById(R.id.searchViewToolbar);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String stext) {
                suAdapter.filter(stext);
                return false;
            }
        });

    }

    private void setRecyclerview() {
        new ContUserLoader().execute();
    }

    public class ContUserLoader extends AsyncTask<Void, Void, List<Contacts>> {

        @Override
        protected List<Contacts> doInBackground(Void... voids) {
            List<Contacts> MHList = mydb.getData();
            return MHList;
        }

        @Override
        protected void onPostExecute(List<Contacts> contactsUsers) {
            if (contactsUsers.isEmpty()==false){
                suAdapter = new ReferralsAdapter(Referrals.this, contactsUsers);

                recyclerView.setLayoutManager(new LinearLayoutManager(Referrals.this));
                recyclerView.setAdapter(suAdapter);
            }
        }
    }

    private void getContacts() {
        //TODO get contacts code here
        Toast.makeText(this, "Get contacts ....", Toast.LENGTH_LONG).show();
        setRecyclerview();
    }

    public void requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Read Contacts permission");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("Please enable access to contacts.");
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {android.Manifest.permission.READ_CONTACTS}
                                    , PERMISSIONS_REQUEST_READ_CONTACTS);
                        }
                    });
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{android.Manifest.permission.READ_CONTACTS},
                            PERMISSIONS_REQUEST_READ_CONTACTS);
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

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,Home.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }
}