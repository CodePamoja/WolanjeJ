package com.example.wolanjej;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Profile49 extends AppCompatActivity {
    private Toolbar tb;
    private EditText editText, editText1, editText2, editText3, editText4, editText5, editText6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile49);

        editText = findViewById(R.id.name_profile2);
        editText1 = findViewById(R.id.username_prof2);
        editText2 =findViewById(R.id.email_prof2);
        editText3 = findViewById(R.id.phoneNumberProf2);
        editText4 = findViewById(R.id.id_profile2);
        editText5 = findViewById(R.id.country_prof2);
        editText6 = findViewById(R.id.date_profile2);
        setToolBar(tb);
        getUserInput();
    }
    public void MoveToProfile(View view){
        Intent intent = new Intent(getApplicationContext(), profile.class);
        startActivity(intent);
    }
    private void setToolBar(Toolbar tb) {
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,profile.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }
    private void getUserInput(){
       String FullName =  editText.getText().toString();

       String UserName = editText1.getText().toString();
       String Email = editText2.getTransitionName().toString();
       String phoneNumber = editText3.getText().toString();
       String IdNo = editText4.getText().toString();
       String Country = editText5.toString();
       String Date = editText6.getTransitionName().toString();
    }
}