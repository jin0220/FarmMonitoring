package com.example.farm_monitoring.ui.home.information;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farm_monitoring.ui.MainActivity;
import com.example.farm_monitoring.R;
import com.example.farm_monitoring.data.model.Information;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InfoDetailFragment extends Fragment implements MainActivity.onBackPressedListener {

    TextView title, content;
    InfoViewModel viewModel;
    ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_detail, container, false);

        viewModel = new ViewModelProvider(this).get(InfoViewModel.class);

        String plant3 = null;
        if(getArguments() != null) {
            plant3 = getArguments().getString("plant3");
        }

        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        image = view.findViewById(R.id.image);

        viewModel.getInfoPlantDetail(plant3);

        viewModel.response.observe(this, new Observer<List<Information>>() {
            @Override
            public void onChanged(List<Information> information) {
                title.setText(information.get(0).getTitle());
                content.setText(information.get(0).getContent());
                Picasso.get().load("http://easyfarm.dothome.co.kr/files/" + information.get(0).getImage()).into(image);
            }
        });

        return view;
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        getActivity().getSupportFragmentManager().popBackStack();
    }
}