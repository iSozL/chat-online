package com.dbfunction;


import com.connmysql.ConnMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBFunction {

    private static final ConnMysql connMysql = new ConnMysql();
    private static final Connection connection = connMysql.getConn();

    //判断登录
    //OK
    private static final String judgeLogin = "SELECT * FROM users where user_id=? and user_pwd=?";
    private static PreparedStatement pstmtJudgeLogin;
    static {
        try {
            pstmtJudgeLogin = connection.prepareStatement(judgeLogin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //获取用户信息
    //OK
    private static final String getUserInfo = "SELECT * FROM users where user_id=?";
    private static PreparedStatement pstmtGetUserInfo;
    static {
        try {
            pstmtGetUserInfo = connection.prepareStatement(getUserInfo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //更新用户信息
    private static final String updateUserInfo = "UPDATE users SET user_name = ?, user_sex = ?, user_birthday = ?, user_address = ?, user_phone = ?, user_signature=? WHERE user_id = ?";
    private static PreparedStatement pstmtUpdateUserInfo;
    static {
        try {
            pstmtUpdateUserInfo = connection.prepareStatement(updateUserInfo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //创建账户
    //OK
    private static final String createUser = "INSERT INTO users ( user_pwd, user_name, user_sex, user_birthday, user_address, user_phone, user_signature) VALUES (?,?,?,?,?,?,?)";
    private static PreparedStatement pstmtCreateUser;
    static {
        try {
            pstmtCreateUser = connection.prepareStatement(createUser);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //发送信息
    private static final String sendMassage = "INSERT INTO unread_massage(user_receive,user_send,massage,is_friend_request) VALUES(?,?,?,?)";
    private static PreparedStatement pstmtSendMasssge;
    static {
        try {
            pstmtSendMasssge = connection.prepareStatement(sendMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //删除已读消息
    private static final String deleteReadMassage = "DELETE FROM read_massage WHERE user_receive =? and user_send=? and massage=? and send_time=? and is_friend_request=?";
    private static PreparedStatement pstmtDeleteReadMassage;
    static {
        try {
            pstmtDeleteReadMassage = connection.prepareStatement(deleteReadMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //删除未读消息
    private static final String deleteUnreadMassage = "DELETE FROM unread_massage WHERE user_receive =? and user_send=? ";
    private static PreparedStatement pstmtDeleteUnreadMassage;
    static {
        try {
            pstmtDeleteUnreadMassage = connection.prepareStatement(deleteUnreadMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //增加已读消息
    private static final String addReadMassage = "INSERT INTO read_massage(user_receive,user_send,massage,send_time,is_friend_request) VALUES(?,?,?,?,?)";
    private static PreparedStatement pstmtAddReadMasssge;
    static {
        try {
            pstmtAddReadMasssge = connection.prepareStatement(addReadMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //创建分组
    private static final String createGroup = "INSERT INTO group_information VALUES(?,?)";
    private static PreparedStatement pstmtCreateGroup;
    static {
        try {
            pstmtCreateGroup = connection.prepareStatement(createGroup);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //获取某个账户发来的未读消息
    private static final String getUnreadMassage = "SELECT * FROM unread_massage where user_receive=? and user_send=?";
    private static PreparedStatement pstmtGetUnreadMassge;
    static {
        try {
            pstmtGetUnreadMassge = connection.prepareStatement(getUnreadMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //获取所有未读消息
    private static final String getAllUnreadMassage = "SELECT * FROM unread_massage where user_receive=?";
    private static PreparedStatement pstmtGetAllUnreadMassge;
    static {
        try {
            pstmtGetAllUnreadMassge = connection.prepareStatement(getAllUnreadMassage);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //获取分组信息
    //OK
    private static final String getGroupInfo = "SELECT user_group FROM group_information WHERE user_id=?";
    private static PreparedStatement pstmtGetGroupInfo;
    static {
        try {
            pstmtGetGroupInfo = connection.prepareStatement(getGroupInfo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //查看好友及好友分组
    //OK
    private static final String getFriend = "SELECT user_id2,user_group,is_accept FROM user_relation where user_id1=?";
    private static PreparedStatement pstmtGetFriend;
    static {
        try {
            pstmtGetFriend = connection.prepareStatement(getFriend);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //接受好友请求
    //OK
    private static final String addFriend = "INSERT INTO user_relation VALUES (?,?,?,?);";
    private static PreparedStatement pstmtAddFriend;
    static {
        try {
            pstmtAddFriend = connection.prepareStatement(addFriend);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //接受好友请求
    //OK
    private static final String acceptFriend = "UPDATE user_relation SET is_accept=1 where user_id1=? and user_id2=?";
    private static PreparedStatement pstmtAcceptFriend;
    static {
        try {
            pstmtAcceptFriend = connection.prepareStatement(acceptFriend);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //删除好友
    private static final String deleteFriend = "DELETE FROM user_relation WHERE user_id1=? and user_id2=?";
    private static PreparedStatement pstmtDeleteFriend;
    static {
        try {
            pstmtDeleteFriend = connection.prepareStatement(deleteFriend);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //移动好友分组
    //OK
    private static final String moveFriend = "UPDATE user_relation SET user_group=? WHERE user_id1=? and user_group=?";
    private static PreparedStatement pstmtMoveFriend;
    static {
        try {
            pstmtMoveFriend = connection.prepareStatement(moveFriend);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //修改好友分组
    //OK
    private static final String updateGroup = "UPDATE user_relation SET user_group=? WHERE user_id1=?";
    private static PreparedStatement pstmtUpdateGroup;
    static {
        try {
            pstmtUpdateGroup = connection.prepareStatement(updateGroup);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //删除分组
    private static final String deleteGroup = "DELETE FROM group_information WHERE user_id=? and user_group=?";
    private static PreparedStatement pstmtDeleteGroup;
    static {
        try {
            pstmtDeleteGroup = connection.prepareStatement(deleteGroup);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //添加好友印象
    private static final String addEvaluate = "INSERT INTO user_evaluate VALUES (?,?,?)";
    private static PreparedStatement pstmtAddEvaluate;
    static {
        try {
            pstmtAddEvaluate = connection.prepareStatement(addEvaluate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static final String showEvaluate = "SELECT * FROM user_evaluate WHERE user_id1=?";
    private static PreparedStatement pstmtShowEvaluate;
    static {
        try {
            pstmtShowEvaluate = connection.prepareStatement(showEvaluate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static final String deleteEvaluate = "DELETE FROM user_evaluate WHERE user_id1=? and user_id2=? and evaluate=?";
    private static PreparedStatement pstmtDeleteEvaluate;
    static {
        try {
            pstmtDeleteEvaluate = connection.prepareStatement(deleteEvaluate);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //判断登录，登录成功返回true
    public static User judgeLogin(int id, String pwd){
        ResultSet rs = null;
        try {
            pstmtJudgeLogin.setInt(1,id);
            pstmtJudgeLogin.setString(2,pwd);
            rs = pstmtJudgeLogin.executeQuery();
            if(rs.next()) {
                List<UserFriend> userFriends = new ArrayList<UserFriend>();
                List<String> userGroups = getGroup(id);
                pstmtGetFriend.setInt(1,rs.getInt(1));
                ResultSet rsFriend = pstmtGetFriend.executeQuery();
                while(rsFriend.next())
                    userFriends.add(new UserFriend(rsFriend.getInt(1),rsFriend.getString(2),rsFriend.getInt(3)));
                User user = new User(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),userGroups,userFriends );
                return user;
            }
            else
                return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
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

    //获取用户信息
    public static User getUserinfo(int id){
        ResultSet rs = null;
        try {
            pstmtGetUserInfo.setInt(1,id);
            rs = pstmtGetUserInfo.executeQuery();
            if(rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                return user;
            }
            else
                return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //修改用户信息，并返回修改后的信息
    public static User updateUserInfo(int id,String userName,int userSex,String userBirthday,String userAddress,String userPhone,String userSignature){
        try {
            pstmtUpdateUserInfo.setString(1,userName);
            pstmtUpdateUserInfo.setInt(2,userSex);
            pstmtUpdateUserInfo.setString(3,userBirthday);
            pstmtUpdateUserInfo.setString(4,userAddress);
            pstmtUpdateUserInfo.setString(5,userPhone);
            pstmtUpdateUserInfo.setString(6,userSignature);
            pstmtUpdateUserInfo.setInt(7,id);
            pstmtUpdateUserInfo.executeUpdate();
            User user = getUserinfo(id);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //添加好友
    public static boolean addFriend(int sendId,int receiveId,String massage,String group){
        try {
            pstmtSendMasssge.setInt(1,receiveId);
            pstmtSendMasssge.setInt(2,sendId);
            pstmtSendMasssge.setString(3,massage);
            pstmtSendMasssge.setInt(4,1);
            pstmtSendMasssge.executeUpdate();
            pstmtAddFriend.setInt(1,sendId);
            pstmtAddFriend.setInt(2,receiveId);
            pstmtAddFriend.setString(3,group);
            pstmtAddFriend.setInt(4,0);
            pstmtAddFriend.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //获取分组
    public static List getGroup(int userId){
        try {
            pstmtGetGroupInfo.setInt(1,userId);
            List<String> userGroups = new ArrayList<String>();
            ResultSet rsGroup = pstmtGetGroupInfo.executeQuery();
            while(rsGroup.next()) userGroups.add(rsGroup.getString(1));
            return userGroups;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //接受好友
    public static boolean acceptFriend(int userId1,int userId2,String group){
        boolean isExist=false;
        try {
            List<String> existGroups = getGroup(userId1);
            for(String existGroup:existGroups){
                if(existGroup.equals(group))
                    isExist=true; break;
            }
            if(isExist){
                pstmtAddFriend.setInt(1,userId1);
                pstmtAddFriend.setInt(2,userId2);
                pstmtAddFriend.setString(3,group);
                pstmtAddFriend.setInt(4,1);
                pstmtAddFriend.executeUpdate();
                pstmtAcceptFriend.setInt(1,userId2);
                pstmtAcceptFriend.setInt(2,userId1);
                pstmtAcceptFriend.executeUpdate();
                return true;
            }else
                return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //发送消息
    public static boolean sendMassage(int sendId,int receiveId,String massage){
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
    public static List getUnreadMassage(int receiveId){
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

    //删除已读消息
    public static boolean deleteReadMassage(int receiveId,int sendId,String massage,String sendTime,int isFriendRequest){
        try {
            pstmtDeleteReadMassage.setInt(1,receiveId);
            pstmtDeleteReadMassage.setInt(2,sendId);
            pstmtDeleteReadMassage.setString(3,massage);
            pstmtDeleteReadMassage.setString(4,sendTime);
            pstmtDeleteReadMassage.setInt(5,isFriendRequest);
            pstmtDeleteReadMassage.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //添加分组
    public static boolean addGroup(int id, String groupName){
        try {
            pstmtCreateGroup.setInt(1,id);
            pstmtCreateGroup.setString(2,groupName);
            pstmtCreateGroup.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //删除分组
    public static boolean deleteGroup(int id, String groupName){
        try {
            pstmtUpdateGroup.setString(1,"默认分组");
            pstmtUpdateGroup.setInt(2,id);
            pstmtUpdateGroup.executeUpdate();
            pstmtDeleteGroup.setInt(1,id);
            pstmtDeleteGroup.setString(2,groupName);
            pstmtDeleteGroup.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //删除好友
    public static boolean deleteFriend(int userId1,int userId2){
        try {
            pstmtDeleteFriend.setInt(1,userId1);
            pstmtDeleteFriend.setInt(2,userId2);
            pstmtDeleteFriend.executeUpdate();
            pstmtDeleteFriend.setInt(1,userId2);
            pstmtDeleteFriend.setInt(2,userId1);
            pstmtDeleteFriend.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //移动好友分组
    public static boolean moveFriend(int userId1,int userId2,String group){
        try {
            pstmtMoveFriend.setString(1,group);
            pstmtMoveFriend.setInt(2,userId1);
            pstmtMoveFriend.setInt(3,userId2);
            pstmtMoveFriend.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //添加好友印象
    public static boolean addEvaluate(int userId1, int userId2, String evaluate){
        try {
            pstmtAddEvaluate.setInt(1,userId2);
            pstmtAddEvaluate.setInt(2,userId1);
            pstmtAddEvaluate.setString(3,evaluate);
            pstmtAddEvaluate.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //查看好友印象
    public static List showEvaluate(int userId){
        List<Evaluate> evaluates = new ArrayList<Evaluate>();
        try {
            pstmtShowEvaluate.setInt(1,userId);
            ResultSet rs = pstmtShowEvaluate.executeQuery();
            while (rs.next()){
                evaluates.add(new Evaluate(rs.getInt(1),rs.getInt(2),rs.getString(3)));
            }
            return evaluates;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static boolean deleteEvaluate(int userId1,int userId2,String evaluate){
        try {
            pstmtDeleteEvaluate.setInt(1,userId1);
            pstmtDeleteEvaluate.setInt(2,userId2);
            pstmtDeleteEvaluate.setString(3,evaluate);
            pstmtDeleteEvaluate.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        /*
        完成：
            用户登录（查询user表、user_relation表、group_information表）
            用户注册
            修改资料卡
            分组创建
            发送消息
            未读消息显示
            阅读消息
            好友添加
            查看资料卡（查询user表）
            同意好友请求
            好友删除
            分组查询
            好友移动
            删除分组（删除分组，好友放入默认分组）
            添加好友印象
            查看好友印象
            删除好友印象
            删除消息记录
        待完成：
         */

        //用户登录（成功）
//        System.out.println(judgeLogin(10001, "123456"));


        //用户注册（成功）
//        createUser("123456","发狂的小胖",0, "2020-12-03",null,null,null);

        //修改信息
//        System.out.println(updateUserInfo(10001, "root", 0, "2020-12-03", null, null, null));

        //分组创建（成功）
//        addGroup(10001,"我的最爱");

        //发送消息（成功）
//        sendMassage(10001,10002,"你好");

        //未读消息显示（成功）
//        List<Massage> massages1 =  getAllUnreadMassage(10001);
//        for(Massage massage:massages1){
//            System.out.println(massage.getUserReceive() + " " + massage.getUserSend() + " " + massage.getMassage() + " " + massage.getSendTime() + " " + massage.getIsFriendRequest());
//        }

        //获取某一个人消息
//        List<Massage> massages =  getUnreadMassage(10001,10002);
//        for(Massage massage:massages){
//            System.out.println(massage.getUserReceive() + " " + massage.getUserSend() + " " + massage.getMassage() + " " + massage.getSendTime() + " " + massage.getIsFriendRequest());
//        }

        //好友添加
//        addFriend(10001,10002,"hello world!","我的最爱");

        //阅读消息
//        readMassage(10001,10002);

        //删除消息
//        deleteReadMassage(10001,10002,"你好","2020-12-7 11:47:57",0);

        //查看资料卡
//        getUserinfo(10001);

        //接受好友请求
//        acceptFriend(10002,10001,"默认分组");

        //删除好友
//        deleteFriend(10001,10001);

        //移动好友分组
//        moveFriend(10001,10002,"我的最爱");

        //删除分组
//        deleteGroup(10001,"我的最爱");

        //添加好友印象
//        addEvaluate(10001,10002,"这是个傻逼");

        //查询好友印象
//        System.out.println(showEvaluate(10001));

        //删除好友印象
//        deleteEvaluate(10001,10002,"这是个傻逼");

    }
}