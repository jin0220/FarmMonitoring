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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InformationCategoryFragment extends Fragment implements MainActivity.onBackPressedListener {

    InformationAdapter adapter;

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

        dataLoad(name);

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

    public void dataLoad(String name) {
        String url = "http://easyfarm.dothome.co.kr/json/plant_table.php?plant1=" + name;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String plant1 = jsonObject.getString("plant1");
                        String plant2 = jsonObject.getString("plant2");
                        String plant3 = jsonObject.getString("plant3");
//                        String image = jsonObject.getString("hash");

                        adapter.addData(plant1, plant2, plant3);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "데이터를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(jsonArrayRequest);
    }
}