package com.example.farm_monitoring.data.model;

public class Comment {
    private String id;
    private String comment;
    private int viewType;

    public Comment(String id, String comment, int viewType){
        this.id = id;
        this.comment = comment;
        this.viewType = viewType;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getViewType() {
        return viewType;
    }
}
