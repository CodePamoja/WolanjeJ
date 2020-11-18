package com.wolanjeAfrica.wolanjej.RetrofitUtils;


import com.google.gson.JsonObject;
import com.wolanjeAfrica.wolanjej.models.ModelUserDetails;
import com.wolanjeAfrica.wolanjej.models.NewBillmodel;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface JsonPlaceHolders {


    @GET("api/services")
    Call<ApiJsonObjects> getHistory(@HeaderMap Map<String, String> headers);

    @Headers("Content-Type: application/json")
    @GET("api/balance")
    Call<ApiJsonObjects> getBalance(@HeaderMap Map<String, String> headers);

    @POST("api/profiles")
    Call<ModelUserDetails> createUserDetails(@Body ModelUserDetails modelUserDetails, @HeaderMap Map<String, String> headers);

    @GET("api/profiles")
    Call<ApiJsonObjects> getProfile(@HeaderMap Map<String, String> headers);

    @POST("api/bills")
    Call<NewBillmodel> createNewBill(@Body NewBillmodel newBillmodel, @HeaderMap Map<String, String> headers);

    @GET("api/bills")
    Call<ApiJsonObjects> getListOfBills(@HeaderMap Map<String, String> headers);


    @POST("api")
    Call<ApiJsonObjects> loginUser(@HeaderMap Map<String, String> headers );

    @POST("gapi/verifyOTP")
    Call<JsonObject> VerifyOTP(@Body JsonObject jsonObject);



}
