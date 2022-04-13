package com.example.farm_monitoring.ui.home.information;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farm_monitoring.ui.MainActivity;
import com.example.farm_monitoring.R;
import com.example.farm_monitoring.data.model.PlantCategory;

import java.util.List;

public class InformationCategoryFragment extends Fragment implements MainActivity.onBackPressedListener {

    InformationAdapter adapter;
    InfoViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information_category, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        TextView title = view.findViewById(R.id.title);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new InformationAdapter();
        recyclerView.setAdapter(adapter);

        String name = null;
        if(getArguments() != null) {
            name = getArguments().getString("name");
        }

        title.setText(name);


        viewModel = new ViewModelProvider(this).get(InfoViewModel.class);

        viewModel.getPlantCategory(name);

        viewModel.response2.observe(this, new Observer<List<PlantCategory>>() {
            @Override
            public void onChanged(List<PlantCategory> plantCategories) {
                adapter.items = plantCategories;
                adapter.notifyDataSetChanged();
            }
        });


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, String plant3) {
                InfoDetailFragment fragment = new InfoDetailFragment();

                Bundle bundle = new Bundle();
                bundle.putString("plant3", plant3);

                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fram_fragment, fragment)
                        .addToBackStack(null).commit();
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