package com.example.farm_monitoring.data.model;

import com.google.gson.annotations.SerializedName;

public class Community {
    private String num;
    private String id;
    @SerializedName("subject")
    private String title;
    @SerializedName("memo")
    private String content;
    @SerializedName("hash")
    private String image;
    private String time;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
