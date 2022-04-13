package com.example.farm_monitoring.ui.plant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.farm_monitoring.data.PreferenceManager;
import com.example.farm_monitoring.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlantStateFragment extends Fragment {

    PlantStateAdapter adapter;

    TextView plant, date, Dday;

    ImageView add;

    long D_day = 0;

    boolean check = false; //현재 키우고 있는 작물이 있는지 확인

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        dataLoad(PreferenceManager.getString(getContext(),"id"));

        add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){ //현재 키우고 있는 작물이 있을 경우
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("현재 키우고 있는 작물이 있습니다.")
                            .setMessage("다 자라서 수확을 하셨다면 아래 수확 완료 버튼을 눌러주세요!");

                    builder.setPositiveButton("수확하기", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getContext(), "수확완료", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("닫기", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{

                }
            }
        });

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

                            check = true;
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