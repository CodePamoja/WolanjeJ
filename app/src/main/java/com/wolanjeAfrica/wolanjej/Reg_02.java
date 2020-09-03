package com.wolanjeAfrica.wolanjej;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class Reg_02 extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "Reg02";
    private static final int FILE_SELECT_CODE = 0;
    private Intent myintent;
    private TextView filetxt;
    private Toolbar tb;
    private String path;
    private EditText editText1, editText2, editText3, editText4;
    private ImageView imageView, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_02);
        filetxt = findViewById(R.id.myidupload);

        imageView = findViewById(R.id.img_uploadfile);
        imageView2 = findViewById(R.id.img_discardFile);imageView2.setOnClickListener(this);
        editText1 = findViewById(R.id.edit_textName);
        editText2 = findViewById(R.id.editTextPhone);
        editText3 = findViewById(R.id.edit_email);
        editText4 = findViewById(R.id.edit_id_number);


        setToolBar(tb);
    }

    private void setToolBar(androidx.appcompat.widget.Toolbar tb) {
        tb = findViewById(R.id.toolbarhome);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent moveToLogo = new Intent(this, Registration05.class);
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

    public void loadCopyOfId(View view) {
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


    public void movetoreg3(View view) {
        String name = editText1.getText().toString();
        String phone = editText2.getText().toString();
        String email = editText3.getText().toString();
        String id_number = editText4.getText().toString();
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || id_number.isEmpty() || path == null){
            Toast.makeText(this, "please provide all details", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences("BusinessReg", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name" , phone);
        editor.putString("phone", phone);
        editor.putString("email", email);
        editor.putString("id_number", id_number);
        editor.putString("url", path);
        editor.apply();
        startActivity(new Intent(this, Reg03Activity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_discardFile:
                path =null;
                imageView2.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}