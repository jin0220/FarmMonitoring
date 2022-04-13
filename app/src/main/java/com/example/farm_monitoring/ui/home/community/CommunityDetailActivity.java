package com.example.farm_monitoring.ui.home.community;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farm_monitoring.data.PreferenceManager;
import com.example.farm_monitoring.R;
import com.example.farm_monitoring.data.model.Comment;
import com.example.farm_monitoring.data.model.Community;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CommunityDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CommunityCommentAdapter adapter;
    List<Comment> items = new ArrayList<>();
    CommunityViewModel viewModel;

    TextView userId, title, content, date;
    ImageView image;
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

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommunityCommentAdapter(items);
        recyclerView.setAdapter(adapter);

        comment_write = findViewById(R.id.comment_write);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentInsert(page);
            }
        });

        viewModel = new ViewModelProvider(this).get(CommunityViewModel.class);

        viewModel.getComment(page);
        viewModel.response2.observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                items.clear();
                for(int i = 0; i < comments.size(); i++) {
                    items.add(new Comment(comments.get(i).getId(), comments.get(i).getComment(), 0));
                    adapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.getCommentDetail(page);
        viewModel.response3.observe(this, new Observer<Community>() {
            @Override
            public void onChanged(Community community) {
                image.setVisibility(View.GONE);

                userId.setText(community.getId());
                title.setText(community.getTitle());
                content.setText(community.getContent());
                date.setText(community.getTime());

                if(!community.getImage().equals("")){
                    image.setVisibility(View.VISIBLE);
                    Picasso.get().load("http://easyfarm.dothome.co.kr/files/" + community.getImage()).into(image);
                }
            }
        });
    }

    public void commentInsert(String page) {
        String comment = comment_write.getText().toString();
        String id = PreferenceManager.getString(this, "id");

        if (comment.isEmpty()) {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.commentInsert(id, page, comment);

            items.add(new Comment(id, comment, 0));
            adapter.notifyDataSetChanged();
            comment_write.setText("");
        }
    }
}