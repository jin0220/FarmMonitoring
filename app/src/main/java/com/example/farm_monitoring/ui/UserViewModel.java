package com.example.farm_monitoring.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farm_monitoring.data.model.Community;
import com.example.farm_monitoring.data.model.User;
import com.example.farm_monitoring.data.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private static UserRepository repository = new UserRepository();

    public void register(String id, String farm_id, String username, String email, String phone,
                         String password, String plant1, String plant2, String plant3){
        repository.register(id, farm_id, username, email, phone, password, plant1, plant2, plant3);
    }

    public void account(String id, String farm_id, String username, String email, String phone, String password){
        repository.account(id, farm_id, username, email, phone, password);
    }

    public MutableLiveData<User> response = new MutableLiveData<>();
    public void login(String id, String password){
        Log.d("확인", id + " " + password);
        repository.login(id, password);
        response = repository.dateList3;
    }
}
