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
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.wolanjej.models.SelectUser;
import com.example.wolanjej.recyclerAdapters.SelectUserAdapter;

import java.util.List;

public class ContactsView extends AppCompatActivity {

    DatabaseAdapter mydb;
    SelectUserAdapter suAdapter;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    RecyclerView recyclerView;
    SearchView search;


    private String classType;
    private  String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_view);

//        setToolBar();

        Intent intentExtra = getIntent();
        className = getIntent().getStringExtra("Class");
        Log.e("class Type className", className);
        if(className.equals("TransferToPhone50")) {
            this.classType = intentExtra.getStringExtra(TransferToPhone50.EXTRA_CLASSTYPE);
        }else if (className.equals("TransferToWalletSingle37")){
            this.classType = intentExtra.getStringExtra(TransferToWalletSingle37.EXTRA_CLASSTYPE);
        }else if (className.equals("TransferToBank44")){
            this.classType = intentExtra.getStringExtra(TransferToBank44.EXTRA_CLASSTYPE);
        }else if (className.equals("TopUpOtherNumber")){
            this.classType = intentExtra.getStringExtra(TopupOtherNumber.EXTRA_CLASSTYPE);
        }


        mydb  = new DatabaseAdapter(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.contacts_list);
        requestContactPermission();
        search = (SearchView)findViewById(R.id.searchView);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String stext) {
                if (stext == null){
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                suAdapter.filter(stext);
                return false;
            }
        });

    }

    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");


        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent movetoLogo = null;
                        if(className.equals("TransferToPhone50")) {
                            movetoLogo = new Intent(ContactsView.this,TransferToPhone50.class);
                        }else if (className.equals("TransferToWalletSingle37")){
                            movetoLogo = new Intent(ContactsView.this,TransferToWalletSingle37.class);
                        }else if (className.equals("TransferToBank44")){
                            movetoLogo = new Intent(ContactsView.this,TransferToBank44.class);
                        }else if (className.equals("TopUpOtherNumber")){
                            movetoLogo = new Intent(ContactsView.this,TopupOtherNumber.class);
                        }
                        final Intent finalMovetoLogo = movetoLogo;
                        finalMovetoLogo.putExtra("Class","ContactsView");
                        startActivity(finalMovetoLogo);
                    }
                }
        );

    }

    private void setRecyclerview() {
        new ConttactLoader().execute();
    }

    public class ConttactLoader extends AsyncTask<Void, Void, List<SelectUser>> {

        @Override
        protected List<SelectUser> doInBackground(Void... voids) {
            List<SelectUser> MHList = mydb.getData();
            return MHList;
        }

        @Override
        protected void onPostExecute(List<SelectUser> selectUsers) {
            if (selectUsers.isEmpty()==false){
                suAdapter = new SelectUserAdapter(ContactsView.this, selectUsers, classType);

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
}
