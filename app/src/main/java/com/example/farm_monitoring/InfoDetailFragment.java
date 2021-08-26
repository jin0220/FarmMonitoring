package com.example.farm_monitoring;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

public class InfoDetailFragment extends Fragment implements MainActivity.onBackPressedListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextView title, content;

    public InfoDetailFragment() {
        // Required empty public constructor
    }

    public static InfoDetailFragment newInstance(String param1, String param2) {
        InfoDetailFragment fragment = new InfoDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_info_detail, container, false);

        String plant3 = null;
        if(getArguments() != null) {
            plant3 = getArguments().getString("plant3");
        }

        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);

        dataLoad(plant3);

        return view;
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public void dataLoad(String plant3) {
        String url = "http://easyfarm.dothome.co.kr/json/information_table.php?plant3=" + plant3;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String plant1 = jsonObject.getString("plant1");
                        String plant2 = jsonObject.getString("plant2");
                        String plant3 = jsonObject.getString("plant3");
                        String t = jsonObject.getString("title");
                        String c = jsonObject.getString("content");

                        title.setText(t);
                        content.setText(c);
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