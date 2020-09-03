package com.wolanjeAfrica.wolanjej;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Reg03Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Reg03";
    private static final int FILE_SELECT_CODE = 0;
    private EditText editText1, editText2, editText3;
    private ImageView imageView, imageView2;
    private String path;
    private Intent myintent;
    private TextView filetxt;
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_03);

        imageView = findViewById(R.id.img_uploadFileReg3);
        imageView2 =findViewById(R.id.img_discardFileReg3);
        editText1 = findViewById(R.id.editTextNameReg03);
        editText2 = findViewById(R.id.EditTextEmailReg03);
        editText3 = findViewById(R.id.EditTextIdNumber03);
        filetxt = findViewById(R.id.filetxt);


        setToolBar(tb);
    }
    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbarhome);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this,Reg_02.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(moveToLogo);
                    }
                }
        );

    }
    public void uploadid(View view) {
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
        imageView.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.VISIBLE);
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    // Get the path
                    path = getPath(uri);
                    Log.d(TAG, "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
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


    public void movetoreg04(View view) {
        String name = editText1.getText().toString();
        String phone = editText2.getText().toString();
        String email = editText3.getText().toString();
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || path == null){
            Toast.makeText(this, "please provide all details", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences("BusinessReg", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("CeoName" , phone);
        editor.putString("CeoPhone", phone);
        editor.putString("CeoEmail", email);
        editor.putString("CeoIdUrl", path);
        editor.apply();
        Intent movetoreg4 = new Intent(this,Reg_04.class);
        startActivity(movetoreg4);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_discardFileReg3:
                path =null;
                imageView2.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}