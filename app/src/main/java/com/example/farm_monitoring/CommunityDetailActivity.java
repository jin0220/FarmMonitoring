package com.example.farm_monitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.farm_monitoring.request.AccountRequest;
import com.example.farm_monitoring.request.CommentRequest;
import com.example.farm_monitoring.request.CommunityRequest;
import com.example.farm_monitoring.request.SignUpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommunityDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CommunityCommentAdapter adapter;
    List<CommunityCommentData> items = new ArrayList<>();

    TextView userId, title, content, date;
    ImageView image;
    Bitmap bitmap;
    EditText comment_write;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        String page = getIntent().getStringExtra("page");

        userId = findViewById(R.id.id);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        date = findViewById(R.id.date);
        image = findViewById(R.id.image);

        comment_write = findViewById(R.id.comment_write);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentInsert(page);
            }
        });

        ItemAdd(page);
        commentAdd(page);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
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
                            String id = jsonObject.getString("id");
                            String subject = jsonObject.getString("subject");
                            String memo = jsonObject.getString("memo");
                            String hash = jsonObject.getString("hash");
                            String time = jsonObject.getString("time");

                            userId.setText(id);
                            title.setText(subject);
                            content.setText(memo);
                            date.setText(time);

                            //이미지가 있을 경우
                            if(!hash.equals("")) {
                                image.setVisibility(View.VISIBLE);
                                //안드로이드에서 네트워크와 관련된 작업을 할 때,
                                //반드시 메인 Thread가 아닌 별도의 작업 Thread를 생성하여 작업해야 한다.
                                Thread uThread = new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            //서버에 올려둔 이미지 URL
                                            URL url = new URL("http://easyfarm.dothome.co.kr/files/" + hash);

                                            //Web에서 이미지 가져온 후 ImageView에 지정할 Bitmap 만들기
                                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                                            conn.setDoInput(true); //Server 통신에서 입력 가능한 상태로 만듦
                                            conn.connect(); //연결된 곳에 접속할 때 (connect() 호출해야 실제 통신 가능함)

                                            InputStream is = conn.getInputStream(); //inputStream 값 가져오기
                                            bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 반환

                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };

                                uThread.start(); // 작업 Thread 실행

                                try {
                                    //메인 Thread는 별도의 작업을 완료할 때까지 대기한다!
                                    //join() 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다림
                                    //join() 메서드는 InterruptedException을 발생시킨다.
                                    uThread.join();

                                    //작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
                                    //UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지 지정
                                    image.setImageBitmap(bitmap);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
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
//        items = new ArrayList<>();
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

    public void commentInsert(String page) {
        String comment = comment_write.getText().toString();
        String id = PreferenceManager.getString(this, "id");


        if (comment.isEmpty()) {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {
                            items.add(new CommunityCommentData(id, comment, 0));
                            adapter.notifyDataSetChanged();
                            comment_write.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "입력에 실패 하셨습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            CommentRequest commentRequest = new CommentRequest(id, page, comment, responseListener);
            RequestQueue queue = Volley.newRequestQueue(CommunityDetailActivity.this);
            queue.add(commentRequest);
        }
    }
}