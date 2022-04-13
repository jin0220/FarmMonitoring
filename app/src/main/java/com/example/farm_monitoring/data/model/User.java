package com.example.farm_monitoring.data.model;

public class User {
    private boolean success;
    private String id;
    private String farm_id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String plant1;
    private String plant2;
    private String plant3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public void setFarm_id(String farm_id) {
        this.farm_id = farm_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
