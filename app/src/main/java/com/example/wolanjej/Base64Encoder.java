package com.example.wolanjej;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Encoder {
    public String encodedValue(String value){

        byte[] data = new byte[0];
        try {
            data = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = Base64.encodeToString(data, Base64.NO_WRAP);

        return result;
    }
}
