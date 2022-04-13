package com.example.farm_monitoring.ui.home.community;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farm_monitoring.R;
import com.example.farm_monitoring.data.model.Comment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CommunityCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int COMMENT_VIEW = 0;

    List<Comment> items = null;

    public CommunityCommentAdapter(List<Comment> items){
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == COMMENT_VIEW){
            view = inflater.inflate(R.layout.recyclerview_comment, parent, false);
            return new CommentViewHolder(view);
        }
        else{
            view = inflater.inflate(R.layout.recyclerview_comment_2, parent, false);
            return new NestedCommentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CommentViewHolder){
            ((CommentViewHolder)holder).id.setText(items.get(position).getId());
            ((CommentViewHolder)holder).comment.setText(items.get(position).getComment());
        }else{
            ((NestedCommentViewHolder)holder).id.setText(items.get(position).getId());
            ((NestedCommentViewHolder)holder).comment.setText(items.get(position).getComment());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView id, comment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            comment = itemView.findViewById(R.id.comment);

        }
    }

    public class NestedCommentViewHolder extends RecyclerView.ViewHolder{
        TextView id, comment;
        public NestedCommentViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            comment = itemView.findViewById(R.id.comment);

        }
    }
}
