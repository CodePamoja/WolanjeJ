package com.wolanjeAfrica.wolanjej.RetrofitUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMerchantClient {
    private static Retrofit OUR_INSTANCE;
    public static Retrofit getInstance(){

        if (OUR_INSTANCE == null)
            OUR_INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://3.133.99.145:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return OUR_INSTANCE;
    }

    public RetrofitMerchantClient() {
    }
}
