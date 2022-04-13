package com.example.farm_monitoring.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.farm_monitoring.data.api.DBRetrofitClient;
import com.example.farm_monitoring.data.model.Information;
import com.example.farm_monitoring.data.model.PlantCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoRepository {

    // 찾아보기 카테고리
    public MutableLiveData<List<PlantCategory>> categoryList = new MutableLiveData<>();

    public void getPlantCategory(String plant1) {
        Call<List<PlantCategory>> call = DBRetrofitClient.api().getPlantCategory(plant1);

        call.enqueue(new Callback<List<PlantCategory>>() {
            @Override
            public void onResponse(Call<List<PlantCategory>> call, Response<List<PlantCategory>> response) {
                if(response.isSuccessful()){
                    Log.d("확인", "getPlantCategory() 응답 성공 ->" + response.body());
                    categoryList.setValue(response.body());
                }
                else {
                    Log.d("확인", "getPlantCategory() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<List<PlantCategory>> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "getPlantCategory() 통신 실패");
            }
        });
    }

    // 찾아보기 상세페이지
    public MutableLiveData<List<Information>> dataList = new MutableLiveData<>();

    public void getInfoPlantDetail(String plant3) {
        Call<List<Information>> call = DBRetrofitClient.api().getInfoPlantDetail(plant3);

        call.enqueue(new Callback<List<Information>>() {
            @Override
            public void onResponse(Call<List<Information>> call, Response<List<Information>> response) {
                if(response.isSuccessful()){
                        Log.d("확인", "getInfoPlantDetail() 응답 성공 ->" + response.body());
                        dataList.setValue(response.body());
                }
                else {
                    Log.d("확인", "getInfoPlantDetail() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<List<Information>> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "getInfoPlantDetail() 통신 실패");
            }
        });
    }
}
