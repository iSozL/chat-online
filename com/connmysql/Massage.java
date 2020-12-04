package com.connmysql;

public class Massage {
    private int userReceive;
    private int userSend;
    private String massage;
    private String sendTime;
    private boolean isFriendRequest;
    public Massage(
            int userReceive,
            int userSend,
            String massage,
            String sendTime,
            boolean isFriendRequest){

        this.userReceive = userReceive;
        this.userSend = userSend;
        this.massage = massage;
        this.sendTime = sendTime;
        this.isFriendRequest = isFriendRequest;
    }

    public int getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(int userReceive) {
        this.userReceive = userReceive;
    }

    public int getUserSend() {
        return userSend;
    }

    public void setUserSend(int userSend) {
        this.userSend = userSend;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public boolean getIsFriendRequest() {
        return isFriendRequest;
    }

    public void setIsFriendRequest(boolean friendRequest) {
        isFriendRequest = friendRequest;
    }
}
