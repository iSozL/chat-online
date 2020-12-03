package com.connmysql;


import java.sql.*;

public class DBFunction {

    private static final ConnMysql connMysql = new ConnMysql();
    private static final Connection connection = connMysql.getConn();
//    private static final Date date = new Date(0,0,0);
    private static final String createUser = "INSERT INTO `users` ( user_pwd, user_name, user_sex, user_birthday, user_address, user_phone, user_signature) VALUES (?,?,?,?,?,?,?)";
    private static final String massage = "INSERT INTO unread_massage(user_receive,user_send,massage,is_friend_request) VALUES(?,?,?,?)";

    private static PreparedStatement pstmtCreate;
    private static PreparedStatement pstmtMasssge;

    static {
        try {
            pstmtCreate = connection.prepareStatement(createUser);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    static {
        try {
            pstmtMasssge = connection.prepareStatement(massage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean createUser(String pwd, String name, int sex, String birthday, String address, String phone, String signature){
        try {
            pstmtCreate.setString(1,pwd);
            pstmtCreate.setString(2,name);
            pstmtCreate.setInt(3,sex);
            pstmtCreate.setString(4,birthday);
            pstmtCreate.setString(5,address);
            pstmtCreate.setString(6,phone);
            pstmtCreate.setString(7,signature);
            pstmtCreate.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean addFriend(int receiveId,int sendId){
        try {
            pstmtMasssge.setInt(1,receiveId);
            pstmtMasssge.setInt(2,sendId);
            pstmtMasssge.setString(3,"你好");
            pstmtMasssge.setInt(4,1);
            int result = pstmtMasssge.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean addFriend(int receiveId,int sendId,String massage){
        try {
            pstmtMasssge.setInt(1,receiveId);
            pstmtMasssge.setInt(2,sendId);
            pstmtMasssge.setString(3,massage);
            pstmtMasssge.setInt(4,1);
            int result = pstmtMasssge.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        createUser("123456","发狂的小胖",0, "2020-12-03",null,null,null);
    }
}
//class Date extends java.sql.Date{
//    public Date(int year, int month, int day) {
//        super(year, month, day);
//    }
//    public Date(long date) {
//        super(date);
//    }
//    @Override
//    public String toString () {
//        int year = super.getYear();
//        int month = super.getMonth();
//        int day = super.getDate();
//
//        char buf[] = "2000-00-00".toCharArray();
//        buf[0] = Character.forDigit(year/1000,10);
//        buf[1] = Character.forDigit((year/100)%10,10);
//        buf[2] = Character.forDigit((year/10)%10,10);
//        buf[3] = Character.forDigit(year%10,10);
//        buf[5] = Character.forDigit(month/10,10);
//        buf[6] = Character.forDigit(month%10,10);
//        buf[8] = Character.forDigit(day/10,10);
//        buf[9] = Character.forDigit(day%10,10);
//
//        return new String(buf);
//    }
//}