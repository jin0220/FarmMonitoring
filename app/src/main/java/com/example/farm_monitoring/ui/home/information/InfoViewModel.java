package com.example.farm_monitoring.ui.home.information;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farm_monitoring.data.model.Information;
import com.example.farm_monitoring.data.model.PlantCategory;
import com.example.farm_monitoring.data.repository.InfoRepository;

import java.util.List;

public class InfoViewModel extends ViewModel {
    private static InfoRepository repository = new InfoRepository();

    MutableLiveData<List<Information>> response = new MutableLiveData<>();

    public void getInfoPlantDetail(String plant3) {
        repository.getInfoPlantDetail(plant3);
        response = repository.dataList;
    }


    MutableLiveData<List<PlantCategory>> response2 = new MutableLiveData<>();

    public void getPlantCategory(String plant1) {
        repository.getPlantCategory(plant1);
        response2 = repository.categoryList;
    }
}
