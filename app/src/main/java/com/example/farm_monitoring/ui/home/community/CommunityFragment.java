package com.example.farm_monitoring.ui.home.community;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.farm_monitoring.R;
import com.example.farm_monitoring.data.model.Community;
import com.example.farm_monitoring.data.repository.CommunityRepository;
import com.example.farm_monitoring.ui.home.information.InfoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CommunityFragment extends Fragment {

    CommunityAdapter adapter;
    CommunityViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommunityAdapter();
        recyclerView.setAdapter(adapter);


        viewModel = new ViewModelProvider(this).get(CommunityViewModel.class);

        viewModel.getCommunityList();
        viewModel.response1.observe(this, new Observer<List<Community>>() {
            @Override
            public void onChanged(List<Community> communities) {
                adapter.items = communities;
                adapter.notifyDataSetChanged();
            }
        });


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CommunityWriteActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}