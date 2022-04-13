package com.example.farm_monitoring.data.model;

import com.google.gson.annotations.SerializedName;

public class Information {
    @SerializedName("num")
    private int num;
    @SerializedName("plant1")
    private String plant1;
    @SerializedName("plant2")
    private String plant2;
    @SerializedName("plant3")
    private String plant3;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("image")
    private String image;
    @SerializedName("date")
    private String date;


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPlant1() {
        return plant1;
    }

    public void setPlant1(String plant1) {
        this.plant1 = plant1;
    }

    public String getPlant2() {
        return plant2;
    }

    public void setPlant2(String plant2) {
        this.plant2 = plant2;
    }

    public String getPlant3() {
        return plant3;
    }

    public void setPlant3(String plant3) {
        this.plant3 = plant3;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
