package com.example.farm_monitoring;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InformationFragment extends HomeFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public InformationFragment() {
        // Required empty public constructor
    }

    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        ((LinearLayout) view.findViewById(R.id.category1)).setOnClickListener(categoryListener);
        ((LinearLayout) view.findViewById(R.id.category2)).setOnClickListener(categoryListener);
        ((LinearLayout) view.findViewById(R.id.category3)).setOnClickListener(categoryListener);
        ((LinearLayout) view.findViewById(R.id.category4)).setOnClickListener(categoryListener);
        ((LinearLayout) view.findViewById(R.id.category5)).setOnClickListener(categoryListener);
        ((LinearLayout) view.findViewById(R.id.category6)).setOnClickListener(categoryListener);

        return view;
    }

    private View.OnClickListener categoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            String name = "";
            switch(id){
                case R.id.category1:
                    name = "잎채소";
                    break;
                case R.id.category2:
                    name = "열매채소";
                    break;
                case R.id.category3:
                    name = "뿌리채소";
                    break;
                case R.id.category4:
                    name = "식량작물";
                    break;
                case R.id.category5:
                    name = "허브";
                    break;
                case R.id.category6:
                    name = "재배하기 팁";
                    break;
            }

            InformationCategoryFragment fragment = new InformationCategoryFragment();

            Bundle bundle = new Bundle();
            bundle.putString("name",name);

            fragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fram_fragment, fragment)
                    .addToBackStack(null).commit();
        }
    };
}