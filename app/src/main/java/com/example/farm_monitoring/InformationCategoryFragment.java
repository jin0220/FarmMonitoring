package com.example.farm_monitoring;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InformationCategoryFragment extends Fragment {

    public InformationCategoryFragment() {
        // Required empty public constructor
    }

    public static InformationCategoryFragment newInstance() {
        InformationCategoryFragment fragment = new InformationCategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information_category, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        InformationAdapter adapter = new InformationAdapter();
        recyclerView.setAdapter(adapter);

        adapter.addData("상추");
        adapter.addData("부추");
        adapter.addData("잎들깨");
        adapter.addData("근대");

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram_fragment, new InfoDetailFragment()).commit();
            }
        });

        return view;
    }
}