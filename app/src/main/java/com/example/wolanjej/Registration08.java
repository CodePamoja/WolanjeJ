        package com.example.wolanjej;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.biometric.BiometricManager;
        import androidx.biometric.BiometricPrompt.AuthenticationResult;
        import androidx.core.content.ContextCompat;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;
        import java.util.concurrent.Executor;

        import static android.widget.Toast.LENGTH_SHORT;


        public class Registration08 extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    private  androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    private  androidx.biometric.BiometricPrompt biometricPrompt;

    private int check_device;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration08);
        executor = ContextCompat.getMainExecutor(this);
        final BiometricManager biometricManager = BiometricManager.from(this);

            biometricPrompt = new androidx.biometric.BiometricPrompt(Registration08.this,executor,new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    //super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(Registration08.this,"Error"+errString, LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull AuthenticationResult result) {
                    //super.onAuthenticationSucceeded(result);
                    Toast.makeText(Registration08.this,"Succes"+result, LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationFailed() {
                    //super.onAuthenticationFailed();
                    Toast.makeText(Registration08.this,"Failed", LENGTH_SHORT).show();
                }
            });

            promptInfo = new androidx.biometric.BiometricPrompt.PromptInfo.Builder().setTitle("Biometric Registration")
                    .setSubtitle("Register using your biometric credential")
                    .setNegativeButtonText("Use account password")
                    .build();



        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (biometricManager.canAuthenticate()) {
                    case BiometricManager.BIOMETRIC_SUCCESS:
                        biometricPrompt.authenticate(promptInfo);
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                        Toast.makeText(Registration08.this,"No hardware", LENGTH_SHORT).show();
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Toast.makeText(Registration08.this,"Hardware unavailable", LENGTH_SHORT).show();
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                        Toast.makeText(Registration08.this,"No Bio Enrolled", LENGTH_SHORT).show();
                        break;
                }

                            }
        });

        setToolBar();
        imageView = findViewById(R.id.image_holder);
        imageView.setImageResource(R.drawable.ic_group_7);
    }

    private void setToolBar() {
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");
        final Intent movetoLogo = new Intent(this,Registration07.class);
        tb.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(movetoLogo);
                    }
                }
        );

    }
    public void sendtoLogin(View view) {
        Intent move = new Intent(this, LogIn.class);
        startActivity(move);
    }


}
