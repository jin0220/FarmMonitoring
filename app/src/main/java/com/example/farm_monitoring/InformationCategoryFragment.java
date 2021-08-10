package com.example.farm_monitoring;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class InformationCategoryFragment extends Fragment implements MainActivity.onBackPressedListener {

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


        if(getArguments() != null) {
            String name = getArguments().getString("name");
            Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        }

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter.setOnItemClickListener(new InformationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fram_fragment, new InfoDetailFragment())
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