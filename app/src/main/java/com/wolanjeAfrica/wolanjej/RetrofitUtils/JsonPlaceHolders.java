package com.wolanjeAfrica.wolanjej.RetrofitUtils;


import com.google.gson.JsonObject;
import com.wolanjeAfrica.wolanjej.models.ModelUserDetails;
import com.wolanjeAfrica.wolanjej.models.NewBillmodel;

import java.util.Map;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    Call<ApiJsonObjects> loginUser(@HeaderMap Map<String, String> headers);

    @POST("gapi/verifyOTP")
    Call<JsonObject> VerifyOTP(@Body JsonObject jsonObject);

    @POST("api/transactions")
    Call<JsonObject> InitiateTransaction(@Body JsonObject jsonobject,@HeaderMap Map<String, String> headers);

    @POST("api/")
    Call<ResponseBody> sendPinToServer(@Body JsonObject jsonObject, @HeaderMap Map<String, String> headers);

    @POST("gapi/sendOTP")
    Call<ResponseBody> RegisterPhoneNumber(@Body JsonObject jsonObject);


/*
   Merchant Endpoints
 */

    @POST("merchant/new")
    Call<Response> CreateAccountMerchant(@Body JsonObject jsonObject, @HeaderMap Map<String, String> headers);


    @POST("merchant/login")
    Call<Response> LoginMerchant(@Body JsonObject jsonObject, @HeaderMap Map<String, String> headers);


    @GET("merchant/balance")
    Call<Response> MerchantUserBalance(@Query("userid") String id, @HeaderMap Map<String, String> headers);



    @POST("merchant/verify")
    Call<ResponseBody> merchantVerify(@Body JsonObject jsonObject, @HeaderMap Map<String, String> headers);


    @POST("payment/request")
    Call<ResponseBody> AddScheduledPayment(@Body JsonObject jsonObject);


    @GET("payment/transactions")
    Call<ResponseBody> FetchTransactions(@Query("userId") String userId,@Query("limit")String limit, @HeaderMap Map<String, String>headers);


    @POST("wallet/transfer")
    Call<Response> WalletTransfer(@Body JsonObject jsonObject, @HeaderMap Map<String, String > headers);


    @POST("payment/delete")
    Call<ResponseBody> DeleteScheduledPayment(@Body JsonObject jsonObject, @HeaderMap Map<String ,String > headers);


    @POST("payment/edit")
    Call<ResponseBody> EditScheduledPayments(@Body JsonObject jsonObject, @HeaderMap Map<String ,String> headers);









}
