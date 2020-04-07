package com.example.wolanjej;

import android.hardware.biometrics.BiometricManager;

public class Bioscanner {

    BiometricManager bioscan;

    public int check(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            return bioscan.canAuthenticate();
        }
        return 0;
    }
}
