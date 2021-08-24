package com.example.farm_monitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.farm_monitoring.request.CommunityRequest;
import com.example.farm_monitoring.request.SignUpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommunityDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CommunityCommentAdapter adapter;
    List<CommunityCommentData> items;

    TextView userId, title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        String page = getIntent().getStringExtra("page");

        userId = findViewById(R.id.id);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        ItemAdd(page);
        commentAdd(page);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommunityCommentAdapter(items);
        recyclerView.setAdapter(adapter);

    }

    public void ItemAdd(String page){
        if(page != null) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {
                            String num = jsonObject.getString("num");
                            String id = jsonObject.getString("id");
                            String subject = jsonObject.getString("subject");
                            String memo = jsonObject.getString("memo");
                            String hash = jsonObject.getString("hash");
                            String time = jsonObject.getString("time");

                            userId.setText(id);
                            title.setText(subject);
                            content.setText(memo);
                        } else {
                            Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패 하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            CommunityRequest signUpRequest = new CommunityRequest(page, responseListener);
            RequestQueue queue = Volley.newRequestQueue(CommunityDetailActivity.this);
            queue.add(signUpRequest);
        }else{
            Toast.makeText(this, "페이지 오류", Toast.LENGTH_SHORT).show();
        }
    }

    public void commentAdd(String page){
        String url = "http://easyfarm.dothome.co.kr/json/comment_table.php?num="+page;
        items = new ArrayList<>();
//        items.add(new CommunityCommentData("아이디1", "저는 그냥 오프라인에서 사요! 가격은 모르겠지만 상태를 보고 살 수 있어서 좋더라구요^^", 0));
//        items.add(new CommunityCommentData("글쓴이", "아 오프라인으로 사면 그게 장점이 겠군요~~ 정보 감사해요 ㅎㅎ", 1));
//        items.add(new CommunityCommentData("아이디2", "가격은 11번가가 더 저렴한 것 같아요!", 0));

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String comment = jsonObject.getString("comment");
                        //글쓴 날짜 추가

                        items.add(new CommunityCommentData(id, comment, 0));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "데이터를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonArrayRequest);
    }
}