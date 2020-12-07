package com.dbfunction;

public class Evaluate {
    private int userId1;
    private int userId2;
    private String evaluate;

    public Evaluate(int userId1, int userId2, String evaluate) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.evaluate = evaluate;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "userId1=" + userId1 +
                ", userId2=" + userId2 +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}
