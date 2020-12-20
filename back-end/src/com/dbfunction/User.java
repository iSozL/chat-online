package com.dbfunction;

import java.util.List;

public class User {
    private int id;
    private String userName;
    private int userSex;
    private String userBirthday;
    private String userAddress;
    private String userPhone;
    private String userSignature;
    private List<String> userGroup;
    private List<UserFriend> userFriend;

    public User(int id, String userName, int userSex, String userBirthday, String userAddress, String userPhone, String userSignature, List<String> userGroup, List<UserFriend> userFriend) {
        this.id = id;
        this.userName = userName;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userSignature = userSignature;
        this.userGroup = userGroup;
        this.userFriend = userFriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public List<String> getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(List<String> userGroup) {
        this.userGroup = userGroup;
    }

    public List<UserFriend> getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(List<UserFriend> userFriend) {
        this.userFriend = userFriend;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userBirthday='" + userBirthday + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userSignature='" + userSignature + '\'' +
                ", userGroup=" + userGroup +
                ", userFriend=" + userFriend +
                '}';
    }
}
