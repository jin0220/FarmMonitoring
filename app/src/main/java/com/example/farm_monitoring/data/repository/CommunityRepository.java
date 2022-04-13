package com.example.farm_monitoring.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.farm_monitoring.data.api.DBRetrofitClient;
import com.example.farm_monitoring.data.model.Comment;
import com.example.farm_monitoring.data.model.Community;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityRepository {

    public MutableLiveData<List<Community>> dataList1 = new MutableLiveData<>();
    public void getCommunityList(){
        Call<List<Community>> call = DBRetrofitClient.api().getCommunity();

        call.enqueue(new Callback<List<Community>>() {
            @Override
            public void onResponse(Call<List<Community>> call, Response<List<Community>> response) {
                if(response.isSuccessful()){
                    Log.d("확인", "getCommunityList() 응답 성공 ->" + response.body());
                    dataList1.setValue(response.body());
                }
                else {
                    Log.d("확인", "getCommunityList() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<List<Community>> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "getCommunityList() 통신 실패");
            }
        });
    }

    public MutableLiveData<Community> dataList4 = new MutableLiveData<>();
    public void getCommunityDetail(String num){
        Call<Community> call = DBRetrofitClient.api().getCommunityDetail(num);

        call.enqueue(new Callback<Community>() {
            @Override
            public void onResponse(Call<Community> call, Response<Community> response) {
                if(response.isSuccessful()){
                    Log.d("확인", "getCommunityDetail() 응답 성공 ->" + response.body());
                    dataList4.setValue(response.body());
                }
                else {
                    Log.d("확인", "getCommunityDetail() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<Community> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "getCommunityDetail() 통신 실패");
            }
        });
    }

    public MutableLiveData<List<Comment>> dataList2 = new MutableLiveData<>();
    public void getComment(String num) {
        Call<List<Comment>> call = DBRetrofitClient.api().getComment(num);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    Log.d("확인", "getComment() 응답 성공 ->" + response.body());
                    dataList2.setValue(response.body());
                }
                else {
                    Log.d("확인", "getComment() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "getComment() 통신 실패");
            }
        });
    }

    public MutableLiveData<Comment> dataList3 = new MutableLiveData<>();
    public void commentInsert(String id, String num, String comment){
        Call<Comment> call = DBRetrofitClient.api().commentInsert(id, num, comment);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(response.isSuccessful()){
                    Log.d("확인", "commentInsert() 응답 성공 ->" + response.body());
                    dataList3.setValue(response.body());
                }
                else {
                    Log.d("확인", "commentInsert() 응답 실패");
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                t.printStackTrace();
                Log.d("확인", "commentInsert() 통신 실패");
            }
        });
    }
}
