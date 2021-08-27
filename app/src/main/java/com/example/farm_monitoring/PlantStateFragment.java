package com.example.farm_monitoring;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlantStateFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    PlantStateAdapter adapter;

    TextView plant, date, Dday;

    long D_day = 0;

    public PlantStateFragment() {
        // Required empty public constructor
    }

    public static PlantStateFragment newInstance(String param1, String param2) {
        PlantStateFragment fragment = new PlantStateFragment();
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
        View view = inflater.inflate(R.layout.fragment_plant_state, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PlantStateAdapter();
        recyclerView.setAdapter(adapter);

        plant = view.findViewById(R.id.plant);
        date = view.findViewById(R.id.date);
        Dday = view.findViewById(R.id.Dday);

        dataLoad("20172804");

        return view;
    }

    public void dataLoad(String id) {
        String url = "http://easyfarm.dothome.co.kr/json/user_plant_table.php?id=" + id;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String plant3 = jsonObject.getString("plant3");
                        String start = jsonObject.getString("start").split(" ")[0];
                        String end = jsonObject.getString("end").split(" ")[0];

                        Date currentTime = Calendar.getInstance().getTime();
                        long current = currentTime.getTime();

                        if(end.equals("null")){
                            plant.setText(plant3);
                            date.setText(start);

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date startTime = sdf.parse(start);
                            long timestamp = startTime.getTime();

                            D_day = (current - timestamp) / (24*60*60*1000);
                            Dday.setText("D+" + D_day);
                        }else {
                            adapter.addData(plant3, start, end);
                        }
                    }
                } catch (JSONException | ParseException e) {
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