package com.wolanjeAfrica.wolanjej.ViewModels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wolanjeAfrica.wolanjej.RetrofitUtils.ApiJsonObjects;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.JsonPlaceHolders;
import com.wolanjeAfrica.wolanjej.RetrofitUtils.RetrofitClient;
import com.wolanjeAfrica.wolanjej.models.TranasactionHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ServicesViewModel extends ViewModel {
    private MutableLiveData<List<TranasactionHistory>> mutableLiveData;
    private List<TranasactionHistory> histories = new ArrayList<>();
    private static final String TAG = "userbalanceviewmodel";

    public LiveData<List<TranasactionHistory>> getSentTransaction(Context context, String sessionId) {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadHistory(context, sessionId);
        }
        return mutableLiveData;
    }

    private void loadHistory(Context context, String sessionId) {
        Log.d(TAG, "loadHistory: ");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + sessionId + "");
        Retrofit retrofit = RetrofitClient.getInstance();  //        Getting Retrofit Instance
        JsonPlaceHolders jsonPlaceHolders = retrofit.create(JsonPlaceHolders.class);
        Call<ApiJsonObjects> call = jsonPlaceHolders.getHistory(headers);
        call.enqueue(new Callback<ApiJsonObjects>() {
            @Override
            public void onResponse(Call<ApiJsonObjects> call, Response<ApiJsonObjects> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context.getApplicationContext(), "response" + response.message(), Toast.LENGTH_SHORT).show();
                }
                histories = response.body().getHistory();
                mutableLiveData.postValue(histories);
            }

            @Override
            public void onFailure(Call<ApiJsonObjects> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
