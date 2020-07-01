package com.example.wolanjej.RetrofitUtils;

import com.example.wolanjej.models.ModelUserDetails;
import com.example.wolanjej.models.TranasactionHistory;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JsonPlaceHolders {


    @GET("services")
    Call<ApiJsonObjects> getHistory(@HeaderMap Map<String, String> headers);

    @Headers("Content-Type: application/json")
    @GET("balance")
    Call<ApiJsonObjects> getBalance(@HeaderMap Map<String, String> headers);

    @POST("profiles")
    Call<ModelUserDetails> createUserDetails(@Body ModelUserDetails modelUserDetails, @HeaderMap Map<String, String> headers);

    @GET("profiles")
    Call<ApiJsonObjects> getProfile(@HeaderMap Map<String, String> headers);
}
