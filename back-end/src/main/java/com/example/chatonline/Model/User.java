package com.example.chatonline.Model;

import java.util.ArrayList;

public class User {
    private  String  userId;
    private  String password;
    private  String nickname;
    private  String sex;
    private  ArrayList<Group> groups;

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String  userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<Group> getGroups() {
        return groups;
    }
    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
