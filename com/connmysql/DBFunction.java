package com.connmysql;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBFunction {

    private static final ConnMysql connMysql = new ConnMysql();
    private static final Connection connection = connMysql.getConn();

    private static final String judgeLogin = "SELECT * FROM users where user_id=? and user_pwd=?";
    private static final String createUser = "INSERT INTO users ( user_pwd, user_name, user_sex, user_birthday, user_address, user_phone, user_signature) VALUES (?,?,?,?,?,?,?)";
    private static final String sendMassage = "INSERT INTO unread_massage(user_receive,user_send,massage,is_friend_request) VALUES(?,?,?,?)";
    private static final String deleteUnreadMassage = "DELETE FROM unread_massage WHERE user_receive =? and user_send=? ";
    private static final String addReadMassage = "INSERT INTO read_massage(user_receive,user_send,massage,send_time,is_friend_request) VALUES(?,?,?,?,?)";
    private static final String createGroup = "INSERT INTO group_information VALUES(?,?)";
    private static final String getUnreadMassage = "SELECT * FROM unread_massage where user_receive=? and user_send=?";
    private static final String getAllUnreadMassage = "SELECT * FROM unread_massage where user_receive=?";

    private static PreparedStatement pstmtJudgeLogin;
    private static PreparedStatement pstmtCreateUser;
    private static PreparedStatement pstmtSendMasssge;
    private static PreparedStatement pstmtDeleteUnreadMassage;
    private static PreparedStatement pstmtAddReadMasssge;
    private static PreparedStatement pstmtCreateGroup;
    private static PreparedStatement pstmtGetUnreadMassge;
    private static PreparedStatement pstmtGetAllUnreadMassge;

    static {
        try {
            pstmtJudgeLogin = connection.prepareStatement(judgeLogin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
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
            pstmtDeleteUnreadMassage = connection.prepareStatement(deleteUnreadMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    static {
        try {
            pstmtAddReadMasssge = connection.prepareStatement(addReadMassage);
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
    static {
        try {
            pstmtGetUnreadMassge = connection.prepareStatement(getUnreadMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    static {
        try {
            pstmtGetAllUnreadMassge = connection.prepareStatement(getAllUnreadMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //判断登录，登录成功返回true
    public static boolean judgeLogin(int id,String pwd){
        ResultSet rs = null;
        try {
            pstmtJudgeLogin.setInt(1,id);
            pstmtJudgeLogin.setString(2,pwd);
            rs = pstmtJudgeLogin.executeQuery();
            if(rs.next())
                return true;
            else
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
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

    //发送消息
    public static boolean sendMassage(int receiveId,int sendId,String massage){
        try {
            pstmtSendMasssge.setInt(1,receiveId);
            pstmtSendMasssge.setInt(2,sendId);
            pstmtSendMasssge.setString(3,massage);
            pstmtSendMasssge.setInt(4,0);
            int result = pstmtSendMasssge.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    //获取某一个人的未读消息
    public static List getUnreadMassage(int receiveId,int sendId){
        List<Massage> massages = new ArrayList<Massage>();
        try {
            pstmtGetUnreadMassge.setInt(1,receiveId);
            pstmtGetUnreadMassge.setInt(2,sendId);
            ResultSet rs = pstmtGetUnreadMassge.executeQuery();
            while(rs.next()){
                massages.add(new Massage(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5)==1 ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return massages;
    }

    //获取所有未读消息
    public static List getAllUnreadMassage(int receiveId){
        List<Massage> massages = new ArrayList<Massage>();
        try {
            pstmtGetAllUnreadMassge.setInt(1,receiveId);
            ResultSet rs = pstmtGetAllUnreadMassge.executeQuery();
            while(rs.next()){
                massages.add(new Massage(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5)==1 ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return massages;
    }

    //消息阅读
    public static void readMassage(int receiveId,int sendId){
        try {
            List<Massage> massages = getUnreadMassage(receiveId,sendId);
            for(Massage massage:massages) {
                pstmtAddReadMasssge.setInt(1, massage.getUserReceive());
                pstmtAddReadMasssge.setInt(2, massage.getUserSend());
                pstmtAddReadMasssge.setString(3,massage.getMassage());
                pstmtAddReadMasssge.setString(4,massage.getSendTime());
                pstmtAddReadMasssge.setInt(5,massage.getIsFriendRequest()?1:0);
                pstmtAddReadMasssge.executeUpdate();
            }
            pstmtDeleteUnreadMassage.setInt(1,receiveId);
            pstmtDeleteUnreadMassage.setInt(2,sendId);
            pstmtDeleteUnreadMassage.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //添加分组
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
            用户登录
            用户注册
            分组创建
            发送消息
            未读消息显示
            阅读消息
            好友添加
        待完成：
            获取用户信息
            同意好友请求
            好友删除
            分组查询
            删除分组（删除分组，好友放入默认分组）
            好友移动
            添加好友印象
            查看好友印象
            修改资料卡
            查看好友资料卡
         */
        //用户登录（成功）
//        System.out.println(judgeLogin(10000, "123456"));



        //用户注册（成功）
//        createUser("123456","root",0, "2020-12-03",null,null,null);

        //分组创建（成功）
//        addGroup(10000,"我的最爱");

        //发送消息（成功）
//        sendMassage(10001,10001,"你好");

        //未读消息显示（成功）
//        List<Massage> massages =  getUnreadMassage(10000);
//        for(Massage massage:massages){
//            System.out.println(massage.getUserReceive() + " " + massage.getUserSend() + " " + massage.getMassage() + " " + massage.getSendTime() + " " + massage.getIsFriendRequest());
//        }

        //获取某一个人消息
//        List<Massage> massages =  getUnreadMassage(10001,10001);
//        for(Massage massage:massages){
//            System.out.println(massage.getUserReceive() + " " + massage.getUserSend() + " " + massage.getMassage() + " " + massage.getSendTime() + " " + massage.getIsFriendRequest());
//        }

        //好友添加
//        addFriend(10001,10001,"hello world!");

        //阅读消息
//        readMassage(10001,10001);

    }
}