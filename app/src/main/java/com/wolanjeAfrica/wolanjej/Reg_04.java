package com.wolanjeAfrica.wolanjej;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.checkbox.MaterialCheckBox;

public class Reg_04 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Reg04";
    private static final int FILE_SELECT_CODE = 0;
    private static String pathCertificate, pathSignedOffAgreement, pathKraPin = null;
    private ImageView imageView, imageView2, imageView3, imageView4, imageView5, imageView6;
    private Intent myintent;
    private TextView tx;
    private String path;
    private Toolbar tb;
    private MaterialCheckBox materialCheckBox;
    private String CheckBoxl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_04);

        tx = (TextView) findViewById(R.id.mytlc);
        tx.setMovementMethod(LinkMovementMethod.getInstance());
        imageView = findViewById(R.id.uploadFile1Reg04);
        imageView2 = findViewById(R.id.img_discardFile1Reg4);
        imageView3 = findViewById(R.id.uploadFile2Reg04);
        imageView4 = findViewById(R.id.img_discardFile2Reg4);
        imageView5 = findViewById(R.id.uploadFile3Reg04);
        imageView6 = findViewById(R.id.img_discardFile3Reg4);
        materialCheckBox = findViewById(R.id.mycheckboxReg04);
        materialCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Reg_04.this.CheckBoxl = "1";
            } else {
                Reg_04.this.CheckBoxl = null;
            }
        });

        setToolBar(tb);
    }

    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbarhome);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this, Reg03Activity.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue_grey));
    }

    public void uploadCetr(View view) {
        uploadid();
        pathCertificate = path;
        imageView.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.VISIBLE);
    }

    public void uploadSigned(View view) {
        uploadid();
        pathSignedOffAgreement = path;
        imageView3.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.VISIBLE);
    }

    public void uploadPin(View view) {
        uploadid();
        pathKraPin = path;
        imageView5.setVisibility(View.INVISIBLE);
        imageView6.setVisibility(View.VISIBLE);
    }

    public void uploadid() {
        myintent = new Intent(Intent.ACTION_GET_CONTENT);
        myintent.setType("*/*");
        myintent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(
                    Intent.createChooser(myintent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    if (uri != null) {
                        Log.d(TAG, "File Uri: " + uri.toString());
                        // Get the path
                        path = getPath(uri);
                        //TODO: COLLECT EACH FILE SEPARATELY
                        Log.d(TAG, "File Path: " + pathCertificate + ":" + pathSignedOffAgreement + ":" + pathKraPin);
                        // Get the file instance
                        // File file = new File(path);
                        // Initiate the upload
                    }
                } else {
                    Toast.makeText(this, "please select a valid file", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getPath(Uri uri) {

        String path = null;
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor == null) {
            path = uri.getPath();
        } else {
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }

    public void movetoLogin(View view) {
        if (pathCertificate == null) {
            Toast.makeText(this, "Certificate of Incorporation Missing", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pathKraPin == null) {
            Toast.makeText(this, "Signed Of a Agreement Missing", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pathSignedOffAgreement == null) {
            Toast.makeText(this, "KRA Pin Certificate Missing", Toast.LENGTH_SHORT).show();
            return;
        }
        if (CheckBoxl == null) {
            Toast.makeText(this, "please Agree to Terms & Conditions", Toast.LENGTH_SHORT).show();
            return;
        }
        /*
         perform async
         */
        SuccessSignUp();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("BusinessReg", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    private void SuccessSignUp() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.succes_signup_dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        ImageButton btn = view.findViewById(R.id.btn_closeSuccess);
        btn.setOnClickListener(v -> {
            alertDialog.dismiss();
            Intent intent = new Intent(Reg_04.this, HomeTwo.class);
            startActivity(intent);
        });

        alertDialog.show();
    }

    private void UnSuccessfulSignUp() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.unsuccessful_sign_up_dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        ImageButton btn = view.findViewById(R.id.btn_closeUnSuccess);
        btn.setOnClickListener(v -> {
            alertDialog.dismiss();
            Intent intent = new Intent(Reg_04.this, Registration05.class);
            startActivity(intent);
            finish();
        });

        alertDialog.show();
    }

    @Override
    public void onClick(View v) {

    }

}