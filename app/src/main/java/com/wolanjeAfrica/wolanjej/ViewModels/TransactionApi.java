package com.wolanjeAfrica.wolanjej.ViewModels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;
import com.wolanjeAfrica.wolanjej.models.ServicesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TransactionApi extends ViewModel {
    private static final String TAG = "TransactionApi";
    private MutableLiveData<ServicesModel> mutableLiveData = new MutableLiveData<>();
    private ServicesModel servicesModel;

    public LiveData<ServicesModel> userTransactions(Context context, String sessionId, String productName, String refValue, String phoneNumber, String amount) {

        InitializeTransaction(context, sessionId, productName, refValue, phoneNumber, amount);
        return mutableLiveData;
    }

    private void InitializeTransaction(Context context, String sessionId, String productName, String refValue, String phoneNumber, String amount) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionId + "");

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonObject.addProperty("product_name", productName);
        jsonObject.addProperty("amount", amount);
        jsonObject.addProperty("phone", phoneNumber);
        jsonObject.addProperty("ref", refValue);

        jsonArray.add(jsonObject);

        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("ac_uname", "test");
        jsonObject1.add("services", jsonArray);

        Retrofit retrofit = RetrofitClient.getInstance();
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);
        Call<JsonObject> call = jsonPlaceHolders.InitiateTransaction(jsonObject1, headers);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Transaction failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.e(TAG, "onResponse: "+ response.body() );
                ServicesModel servicesModel = new ServicesModel();
                JsonArray responseJson = response.body().get("services").getAsJsonArray();
                servicesModel.setAmountTransaction(responseJson.get(0).getAsJsonObject().get("amount").getAsString());
                servicesModel.setTransactionFee(responseJson.get(0).getAsJsonObject().get("fee").getAsString());
                servicesModel.setRef(responseJson.get(0).getAsJsonObject().get("ref").getAsString());
                servicesModel.setId(responseJson.get(0).getAsJsonObject().get("id").getAsString());
                servicesModel.setLast_status(responseJson.get(0).getAsJsonObject().get("status").getAsString());
                mutableLiveData.postValue(servicesModel);


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(context, "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
