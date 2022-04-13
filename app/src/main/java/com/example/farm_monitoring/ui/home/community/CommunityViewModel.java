package com.example.farm_monitoring.ui.home.community;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farm_monitoring.data.model.Comment;
import com.example.farm_monitoring.data.model.Community;
import com.example.farm_monitoring.data.repository.CommunityRepository;

import java.util.List;

public class CommunityViewModel extends ViewModel {
    private static CommunityRepository repository = new CommunityRepository();

    MutableLiveData<List<Community>> response1 = new MutableLiveData<>();
    public void getCommunityList() {
        repository.getCommunityList();
        response1 = repository.dataList1;
    }

    MutableLiveData<Community> response3 = new MutableLiveData<>();
    public void getCommentDetail(String num) {
        repository.getCommunityDetail(num);
        response3 = repository.dataList4;
    }

    MutableLiveData<List<Comment>> response2 = new MutableLiveData<>();
    public void getComment(String num){
        repository.getComment(num);
        response2 = repository.dataList2;
    }

    public void commentInsert(String id, String num, String comment){
        repository.commentInsert(id, num, comment);
    }
}
