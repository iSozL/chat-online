package com.connmysql;


import java.sql.*;

public class DBFunction {

    private static final ConnMysql connMysql = new ConnMysql();
    private static final Connection connection = connMysql.getConn();
    private static final String createUser = "INSERT INTO users ( user_pwd, user_name, user_sex, user_birthday, user_address, user_phone, user_signature) VALUES (?,?,?,?,?,?,?)";
    private static final String sendMassage = "INSERT INTO unread_massage(user_receive,user_send,massage,is_friend_request) VALUES(?,?,?,?)";
    private static final String readMassage = "INSERT INTO read_massage(user_receive,user_send,massage,is_friend_request) VALUES(?,?,?,?)";
    private static final String createGroup = "INSERT INTO group_information VALUES(?,?)";

    private static PreparedStatement pstmtCreateUser;
    private static PreparedStatement pstmtSendMasssge;
    private static PreparedStatement pstmtReadMasssge;
    private static PreparedStatement pstmtCreateGroup;

    static {
        try {
            pstmtCreateUser = connection.prepareStatement(createUser);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    static {
        try {
            pstmtSendMasssge = connection.prepareStatement(sendMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    static {
        try {
            pstmtReadMasssge = connection.prepareStatement(readMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    static {
        try {
            pstmtCreateGroup = connection.prepareStatement(createGroup);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //密码，用户名，性别为必填项，性别：0为保密，1为男，2为女，其他可填null
    public static boolean createUser(String pwd, String name, int sex, String birthday, String address, String phone, String signature){
        try {
            pstmtCreateUser.setString(1,pwd);
            pstmtCreateUser.setString(2,name);
            pstmtCreateUser.setInt(3,sex);
            pstmtCreateUser.setString(4,birthday);
            pstmtCreateUser.setString(5,address);
            pstmtCreateUser.setString(6,phone);
            pstmtCreateUser.setString(7,signature);
            pstmtCreateUser.executeUpdate();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(user_id) FROM users");
            if(rs.next()) {
                int id = rs.getInt("MAX(user_id)");
                addGroup(id,"默认分组");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    //不带问候消息的添加好友方式
    public static boolean addFriend(int receiveId,int sendId){
        try {
            pstmtSendMasssge.setInt(1,receiveId);
            pstmtSendMasssge.setInt(2,sendId);
            pstmtSendMasssge.setString(3,"你好");
            pstmtSendMasssge.setInt(4,1);
            int result = pstmtSendMasssge.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    //带有问候消息的添加好友方式
    public static boolean addFriend(int receiveId,int sendId,String massage){
        try {
            pstmtSendMasssge.setInt(1,receiveId);
            pstmtSendMasssge.setInt(2,sendId);
            pstmtSendMasssge.setString(3,massage);
            pstmtSendMasssge.setInt(4,1);
            int result = pstmtSendMasssge.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static void sendMassge(int receiveId,int sendId,String massage){
        try {
            pstmtSendMasssge.setInt(1,receiveId);
            pstmtSendMasssge.setInt(2,sendId);
            pstmtSendMasssge.setString(3,massage);
            pstmtSendMasssge.setInt(4,0);
            int result = pstmtSendMasssge.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void readMassge(int receiveId,int sendId){
        try {
            Statement stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean addGroup(int id, String groupName){
        try {
            pstmtCreateGroup.setInt(1,id);
            pstmtCreateGroup.setString(2,groupName);
            pstmtCreateGroup.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        /*
        完成：
            用户注册
            分组创建
            发送消息
        待完成：
            好友添加
            阅读消息
            好友印象
         */

        createUser("123456","发狂的小胖",0, "2020-12-03",null,null,null);
    }
}