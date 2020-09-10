package com.wolanjeAfrica.wolanjej.ViewModels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wolanjeAfrica.wolanjej.LogIn;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.ApiJsonObjects;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;
import com.wolanjeAfrica.wolanjej.models.BalanceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class UserBalanceViewModel extends ViewModel {
    private MutableLiveData<List<BalanceModel>> mutableLiveData = mutableLiveData = new MutableLiveData<List<BalanceModel>>();
    private List<BalanceModel> balanceModel = new ArrayList<>();
    private static final String TAG = "userbalanceviewmodel";

    public LiveData<List<BalanceModel>> getUserBalance(Context context, String sessionId) {

        loadUsers(context, sessionId);
        return mutableLiveData;
    }

    private void loadUsers(Context context, String sessionId) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionId + "");

        Retrofit retrofit = RetrofitClient.getInstance();  //        Getting Retrofit Instance
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);

        Call<ApiJsonObjects> call = jsonPlaceHolders.getBalance(headers);
        call.enqueue(new Callback<ApiJsonObjects>() {
            @Override
            public void onResponse(Call<ApiJsonObjects> call, retrofit2.Response<ApiJsonObjects> response) {

                if (!response.isSuccessful()) {

                    Toast.makeText(context.getApplicationContext(), "code" + response.code() + response.message(), Toast.LENGTH_SHORT).show();

                    SharedPreferences pref = context.getApplicationContext().getSharedPreferences("LogIn", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("session_token", null);
                    editor.apply();
                    Intent move = new Intent(context.getApplicationContext(), LogIn.class);
                    context.startActivity(move);
                    return;
                }

                balanceModel = response.body().getBalances();
                mutableLiveData.postValue(balanceModel);

            }

            @Override
            public void onFailure(Call<ApiJsonObjects> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
