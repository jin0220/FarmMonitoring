package com.example.farm_monitoring.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.farm_monitoring.data.api.DBRetrofitClient;
import com.example.farm_monitoring.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class UserRepository {
    public MutableLiveData<User> dateList1 = new MutableLiveData<>();
    public void register(String id, String farm_id, String username, String email, String phone,
                         String password, String plant1, String plant2, String plant3){
        Call<User> call = DBRetrofitClient.api().register(id, farm_id, username, email, phone,
                                                                password, plant1, plant2, plant3);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Log.d("확인", "register() 응답 성공 ->" + response.body());
                    dateList1.setValue(response.body());
                }
                else {
                    Log.d("확인", "register() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "register() 통신 실패");
            }
        });
    }

    public MutableLiveData<User> dateList2 = new MutableLiveData<>();
    public void account(String id, String farm_id, String username, String email, String phone, String password){
        Call<User> call = DBRetrofitClient.api().account(id, farm_id, username, email, phone, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Log.d("확인", "account() 응답 성공 ->" + response.body());
                    dateList2.setValue(response.body());
                }
                else {
                    Log.d("확인", "account() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "account() 통신 실패");
            }
        });
    }

    public MutableLiveData<User> dateList3 = new MutableLiveData<>();
    public void login(String id, String password){
        Call<User> call = DBRetrofitClient.api().login(id, password);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Log.d("확인", "login() 응답 성공 ->" + response.body().getUsername());
                    dateList3.setValue(response.body());
                }
                else {
                    Log.d("확인", "login() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "login() 통신 실패");
            }
        });
    }
}
