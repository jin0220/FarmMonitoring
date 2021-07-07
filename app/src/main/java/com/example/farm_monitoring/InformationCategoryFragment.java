package com.example.farm_monitoring;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

        return view;
    }
}