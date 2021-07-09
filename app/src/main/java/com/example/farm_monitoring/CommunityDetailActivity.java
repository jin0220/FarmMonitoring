package com.example.farm_monitoring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class CommunityDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CommunityCommentAdapter adapter;
    List<CommunityCommentData> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        ItemAdd();

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommunityCommentAdapter(items);
        recyclerView.setAdapter(adapter);

    }

    public void ItemAdd(){
        items = new ArrayList<>();
        items.add(new CommunityCommentData("아이디1","저는 그냥 오프라인에서 사요! 가격은 모르겠지만 상태를 보고 살 수 있어서 좋더라구요^^",0));
        items.add(new CommunityCommentData("글쓴이","아 오프라인으로 사면 그게 장점이 겠군요~~ 정보 감사해요 ㅎㅎ",1));
        items.add(new CommunityCommentData("아이디2","가격은 11번가가 더 저렴한 것 같아요!",0));
    }
}